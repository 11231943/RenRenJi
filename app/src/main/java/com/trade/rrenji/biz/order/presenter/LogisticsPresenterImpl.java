package com.trade.rrenji.biz.order.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.trade.rrenji.bean.goods.NetAccessoryListBean;
import com.trade.rrenji.bean.order.NetLogisticsBean;
import com.trade.rrenji.biz.base.BasePresenter;
import com.trade.rrenji.biz.base.Presenter;
import com.trade.rrenji.biz.order.model.OrderModel;
import com.trade.rrenji.biz.order.model.OrderModelImpl;
import com.trade.rrenji.biz.order.ui.view.AccessoryInfoView;
import com.trade.rrenji.biz.order.ui.view.LogisticsView;
import com.trade.rrenji.net.XUtils;
import com.trade.rrenji.utils.Contetns;

/**
 * Created by zhanghuatao on 2016/9/26.
 */
public class LogisticsPresenterImpl extends BasePresenter<LogisticsView> implements LogisticsPresenter {
    private OrderModel mModel;

    Context mContext;

    public LogisticsPresenterImpl(Context context) {
        mContext = context;
        mModel = new OrderModelImpl(context);
    }

    @Override
    public void getOrderExpress(Context mContext, String orderId, int orderType) {
        mModel.getOrderExpress(mContext, orderId, orderType, new XUtils.ResultListener() {
            @Override
            public void onResponse(String result) {
                try {
                    if (getActivityView() != null) {
                        getActivityView().hideLoading();
                    }
                    Gson gson = new Gson();
                    final NetLogisticsBean netShareBean = gson.fromJson(result, NetLogisticsBean.class);
                    if (Integer.valueOf(netShareBean.getCode()) == Contetns.STATE_OK) {
                        if (getActivityView() != null) {
                            getActivityView().getOrderExpressSuccess(netShareBean);
                        }
                    } else {
                        if (getActivityView() != null) {
                            getActivityView().getOrderExpressError(Integer.valueOf(netShareBean.getCode()), netShareBean.getMsg());
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable error) {
                getActivityView().getOrderExpressError(-10000, "请求错误");
            }
        });

    }
}
