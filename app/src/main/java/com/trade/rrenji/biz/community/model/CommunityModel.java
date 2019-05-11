package com.trade.rrenji.biz.community.model;

import android.content.Context;

import com.trade.rrenji.biz.base.PlusBaseService;
import com.trade.rrenji.net.XUtils.ResultListener;

public interface CommunityModel extends PlusBaseService {

    void getEveryoneCommunityList(Context mContext, int page, int rows, ResultListener resultListener);

}
