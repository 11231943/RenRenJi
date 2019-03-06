package com.trade.rrenji.biz.address.presenter;

import android.content.Context;

import com.trade.rrenji.biz.address.ui.view.DelAddressActivityView;
import com.trade.rrenji.biz.base.Presenter;

/**
 * Created by zhanghuatao on 2016/9/26.
 */
public interface DelAddressActivityPresenter extends Presenter<DelAddressActivityView> {

    void delAddressList(Context mContext, String addressId);

    void isUpAddress(Context mContext, String addressId);
}
