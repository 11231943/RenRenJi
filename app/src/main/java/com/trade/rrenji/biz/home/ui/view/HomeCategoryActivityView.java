package com.trade.rrenji.biz.home.ui.view;

import com.trade.rrenji.bean.category.NetCategoryListBean;
import com.trade.rrenji.bean.category.NetScreenBean;
import com.trade.rrenji.bean.category.NetScreenListBean;
import com.trade.rrenji.biz.base.BaseView;

/**
 * Created by monster on 8/4/18.
 */

public interface HomeCategoryActivityView extends BaseView {

    void getHomeCategorySuccess(NetCategoryListBean netShareBean);

    void getHomeCategoryError(int code, String msg);

    void getModelAttrSuccess(NetScreenBean netShareBean);

    void getModelAttrError(int code, String msg);

    void getAttributeProductListSuccess(NetScreenListBean netShareBean);

    void getAttributeProductListError(int code, String msg);
}
