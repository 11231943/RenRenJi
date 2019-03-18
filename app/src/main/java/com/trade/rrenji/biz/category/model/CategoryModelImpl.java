package com.trade.rrenji.biz.category.model;

import android.content.Context;

import com.trade.rrenji.net.ServiceHelper;
import com.trade.rrenji.net.XUtils;
import com.trade.rrenji.net.XUtils.ResultListener;

import java.util.Map;

public class CategoryModelImpl implements CategoryModel {

    private Context mContext;

    public CategoryModelImpl(Context context) {
        this.mContext = context;
    }

    @Override
    public void getClassifyDataByType(Context mContext, String id, String type, ResultListener resultListener) {
        String url = ServiceHelper.buildUrl("api.v2.classify.getClassifyDataByType");
        ServiceHelper.ParamBuilder paramBuilder = new ServiceHelper.ParamBuilder(mContext);
        paramBuilder.add("id", id);
        paramBuilder.add("type", type);
        Map<String, String> params = paramBuilder.build();
        XUtils.getInstance().get(url, params, resultListener);
    }

    @Override
    public void getCategoryLeft(Context mContext, ResultListener resultListener) {
        String url = ServiceHelper.buildUrl("api.v2.classify.getClassifyData");
        ServiceHelper.ParamBuilder paramBuilder = new ServiceHelper.ParamBuilder(mContext);
        Map<String, String> params = paramBuilder.build();
        XUtils.getInstance().get(url, params, resultListener);
    }
}
