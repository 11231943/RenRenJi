package com.trade.rrenji.biz.search.ui.view;

import com.trade.rrenji.bean.search.NetSearchBean;
import com.trade.rrenji.bean.search.NetSearchValueBean;
import com.trade.rrenji.biz.base.BaseView;

/**
 * Created by monster on 8/4/18.
 */

public interface SearchActivityView extends BaseView {

    void getHotSearchGoodsNameList(NetSearchBean netShareBean);

    void getHotSearchGoodsNameListError(int code, String msg);

    void getProductListByGoodsName(NetSearchValueBean netShareBean);

    void getProductListByGoodsNameError(int code, String msg);

}
