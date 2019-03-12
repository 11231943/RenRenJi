package com.trade.rrenji.biz.order.presenter;

import android.content.Context;

import com.trade.rrenji.bean.order.CreateOrderBean;
import com.trade.rrenji.biz.base.Presenter;
import com.trade.rrenji.biz.order.ui.view.AccessoryInfoView;
import com.trade.rrenji.biz.order.ui.view.GetUserCreateOrderInfoView;

/**
 * Created by zhanghuatao on 2016/9/26.
 */
public interface GetUserCreateOrderInfoPresenter extends Presenter<GetUserCreateOrderInfoView> {

    void getUserCreateOrderInfoByUserId(Context mContext);

    void createOrder(Context mContext, CreateOrderBean bean);

}
