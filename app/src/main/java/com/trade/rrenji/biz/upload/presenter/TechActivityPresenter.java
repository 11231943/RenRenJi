package com.trade.rrenji.biz.upload.presenter;

import android.content.Context;

import com.trade.rrenji.biz.base.Presenter;
import com.trade.rrenji.biz.tech.ui.view.TechActivityView;

public interface TechActivityPresenter extends Presenter<TechActivityView> {

    void getTechList(Context mContext, int pageNum);

}
