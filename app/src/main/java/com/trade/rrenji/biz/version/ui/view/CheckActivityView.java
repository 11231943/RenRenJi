package com.trade.rrenji.biz.version.ui.view;

import com.trade.rrenji.bean.check.NetCheckBean;
import com.trade.rrenji.biz.base.BaseView;

/**
 * Created by monster on 8/4/18.
 */

public interface CheckActivityView extends BaseView {

    void getCheckSuccess(NetCheckBean netShareBean);

    void getCheckError(int code, String msg);
}
