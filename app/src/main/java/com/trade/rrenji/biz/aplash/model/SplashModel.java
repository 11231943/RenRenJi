package com.trade.rrenji.biz.aplash.model;

import android.content.Context;

import com.trade.rrenji.biz.base.PlusBaseService;
import com.trade.rrenji.net.XUtils.ResultListener;

/**
 * Created by monster on 8/4/18.
 */

public interface SplashModel extends PlusBaseService {

    void getFirstAppPicList(Context mContext, ResultListener resultListener);

}
