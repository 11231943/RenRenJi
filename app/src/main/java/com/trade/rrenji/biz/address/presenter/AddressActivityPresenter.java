package com.trade.rrenji.biz.address.presenter;

import android.content.Context;

import com.trade.rrenji.biz.address.ui.view.AddressActivityView;
import com.trade.rrenji.biz.base.Presenter;

/**
 * Created by zhanghuatao on 2016/9/26.
 */
public interface AddressActivityPresenter extends Presenter<AddressActivityView> {

    void getAddressList(Context mContext, int pageNum);

}
