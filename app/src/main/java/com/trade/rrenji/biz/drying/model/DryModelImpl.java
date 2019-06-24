package com.trade.rrenji.biz.drying.model;

import android.content.Context;

import com.trade.rrenji.net.ServiceHelper;
import com.trade.rrenji.net.XUtils;
import com.trade.rrenji.net.XUtils.ResultListener;

import java.util.Map;

public class DryModelImpl implements DryModel {

    private Context mContext;

    public DryModelImpl(Context context) {
        this.mContext = context;
    }

    @Override
    public void getDryList(Context mContext, int pageNum, ResultListener resultListener) {
        String url = ServiceHelper.buildUrl("api.v2.share.order");
        ServiceHelper.ParamBuilder paramBuilder = new ServiceHelper.ParamBuilder(mContext);
        paramBuilder.add("page", pageNum);
        paramBuilder.add("rows", 20);
        Map<String, String> params = paramBuilder.build();
        XUtils.getInstance().get(url, params, resultListener);
    }
}
