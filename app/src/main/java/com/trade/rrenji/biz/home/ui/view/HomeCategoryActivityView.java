package com.trade.rrenji.biz.home.ui.view;

import com.trade.rrenji.bean.category.NetCategoryListBean;
import com.trade.rrenji.biz.base.BaseView;

/**
 * Created by monster on 8/4/18.
 */

public interface HomeCategoryActivityView extends BaseView {

    void getHomeCategorySuccess(NetCategoryListBean netShareBean);

    void getHomeCategoryError(int code, String msg);
}
