package com.trade.rrenji.biz.renren.ui.view;

import com.trade.rrenji.bean.renren.NetRenRebBean;
import com.trade.rrenji.biz.base.BaseView;

/**
 * Created by monster on 8/4/18.
 */

public interface RenRenActivityView extends BaseView {

    void getEveryoneHomeListSuccess(NetRenRebBean netShareBean);

    void getEveryoneHomeListError(int code, String msg);
}
