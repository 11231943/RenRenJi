package com.trade.rrenji.biz.order.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.trade.rrenji.bean.goods.NetAccessoryListBean;
import com.trade.rrenji.bean.order.NetOrderBean;
import com.trade.rrenji.biz.base.BasePresenter;
import com.trade.rrenji.biz.order.model.OrderModel;
import com.trade.rrenji.biz.order.model.OrderModelImpl;
import com.trade.rrenji.biz.order.ui.view.AccessoryInfoView;
import com.trade.rrenji.biz.order.ui.view.OrderActivityView;
import com.trade.rrenji.net.XUtils;
import com.trade.rrenji.utils.Contetns;

public class AccessoryInfoPresenterImpl extends BasePresenter<AccessoryInfoView> implements AccessoryInfoPresenter {

    private OrderModel mModel;

    Context mContext;

    public AccessoryInfoPresenterImpl(Context context) {
        mContext = context;
        mModel = new OrderModelImpl(context);
    }

    @Override
    public void getAccessoryInfo(Context mContext, String goodsCode) {
        mModel.getAccessoryInfo(mContext, goodsCode, new XUtils.ResultListener() {
            @Override
            public void onResponse(String result) {
                try {
                    if (getActivityView() != null) {
                        getActivityView().hideLoading();
                    }
                    Gson gson = new Gson();
                    final NetAccessoryListBean netShareBean = gson.fromJson(result, NetAccessoryListBean.class);
                    if (Integer.valueOf(netShareBean.getCode()) == Contetns.STATE_OK) {
                        if (getActivityView() != null) {
                            getActivityView().getAccessoryInfoSuccess(netShareBean);
                        }
                    } else {
                        if (getActivityView() != null) {
                            getActivityView().getAccessoryInfoError(Integer.valueOf(netShareBean.getCode()), netShareBean.getMsg());
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable error) {
            getActivityView().getAccessoryInfoError(-10000, "请求错误");
            }
        });
    }
}
