package com.trade.rrenji.biz.account.model;

import android.content.Context;

import com.trade.rrenji.bean.account.LoginBean;
import com.trade.rrenji.bean.account.ValidateCodeBean;
import com.trade.rrenji.net.ServiceHelper;
import com.trade.rrenji.net.XUtils;
import com.trade.rrenji.utils.GsonUtils;

import org.xutils.common.Callback;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

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
        ValidateCodeBean validateCodeBean =new ValidateCodeBean();
        validateCodeBean.setPhone(phone);
        RequestParams requestParams = new RequestParams(url+"/"+timeStamp);
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
    public void login(Context mContext, String phone, String code, String device, String channel_id,final XUtils.ResultListener resultListener) {
        String url = ServiceHelper.buildUrl("api.v2.action.login");
        long timeStamp = System.currentTimeMillis();
        LoginBean loginBean =new LoginBean();
        loginBean.setPhone(phone);
        loginBean.setDeviceCode(channel_id);
        loginBean.setDeviceType(device);
        loginBean.setSmsCode(code);
        RequestParams requestParams = new RequestParams(url+"/"+timeStamp);
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

}
