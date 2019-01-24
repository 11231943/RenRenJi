package com.trade.rrenji.biz.home.ui.view;

import com.trade.rrenji.bean.home.NetHomeBean;
import com.trade.rrenji.biz.base.BaseView;

/**
 * Created by monster on 8/4/18.
 */

public interface HomeActivityView extends BaseView {

    void getHomeList(NetHomeBean mNetHomeBean);

    void getHomeListError(int code, String msg);
}
