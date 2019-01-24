package com.trade.rrenji.biz.account.presenter;

import android.content.Context;

import  com.trade.rrenji.biz.account.ui.view.LoginActivityView;
import  com.trade.rrenji.biz.base.Presenter;
import  com.trade.rrenji.net.XUtils;

/**
 * Created by zhanghuatao on 2016/9/26.
 */
public interface LoginActivityPresenter extends Presenter<LoginActivityView> {

    void login(Context mContext, String name, String code, String device, String channel_id);

    void getVerifyCode(Context mContext, String phone);

}
