package com.trade.rrenji.biz.coupon.ui.view;

import com.trade.rrenji.bean.address.NetAddressBean;
import com.trade.rrenji.biz.base.BaseView;

/**
 * Created by monster on 8/4/18.
 */

public interface CouponActivityView extends BaseView {

    void getCouponList(NetAddressBean netShareBean);

    void getCouponListError(int code, String msg);
}
