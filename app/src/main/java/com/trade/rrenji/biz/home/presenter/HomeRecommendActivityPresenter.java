package com.trade.rrenji.biz.home.presenter;

import android.content.Context;

import com.trade.rrenji.biz.base.Presenter;
import com.trade.rrenji.biz.home.ui.view.HomeCategoryActivityView;
import com.trade.rrenji.biz.home.ui.view.HomeRecommendActivityView;

/**
 * Created by zhanghuatao on 2016/9/26.
 */
public interface HomeRecommendActivityPresenter extends Presenter<HomeRecommendActivityView> {

    void getNewHomeGoodsMoreByTypes(Context mContext, int type, int pageNum, int rows);

}
