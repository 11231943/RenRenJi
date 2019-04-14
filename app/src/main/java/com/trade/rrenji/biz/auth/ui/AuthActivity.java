package com.trade.rrenji.biz.auth.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.AuthTask;
import com.trade.rrenji.R;
import com.trade.rrenji.bean.pay.AuthResult;
import com.trade.rrenji.biz.base.BaseActivity;
import com.trade.rrenji.biz.order.ui.activity.PayConfirmOrderActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.Map;

@ContentView(R.layout.auth_main_layout)
public class AuthActivity extends BaseActivity {

    private static final int SDK_AUTH_FLAG = 2;

    @ViewInject(R.id.bind_zfb)
    public TextView bind_zfb;
    @ViewInject(R.id.bind_wechat)
    public TextView bind_wechat;
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_AUTH_FLAG: {
                    @SuppressWarnings("unchecked")
                    AuthResult authResult = new AuthResult((Map<String, String>) msg.obj, true);
                    String resultStatus = authResult.getResultStatus();
                    // 判断resultStatus 为“9000”且result_code
                    // 为“200”则代表授权成功，具体状态码代表含义可参考授权接口文档
                    if (TextUtils.equals(resultStatus, "9000") && TextUtils.equals(authResult.getResultCode(), "200")) {
                        // 获取alipay_open_id，调支付时作为参数extern_token 的value
                        // 传入，则支付账户为该授权账户
                        Toast.makeText(AuthActivity.this,
                                "授权成功\n" + String.format("authCode:%s", authResult.getAuthCode()), Toast.LENGTH_SHORT)
                                .show();
                    } else {
                        // 其他状态值则为授权失败
                        Toast.makeText(AuthActivity.this,
                                "授权失败" + String.format("authCode:%s", authResult.getAuthCode()), Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
                default:
                    break;
            }
        }
    };

    private String mAuthInfo = "";

    @Event(value = {R.id.login_out})
    private void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bind_zfb:
                Runnable authRunnable = new Runnable() {

                    @Override
                    public void run() {
                        // 构造AuthTask 对象
                        AuthTask authTask = new AuthTask(AuthActivity.this);
                        // 调用授权接口，获取授权结果
                        Map<String, String> result = authTask.authV2(mAuthInfo, true);

                        Message msg = new Message();
                        msg.what = SDK_AUTH_FLAG;
                        msg.obj = result;
                        mHandler.sendMessage(msg);
                    }
                };

                // 必须异步调用
                Thread authThread = new Thread(authRunnable);
                authThread.start();
                break;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActionBarTitle("实名认证");
    }

    @Override
    protected void attachPresenter() {

    }

    @Override
    protected void detachPresenter() {

    }
}
