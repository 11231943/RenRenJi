package com.trade.rrenji.biz.upload.model;

import android.content.Context;

import com.trade.rrenji.biz.base.PlusBaseService;
import com.trade.rrenji.net.XUtils.ResultListener;

public interface TechModel extends PlusBaseService {

    void getTechList(Context mContext, int pageNum, ResultListener resultListener);

}
