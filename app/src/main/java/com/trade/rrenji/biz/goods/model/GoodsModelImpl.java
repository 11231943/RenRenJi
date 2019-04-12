package com.trade.rrenji.biz.goods.model;

import android.content.Context;

import com.trade.rrenji.net.ServiceHelper;
import com.trade.rrenji.net.XUtils;
import com.trade.rrenji.utils.Contetns;
import com.trade.rrenji.utils.SettingUtils;

import java.util.Map;

/**
 * Created by monster on 8/4/18.
 */

public class GoodsModelImpl implements GoodsModel {

    private Context mContext;

    public GoodsModelImpl(Context context) {
        this.mContext = context;
    }

    @Override
    public void getGoodsDetail(Context mContext, String goodsCode, XUtils.ResultListener resultListener) {
        String url = ServiceHelper.buildUrl("api.v2.product.detail");
        long timeStamp = System.currentTimeMillis();
        String token = "1";
        url = url + SettingUtils.getInstance().getSessionkeyString() + "/" + timeStamp + "/" + goodsCode + "/" + token;
        ServiceHelper.ParamBuilder paramBuilder = new ServiceHelper.ParamBuilder(mContext);
        Map<String, String> params = paramBuilder.build();
        XUtils.getInstance().get(url, params, resultListener);
    }

    @Override
    public void getReplyComment(Context mContext, String goodsCode, int currentPage, XUtils.ResultListener resultListener) {
        String url = ServiceHelper.buildUrl("api.v2.product.evaluate");
        long timeStamp = System.currentTimeMillis();
        String token = "1";
        url = url + SettingUtils.getInstance().getSessionkeyString() + "/" + timeStamp + "/" + goodsCode + "/" + currentPage+"/"+token;
        ServiceHelper.ParamBuilder paramBuilder = new ServiceHelper.ParamBuilder(mContext);
        Map<String, String> params = paramBuilder.build();
        XUtils.getInstance().get(url, params, resultListener);
    }
}
