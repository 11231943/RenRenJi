package com.trade.rrenji.biz.search.ui.view;

import com.trade.rrenji.bean.address.NetAddressBean;
import com.trade.rrenji.biz.base.BaseView;

/**
 * Created by monster on 8/4/18.
 */

public interface SearchActivityView extends BaseView {

    void getCollectionList(NetAddressBean netShareBean);

    void getCollectionListError(int code, String msg);
}
