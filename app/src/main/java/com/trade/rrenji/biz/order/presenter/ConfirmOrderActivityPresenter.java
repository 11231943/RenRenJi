package com.trade.rrenji.biz.order.presenter;

import android.content.Context;

import com.trade.rrenji.biz.base.Presenter;
import com.trade.rrenji.biz.order.ui.view.ConfirmActivityView;

/**
 * Created by zhanghuatao on 2016/9/26.
 */
public interface ConfirmOrderActivityPresenter extends Presenter<ConfirmActivityView> {

    void confirmOrder(Context mContext, int type, String goodsId);

}
