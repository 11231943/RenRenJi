package com.trade.rrenji.biz.order.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.trade.rrenji.biz.base.BasePresenter;
import com.trade.rrenji.biz.base.NetBaseResultBean;
import com.trade.rrenji.biz.order.model.OrderModel;
import com.trade.rrenji.biz.order.model.OrderModelImpl;
import com.trade.rrenji.biz.order.ui.view.ConfirmActivityView;
import com.trade.rrenji.net.XUtils;
import com.trade.rrenji.utils.Contetns;

public class ConfirmOrderActivityPresenterImpl extends BasePresenter<ConfirmActivityView> implements ConfirmOrderActivityPresenter {

    private OrderModel mModel;

    Context mContext;

    public ConfirmOrderActivityPresenterImpl(Context context) {
        mContext = context;
        mModel = new OrderModelImpl(context);
    }

    @Override
    public void confirmOrder(Context mContext, int type, String goodsId) {
        mModel.confirmOrder(mContext, type, goodsId, new XUtils.ResultListener() {
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
                            getActivityView().getConfirmOrderSuccess(netShareBean);
                        }
                    } else {
                        if (getActivityView() != null) {
                            getActivityView().getConfirmOrderError(Integer.valueOf(netShareBean.getCode()), netShareBean.getMsg());
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    getActivityView().getConfirmOrderError(-10000, "请求错误");
                }
            }

            @Override
            public void onError(Throwable error) {
                getActivityView().getConfirmOrderError(-10000, "请求错误");
            }
        });
    }
}
