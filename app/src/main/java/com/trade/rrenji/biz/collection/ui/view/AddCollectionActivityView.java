package com.trade.rrenji.biz.collection.ui.view;

import com.trade.rrenji.bean.collection.NetCollectionBean;
import com.trade.rrenji.biz.base.BaseView;

/**
 * Created by monster on 8/4/18.
 */

public interface AddCollectionActivityView extends BaseView {

    void addCollection(NetCollectionBean netShareBean);

    void addCollectionError(int code, String msg);
}
