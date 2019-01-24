package com.trade.rrenji.biz.coupon.model;

import android.content.Context;

import com.trade.rrenji.biz.base.PlusBaseService;
import com.trade.rrenji.net.XUtils.ResultListener;

/**
 * Created by monster on 8/4/18.
 */

public interface CouponModel extends PlusBaseService {

    void getCouponList(Context mContext, int pageNum, ResultListener resultListener);

}
