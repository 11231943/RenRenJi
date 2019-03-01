package com.trade.rrenji.biz.order.ui.view;

import com.trade.rrenji.bean.goods.NetAccessoryListBean;
import com.trade.rrenji.biz.base.BaseView;

public interface AccessoryInfoView extends BaseView {

    void getAccessoryInfoSuccess(NetAccessoryListBean netAccessoryListBean);

    void getAccessoryInfoError(int code, String msg);
}
