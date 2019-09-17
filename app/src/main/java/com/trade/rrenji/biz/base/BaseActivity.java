package com.trade.rrenji.biz.base;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.trade.rrenji.MiGoApplication;

import org.xutils.x;

import butterknife.ButterKnife;


/**
 * @author zhanghuatao
 */
public abstract class BaseActivity extends AppCompatActivity implements BaseView {

    protected ProgressView mProgressView;

    ActionBarHelper.CustomViewTitle mCustomTitle;
    private GoBackListener mGoBackListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && isMIUI6Later()) {
//            int systemUiVisibility = getWindow().getDecorView().getSystemUiVisibility();
//            if ((systemUiVisibility & View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
//                    == View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR) {
//                setStatusBarDarkModeForMIUI6(getWindow(), true);
//            }
//        }
//        if (getLayoutResID() > 0) {
//
//            setContentView(getLayoutResID());
//
//            injectViews();
//
//            attachPresenter();
//        }
        injectViews();
        attachPresenter();

    }
    public void setActionBarTitle(int resId) {
        setActionBarTitle(getString(resId));
    }

    public void setActionBarTitle(String title) {
        ActionBar actionBar = getSupportActionBar();
        if (mCustomTitle == null) {
            mCustomTitle = new ActionBarHelper.CustomViewTitle(this, actionBar);
        }
        mCustomTitle.setTitle(title);
        mCustomTitle.addToTarget();
    }

    public void setActionBarTitle(String title, int color) {
        ActionBar actionBar = getSupportActionBar();
        if (mCustomTitle == null) {
            mCustomTitle = new ActionBarHelper.CustomViewTitle(this, actionBar);
        }
        mCustomTitle.setTitle(title, color);
        mCustomTitle.addToTarget();
    }


    /**
     * 设置标题栏中返回按钮事件,若不设置默认操作是finish当前Activity
     *
     * @param listener {@link GoBackListener}
     */
    public void setGoBackListener(GoBackListener listener) {
        mGoBackListener = listener;
    }

//    protected abstract int getLayoutResID();

    protected abstract void attachPresenter();

    protected abstract void detachPresenter();

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (mGoBackListener != null) {
                    mGoBackListener.goBack();
                } else {
                    finish();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



    private void injectViews() {
        x.view().inject(this);
//        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dismissLoading();
//        ButterKnife.unbind(this);
        detachPresenter();
    }

    @Override
    public void showLoading(String msg) {
        if (isFinishing()) return;
        mProgressView.show(MiGoApplication.getApp(), msg);
    }

    @Override
    public void hideLoading() {
        if (mProgressView != null && !isFinishing()) {
            mProgressView.dismiss();
        }
    }

    /**
     * 销毁对话框
     */
    private void dismissLoading() {
        if (mProgressView != null) {
            mProgressView.dismiss();
            mProgressView = null;
        }
    }

    @Override
    public void showError(String msg) {
    }

    @Override
    public void showEmpty(String msg) {

    }
}
