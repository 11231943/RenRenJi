package com.trade.rrenji.biz.sale.model;

import android.content.Context;

import com.trade.rrenji.biz.base.PlusBaseService;
import com.trade.rrenji.net.XUtils.ResultListener;

/**
 * Created by monster on 8/4/18.
 */

public interface SaleModel extends PlusBaseService {

    void getCollectionList(Context mContext, int pageNum, ResultListener resultListener);

}
