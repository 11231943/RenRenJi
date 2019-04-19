package com.trade.rrenji.biz.aplash.model;

import android.content.Context;

import com.trade.rrenji.net.ServiceHelper;
import com.trade.rrenji.net.XUtils;
import com.trade.rrenji.utils.SettingUtils;

import java.util.Map;

/**
 * Created by monster on 8/4/18.
 */

public class SplashModelImpl implements SplashModel {

    private Context mContext;

    public SplashModelImpl(Context context) {
        this.mContext = context;
    }

    @Override
    public void getFirstAppPicList(Context mContext, XUtils.ResultListener resultListener) {
        String url = ServiceHelper.buildUrl("api.v2.splash");
        ServiceHelper.ParamBuilder paramBuilder = new ServiceHelper.ParamBuilder(mContext);
        Map<String, String> params = paramBuilder.build();
        XUtils.getInstance().get(url, params, resultListener);
    }
}
