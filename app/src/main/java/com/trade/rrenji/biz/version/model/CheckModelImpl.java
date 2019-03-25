package com.trade.rrenji.biz.version.model;

import android.content.Context;

import com.trade.rrenji.net.ServiceHelper;
import com.trade.rrenji.net.XUtils;
import com.trade.rrenji.utils.SettingUtils;

import java.util.Map;

public class CheckModelImpl implements CheckModel {

    private Context mContext;

    public CheckModelImpl(Context context) {
        this.mContext = context;
    }

    @Override
    public void check(Context mContext, XUtils.ResultListener resultListener) {
        String url = ServiceHelper.buildUrl("api.v2.version.check");
        long timeStamp = System.currentTimeMillis();
        String token = "0";
        url = url + SettingUtils.getInstance().getSessionkeyString() + "/" + timeStamp + "/" + 1 + "/" + 1 + "/" + token;
        ServiceHelper.ParamBuilder paramBuilder = new ServiceHelper.ParamBuilder(mContext);
        Map<String, String> params = paramBuilder.build();
        XUtils.getInstance().get(url, params, resultListener);
    }
}
