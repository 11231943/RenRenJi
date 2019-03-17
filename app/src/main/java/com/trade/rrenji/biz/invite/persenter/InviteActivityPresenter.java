package com.trade.rrenji.biz.invite.persenter;

import android.content.Context;

import com.trade.rrenji.biz.base.Presenter;
import com.trade.rrenji.biz.invite.ui.view.InviteActivityView;

public interface InviteActivityPresenter extends Presenter<InviteActivityView> {

    void getInviteCode(Context context);

}
