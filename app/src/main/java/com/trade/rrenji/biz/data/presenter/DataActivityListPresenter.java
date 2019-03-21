package com.trade.rrenji.biz.data.presenter;

import android.content.Context;

import com.trade.rrenji.biz.data.ui.view.DataActivityListView;
import com.trade.rrenji.biz.base.Presenter;

/**
 * Created by zhanghuatao on 2016/9/26.
 */
public interface DataActivityListPresenter extends Presenter<DataActivityListView> {

    void getHotDataById(Context mContext, String id, int page, int rows);

}
