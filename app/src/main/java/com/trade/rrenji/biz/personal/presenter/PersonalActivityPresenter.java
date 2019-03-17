package com.trade.rrenji.biz.personal.presenter;

import android.content.Context;

import com.trade.rrenji.biz.base.Presenter;
import com.trade.rrenji.biz.personal.ui.view.PersonalActivityView;
import com.trade.rrenji.biz.sale.ui.view.SaleActivityView;

/**
 * Created by zhanghuatao on 2016/9/26.
 */
public interface PersonalActivityPresenter extends Presenter<PersonalActivityView> {

    void getUserPersonalInfo(Context mContext, String friendId, int page, int rows);

}
