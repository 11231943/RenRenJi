package com.trade.rrenji.biz.invite.model;

import android.content.Context;

import com.trade.rrenji.biz.base.PlusBaseService;
import com.trade.rrenji.net.XUtils;

public interface InviteModel extends PlusBaseService {

    void getInviteCode(Context mContext, XUtils.ResultListener resultListener);

}
