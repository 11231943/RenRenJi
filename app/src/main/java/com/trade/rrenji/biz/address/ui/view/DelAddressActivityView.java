package com.trade.rrenji.biz.address.ui.view;

import com.trade.rrenji.bean.address.DelAdressBean;
import com.trade.rrenji.biz.base.BaseView;

/**
 * Created by monster on 8/4/18.
 */

public interface DelAddressActivityView extends BaseView {

    void delAddressListSuccess(DelAdressBean netShareBean);

    void delAddressListError(int code, String msg);

}
