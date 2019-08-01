package com.trade.rrenji.biz.order.model;

import android.content.Context;
import android.util.Log;

import com.trade.rrenji.bean.account.ValidateCodeBean;
import com.trade.rrenji.bean.drying.UploadDryBean;
import com.trade.rrenji.bean.order.ConfirmOrderBean;
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

import java.io.File;
import java.util.HashMap;
import java.util.List;
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
    public void getPayPlanInfoList(Context mContext, double total, String goodsId, ResultListener resultListener) {
        String url = ServiceHelper.buildUrl("api.v2.newOrder.getPayPlanInfoList");
        long timeStamp = System.currentTimeMillis();
        String token = "1";
        url = url + SettingUtils.getInstance().getSessionkeyString() + "/" + timeStamp + "/" + token;
        ServiceHelper.ParamBuilder paramBuilder = new ServiceHelper.ParamBuilder(mContext);
        Map<String, String> params = paramBuilder.build();
        params.put("total", total + "");
        params.put("goodsId", goodsId);
        XUtils.getInstance().get(url, params, resultListener);
    }

    @Override
    public void getAfterSaleOrderList(Context mContext, int page, ResultListener resultListener) {
        String url = ServiceHelper.buildUrl("api.v2.order.getAfterSaleOrderList");
        url = url + SettingUtils.getInstance().getSessionkeyString();
        ServiceHelper.ParamBuilder paramBuilder = new ServiceHelper.ParamBuilder(mContext);
        Map<String, String> params = paramBuilder.build();
        params.put("page", page + "");
        params.put("rows", "20");
        XUtils.getInstance().get(url, params, resultListener);
    }

    @Override
    public void getUserOrderDetailByOrderId(Context mContext, String orderId, String orderType, ResultListener resultListener) {
        String url = ServiceHelper.buildUrl("api.v2.order.getUserOrderDetailByOrderId");
        long timeStamp = System.currentTimeMillis();
        String token = "1";
        url = url + SettingUtils.getInstance().getSessionkeyString() + "/" + timeStamp + "/" + token;
        ServiceHelper.ParamBuilder paramBuilder = new ServiceHelper.ParamBuilder(mContext);
        Map<String, String> params = paramBuilder.build();
        params.put("orderId", orderId);
        params.put("orderType", orderType);
        XUtils.getInstance().get(url, params, resultListener);
    }

    @Override
    public void getOrderExpress(Context mContext, String orderId, int orderType, ResultListener resultListener) {
        String url = ServiceHelper.buildUrl("api.v2.order.getOrderExpress");
        url = url + SettingUtils.getInstance().getSessionkeyString();
        ServiceHelper.ParamBuilder paramBuilder = new ServiceHelper.ParamBuilder(mContext);
        Map<String, String> params = paramBuilder.build();
        params.put("orderId", orderId);
        params.put("orderType", orderType + "");
        XUtils.getInstance().get(url, params, resultListener);
    }

    @Override
    public void share(Context mContext, String orderId, String comment, List<String> urls, String location, final ResultListener resultListener) {

        String url = ServiceHelper.buildUrl("api.v2.order.share");
        long timeStamp = System.currentTimeMillis();
        String token = "1";
        url = url + SettingUtils.getInstance().getSessionkeyString() + "/" + timeStamp + "/" + token;

        UploadDryBean uploadDryBean = new UploadDryBean();
        uploadDryBean.setOrderId(orderId);
        uploadDryBean.setComment(comment);
        uploadDryBean.setLocation(location);
        uploadDryBean.setUrls(urls);
        RequestParams requestParams = new RequestParams(url);
        requestParams.setAsJsonContent(true);
        requestParams.setBodyContent(GsonUtils.getGson().toJson(uploadDryBean));
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
    public void delOrder(Context mContext, String orderId, final ResultListener resultListener) {
        String url = ServiceHelper.buildUrl("api.v2.order.delete");
        long timeStamp = System.currentTimeMillis();
        String token = "1";
        url = url + SettingUtils.getInstance().getSessionkeyString() + "/" + timeStamp + "/" + orderId + "/" + token;
        RequestParams requestParams = new RequestParams(url);
        x.http().request(HttpMethod.DELETE, requestParams, new Callback.CommonCallback<String>() {
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
    public void confirmOrder(Context mContext, int type, String goodsId, final ResultListener resultListener) {
        String url = ServiceHelper.buildUrl("api.v2.newOrder.confirmOrder");
        url = url + SettingUtils.getInstance().getSessionkeyString();
        ConfirmOrderBean orderBean = new ConfirmOrderBean();
        orderBean.setOrderId(goodsId);
        orderBean.setOrderType(String.valueOf(type));
        RequestParams requestParams = new RequestParams(url);
        requestParams.setAsJsonContent(true);
        requestParams.setBodyContent(GsonUtils.getGson().toJson(orderBean));
        Log.e("createOrder", "url :" + url + " bean = " + GsonUtils.getGson().toJson(orderBean));
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
    public void continuePay(Context mContext, ContinuePayBean bean, final ResultListener resultListener) {
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
