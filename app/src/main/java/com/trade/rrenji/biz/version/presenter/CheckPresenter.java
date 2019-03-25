package com.trade.rrenji.biz.version.presenter;

import android.content.Context;

import com.trade.rrenji.biz.base.Presenter;
import com.trade.rrenji.biz.tech.ui.view.TechActivityView;
import com.trade.rrenji.biz.version.ui.view.CheckActivityView;

public interface CheckPresenter extends Presenter<CheckActivityView> {

    void getCheck(Context mContext);

}
