package com.trade.rrenji.biz.order.ui.view;

import com.trade.rrenji.bean.order.NetLogisticsBean;
import com.trade.rrenji.biz.base.BaseView;

public interface LogisticsView extends BaseView {

    void getOrderExpressSuccess(NetLogisticsBean netAccessoryListBean);

    void getOrderExpressError(int code, String msg);
}
