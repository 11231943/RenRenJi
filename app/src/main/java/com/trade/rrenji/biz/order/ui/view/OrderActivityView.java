package com.trade.rrenji.biz.order.ui.view;

import com.trade.rrenji.bean.address.NetAddressBean;
import com.trade.rrenji.bean.order.NetOrderBean;
import com.trade.rrenji.biz.base.BaseView;

/**
 * Created by monster on 8/4/18.
 */

public interface OrderActivityView extends BaseView {

    void getOrderListSuccess(NetOrderBean netOrderBean);

    void getOrderListError(int code, String msg);
}
