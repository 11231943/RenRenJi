package com.trade.rrenji.biz.coupon.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.trade.rrenji.bean.coupon.NetCouponBean;
import com.trade.rrenji.biz.base.BasePresenter;
import com.trade.rrenji.biz.coupon.model.CouponModel;
import com.trade.rrenji.biz.coupon.model.CouponModelImpl;
import com.trade.rrenji.biz.coupon.ui.view.CouponActivityView;
import com.trade.rrenji.net.XUtils;
import com.trade.rrenji.utils.Contetns;

/**
 * Created by monster on 8/4/18.
 */

public class CouponActivityPresenterImpl extends BasePresenter<CouponActivityView> implements CouponActivityPresenter {

    private CouponModel mModel;

    Context mContext;

    public CouponActivityPresenterImpl(Context context) {
        mContext = context;
        mModel = new CouponModelImpl(context);
    }

    @Override
    public void getCouponList(Context mContext, int pageNum) {
        mModel.getCouponList(mContext, pageNum, new XUtils.ResultListener() {
            @Override
            public void onResponse(String result) {
                try {
                    if (getActivityView() != null) {
                        getActivityView().hideLoading();
                    }
                    Gson gson = new Gson();
                    final NetCouponBean netShareBean = gson.fromJson(result, NetCouponBean.class);
                    if (Integer.valueOf(netShareBean.getCode()) == Contetns.STATE_OK) {
                        if (getActivityView() != null) {
                            getActivityView().getCouponList(netShareBean);
                        }
                    } else {
                        if (getActivityView() != null) {
                            getActivityView().getCouponListError(Integer.valueOf(netShareBean.getCode()), netShareBean.getMsg());
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable error) {
                getActivityView().getCouponListError(-10000, "请求错误");
            }
        });
    }

}
