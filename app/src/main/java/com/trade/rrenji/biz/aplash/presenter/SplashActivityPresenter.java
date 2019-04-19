package com.trade.rrenji.biz.aplash.presenter;

import android.content.Context;

import com.trade.rrenji.biz.aplash.ui.view.SplashActivityView;
import com.trade.rrenji.biz.base.Presenter;
import com.trade.rrenji.biz.sale.ui.view.SaleActivityView;

/**
 * Created by zhanghuatao on 2016/9/26.
 */
public interface SplashActivityPresenter extends Presenter<SplashActivityView> {

    void getFirstAppPicList(Context mContext);

}
