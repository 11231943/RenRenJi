package com.trade.rrenji.biz.home.presenter;

import android.content.Context;

import com.trade.rrenji.biz.base.Presenter;
import com.trade.rrenji.biz.home.ui.view.HomeCategoryActivityView;

/**
 * Created by zhanghuatao on 2016/9/26.
 */
public interface HomeCategoryActivityPresenter extends Presenter<HomeCategoryActivityView> {

    void getCategoryDetailById(Context mContext, String categoryId, int pageNum, int rows, int priceSort);

    void getModelAttr(Context mContext, String id);

    void getAttributeProductList(Context mContext,  int priceSort,int page, String model, String memory, String color, String network, String condition, String version);

}
