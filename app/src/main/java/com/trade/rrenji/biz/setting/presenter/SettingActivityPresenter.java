package com.trade.rrenji.biz.setting.presenter;

import android.content.Context;

import com.trade.rrenji.biz.base.Presenter;
import com.trade.rrenji.biz.tech.ui.view.TechActivityView;

/**
 * Created by zhanghuatao on 2016/9/26.
 */
public interface SettingActivityPresenter extends Presenter<TechActivityView> {

    void getTechList(Context mContext, int pageNum);

}
