package com.trade.rrenji.biz.version.model;

import android.content.Context;

import com.trade.rrenji.biz.base.PlusBaseService;
import com.trade.rrenji.net.XUtils;

public interface CheckModel extends PlusBaseService {

    void check(Context mContext, XUtils.ResultListener resultListener);
}
