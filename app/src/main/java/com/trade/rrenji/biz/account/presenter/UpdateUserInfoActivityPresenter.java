package com.trade.rrenji.biz.account.presenter;

import android.content.Context;

import com.trade.rrenji.biz.account.ui.view.LoginActivityView;
import com.trade.rrenji.biz.account.ui.view.UpdateUserInfoActivityView;
import com.trade.rrenji.biz.base.Presenter;
import com.trade.rrenji.net.XUtils;

/**
 * Created by zhanghuatao on 2016/9/26.
 */
public interface UpdateUserInfoActivityPresenter extends Presenter<UpdateUserInfoActivityView> {

    void updateUserInfo(Context mContext, String headImg, String name, String sex, String address);

}
