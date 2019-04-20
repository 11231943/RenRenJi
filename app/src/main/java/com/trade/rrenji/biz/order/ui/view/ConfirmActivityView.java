package com.trade.rrenji.biz.order.ui.view;

import com.trade.rrenji.biz.base.BaseView;
import com.trade.rrenji.biz.base.NetBaseResultBean;

/**
 * Created by monster on 8/4/18.
 */

public interface ConfirmActivityView extends BaseView {

    void getConfirmOrderSuccess(NetBaseResultBean netOrderBean);

    void getConfirmOrderError(int code, String msg);
}
