package com.trade.rrenji.biz.base;

/**
 * Created by zhanghuatao on 2016/9/27.
 */
public interface BaseView {

    void showLoading(String msg);

    void hideLoading();

    void showError(String msg);

    void showEmpty(String msg);

}
