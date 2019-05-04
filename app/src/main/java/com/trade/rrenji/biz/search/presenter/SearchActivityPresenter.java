package com.trade.rrenji.biz.search.presenter;

import android.content.Context;

import com.trade.rrenji.biz.base.Presenter;
import com.trade.rrenji.biz.search.ui.view.SearchActivityView;
import com.trade.rrenji.net.XUtils;

/**
 * Created by zhanghuatao on 2016/9/26.
 */
public interface SearchActivityPresenter extends Presenter<SearchActivityView> {

    void getHotSearchGoodsNameList(Context mContext);

    void getProductListByGoodsName(Context mContext, int page, int rows, int priceSort, String goodsName);

}
