package com.trade.rrenji.biz.home.presenter;

import android.content.Context;

import com.trade.rrenji.biz.base.Presenter;
import com.trade.rrenji.biz.home.ui.view.HomeActivityView;

/**
 * Created by zhanghuatao on 2016/9/26.
 */
public interface HomeActivityPresenter extends Presenter<HomeActivityView> {

    void getHomeList(Context mContext, int pageNum);

}
