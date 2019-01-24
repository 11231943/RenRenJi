package com.trade.rrenji.biz.category.model;

import android.content.Context;

import com.trade.rrenji.biz.base.PlusBaseService;
import com.trade.rrenji.net.XUtils.ResultListener;

public interface CategoryModel extends PlusBaseService {

    void getCategory(Context mContext, ResultListener resultListener);

}
