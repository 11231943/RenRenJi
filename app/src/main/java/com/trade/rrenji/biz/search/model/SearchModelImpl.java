package com.trade.rrenji.biz.search.model;

import android.content.Context;

import com.trade.rrenji.net.ServiceHelper;
import com.trade.rrenji.net.XUtils;
import com.trade.rrenji.utils.SettingUtils;

import java.util.Map;

/**
 * Created by monster on 8/4/18.
 */

public class SearchModelImpl implements SearchModel {

    private Context mContext;

    public SearchModelImpl(Context context) {
        this.mContext = context;
    }

    @Override
    public void getHotSearchGoodsNameList(Context mContext, XUtils.ResultListener resultListener) {
        String url = ServiceHelper.buildUrl("api.v2.getHotSearchGoodsNameList");
        ServiceHelper.ParamBuilder paramBuilder = new ServiceHelper.ParamBuilder(mContext);
        Map<String, String> params = paramBuilder.build();
        XUtils.getInstance().get(url, params, resultListener);
    }

    @Override
    public void getProductListByGoodsName(Context mContext, int page, int rows, int priceSort, String goodsName, XUtils.ResultListener resultListener) {
        String url = ServiceHelper.buildUrl("api.v2.product.getProductListByGoodsName");
        ServiceHelper.ParamBuilder paramBuilder = new ServiceHelper.ParamBuilder(mContext);
        Map<String, String> params = paramBuilder.build();
        params.put("page", page + "");
        params.put("rows", rows + "");
        params.put("priceSort", priceSort + "");
        params.put("goodsName", goodsName);
        XUtils.getInstance().get(url, params, resultListener);
    }
}
