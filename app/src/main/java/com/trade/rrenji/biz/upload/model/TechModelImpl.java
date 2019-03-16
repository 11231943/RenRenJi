package com.trade.rrenji.biz.upload.model;

import android.content.Context;

import com.trade.rrenji.biz.drying.model.DryModel;
import com.trade.rrenji.biz.tech.model.TechModel;
import com.trade.rrenji.net.ServiceHelper;
import com.trade.rrenji.net.XUtils;
import com.trade.rrenji.net.XUtils.ResultListener;

import java.util.Map;

public class TechModelImpl implements TechModel {

    private Context mContext;

    public TechModelImpl(Context context) {
        this.mContext = context;
    }

    @Override
    public void getTechList(Context mContext, int pageNum, ResultListener resultListener) {
        //{sessionKey}/{timeStamp}/{pageNum}/{token}
        String url = ServiceHelper.buildUrl("api.v2.tech.community");
        String sessionKey = "123456";
        long timeStamp = System.currentTimeMillis();
        String token = "1";
        url = url + sessionKey + "/" + timeStamp + "/" + pageNum + "/" + token;
        ServiceHelper.ParamBuilder paramBuilder = new ServiceHelper.ParamBuilder(mContext);
//        paramBuilder.add("sessionKey", "1");
//        paramBuilder.add("timeStamp", System.currentTimeMillis());
//        paramBuilder.add("pageNum", pageNum);
//        paramBuilder.add("token", "1");
        Map<String, String> params = paramBuilder.build();
        XUtils.getInstance().get(url, params, resultListener);
    }
}
