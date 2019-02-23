package com.trade.rrenji.biz.category.ui.view;

import com.trade.rrenji.bean.category.NetCategoryBean;
import com.trade.rrenji.biz.base.BaseView;

/**
 * Created by monster on 8/4/18.
 */

public interface CategoryActivityView extends BaseView {

    void getCategorySuccess(NetCategoryBean netShareBean);

    void getCategoryCodeError(int code, String msg);
}
