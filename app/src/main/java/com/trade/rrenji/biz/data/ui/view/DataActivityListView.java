package com.trade.rrenji.biz.data.ui.view;

import com.trade.rrenji.bean.home.NetHotDataBean;
import com.trade.rrenji.biz.base.BaseView;

/**
 * Created by monster on 8/4/18.
 */

public interface DataActivityListView extends BaseView {

    void getHotListSuccess(NetHotDataBean netShareBean);

    void getHotListError(int code, String msg);
}
