package com.trade.rrenji.biz.coupon.presenter;

import android.content.Context;

import com.trade.rrenji.biz.base.Presenter;
import com.trade.rrenji.biz.coupon.ui.view.CouponActivityView;

/**
 * Created by zhanghuatao on 2016/9/26.
 */
public interface CouponActivityPresenter extends Presenter<CouponActivityView> {

    void getCouponList(Context mContext, int pageNum);

}
