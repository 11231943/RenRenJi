package com.trade.rrenji.biz.order.model;

import android.content.Context;

import com.trade.rrenji.net.ServiceHelper;
import com.trade.rrenji.net.XUtils;
import com.trade.rrenji.net.XUtils.ResultListener;
import com.trade.rrenji.utils.Contetns;
import com.trade.rrenji.utils.SettingUtils;

import java.util.Map;

public class OrderModelImpl implements OrderModel {

    private Context mContext;

    public OrderModelImpl(Context context) {
        this.mContext = context;
    }

    @Override
    public void getOrderList(Context mContext, int pageNum, int type, ResultListener resultListener) {
        //{sessionKey}/{timeStamp}/{pageNum}/{type}/{token}
        //{sessionKey}/{timestamp}/{token}?page=0&rows=2&status=1
        String url = ServiceHelper.buildUrl("api.v2.order.list");
        long timeStamp = System.currentTimeMillis();
        String token = "1";
        url = url + SettingUtils.getInstance().getSessionkeyString() + "/" + timeStamp + "/" + token;
        ServiceHelper.ParamBuilder paramBuilder = new ServiceHelper.ParamBuilder(mContext);
        Map<String, String> params = paramBuilder.build();
        params.put("page", pageNum + "");
        params.put("rows", "20");
        params.put("status", type + "");
        XUtils.getInstance().get(url, params, resultListener);
    }

    @Override
    public void getAccessoryInfo(Context mContext, String goodsCode, ResultListener resultListener) {
        String url = ServiceHelper.buildUrl("api.v2.order.getAccessoryInfoByGoodsCode");
        url = url + SettingUtils.getInstance().getSessionkeyString();
        ServiceHelper.ParamBuilder paramBuilder = new ServiceHelper.ParamBuilder(mContext);
        Map<String, String> params = paramBuilder.build();
        params.put("goodsCode", goodsCode);
        XUtils.getInstance().get(url, params, resultListener);
    }

    @Override
    public void getUserCreateOrderInfoByUserId(Context mContext, ResultListener resultListener) {
        String url = ServiceHelper.buildUrl("api.v2.order.getUserCreateOrderInfoByUserId");
        url = url + SettingUtils.getInstance().getSessionkeyString();
        ServiceHelper.ParamBuilder paramBuilder = new ServiceHelper.ParamBuilder(mContext);
        Map<String, String> params = paramBuilder.build();
        XUtils.getInstance().get(url, params, resultListener);
    }
}
