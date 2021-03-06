package com.trade.rrenji.biz.setting.ui;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.trade.rrenji.R;
import com.trade.rrenji.biz.base.BaseActivity;
import com.trade.rrenji.utils.SettingUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingActivity extends BaseActivity {

    Handler mHandler = new Handler();
    @Bind(R.id.icon_setting)
    ImageView iconSetting;
    @Bind(R.id.icon_go)
    ImageView iconGo;
    @Bind(R.id.bind_wchat)
    RelativeLayout bindWchat;
    @Bind(R.id.cache_size)
    TextView cacheSize;
    @Bind(R.id.clear_cache)
    RelativeLayout clearCache;
    @Bind(R.id.version_name)
    TextView versionName;
    @Bind(R.id.about_version)
    RelativeLayout aboutVersion;
    @Bind(R.id.login_out)
    TextView login_out;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_main_layout);
        ButterKnife.bind(this);
        setActionBarTitle("设置");
//        if (!TextUtils.isEmpty(SettingUtils.getInstance().getSessionkey())) {
//            login_out.setVisibility(View.VISIBLE);
//        } else {
//            login_out.setVisibility(View.GONE);
//        }
    }

    @OnClick({R.id.login_out})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_out:
                if (!TextUtils.isEmpty(SettingUtils.getInstance().getCurrentUid())) {
                    new AlertDialog.Builder(this)
                            .setMessage("是否退出人人机？")
                            .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    SettingUtils.getInstance().clear();
                                    mHandler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            finish();
                                        }
                                    }, 500);

                                }
                            })
                            .setNegativeButton(R.string.cancel, null)
                            .show();
                }
                break;
        }
    }

    @Override
    protected void attachPresenter() {

    }

    @Override
    protected void detachPresenter() {

    }
}
