package com.trade.rrenji.biz.order.model;

import android.content.Context;

import com.trade.rrenji.net.ServiceHelper;
import com.trade.rrenji.net.XUtils;
import com.trade.rrenji.net.XUtils.ResultListener;
import com.trade.rrenji.utils.Contetns;

import java.util.Map;

public class OrderModelImpl implements OrderModel {

    private Context mContext;

    public OrderModelImpl(Context context) {
        this.mContext = context;
    }

    @Override
    public void getOrderList(Context mContext, int pageNum, int type, ResultListener resultListener) {
        //{sessionKey}/{timeStamp}/{pageNum}/{type}/{token}
        String url = ServiceHelper.buildUrl("api.v2.order.list");
        String sessionKey = Contetns.sessionKey;
        long timeStamp = System.currentTimeMillis();
        String token = "1";
        url = url + sessionKey + "/" + timeStamp + "/" + pageNum + "/" + type + "/" + token;
        ServiceHelper.ParamBuilder paramBuilder = new ServiceHelper.ParamBuilder(mContext);
        Map<String, String> params = paramBuilder.build();
        XUtils.getInstance().get(url, params, resultListener);
    }
}
