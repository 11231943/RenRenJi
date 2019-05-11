package com.trade.rrenji.biz.community.presenter;

import android.content.Context;

import com.trade.rrenji.biz.base.Presenter;
import com.trade.rrenji.biz.community.ui.view.CommunityActivityView;

/**
 * Created by zhanghuatao on 2016/9/26.
 */
public interface CommunityActivityPresenter extends Presenter<CommunityActivityView> {

    void getEveryoneCommunityList(Context mContext, int page, int rows);

}
