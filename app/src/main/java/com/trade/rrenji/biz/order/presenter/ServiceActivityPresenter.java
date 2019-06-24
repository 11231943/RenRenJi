package com.trade.rrenji.biz.order.presenter;

import android.content.Context;
import com.trade.rrenji.biz.base.Presenter;
import com.trade.rrenji.biz.order.ui.view.ServiceActivityView;

/**
 * Created by zhanghuatao on 2016/9/26.
 */
public interface ServiceActivityPresenter extends Presenter<ServiceActivityView> {

    void getAfterSaleOrderList(Context mContext, int pageNum);

}
