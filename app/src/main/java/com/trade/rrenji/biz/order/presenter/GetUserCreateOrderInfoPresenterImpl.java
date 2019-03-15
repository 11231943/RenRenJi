package com.trade.rrenji.biz.order.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.trade.rrenji.bean.order.ContinuePayBean;
import com.trade.rrenji.bean.order.CreateOrderBean;
import com.trade.rrenji.bean.order.NetGetUserCreateOrderBean;
import com.trade.rrenji.bean.order.NetResultCreateOrderBean;
import com.trade.rrenji.biz.base.BasePresenter;
import com.trade.rrenji.biz.order.model.OrderModel;
import com.trade.rrenji.biz.order.model.OrderModelImpl;
import com.trade.rrenji.biz.order.ui.view.GetUserCreateOrderInfoView;
import com.trade.rrenji.net.XUtils;
import com.trade.rrenji.utils.Contetns;

public class GetUserCreateOrderInfoPresenterImpl extends BasePresenter<GetUserCreateOrderInfoView> implements GetUserCreateOrderInfoPresenter {

    private OrderModel mModel;

    Context mContext;

    public GetUserCreateOrderInfoPresenterImpl(Context context) {
        mContext = context;
        mModel = new OrderModelImpl(context);
    }

    @Override
    public void newContinuePay(Context mContext, ContinuePayBean continuePayBean) {
        mModel.continuePay(mContext, continuePayBean, new XUtils.ResultListener() {
            @Override
            public void onResponse(String result) {
                try {
                    if (getActivityView() != null) {
                        getActivityView().hideLoading();
                    }
                    Gson gson = new Gson();
                    final NetResultCreateOrderBean netShareBean = gson.fromJson(result, NetResultCreateOrderBean.class);
                    if (Integer.valueOf(netShareBean.getCode()) == Contetns.STATE_OK) {
                        if (getActivityView() != null) {
                            getActivityView().createOrder(netShareBean);
                        }
                    } else {
                        if (getActivityView() != null) {
                            getActivityView().createOrderError(Integer.valueOf(netShareBean.getCode()), netShareBean.getMsg());
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(Throwable error) {
                getActivityView().createOrderError(-10000, "请求错误");
            }
        });

    }

    @Override
    public void createOrder(Context mContext, CreateOrderBean bean) {
        mModel.createOrder(mContext, bean, new XUtils.ResultListener() {
            @Override
            public void onResponse(String result) {
                try {
                    if (getActivityView() != null) {
                        getActivityView().hideLoading();
                    }
                    Gson gson = new Gson();
                    final NetResultCreateOrderBean netShareBean = gson.fromJson(result, NetResultCreateOrderBean.class);
                    if (Integer.valueOf(netShareBean.getCode()) == Contetns.STATE_OK) {
                        if (getActivityView() != null) {
                            getActivityView().createOrder(netShareBean);
                        }
                    } else {
                        if (getActivityView() != null) {
                            getActivityView().createOrderError(Integer.valueOf(netShareBean.getCode()), netShareBean.getMsg());
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(Throwable error) {
                getActivityView().createOrderError(-10000, "请求错误");
            }
        });
    }

    @Override
    public void getUserCreateOrderInfoByUserId(Context mContext) {
        mModel.getUserCreateOrderInfoByUserId(mContext, new XUtils.ResultListener() {
            @Override
            public void onResponse(String result) {
                try {
                    if (getActivityView() != null) {
                        getActivityView().hideLoading();
                    }
                    Gson gson = new Gson();
                    final NetGetUserCreateOrderBean netShareBean = gson.fromJson(result, NetGetUserCreateOrderBean.class);
                    if (Integer.valueOf(netShareBean.getCode()) == Contetns.STATE_OK) {
                        if (getActivityView() != null) {
                            getActivityView().getUserCreateOrderInfoByUserIdSuccess(netShareBean);
                        }
                    } else {
                        if (getActivityView() != null) {
                            getActivityView().getUserCreateOrderInfoByUserIdError(Integer.valueOf(netShareBean.getCode()), netShareBean.getMsg());
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable error) {
                getActivityView().getUserCreateOrderInfoByUserIdError(-10000, "请求错误");
            }
        });
    }

}
