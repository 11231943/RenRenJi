package com.trade.rrenji.biz.goods.model;

import android.content.Context;

import com.trade.rrenji.biz.base.PlusBaseService;
import com.trade.rrenji.net.XUtils.ResultListener;

/**
 * Created by monster on 8/4/18.
 */

public interface GoodsModel extends PlusBaseService {

    void getGoodsDetail(Context mContext, String goodsCode, ResultListener resultListener);

    void getReplyComment(Context mContext,String goodsCode,int currentPage,ResultListener resultListener);

}
