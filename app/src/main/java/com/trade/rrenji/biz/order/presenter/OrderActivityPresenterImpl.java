package com.trade.rrenji.biz.order.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.trade.rrenji.bean.order.NetOrderBean;
import com.trade.rrenji.biz.base.BasePresenter;
import com.trade.rrenji.biz.base.NetBaseResultBean;
import com.trade.rrenji.biz.order.model.OrderModel;
import com.trade.rrenji.biz.order.model.OrderModelImpl;
import com.trade.rrenji.biz.order.ui.view.OrderActivityView;
import com.trade.rrenji.net.XUtils;
import com.trade.rrenji.utils.Contetns;

public class OrderActivityPresenterImpl extends BasePresenter<OrderActivityView> implements OrderActivityPresenter {

    private OrderModel mModel;

    Context mContext;

    public OrderActivityPresenterImpl(Context context) {
        mContext = context;
        mModel = new OrderModelImpl(context);
    }

    @Override
    public void delOrder(Context mContext, String orderId) {
        mModel.delOrder(mContext, orderId, new XUtils.ResultListener() {
            @Override
            public void onResponse(String result) {
                try {
                    if (getActivityView() != null) {
                        getActivityView().hideLoading();
                    }
                    Gson gson = new Gson();
                    final NetBaseResultBean netShareBean = gson.fromJson(result, NetBaseResultBean.class);
                    if (Integer.valueOf(netShareBean.getCode()) == Contetns.STATE_OK) {
                        if (getActivityView() != null) {
                            getActivityView().delOrderSuccess(netShareBean);
                        }
                    } else {
                        if (getActivityView() != null) {
                            getActivityView().delOrderError(Integer.valueOf(netShareBean.getCode()), netShareBean.getMsg());
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(Throwable error) {
                getActivityView().delOrderError(-10000, "请求错误");

            }
        });
    }

    @Override
    public void getOrderList(Context mContext, int pageNum, String status) {
        mModel.getOrderList(mContext, pageNum, status, new XUtils.ResultListener() {
            @Override
            public void onResponse(String result) {
                try {
                    if (getActivityView() != null) {
                        getActivityView().hideLoading();
                    }
                    Gson gson = new Gson();
                    final NetOrderBean netShareBean = gson.fromJson(result, NetOrderBean.class);
                    if (Integer.valueOf(netShareBean.getCode()) == Contetns.STATE_OK) {
                        if (getActivityView() != null) {
                            getActivityView().getOrderListSuccess(netShareBean);
                        }
                    } else {
                        if (getActivityView() != null) {
                            getActivityView().getOrderListError(Integer.valueOf(netShareBean.getCode()), netShareBean.getMsg());
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable error) {
                getActivityView().getOrderListError(-10000, "请求错误");

            }
        });
    }
}
