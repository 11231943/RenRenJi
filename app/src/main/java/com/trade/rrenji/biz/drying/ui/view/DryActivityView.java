package com.trade.rrenji.biz.drying.ui.view;

import com.trade.rrenji.bean.drying.NetShareBean;
import com.trade.rrenji.biz.base.BaseView;

/**
 * Created by monster on 8/4/18.
 */

public interface DryActivityView extends BaseView {

    void getDryingSuccess(NetShareBean netShareBean);

    void getDryingCodeError(int code, String msg);
}
