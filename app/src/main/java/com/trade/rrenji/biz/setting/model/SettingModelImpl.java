package com.trade.rrenji.biz.setting.model;

import android.content.Context;

import com.trade.rrenji.net.ServiceHelper;
import com.trade.rrenji.net.XUtils;
import com.trade.rrenji.net.XUtils.ResultListener;

import java.util.Map;

public class SettingModelImpl implements SettingModel {

    private Context mContext;

    public SettingModelImpl(Context context) {
        this.mContext = context;
    }

    @Override
    public void getSystemData(Context mContext, ResultListener resultListener) {
        String url = ServiceHelper.buildUrl("api.v2.system.getSystemData");
        ServiceHelper.ParamBuilder paramBuilder = new ServiceHelper.ParamBuilder(mContext);
        Map<String, String> params = paramBuilder.build();
        XUtils.getInstance().get(url, params, resultListener);
    }
}
