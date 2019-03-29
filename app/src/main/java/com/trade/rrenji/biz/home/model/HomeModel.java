package com.trade.rrenji.biz.home.model;

import android.content.Context;

import com.trade.rrenji.biz.base.PlusBaseService;
import com.trade.rrenji.net.XUtils.ResultListener;

/**
 * Created by monster on 8/4/18.
 */

public interface HomeModel extends PlusBaseService {

    void getHomeList(Context mContext, int pageNum, ResultListener resultListener);

    void getCategoryDetailById(Context mContext, String categoryId, int pageNum, int rows, ResultListener resultListener);

}
