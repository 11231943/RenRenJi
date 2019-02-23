package com.trade.rrenji.biz.goods.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.trade.rrenji.biz.base.BasePresenter;
import com.trade.rrenji.biz.goods.model.GoodsModel;
import com.trade.rrenji.biz.goods.model.GoodsModelImpl;
import com.trade.rrenji.biz.goods.ui.view.GoodsActivityView;
import com.trade.rrenji.net.XUtils;
import com.trade.rrenji.bean.goods.NetGoodsDetailBean;
import com.trade.rrenji.utils.Contetns;

/**
 * Created by monster on 8/4/18.
 */

public class GoodsActivityPresenterImpl extends BasePresenter<GoodsActivityView> implements GoodsActivityPresenter {

    private GoodsModel mModel;

    Context mContext;

    public GoodsActivityPresenterImpl(Context context) {
        mContext = context;
        mModel = new GoodsModelImpl(context);
    }

    @Override
    public void getGoodsDetail(Context mContext, String goodsCode) {
        mModel.getGoodsDetail(mContext, goodsCode, new XUtils.ResultListener() {
            @Override
            public void onResponse(String result) {
                try {
                    if (getActivityView() != null) {
                        getActivityView().hideLoading();
                    }
                    Gson gson = new Gson();
                    final NetGoodsDetailBean netShareBean = gson.fromJson(result, NetGoodsDetailBean.class);
                    if (netShareBean.getCode().equals(Contetns.RESPONSE_OK)) {
                        if (getActivityView() != null) {
                            getActivityView().getGoodsDetail(netShareBean);
                        }
                    } else {
                        if (getActivityView() != null) {
                            getActivityView().getGoodsDetailError(Integer.valueOf(netShareBean.getCode()), netShareBean.getMsg());
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable error) {
                getActivityView().getGoodsDetailError(-10000, "请求错误");
            }
        });
    }
}
