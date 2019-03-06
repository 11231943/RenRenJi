package com.trade.rrenji.biz.address.ui.view;

import com.trade.rrenji.biz.base.BaseView;
import com.trade.rrenji.biz.base.NetBaseResultBean;

/**
 * Created by monster on 8/4/18.
 */

public interface DelAddressActivityView extends BaseView {

    void delAddressListSuccess(NetBaseResultBean netShareBean);

    void delAddressListError(int code, String msg);

    void isUpAddressSuccess(NetBaseResultBean netShareBean);

    void isUpAddressError(int code, String msg);

    void isNotUpAddressSuccess(NetBaseResultBean netShareBean);

    void isNotUpAddressError(int code, String msg);
}
