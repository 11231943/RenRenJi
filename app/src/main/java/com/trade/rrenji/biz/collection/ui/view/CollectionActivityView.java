package com.trade.rrenji.biz.collection.ui.view;

import com.trade.rrenji.bean.collection.NetCollectionListBean;
import com.trade.rrenji.biz.base.BaseView;
import com.trade.rrenji.biz.base.NetBaseResultBean;

/**
 * Created by monster on 8/4/18.
 */

public interface CollectionActivityView extends BaseView {

    void getCollectionListSuccess(NetCollectionListBean netShareBean);

    void getCollectionListError(int code, String msg);

    void delCollectionSuccess(NetBaseResultBean netBaseResultBean);

    void delCollectionError(int code, String msg);
}
