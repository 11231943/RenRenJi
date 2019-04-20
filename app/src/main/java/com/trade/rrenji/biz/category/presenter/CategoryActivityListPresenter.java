package com.trade.rrenji.biz.category.presenter;

import android.content.Context;

import com.trade.rrenji.biz.base.Presenter;
import com.trade.rrenji.biz.category.ui.view.CategoryActivityListView;
import com.trade.rrenji.biz.category.ui.view.CategoryActivityView;

/**
 * Created by zhanghuatao on 2016/9/26.
 */
public interface CategoryActivityListPresenter extends Presenter<CategoryActivityListView> {

    void getClassifyDataByType(Context mContext, String id, String type);

    void getModelAttr(Context mContext, String id);

    void getAttributeProductList(Context mContext, int page, String memory, String color, String network, String condition, String version);
}
