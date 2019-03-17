package com.trade.rrenji.biz.personal.ui.view;

import com.trade.rrenji.bean.personal.NetPersonalBean;
import com.trade.rrenji.biz.base.BaseView;

/**
 * Created by monster on 8/4/18.
 */

public interface PersonalActivityView extends BaseView {

    void getUserPersonalInfo(NetPersonalBean netShareBean);

    void getUserPersonalInfoError(int code, String msg);
}
