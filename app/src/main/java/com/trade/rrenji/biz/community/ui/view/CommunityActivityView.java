package com.trade.rrenji.biz.community.ui.view;

import com.trade.rrenji.bean.communty.NetCommuntyBean;
import com.trade.rrenji.biz.base.BaseView;

/**
 * Created by monster on 8/4/18.
 */

public interface CommunityActivityView extends BaseView {

    void getEveryoneCommunityListSuccess(NetCommuntyBean netShareBean);

    void getEveryoneCommunityListError(int code, String msg);
}
