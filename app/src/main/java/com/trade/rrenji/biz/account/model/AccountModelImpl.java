package com.trade.rrenji.biz.account.model;

import android.content.Context;
import android.util.Log;

import com.trade.rrenji.bean.account.LoginBean;
import com.trade.rrenji.bean.account.ValidateCodeBean;
import com.trade.rrenji.net.ServiceHelper;
import com.trade.rrenji.net.UrlEncodedParamsBody;
import com.trade.rrenji.net.XUtils;
import com.trade.rrenji.utils.GsonUtils;
import com.trade.rrenji.utils.SettingUtils;

import org.xutils.common.Callback;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.IOException;
import java.util.Map;

/**
 * Created by monster on 8/4/18.
 */

public class AccountModelImpl implements AccountModel {

    private Context mContext;

    public AccountModelImpl(Context context) {
        this.mContext = context;
    }

    @Override
    public void getVerifyCode(Context mContext, String phone, final XUtils.ResultListener resultListener) {
        String url = ServiceHelper.buildUrl("api.v2.action.sendCode");
        long timeStamp = System.currentTimeMillis();
//        ServiceHelper.ParamBuilder paramBuilder = new ServiceHelper.ParamBuilder(mContext);
//        paramBuilder.add("phone", phone);
//        Map<String, String> params = paramBuilder.build();
        ValidateCodeBean validateCodeBean = new ValidateCodeBean();
        validateCodeBean.setPhone(phone);
        RequestParams requestParams = new RequestParams(url + "/" + timeStamp);
        requestParams.setAsJsonContent(true);
        requestParams.setBodyContent(GsonUtils.getGson().toJson(validateCodeBean));
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
    public void login(Context mContext, String phone, String code, String device, String channel_id, final XUtils.ResultListener resultListener) {
        String url = ServiceHelper.buildUrl("api.v2.action.login");
        long timeStamp = System.currentTimeMillis();
        LoginBean loginBean = new LoginBean();
        loginBean.setPhone(phone);
        loginBean.setDeviceCode(channel_id);
        loginBean.setDeviceType(device);
        loginBean.setSmsCode(code);
        RequestParams requestParams = new RequestParams(url + timeStamp);
        requestParams.setAsJsonContent(true);
        requestParams.setBodyContent(GsonUtils.getGson().toJson(loginBean));
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
    public void updateUserInfo(Context mContext, String headImg, String name, String sex, String address, final XUtils.ResultListener resultListener) {
        String url = ServiceHelper.buildUrl("api.v2.action.updateUserInfo");
        ServiceHelper.ParamBuilder paramBuilder = new ServiceHelper.ParamBuilder(mContext);
        paramBuilder.add("headImg", headImg);
        paramBuilder.add("name", name);
        paramBuilder.add("sex", sex);
        paramBuilder.add("address", address);
        Map<String, String> params = paramBuilder.build();
        url = url + SettingUtils.getInstance().getSessionkeyString();
        XUtils.getInstance().post(url, params, resultListener);
//        RequestParams requestParams = new RequestParams(url);
//        requestParams.addBodyParameter("name","小百度");
//        requestParams.addBodyParameter("headImg","http://qiniu.rrenji.com/FkBWf3d7MMnDdH7cDQhmIp_RDE4i");
//        requestParams.addBodyParameter("sex","1");
//        requestParams.addBodyParameter("address","湖北武汉");
//        requestParams.setMultipart(true);
//        try {
//            Log.d("tag",requestParams.getRequestBody().getContentType()+"");
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//        x.http().request(HttpMethod.POST, requestParams, new Callback.CommonCallback<String>() {
//            @Override
//            public void onSuccess(String result) {
//                resultListener.onResponse(result);
//            }
//
//            @Override
//            public void onError(Throwable ex, boolean isOnCallback) {
//                resultListener.onError(ex);
//            }
//
//            @Override
//            public void onCancelled(CancelledException cex) {
//
//            }
//
//            @Override
//            public void onFinished() {
//
//            }
//        });
    }
}
