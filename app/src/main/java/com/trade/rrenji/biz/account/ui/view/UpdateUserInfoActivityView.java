package com.trade.rrenji.biz.account.ui.view;

import com.trade.rrenji.biz.base.BaseView;
import com.trade.rrenji.biz.base.NetBaseResultBean;

public interface UpdateUserInfoActivityView extends BaseView {

    void updateUserInfoSuccess(NetBaseResultBean netBaseResultBean);

    void updateUserInfoError(int code, String msg);
}
