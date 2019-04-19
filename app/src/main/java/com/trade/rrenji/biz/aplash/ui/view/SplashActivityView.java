package com.trade.rrenji.biz.aplash.ui.view;

import com.trade.rrenji.bean.splash.NetSplashBean;
import com.trade.rrenji.biz.base.BaseView;

/**
 * Created by monster on 8/4/18.
 */

public interface SplashActivityView extends BaseView {

    void getFirstAppPicList(NetSplashBean netShareBean);

    void getFirstAppPicListError(int code, String msg);
}
