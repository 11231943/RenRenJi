package com.trade.rrenji.biz.account.ui.activity;


import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.trade.rrenji.R;
import com.trade.rrenji.bean.account.LoginJsonBean;
import com.trade.rrenji.biz.account.presenter.LoginActivityPresenter;
import com.trade.rrenji.biz.account.presenter.LoginActivityPresenterImpl;
import com.trade.rrenji.biz.account.ui.view.LoginActivityView;
import com.trade.rrenji.biz.base.BaseActivity;
import com.trade.rrenji.biz.base.ProgressView;
import com.trade.rrenji.utils.Contetns;
import com.trade.rrenji.utils.SettingUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.UUID;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.api.BasicCallback;

/**
 * Created by monster on 8/4/18.
 */
public class LoginActivity extends BaseActivity implements LoginActivityView {
    private static String TAG = LoginActivity.class.getSimpleName();
    LoginActivityPresenter mPresenter;

    @Bind(R.id.login_phone)
    EditText mLoginPhone;
    @Bind(R.id.login_code)
    EditText mLoginCode;
    @Bind(R.id.btn_get_code_ac_login)
    TextView btnGetCode;
    @Bind(R.id.login)
    TextView mLoginButton;

    private InputMethodManager imm = null;
    public static final int LOGIN_SUCCESS = 1;
    public static final int LOGIN_FAIL = 2;
    private static final int SDK_PERMISSION_REQUEST = 127;
    private static final int AGAIN_PERMISSION_REQUEST = 126;
    private int thirdPlatformType = -1;
    private int thirdPlatformGender = 0;

    private int mType = 0;
    private CountDownTimer timerDown = new CountDownTimer(60000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            btnGetCode.setText(millisUntilFinished / 1000 + "秒");
        }

        @Override
        public void onFinish() {
            btnGetCode.setEnabled(true);
            btnGetCode.setText("获取验证码");
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        setActionBarTitle("登陆");
        mType = getIntent().getIntExtra("type", -1);
        init();
    }

    private void init() {
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        setListeners();
    }

    private void setListeners() {
        TextWatcher textChangeL = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (mLoginPhone.getText().toString().trim().isEmpty() || mLoginCode.getText().toString().trim().isEmpty()) {
                    mLoginButton.setSelected(false);
                    mLoginButton.setEnabled(false);
                    mLoginButton.setBackgroundResource(R.drawable.login_status_normal_bg);
                } else {
                    if (mLoginPhone.getText().toString().trim().length() == 11 && mLoginCode.getText().toString().trim().length() >= 6) {
                        mLoginButton.setSelected(true);
                        mLoginButton.setEnabled(true);
                        mLoginButton.setBackgroundResource(R.drawable.login_status_pre_bg);
                    }

                }
            }
        };
        mLoginPhone.addTextChangedListener(textChangeL);
        mLoginCode.addTextChangedListener(textChangeL);
    }

    @OnClick({R.id.btn_get_code_ac_login, R.id.login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_get_code_ac_login:
                String phoneNum = mLoginPhone.getText().toString().trim();
                if (phoneNum.length() != 11) {
                    Toast.makeText(LoginActivity.this, "手机号位数不正确", Toast.LENGTH_SHORT).show();
                    return;
                } else if (phoneNum.charAt(0) != '1') {
                    Toast.makeText(LoginActivity.this, "手机格式不正确", Toast.LENGTH_SHORT).show();
                    return;
                }
                //验证通过后，执行倒计时
                btnGetCode.setEnabled(false);
                timerDown.start();
                mPresenter.getVerifyCode(LoginActivity.this, phoneNum);
                break;
            case R.id.login:
                if (mLoginPhone.getText().toString().trim().isEmpty() || mLoginCode.getText().toString().trim().isEmpty()) {
                    Toast.makeText(LoginActivity.this, "用户名、密码填写不完整", Toast.LENGTH_SHORT).show();
                } else {
                    login();
                }
                break;
        }
    }

    private void login() {
        String login_name = mLoginPhone.getText().toString().trim();
        String login_code = mLoginCode.getText().toString().trim();
//        final String mDeviceId = (String) SharedPreferencesUtil.getParam(this, Contetns.DEVICE_ID, "1");
//        String mChannelId = SharedPreferencesUtil.getChannelId();
        UUID uuid = UUID.randomUUID();
        String rand = uuid.toString().replaceAll("-", "").substring(10, 26);
        mPresenter.login(this, login_name, login_code, "1", rand);
    }

    @Override
    protected void attachPresenter() {
        mPresenter = new LoginActivityPresenterImpl(this);
        mPresenter.attachView(this);
    }

    @Override
    protected void detachPresenter() {
        mPresenter.detachView();
        mPresenter = null;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            setResult(LOGIN_FAIL);
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timerDown != null) {
            timerDown.cancel();
        }
    }

    @Override
    public void showLoading(String msg) {
        ProgressView.show(this, msg);
    }

    @Override
    public void hideLoading() {
        ProgressView.dismiss();
    }

    @Override
    public void loginSuccess(LoginJsonBean jsonBean) {
        Log.d("loginSuccess", jsonBean.toString());
//        Toast.makeText(LoginActivity.this, "loginSuccess : " + jsonBean.getData().toString(), Toast.LENGTH_SHORT).show();
        if (jsonBean.getCode().equals("0")) {
            SettingUtils.getInstance().saveMineInfo(jsonBean.getData());
            loginIM(jsonBean);
            if (mType == 1) {
                setResult(10000);
            }
            finish();
        } else {
            Toast.makeText(LoginActivity.this, "登陆失败!", Toast.LENGTH_SHORT).show();
        }
    }

    private void loginIM(LoginJsonBean jsonBean) {
        String userName = "";
        if (TextUtils.equals(jsonBean.getData().getPhone(), Contetns.ACCOUNT_ADMIN)) {
            //管理员
            userName = Contetns.ACCOUNT_ADMIN;
        } else {
            //普通用户登录
            userName = jsonBean.getData().getPhone();
        }
        JMessageClient.register(userName, "123456", new BasicCallback() {
            @Override
            public void gotResult(int i, String s) {
                Log.e("register", "register s = " + s);
            }
        });
        JMessageClient.login(userName, "123456", new BasicCallback() {
            @Override
            public void gotResult(int i, String s) {
                Log.e(TAG, "JMessageClient.login = " + s);
            }
        });
    }

    @Override
    public void loginError(int code, String msg) {
        Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void getVerifyCodeSuccess() {
        Toast.makeText(this, "获取验证码成功！", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getVerifyCodeError(int code, String msg) {

    }
}
