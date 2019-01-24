package com.trade.rrenji.biz.order.presenter;

import android.content.Context;

import com.trade.rrenji.biz.base.Presenter;
import com.trade.rrenji.biz.order.ui.view.OrderActivityView;

/**
 * Created by zhanghuatao on 2016/9/26.
 */
public interface OrderActivityPresenter extends Presenter<OrderActivityView> {

    void getOrderList(Context mContext, int pageNum, int type);

}
