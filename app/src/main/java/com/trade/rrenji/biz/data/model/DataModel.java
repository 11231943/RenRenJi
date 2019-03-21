package com.trade.rrenji.biz.data.model;

import android.content.Context;

import com.trade.rrenji.biz.base.PlusBaseService;
import com.trade.rrenji.net.XUtils.ResultListener;

public interface DataModel extends PlusBaseService {

    void getHotDataById(Context mContext, String id, int page, int rows, ResultListener resultListener);
}
