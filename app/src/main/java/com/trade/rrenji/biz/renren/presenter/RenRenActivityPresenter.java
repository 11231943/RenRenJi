package com.trade.rrenji.biz.renren.presenter;

import android.content.Context;

import com.trade.rrenji.biz.base.Presenter;
import com.trade.rrenji.biz.renren.ui.view.RenRenActivityView;

/**
 * Created by zhanghuatao on 2016/9/26.
 */
public interface RenRenActivityPresenter extends Presenter<RenRenActivityView> {

    void getEveryoneHomeList(Context mContext, int page, int rows);

}
