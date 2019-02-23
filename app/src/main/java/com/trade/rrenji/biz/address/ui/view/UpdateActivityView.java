package com.trade.rrenji.biz.address.ui.view;

import com.trade.rrenji.bean.address.AddressUpdateBean;
import com.trade.rrenji.biz.base.BaseView;

/**
 * Created by monster on 8/4/18.
 */

public interface UpdateActivityView extends BaseView {

    void updateAddressSuccess(AddressUpdateBean updateBean);

    void updateAddressError(int code, String msg);
}
