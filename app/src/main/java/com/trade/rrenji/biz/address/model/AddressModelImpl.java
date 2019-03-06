package com.trade.rrenji.biz.address.model;

import android.content.Context;

import com.trade.rrenji.bean.address.UserAddressCurd;
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

public class AddressModelImpl implements AddressModel {

    private Context mContext;

    public AddressModelImpl(Context context) {
        this.mContext = context;
    }

    @Override
    public void getAddressList(Context mContext, int pageNum, ResultListener resultListener) {
        String url = ServiceHelper.buildUrl("api.v2.address.list");
        long timeStamp = System.currentTimeMillis();
        String token = "1";
        url = url + SettingUtils.getInstance().getSessionkeyString() + "/" + timeStamp + "/" + pageNum + "/" + token;
        ServiceHelper.ParamBuilder paramBuilder = new ServiceHelper.ParamBuilder(mContext);
        Map<String, String> params = paramBuilder.build();
        XUtils.getInstance().get(url, params, resultListener);
    }

    @Override
    public void isUpAddress(Context mContext, String addressId, ResultListener resultListener) {
        String url = ServiceHelper.buildUrl("api.v2.address.isUpAddress");
        long timeStamp = System.currentTimeMillis();
        url = url + SettingUtils.getInstance().getSessionkeyString() + "/" + timeStamp;
        ServiceHelper.ParamBuilder paramBuilder = new ServiceHelper.ParamBuilder(mContext);
        paramBuilder.add("addressId",addressId);
        Map<String, String> params = paramBuilder.build();
        XUtils.getInstance().post(url, params, resultListener);
    }

    @Override
    public void delAddress(Context mContext, String addressId,final ResultListener resultListener) {
        String url = ServiceHelper.buildUrl("api.v2.address.del");
        long timeStamp = System.currentTimeMillis();
        String token = "1";
        url = url + SettingUtils.getInstance().getSessionkeyString() + "/" + timeStamp+ "/" + addressId + "/" + token;
        RequestParams requestParams = new RequestParams(url);
        x.http().request(HttpMethod.DELETE,requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                resultListener.onResponse(result);
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                resultListener.onError(ex);
            }

            @Override
            public void onFinished() {

            }
        });
    }

    @Override
    public void updateAddress(Context mContext, int type, UserAddressCurd addressCurd,final ResultListener resultListener) {
        String url = ServiceHelper.buildUrl("api.v2.address.operate");

        long timeStamp = System.currentTimeMillis();
        String token = "1";
        url = url + SettingUtils.getInstance().getSessionkeyString() + "/" + timeStamp + "/" + token;
        ServiceHelper.ParamBuilder paramBuilder = new ServiceHelper.ParamBuilder(mContext);
//        paramBuilder.add("addressId", addressCurd.getAddressId())
//                .add("city", addressCurd.getCity())
//                .add("consigneeName", addressCurd.getConsigneeName())
//                .add("district", addressCurd.getDistrict())
//                .add("location", addressCurd.getLocation())
//                .add("province", addressCurd.getProvince())
//                .add("consigneeTel", addressCurd.getConsigneeTel());
            Map<String, String> params = paramBuilder.build();
//        if (type == 0) {
//            XUtils.getInstance().put(url, params, resultListener);
//        } else if (type == 1) {
//            XUtils.getInstance().post(url, params, resultListener);
//        }
        RequestParams requestParams = new RequestParams(url);
        requestParams.setAsJsonContent(true);
        requestParams.setBodyContent(GsonUtils.getGson().toJson(addressCurd));
//        requestParams.setBodyEntity(new StringEntity(gson.toJson(要转成json的对象),"UTF-8"));
//        requestParams.setContentType("application/json");
        x.http().request(HttpMethod.PUT, requestParams, new Callback.CommonCallback<String>() {
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
