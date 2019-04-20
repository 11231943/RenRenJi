package com.trade.rrenji.biz.category.ui.view;

import com.trade.rrenji.bean.category.NetScreenBean;
import com.trade.rrenji.biz.base.BaseView;

/**
 * Created by monster on 8/4/18.
 */

public interface ScreenActivityListView extends BaseView {

    void getModelAttrSuccess(NetScreenBean netShareBean);

    void getModelAttrError(int code, String msg);


}
