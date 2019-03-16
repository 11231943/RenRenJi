package com.trade.rrenji.biz.upload.ui.view;

import com.trade.rrenji.bean.tech.NetTechBean;
import com.trade.rrenji.biz.base.BaseView;

/**
 * Created by monster on 8/4/18.
 */

public interface TechActivityView extends BaseView {

    void getTechSuccess(NetTechBean netShareBean);

    void getTechError(int code, String msg);
}
