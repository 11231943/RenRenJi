package com.trade.rrenji.biz.setting.ui;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;

import com.trade.rrenji.R;
import com.trade.rrenji.biz.base.BaseActivity;
import com.trade.rrenji.utils.SettingUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.setting_main_layout)
public class SettingActivity extends BaseActivity {

    @ViewInject(R.id.login_out)
    public TextView login_out;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActionBarTitle("设置");
    }

    @Event(value = {R.id.login_out})
    private void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_out:


                new AlertDialog.Builder(this)
                        .setMessage("是否退出人人机？")
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                SettingUtils.getInstance().clear();
                            }
                        })
                        .setNegativeButton(R.string.cancel, null)
                        .show();

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
