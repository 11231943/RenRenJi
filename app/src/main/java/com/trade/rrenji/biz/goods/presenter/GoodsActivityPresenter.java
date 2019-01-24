package com.trade.rrenji.biz.goods.presenter;

import android.content.Context;

import com.trade.rrenji.biz.base.Presenter;
import com.trade.rrenji.biz.goods.ui.view.GoodsActivityView;

/**
 * Created by zhanghuatao on 2016/9/26.
 */
public interface GoodsActivityPresenter extends Presenter<GoodsActivityView> {

    void getGoodsDetail(Context mContext, String goodsCode);

}
