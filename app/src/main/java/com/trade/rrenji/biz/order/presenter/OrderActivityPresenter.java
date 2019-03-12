package com.trade.rrenji.biz.order.presenter;

import android.content.Context;

import com.trade.rrenji.biz.base.Presenter;
import com.trade.rrenji.biz.order.ui.view.OrderActivityView;

/**
 * Created by zhanghuatao on 2016/9/26.
 */
public interface OrderActivityPresenter extends Presenter<OrderActivityView> {

    /**
     *
     * @param mContext
     * @param pageNum
     * @param status 1-待付款; 2-待发货; 3-待收货; 4-订单完成; 6-关闭订单,同时state=0; 7-已取消
     */
    void getOrderList(Context mContext, int pageNum, String status);

}
