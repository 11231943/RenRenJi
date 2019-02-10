package com.trade.rrenji.biz.collection.presenter;

import android.content.Context;

import com.trade.rrenji.biz.base.Presenter;
import com.trade.rrenji.biz.collection.ui.view.CollectionActivityView;

/**
 * Created by zhanghuatao on 2016/9/26.
 */
public interface CollectionActivityPresenter extends Presenter<CollectionActivityView> {

    void getCollectionList(Context mContext, String goodsCode);

}
