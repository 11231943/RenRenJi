package com.trade.rrenji.biz.category.model;

import android.content.Context;

import com.trade.rrenji.biz.base.PlusBaseService;
import com.trade.rrenji.net.XUtils.ResultListener;

public interface CategoryModel extends PlusBaseService {

    void getCategoryLeft(Context mContext, ResultListener resultListener);

    void getClassifyDataByType(Context mContext, String id, String type, ResultListener resultListener);

    void getModelAttr(Context mContext, String id, ResultListener resultListener);

    void getAttributeProductList(Context mContext, int page, String memory, String color, String network, String condition, String version, ResultListener resultListener);


}
