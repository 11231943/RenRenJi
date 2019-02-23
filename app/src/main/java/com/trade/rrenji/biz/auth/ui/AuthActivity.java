package com.trade.rrenji.biz.auth.ui;

import android.os.Bundle;

import com.trade.rrenji.R;
import com.trade.rrenji.biz.base.BaseActivity;


import org.xutils.view.annotation.ContentView;

@ContentView(R.layout.auth_main_layout)
public class AuthActivity extends BaseActivity {

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
