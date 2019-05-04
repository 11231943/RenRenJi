package com.trade.rrenji.biz.search.model;

import android.content.Context;

import com.trade.rrenji.biz.base.PlusBaseService;
import com.trade.rrenji.net.XUtils.ResultListener;

/**
 * Created by monster on 8/4/18.
 */

public interface SearchModel extends PlusBaseService {

    void getHotSearchGoodsNameList(Context mContext, ResultListener resultListener);

    void getProductListByGoodsName(Context mContext, int page, int rows, int priceSort, String goodsName, ResultListener resultListener);

}
