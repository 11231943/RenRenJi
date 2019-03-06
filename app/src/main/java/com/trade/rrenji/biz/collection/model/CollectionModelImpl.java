package com.trade.rrenji.biz.collection.model;

import android.content.Context;

import com.trade.rrenji.net.ServiceHelper;
import com.trade.rrenji.net.XUtils;
import com.trade.rrenji.utils.SettingUtils;

import java.util.Map;

/**
 * Created by monster on 8/4/18.
 */

public class CollectionModelImpl implements CollectionModel {

    private Context mContext;

    public CollectionModelImpl(Context context) {
        this.mContext = context;
    }

    @Override
    public void getCollectionList(Context mContext, int currentPage, XUtils.ResultListener resultListener) {
        String url = ServiceHelper.buildUrl("api.v2.product.favorite.list");
        long timeStamp = System.currentTimeMillis();
        int rows = 10;
        url = url + SettingUtils.getInstance().getSessionkeyString() + "/" + timeStamp + "/" + currentPage + "/" + rows;
        ServiceHelper.ParamBuilder paramBuilder = new ServiceHelper.ParamBuilder(mContext);
        Map<String, String> params = paramBuilder.build();
        XUtils.getInstance().get(url, params, resultListener);
    }

    @Override
    public void addCollection(Context mContext, String goodsCode, XUtils.ResultListener resultListener) {
        String url = ServiceHelper.buildUrl("api.v2.product.favorite");
        long timeStamp = System.currentTimeMillis();
        url = url + SettingUtils.getInstance().getSessionkeyString() + "/" + timeStamp + "/" + goodsCode;
        ServiceHelper.ParamBuilder paramBuilder = new ServiceHelper.ParamBuilder(mContext);
        Map<String, String> params = paramBuilder.build();
        XUtils.getInstance().post(url, params, resultListener);
    }

    @Override
    public void delCollection(Context mContext, String id, XUtils.ResultListener resultListener) {
        String url = ServiceHelper.buildUrl("api.v2.product.del.favorite");
        long timeStamp = System.currentTimeMillis();
        url = url + SettingUtils.getInstance().getSessionkeyString() + "/" + timeStamp + "/" + id;
        ServiceHelper.ParamBuilder paramBuilder = new ServiceHelper.ParamBuilder(mContext);
        Map<String, String> params = paramBuilder.build();
        XUtils.getInstance().post(url, params, resultListener);
    }
}
