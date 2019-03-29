package com.trade.rrenji.biz.search.presenter;

import android.content.Context;

import com.trade.rrenji.biz.base.Presenter;
import com.trade.rrenji.biz.search.ui.view.SearchActivityView;

/**
 * Created by zhanghuatao on 2016/9/26.
 */
public interface SearchActivityPresenter extends Presenter<SearchActivityView> {

    void getCollectionList(Context mContext, int pageNum);

}
