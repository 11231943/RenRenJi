package com.trade.rrenji.biz.order.presenter;

import android.content.Context;

import com.trade.rrenji.biz.base.Presenter;
import com.trade.rrenji.biz.order.ui.view.AccessoryInfoView;
import com.trade.rrenji.biz.order.ui.view.OrderActivityView;

/**
 * Created by zhanghuatao on 2016/9/26.
 */
public interface AccessoryInfoPresenter extends Presenter<AccessoryInfoView> {

    void getAccessoryInfo(Context mContext,String goodsCode);

}
