package com.trade.rrenji.biz.category.model;

import android.content.Context;

import com.trade.rrenji.bean.account.ValidateCodeBean;
import com.trade.rrenji.bean.category.ScreenBean;
import com.trade.rrenji.net.ServiceHelper;
import com.trade.rrenji.net.XUtils;
import com.trade.rrenji.net.XUtils.ResultListener;
import com.trade.rrenji.utils.GsonUtils;

import org.xutils.common.Callback;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.Map;

public class CategoryModelImpl implements CategoryModel {

    private Context mContext;

    public CategoryModelImpl(Context context) {
        this.mContext = context;
    }

    @Override
    public void getClassifyDataByType(Context mContext, String id, String type,int page,int rows, ResultListener resultListener) {
        String url = ServiceHelper.buildUrl("api.v2.classify.getClassifyDataByType");
        ServiceHelper.ParamBuilder paramBuilder = new ServiceHelper.ParamBuilder(mContext);
        paramBuilder.add("id", id);
        paramBuilder.add("type", type);
        paramBuilder.add("page", page);
        paramBuilder.add("rows", rows);
        Map<String, String> params = paramBuilder.build();
        XUtils.getInstance().get(url, params, resultListener);
    }

    @Override
    public void getModelAttr(Context mContext, String id, ResultListener resultListener) {
        String url = ServiceHelper.buildUrl("api.v2.product.getModelAttr");
        ServiceHelper.ParamBuilder paramBuilder = new ServiceHelper.ParamBuilder(mContext);
        paramBuilder.add("goodsModelId", id);
        Map<String, String> params = paramBuilder.build();
        XUtils.getInstance().get(url, params, resultListener);
    }

    @Override
    public void getAttributeProductList(Context mContext, int page, String memory, String color, String network, String condition, String version, final ResultListener resultListener) {
        String url = ServiceHelper.buildUrl("api.v2.product.getAttributeProductList");
        ScreenBean screenBean =new ScreenBean();
        screenBean.setColor(color);
        screenBean.setCondition(condition);
        screenBean.setMemory(memory);
        screenBean.setNetwork(network);
        screenBean.setVersion(version);
        RequestParams requestParams = new RequestParams(url + "/" + page);
        requestParams.setAsJsonContent(true);
        requestParams.setBodyContent(GsonUtils.getGson().toJson(screenBean));

        x.http().request(HttpMethod.GET, requestParams, new Callback.CommonCallback<String>() {
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
    public void getCategoryLeft(Context mContext, ResultListener resultListener) {
        String url = ServiceHelper.buildUrl("api.v2.classify.getClassifyData");
        ServiceHelper.ParamBuilder paramBuilder = new ServiceHelper.ParamBuilder(mContext);
        Map<String, String> params = paramBuilder.build();
        XUtils.getInstance().get(url, params, resultListener);
    }
}
