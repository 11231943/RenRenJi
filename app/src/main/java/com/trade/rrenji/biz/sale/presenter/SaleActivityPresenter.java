package com.trade.rrenji.biz.sale.presenter;

import android.content.Context;

import com.trade.rrenji.biz.base.Presenter;
import com.trade.rrenji.biz.sale.ui.view.SaleActivityView;

/**
 * Created by zhanghuatao on 2016/9/26.
 */
public interface SaleActivityPresenter extends Presenter<SaleActivityView> {

    void getCollectionList(Context mContext, int pageNum);

}
