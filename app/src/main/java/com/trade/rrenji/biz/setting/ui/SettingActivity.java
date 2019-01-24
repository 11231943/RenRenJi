package com.trade.rrenji.biz.setting.ui;

import android.os.Bundle;

import com.trade.rrenji.R;
import com.trade.rrenji.biz.base.BaseActivity;

import org.xutils.view.annotation.ContentView;

@ContentView(R.layout.setting_main_layout)
public class SettingActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActionBarTitle("设置");
    }

    @Override
    protected void attachPresenter() {

    }

    @Override
    protected void detachPresenter() {

    }
}
