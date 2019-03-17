package com.trade.rrenji.biz.invite.model;

import android.content.Context;

import com.trade.rrenji.net.ServiceHelper;
import com.trade.rrenji.net.XUtils;
import com.trade.rrenji.utils.SettingUtils;

import java.util.Map;

public class InviteModelImpl implements InviteModel {

    private Context mContext;

    public InviteModelImpl(Context context) {
        this.mContext = context;
    }

    @Override
    public void getInviteCode(Context mContext, XUtils.ResultListener resultListener) {
        String url = ServiceHelper.buildUrl("api.v2.invite_code");
        long timeStamp = System.currentTimeMillis();
        String token = "1";
        url = url + SettingUtils.getInstance().getSessionkeyString() + "/" + timeStamp + "/" + token;
        ServiceHelper.ParamBuilder paramBuilder = new ServiceHelper.ParamBuilder(mContext);
        Map<String, String> params = paramBuilder.build();
        XUtils.getInstance().get(url, params, resultListener);
    }
}
