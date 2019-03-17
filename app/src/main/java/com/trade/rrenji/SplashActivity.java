package com.trade.rrenji;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.trade.rrenji.biz.base.BaseActivity;

import org.xutils.view.annotation.ContentView;

/**
 * 闪屏图
 * 1.无闪屏图，0.8秒进入主页
 * 2.闪屏图，等待3秒进入
 */
@ContentView(R.layout.splash)
public class SplashActivity extends BaseActivity {

    private String TAG = SplashActivity.class.getSimpleName();
    public final static int GO_TO_MAIN = 0;
    public final static int GO_TO_DURATION = 1;

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case GO_TO_MAIN:
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        initAd();
        mHandler.sendEmptyMessageDelayed(GO_TO_MAIN, 800);
    }

    private void initAd() {
        Log.d(TAG, "initAd- sql--- start" + System.currentTimeMillis());
    }

    @Override
    protected void attachPresenter() {

    }

    @Override
    protected void detachPresenter() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mHandler != null) {
            mHandler.removeMessages(0);
        }
    }
}
