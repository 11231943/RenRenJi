package com.trade.rrenji.biz.aplash.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.trade.rrenji.bean.splash.NetSplashBean;
import com.trade.rrenji.biz.aplash.model.SplashModel;
import com.trade.rrenji.biz.aplash.model.SplashModelImpl;
import com.trade.rrenji.biz.aplash.ui.view.SplashActivityView;
import com.trade.rrenji.biz.base.BasePresenter;
import com.trade.rrenji.net.XUtils;
import com.trade.rrenji.utils.Contetns;

/**
 * Created by monster on 8/4/18.
 */

public class SplashActivityPresenterImpl extends BasePresenter<SplashActivityView> implements SplashActivityPresenter {

    private SplashModel mModel;

    Context mContext;

    public SplashActivityPresenterImpl(Context context) {
        mContext = context;
        mModel = new SplashModelImpl(context);
    }

    @Override
    public void getFirstAppPicList(Context mContext) {
        mModel.getFirstAppPicList(mContext, new XUtils.ResultListener() {
            @Override
            public void onResponse(String result) {
                try {
                    if (getActivityView() != null) {
                        getActivityView().hideLoading();
                    }
                    Gson gson = new Gson();
                    final NetSplashBean netShareBean = gson.fromJson(result, NetSplashBean.class);
                    if (Integer.valueOf(netShareBean.getCode()) == Contetns.STATE_OK) {
                        if (getActivityView() != null) {
                            getActivityView().getFirstAppPicList(netShareBean);
                        }
                    } else {
                        if (getActivityView() != null) {
                            getActivityView().getFirstAppPicListError(Integer.valueOf(netShareBean.getCode()), netShareBean.getMsg());
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable error) {
                getActivityView().getFirstAppPicListError(-10000, "请求错误");
            }
        });
    }
}
