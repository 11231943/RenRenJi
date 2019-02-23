package com.trade.rrenji.biz.collection.ui.view;

import com.trade.rrenji.biz.base.BaseView;
import com.trade.rrenji.biz.base.NetBaseResultBean;

/**
 * Created by monster on 8/4/18.
 */

public interface DelCollectionActivityView extends BaseView {

    void deleteCollection(NetBaseResultBean netShareBean);

    void deleteCollectionError(int code, String msg);
}
