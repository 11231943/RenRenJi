package com.trade.rrenji.biz.renren.model;

import android.content.Context;

import com.trade.rrenji.biz.base.PlusBaseService;
import com.trade.rrenji.net.XUtils.ResultListener;

public interface RenRenModel extends PlusBaseService {

    void getEveryoneHomeList(Context mContext, int page, int rows, ResultListener resultListener);

}
