package com.trade.rrenji.biz.order.ui.view;

import com.trade.rrenji.bean.order.NetGetUserCreateOrderBean;
import com.trade.rrenji.bean.order.NetResultCreateOrderBean;
import com.trade.rrenji.biz.base.BaseView;

public interface GetUserCreateOrderInfoView extends BaseView {

    void getUserCreateOrderInfoByUserIdSuccess(NetGetUserCreateOrderBean netAccessoryListBean);

    void getUserCreateOrderInfoByUserIdError(int code, String msg);

    void createOrder(NetResultCreateOrderBean netResultCreateOrderBean);

    void createOrderError(int code, String msg);
}
