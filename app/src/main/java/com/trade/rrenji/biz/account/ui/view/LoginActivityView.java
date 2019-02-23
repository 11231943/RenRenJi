package com.trade.rrenji.biz.account.ui.view;

import  com.trade.rrenji.bean.account.LoginJsonBean;
import com.trade.rrenji.biz.base.BaseView;


/**
 * Created by monster on 8/4/18.
 */

public interface LoginActivityView extends BaseView {

    void loginSuccess(LoginJsonBean jsonBean);

    void loginError(int code, String msg);

    void getVerifyCodeSuccess();

    void getVerifyCodeError(int code, String msg);
}
