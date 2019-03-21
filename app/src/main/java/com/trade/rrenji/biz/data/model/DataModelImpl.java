package com.trade.rrenji.biz.data.model;

import android.content.Context;

import com.trade.rrenji.net.ServiceHelper;
import com.trade.rrenji.net.XUtils;
import com.trade.rrenji.net.XUtils.ResultListener;

import java.util.Map;

public class DataModelImpl implements DataModel {

    private Context mContext;

    public DataModelImpl(Context context) {
        this.mContext = context;
    }

    @Override
    public void getHotDataById(Context mContext, String id, int page,int rows, ResultListener resultListener) {
        String url = ServiceHelper.buildUrl("api.v2.home.hotData");
        ServiceHelper.ParamBuilder paramBuilder = new ServiceHelper.ParamBuilder(mContext);
        paramBuilder.add("actId", id);
        paramBuilder.add("page", page);
        paramBuilder.add("rows", rows);
        Map<String, String> params = paramBuilder.build();
        XUtils.getInstance().get(url, params, resultListener);
    }
}
