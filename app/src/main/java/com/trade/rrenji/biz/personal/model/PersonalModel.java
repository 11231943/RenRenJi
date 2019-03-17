package com.trade.rrenji.biz.personal.model;

import android.content.Context;

import com.trade.rrenji.biz.base.PlusBaseService;
import com.trade.rrenji.net.XUtils.ResultListener;

/**
 * Created by monster on 8/4/18.
 */

public interface PersonalModel extends PlusBaseService {

    void getUserPersonalInfo(Context mContext, String friendId, int page, int rows, ResultListener resultListener);

}
