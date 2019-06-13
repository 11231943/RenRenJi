package com.trade.rrenji.biz.renren.model;

import android.content.Context;

import com.trade.rrenji.net.ServiceHelper;
import com.trade.rrenji.net.XUtils;
import com.trade.rrenji.net.XUtils.ResultListener;

import java.util.Map;

public class RenRenModelImpl implements RenRenModel {

    private Context mContext;

    public RenRenModelImpl(Context context) {
        this.mContext = context;
    }

    @Override
    public void getEveryoneHomeList(Context mContext, int page, int rows, ResultListener resultListener) {
        String url = ServiceHelper.buildUrl("api.v2.home.getEveryoneHomeList");
        ServiceHelper.ParamBuilder paramBuilder = new ServiceHelper.ParamBuilder(mContext);
        paramBuilder.add("page", page);
        paramBuilder.add("rows", rows);
        Map<String, String> params = paramBuilder.build();
        XUtils.getInstance().get(url, params, resultListener);
    }
}
