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
    public void getCategory(Context mContext, ResultListener resultListener) {
        //{sessionKey}/{timeStamp}/{pageNum}/{token}
        String url = ServiceHelper.buildUrl("api.v2.classify.getClassifyData");
//        String sessionKey = "123456";
//        long timeStamp = System.currentTimeMillis();
//        String token = "1";
//        url = url + sessionKey + "/" + timeStamp + "/" + pageNum + "/" + token;
        ServiceHelper.ParamBuilder paramBuilder = new ServiceHelper.ParamBuilder(mContext);
//        paramBuilder.add("sessionKey", "1");
//        paramBuilder.add("timeStamp", System.currentTimeMillis());
//        paramBuilder.add("pageNum", pageNum);
//        paramBuilder.add("token", "1");
        Map<String, String> params = paramBuilder.build();
        XUtils.getInstance().get(url, params, resultListener);
    }
}
