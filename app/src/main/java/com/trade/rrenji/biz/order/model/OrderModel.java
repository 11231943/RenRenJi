package com.trade.rrenji.biz.order.model;

import android.content.Context;

import com.trade.rrenji.bean.order.CreateOrderBean;
import com.trade.rrenji.biz.base.PlusBaseService;
import com.trade.rrenji.net.XUtils.ResultListener;

public interface OrderModel extends PlusBaseService {

    void getOrderList(Context mContext, int pageNum, int type, ResultListener resultListener);

    void getAccessoryInfo(Context mContext, String goodsCode, ResultListener resultListener);

    void getUserCreateOrderInfoByUserId(Context mContext,ResultListener resultListener);

    void createOrder(Context mContext, CreateOrderBean bean, ResultListener resultListener);
}
