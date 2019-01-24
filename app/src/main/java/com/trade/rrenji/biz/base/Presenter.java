package com.trade.rrenji.biz.base;

public interface Presenter<V extends BaseView> {

    void attachView(V baseView);

    void detachView();
}
