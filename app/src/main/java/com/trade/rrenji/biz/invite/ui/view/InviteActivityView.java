package com.trade.rrenji.biz.invite.ui.view;

import com.trade.rrenji.bean.invite.NetInviteBean;
import com.trade.rrenji.biz.base.BaseView;

public interface InviteActivityView extends BaseView {

    void getInviteCodeSuccess(NetInviteBean netInviteBean);

    void getInviteCodeError(int code, String msg);
}
