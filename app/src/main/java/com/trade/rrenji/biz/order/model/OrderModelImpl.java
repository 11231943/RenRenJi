package com.trade.rrenji.biz.order.model;

import android.content.Context;
import android.util.Log;

import com.trade.rrenji.bean.account.ValidateCodeBean;
import com.trade.rrenji.bean.order.ContinuePayBean;
import com.trade.rrenji.bean.order.CreateOrderBean;
import com.trade.rrenji.net.ServiceHelper;
import com.trade.rrenji.net.XUtils;
import com.trade.rrenji.net.XUtils.ResultListener;
import com.trade.rrenji.utils.Contetns;
import com.trade.rrenji.utils.GsonUtils;
import com.trade.rrenji.utils.SettingUtils;

import org.xutils.common.Callback;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.Map;

public class OrderModelImpl implements OrderModel {

    private Context mContext;

    public OrderModelImpl(Context context) {
        this.mContext = context;
    }

    @Override
    public void getOrderList(Context mContext, int pageNum, String status, ResultListener resultListener) {
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
        params.put("status", status);
        XUtils.getInstance().get(url, params, resultListener);
    }

    @Override
    public void getAccessoryInfo(Context mContext, String goodsCode, ResultListener resultListener) {
        String url = ServiceHelper.buildUrl("api.v2.order.getAccessoryInfoByGoodsCode");
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

    @Override
    public void continuePay(Context mContext, ContinuePayBean bean,final ResultListener resultListener) {
        String url = ServiceHelper.buildUrl("api.v2.order.newContinuePay");
        long timeStamp = System.currentTimeMillis();
        url = url + SettingUtils.getInstance().getSessionkeyString() + "/" + timeStamp + "/" + "abc";
        RequestParams requestParams = new RequestParams(url);
        requestParams.setAsJsonContent(true);
        requestParams.setBodyContent(GsonUtils.getGson().toJson(bean));
        Log.e("createOrder", "url :" + url + " bean = " + GsonUtils.getGson().toJson(bean));
        x.http().request(HttpMethod.POST, requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                resultListener.onResponse(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                resultListener.onError(ex);
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    @Override
    public void createOrder(Context mContext, CreateOrderBean bean, final ResultListener resultListener) {
        String url = ServiceHelper.buildUrl("api.v2.order.create");
        long timeStamp = System.currentTimeMillis();
        url = url + SettingUtils.getInstance().getSessionkeyString() + "/" + timeStamp + "/" + "abc";
        RequestParams requestParams = new RequestParams(url);
        requestParams.setAsJsonContent(true);
        requestParams.setBodyContent(GsonUtils.getGson().toJson(bean));
        Log.e("createOrder", "url :" + url + " bean = " + GsonUtils.getGson().toJson(bean));
        x.http().request(HttpMethod.POST, requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                resultListener.onResponse(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                resultListener.onError(ex);
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
}
