package com.trade.rrenji.biz.home.ui.view;

import com.trade.rrenji.bean.category.NetCategoryListBean;
import com.trade.rrenji.biz.base.BaseView;

/**
 * Created by monster on 8/4/18.
 */

public interface HomeRecommendActivityView extends BaseView {

    void getNewHomeGoodsMoreByTypesSuccess(NetCategoryListBean netShareBean);

    void getNewHomeGoodsMoreByTypesError(int code, String msg);
}
