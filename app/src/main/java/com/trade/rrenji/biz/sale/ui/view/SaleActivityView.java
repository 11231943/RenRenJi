package com.trade.rrenji.biz.sale.ui.view;

import com.trade.rrenji.bean.address.NetAddressBean;
import com.trade.rrenji.biz.base.BaseView;

/**
 * Created by monster on 8/4/18.
 */

public interface SaleActivityView extends BaseView {

    void getCollectionList(NetAddressBean netShareBean);

    void getCollectionListError(int code, String msg);
}
