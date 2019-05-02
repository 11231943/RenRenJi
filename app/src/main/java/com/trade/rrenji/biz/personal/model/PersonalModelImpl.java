package com.trade.rrenji.biz.personal.model;

import android.content.Context;

import com.trade.rrenji.net.ServiceHelper;
import com.trade.rrenji.net.XUtils;
import com.trade.rrenji.utils.SettingUtils;

import java.util.Map;

/**
 * Created by monster on 8/4/18.
 */

public class PersonalModelImpl implements PersonalModel {

    private Context mContext;

    public PersonalModelImpl(Context context) {
        this.mContext = context;
    }

    @Override
    public void getUserPersonalInfo(Context mContext, String friendId, int page, int rows, XUtils.ResultListener resultListener) {
        String url = ServiceHelper.buildUrl("api.v2.userInfo.getUserPersonalInfo");
        url = url + SettingUtils.getInstance().getSessionkeyString();
        ServiceHelper.ParamBuilder paramBuilder = new ServiceHelper.ParamBuilder(mContext);
        paramBuilder.add("friendId", friendId);
        paramBuilder.add("page", page);
        paramBuilder.add("rows", rows);
        Map<String, String> params = paramBuilder.build();
        XUtils.getInstance().get(url, params, resultListener);
    }

    @Override
    public void getUserAdviceInfo(Context mContext, XUtils.ResultListener resultListener) {
        String url = ServiceHelper.buildUrl("api.v2.userInfo.getUserAdviceInfo");
        url = url + SettingUtils.getInstance().getSessionkeyString();
        ServiceHelper.ParamBuilder paramBuilder = new ServiceHelper.ParamBuilder(mContext);
        Map<String, String> params = paramBuilder.build();
        XUtils.getInstance().get(url, params, resultListener);
    }

    @Override
    public void getSystemData(Context mContext, XUtils.ResultListener resultListener) {
        String url = ServiceHelper.buildUrl("api.v2.system.getSystemData");
        ServiceHelper.ParamBuilder paramBuilder = new ServiceHelper.ParamBuilder(mContext);
        Map<String, String> params = paramBuilder.build();
        XUtils.getInstance().get(url, params, resultListener);
    }
}
