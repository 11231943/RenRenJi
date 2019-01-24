package com.trade.rrenji.biz.drying.model;

import android.content.Context;

import com.trade.rrenji.biz.base.PlusBaseService;
import com.trade.rrenji.net.XUtils.ResultListener;

public interface DryModel extends PlusBaseService {

    void getDryList(Context mContext, int pageNum, ResultListener resultListener);

}
