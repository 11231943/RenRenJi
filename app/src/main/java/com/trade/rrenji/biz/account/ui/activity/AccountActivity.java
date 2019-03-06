package com.trade.rrenji.biz.account.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;
import com.trade.rrenji.R;
import com.trade.rrenji.biz.account.presenter.UpdateUserInfoActivityPresenter;
import com.trade.rrenji.biz.account.presenter.UpdateUsrInfoActivityPresenterImpl;
import com.trade.rrenji.biz.account.ui.view.UpdateUserInfoActivityView;
import com.trade.rrenji.biz.address.picker.AddressPicker;
import com.trade.rrenji.biz.address.ui.activity.UpdateAddressActivity;
import com.trade.rrenji.biz.base.BaseActivity;
import com.trade.rrenji.biz.base.NetBaseResultBean;
import com.trade.rrenji.utils.AssetsUtils;
import com.trade.rrenji.utils.CollectionUtils;
import com.trade.rrenji.utils.Contetns;
import com.trade.rrenji.utils.GlideUtils;
import com.trade.rrenji.utils.GsonUtils;
import com.trade.rrenji.utils.SettingUtils;
import com.trade.rrenji.utils.ThreadPoolManager;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

@ContentView(R.layout.account_main_layout)
public class AccountActivity extends BaseActivity implements UpdateUserInfoActivityView {

    @ViewInject(R.id.user_phone)
    TextView user_phone;
    @ViewInject(R.id.user_name)
    EditText user_name;
    @ViewInject(R.id.text_sex)
    TextView text_sex;
    @ViewInject(R.id.text_address)
    TextView text_address;
    @ViewInject(R.id.user_avatar)
    ImageView user_avatar;
    @ViewInject(R.id.bind_sex)
    RelativeLayout bind_sex;
    @ViewInject(R.id.address_layout)
    RelativeLayout address_layout;

    UpdateUserInfoActivityPresenter mPresenter;
    private AddressPicker mAddressPicker;
    private ArrayList<AddressPicker.Province> mData;
    private Handler mHandler = new Handler();

    private String[] item = {"男", "女"};
    String mSex = "0";

    String mProvince;
    String mCity;
    String mCounty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActionBarTitle("资料编辑");
        initUser();
    }

    private void initUser() {
        user_name.setText(SettingUtils.getInstance().getUsername());
        GlideUtils.getInstance().loadIcon1(this, SettingUtils.getInstance().getUserImg(), R.drawable.user_default_icon, user_avatar);
        bind_sex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
        address_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAddressPicker != null) {
                    mAddressPicker.show();
                }
            }
        });
        mAddressPicker = new AddressPicker(this);
        mAddressPicker.setTopLineVisible(false);
        mAddressPicker.setTextColor(0xFFFE5252, 0xFF999999);
        mAddressPicker.setLineColor(0xFFFE5252);
        mAddressPicker.setTextSize(12);
        mAddressPicker.setOnAddressPickListener(
                new AddressPicker.OnAddressPickListener() {
                    @Override
                    public void onAddressPicked(String province, String city,
                                                String county) {
                        mProvince = province;
                        mCity = city;
                        mCounty = county;
                        text_address.setText(getString(R.string.account_address_show, province, city));
                    }
                });
        ThreadPoolManager.getInstance().addTask(new MyRunnable());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_update, menu);
        return true;
    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("请选择");
        builder.setItems(item, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mSex = which + "";
                text_sex.setText(item[which]);
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_save_btn:
                new AlertDialog.Builder(this)
                        .setMessage("是否保存修改的资料？")
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                update();
                            }
                        })
                        .setNegativeButton(R.string.cancel, null)
                        .show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    void update() {
        String headImage = "http://qiniu.rrenji.com/FkBWf3d7MMnDdH7cDQhmIp_RDE4i";
        String name = user_name.getText().toString().trim();
        String sex = text_sex.getText().toString().trim();
        String address = text_address.getText().toString().trim();
        mPresenter.updateUserInfo(this, headImage, name, mSex, address);
    }

    @Override
    protected void attachPresenter() {
        mPresenter = new UpdateUsrInfoActivityPresenterImpl(this);
        mPresenter.attachView(this);
    }

    @Override
    protected void detachPresenter() {
        mPresenter.detachView();
        mPresenter = null;
    }

    @Override
    public void updateUserInfoSuccess(NetBaseResultBean netBaseResultBean) {
        if (netBaseResultBean.getCode().equals(Contetns.RESPONSE_OK)) {
            Toast.makeText(this, "修改资料成功！", Toast.LENGTH_SHORT).show();
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    finish();
                }
            }, 500);
        } else {
            Toast.makeText(this, "修改资料失败！", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void updateUserInfoError(int code, String msg) {
        Toast.makeText(this, "修改资料失败！", Toast.LENGTH_SHORT).show();
    }

    private class MyRunnable implements Runnable {
        @Override
        public void run() {
            String json = AssetsUtils.readText(AccountActivity.this, "city.json");
            mData = GsonUtils.getGson().fromJson(json,
                    new TypeToken<ArrayList<AddressPicker.Province>>() {
                    }.getType());

            if (!CollectionUtils.isEmpty(mData)) {
                mAddressPicker.init(mData);
            }
        }
    }

}
