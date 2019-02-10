package com.trade.rrenji.biz.collection.model;

import android.content.Context;

import com.trade.rrenji.biz.base.PlusBaseService;
import com.trade.rrenji.net.XUtils.ResultListener;

/**
 * Created by monster on 8/4/18.
 */

public interface CollectionModel extends PlusBaseService {

    void getCollectionList(Context mContext, String goodsCode, ResultListener resultListener);

}
