package com.trade.rrenji;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gelitenight.superrecyclerview.SuperRecyclerView;
import com.trade.rrenji.bean.splash.NetSplashBean;
import com.trade.rrenji.biz.aplash.presenter.SplashActivityPresenter;
import com.trade.rrenji.biz.aplash.presenter.SplashActivityPresenterImpl;
import com.trade.rrenji.biz.aplash.ui.view.SplashActivityView;
import com.trade.rrenji.biz.base.BaseActivity;
import com.trade.rrenji.utils.Contetns;
import com.trade.rrenji.utils.GlideUtils;
import com.trade.rrenji.utils.GsonUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

/**
 * 闪屏图
 * 1.无闪屏图，0.8秒进入主页
 * 2.闪屏图，等待3秒进入
 */
//@ContentView(R.layout.splash)
public class SplashActivity extends BaseActivity implements SplashActivityView {

    private String TAG = SplashActivity.class.getSimpleName();
    public final static int GO_TO_MAIN = 0;
    public final static int GO_TO_DURATION = 1;

    @ViewInject(R.id.logo)
    public ImageView logo;
    @ViewInject(R.id.splash_ad_image)
    public ImageView splash_ad_image;
    @ViewInject(R.id.show_duration)
    public TextView show_duration;
    @ViewInject(R.id.show_duration_layout)
    public LinearLayout show_duration_layout;


    SplashActivityPresenter mPresenter;
    int mCountDown = 1;

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case GO_TO_MAIN:
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                    break;
                case GO_TO_DURATION:
                    if (mCountDown <= 0) {
                        mHandler.sendEmptyMessage(GO_TO_MAIN);
                        mHandler.removeMessages(GO_TO_DURATION);
                    } else {
                        mHandler.sendEmptyMessageDelayed(GO_TO_DURATION, 1000);
                        show_duration.setText(String.valueOf(mCountDown));
                        mCountDown--;
                    }
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash);
        super.onCreate(savedInstanceState);
        //无title
        init();
    }

    private void init() {
        initAd();
    }

    private void initAd() {
        Log.d(TAG, "initAd- sql--- start" + System.currentTimeMillis());
        mPresenter.getFirstAppPicList(this);
    }

    @Override
    protected void attachPresenter() {
        mPresenter = new SplashActivityPresenterImpl(this);
        mPresenter.attachView(this);
    }

    @Override
    protected void detachPresenter() {
        mPresenter.detachView();
        mPresenter = null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mHandler != null) {
            mHandler.removeMessages(0);
        }
    }

    @Override
    public void getFirstAppPicList(NetSplashBean netShareBean) {
        if (netShareBean.getCode().equals(Contetns.RESPONSE_OK)) {
            String url = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1555669148424&di=9356ed54dd38d19912eb9047619a007e&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201510%2F09%2F20151009204016_ayXwU.thumb.700_0.png";
            logo.setVisibility(View.GONE);
            splash_ad_image.setVisibility(View.VISIBLE);
            show_duration_layout.setVisibility(View.VISIBLE);
            mCountDown = Integer.valueOf(netShareBean.getData().getCountDown());
            GlideUtils.getInstance().loadImageUrl(this, url, R.drawable.ic_launcher, splash_ad_image);
            mHandler.sendEmptyMessage(GO_TO_DURATION);
        } else {
            splash_ad_image.setVisibility(View.GONE);
            logo.setVisibility(View.VISIBLE);
            show_duration_layout.setVisibility(View.GONE);
            mHandler.sendEmptyMessageDelayed(GO_TO_MAIN, mCountDown * 1000);
        }
    }

    @Override
    public void getFirstAppPicListError(int code, String msg) {
        splash_ad_image.setVisibility(View.GONE);
        show_duration_layout.setVisibility(View.GONE);
        logo.setVisibility(View.VISIBLE);
        mHandler.sendEmptyMessageDelayed(GO_TO_MAIN, mCountDown * 1000);
    }
}
