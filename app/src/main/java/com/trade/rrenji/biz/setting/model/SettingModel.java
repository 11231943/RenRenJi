package com.trade.rrenji.biz.setting.model;

import android.content.Context;

import com.trade.rrenji.biz.base.PlusBaseService;
import com.trade.rrenji.net.XUtils.ResultListener;

public interface SettingModel extends PlusBaseService {

    void getSystemData(Context mContext, ResultListener resultListener);

}
