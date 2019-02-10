package com.trade.rrenji.biz.address.presenter;

import android.content.Context;

import com.trade.rrenji.bean.address.UserAddressCurd;
import com.trade.rrenji.biz.account.ui.view.LoginActivityView;
import com.trade.rrenji.biz.address.ui.view.AddressActivityView;
import com.trade.rrenji.biz.base.Presenter;
import com.trade.rrenji.biz.drying.ui.view.DryActivityView;

/**
 * Created by zhanghuatao on 2016/9/26.
 */
public interface AddressActivityPresenter extends Presenter<AddressActivityView> {

    void getAddressList(Context mContext, int pageNum);

}
