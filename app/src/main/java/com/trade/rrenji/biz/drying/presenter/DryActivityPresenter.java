package com.trade.rrenji.biz.drying.presenter;

import android.content.Context;

import com.trade.rrenji.biz.base.Presenter;
import com.trade.rrenji.biz.drying.ui.view.DryActivityView;

/**
 * Created by zhanghuatao on 2016/9/26.
 */
public interface DryActivityPresenter extends Presenter<DryActivityView> {

    void getDryingList(Context mContext, int pageNum);

}
