package com.trade.rrenji.biz.account.ui.activity;

import android.os.Bundle;

import com.trade.rrenji.R;
import com.trade.rrenji.biz.base.BaseActivity;

import org.xutils.view.annotation.ContentView;

@ContentView(R.layout.account_main_layout)
public class AccountActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActionBarTitle("资料编辑");
    }

    @Override
    protected void attachPresenter() {

    }

    @Override
    protected void detachPresenter() {

    }
}
