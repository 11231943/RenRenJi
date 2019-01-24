package com.trade.rrenji.biz.goods.ui.view;

import com.trade.rrenji.bean.goods.NetGoodsDetailBean;
import com.trade.rrenji.biz.base.BaseView;

/**
 * Created by monster on 8/4/18.
 */

public interface GoodsActivityView extends BaseView {

    void getGoodsDetail(NetGoodsDetailBean netGoodsDetailBean);

    void getGoodsDetailError(int code, String msg);
}
