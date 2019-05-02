package com.trade.rrenji.biz.setting.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.trade.rrenji.bean.tech.NetTechBean;
import com.trade.rrenji.biz.base.BasePresenter;
import com.trade.rrenji.biz.tech.model.TechModel;
import com.trade.rrenji.biz.tech.model.TechModelImpl;
import com.trade.rrenji.biz.tech.ui.view.TechActivityView;
import com.trade.rrenji.net.XUtils;
import com.trade.rrenji.utils.Contetns;

public class SettingActivityPresenterImpl extends BasePresenter<TechActivityView> implements SettingActivityPresenter {

    private TechModel mModel;

    Context mContext;

    public SettingActivityPresenterImpl(Context context) {
        mContext = context;
        mModel = new TechModelImpl(context);
    }

    @Override
    public void getTechList(Context mContext, int pageNum) {
        mModel.getTechList(mContext, pageNum, new XUtils.ResultListener() {
            @Override
            public void onResponse(String result) {
                try {
                    if (getActivityView() != null) {
                        getActivityView().hideLoading();
                    }
                    Gson gson = new Gson();
                    final NetTechBean netShareBean = gson.fromJson(result, NetTechBean.class);
                    if (Integer.valueOf(netShareBean.getCode()) == Contetns.STATE_OK) {
                        if (getActivityView() != null) {
                            getActivityView().getTechSuccess(netShareBean);
                        }
                    } else {
                        if (getActivityView() != null) {
                            getActivityView().getTechError(Integer.valueOf(netShareBean.getCode()), netShareBean.getMsg());
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable error) {
                getActivityView().getTechError(-10000, "请求错误");

            }
        });
    }
}
