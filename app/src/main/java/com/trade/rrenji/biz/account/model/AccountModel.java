package com.trade.rrenji.biz.account.model;

import android.content.Context;

import com.trade.rrenji.biz.base.PlusBaseService;
import com.trade.rrenji.net.XUtils;
import com.trade.rrenji.net.XUtils.ResultListener;

/**
 * Created by monster on 8/4/18.
 */

public interface AccountModel extends PlusBaseService {

    void login(Context mContext, String name, String code, String device, String channel_id, ResultListener resultListener);

    void getVerifyCode(Context mContext, String phone, ResultListener resultListener);

}
