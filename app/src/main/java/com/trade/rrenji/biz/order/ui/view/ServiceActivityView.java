package com.trade.rrenji.biz.order.ui.view;

import com.trade.rrenji.bean.order.NetServiceBean;
import com.trade.rrenji.biz.base.BaseView;

/**
 * Created by monster on 8/4/18.
 */

public interface ServiceActivityView extends BaseView {

    void getAfterSaleOrderListSuccess(NetServiceBean netBaseResultBean);

    void getAfterSaleOrderListError(int code, String msg);
}
