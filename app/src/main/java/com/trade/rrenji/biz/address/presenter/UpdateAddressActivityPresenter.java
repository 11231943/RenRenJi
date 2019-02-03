package com.trade.rrenji.biz.address.presenter;

import android.content.Context;

import com.trade.rrenji.bean.address.UserAddressCurd;
import com.trade.rrenji.biz.address.ui.view.UpdateActivityView;
import com.trade.rrenji.biz.base.Presenter;

public interface UpdateAddressActivityPresenter extends Presenter<UpdateActivityView> {
    void updateAddress(Context mContext, int type, UserAddressCurd addressCurd);
}
