package com.trade.rrenji.biz.home.model;

import android.content.Context;

import com.trade.rrenji.biz.base.PlusBaseService;
import com.trade.rrenji.net.XUtils.ResultListener;

/**
 * Created by monster on 8/4/18.
 */

public interface HomeModel extends PlusBaseService {

    void getHomeList(Context mContext, int pageNum, ResultListener resultListener);

    void getCategoryDetailById(Context mContext, String categoryId, int pageNum, int rows,int priceSort, ResultListener resultListener);

    void getNewHomeGoodsMoreByTypes(Context mContext, int type, int pageNum, int rows, ResultListener resultListener);

    void getModelAttr(Context mContext, String id, ResultListener resultListener);

    void getAttributeProductList(Context mContext,int priceSort, int page, String model, String memory, String color, String network, String condition, String version, ResultListener resultListener);

}
