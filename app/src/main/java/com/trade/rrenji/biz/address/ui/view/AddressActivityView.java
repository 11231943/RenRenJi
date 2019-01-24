package com.trade.rrenji.biz.address.ui.view;

import com.trade.rrenji.bean.address.NetAddressBean;
import com.trade.rrenji.bean.drying.NetShareBean;
import com.trade.rrenji.biz.base.BaseView;

/**
 * Created by monster on 8/4/18.
 */

public interface AddressActivityView extends BaseView {

    void getAddressListSuccess(NetAddressBean netShareBean);

    void getAddressListError(int code, String msg);
}
