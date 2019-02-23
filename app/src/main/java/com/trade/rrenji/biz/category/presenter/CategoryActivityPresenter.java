package com.trade.rrenji.biz.category.presenter;

import android.content.Context;

import com.trade.rrenji.biz.base.Presenter;
import com.trade.rrenji.biz.category.ui.view.CategoryActivityView;

/**
 * Created by zhanghuatao on 2016/9/26.
 */
public interface CategoryActivityPresenter extends Presenter<CategoryActivityView> {

    void getCategory(Context mContext);

}
