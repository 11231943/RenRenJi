package com.trade.rrenji.biz.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trade.rrenji.MiGoApplication;

import org.xutils.x;


public abstract class BaseFragment extends Fragment implements BaseView {

    private static final String TAG = BaseFragment.class.getSimpleName();

    protected View rootView;

    private int count;//记录开启进度条的情况 只能开一个
    //当前Fragment是否处于可见状态标志，防止因ViewPager的缓存机制而导致回调函数的触发
    private boolean isFragmentVisible;
    //是否是第一次开启网络加载
    public boolean isFirst;

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Log.e(TAG, "setUserVisibleHint" + hidden);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        Log.e(TAG, "setUserVisibleHint" + isVisibleToUser);
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            isFragmentVisible = true;
        }
        if (rootView == null) {
            return;
        }
        //可见，并且没有加载过
        if (!isFirst && isFragmentVisible) {
            onFragmentVisibleChange(true);
            return;
        }
        //由可见——>不可见 已经加载过
        if (isFragmentVisible) {
            onFragmentVisibleChange(false);
            isFragmentVisible = false;
        }
    }

    protected ProgressView mProgressView;
    protected LayoutInflater mLayoutInflater;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.e(TAG, "onCreateView");
        mLayoutInflater = inflater;
        if (rootView == null)
            rootView = inflater.inflate(getLayoutResource(), container, false);
        initView();
        //可见，但是并没有加载过
        if (isFragmentVisible && !isFirst) {
            onFragmentVisibleChange(true);
        }
        return rootView;
    }

    //获取布局文件
    protected abstract int getLayoutResource();

    //初始化view
    protected abstract void initView();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.e(TAG, "onViewCreated");
        injectViews(view);
        attachPresenter();
    }


//    @Override
//    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(isReuseView ? rootView : view, savedInstanceState);
//        injectViews(view);
//        attachPresenter();
//        //如果setUserVisibleHint()在rootView创建前调用时，那么
//        //就等到rootView创建完后才回调onFragmentVisibleChange(true)
//        //保证onFragmentVisibleChange()的回调发生在rootView创建完成之后，以便支持ui操作
//        if (rootView == null) {
//            rootView = view;
//            if (getUserVisibleHint()) {
//                if (isFirstVisible) {
//                    onFragmentFirstVisible();
//                    isFirstVisible = false;
//                }
//                onFragmentVisibleChange(true);
//                isFragmentVisible = true;
//            }
//        }
//
//
//
//    }

    protected abstract void attachPresenter();

    protected abstract void detachPresenter();

    private void injectViews(View view) {
        x.view().inject(view);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        detachPresenter();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    /**
     * 去除setUserVisibleHint()多余的回调场景，保证只有当fragment可见状态发生变化时才回调
     * 回调时机在view创建完后，所以支持ui操作，解决在setUserVisibleHint()里进行ui操作有可能报null异常的问题
     * <p>
     * 可在该回调方法里进行一些ui显示与隐藏，比如加载框的显示和隐藏
     *
     * @param isVisible true  不可见 -> 可见
     *                  false 可见  -> 不可见
     */
    protected void onFragmentVisibleChange(boolean isVisible) {
        Log.e(TAG, "onFragmentVisibleChange" + isVisible);
    }

    @Override
    public void showLoading(String msg) {
        Activity activity = getActivity();
        if (activity == null) {
            return;
        }
        if (!activity.isFinishing()) {
            mProgressView.show(MiGoApplication.getApp(), msg);
        }
    }

    @Override
    public void hideLoading() {
        Activity activity = getActivity();
        if (activity == null) {
            return;
        }
        if (!activity.isFinishing()) {
            mProgressView.dismiss();
        }
        mProgressView = null;
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void showEmpty(String msg) {

    }

    public void networkChanged(String net) {

    }

    public void networkOffline() {

    }
}
