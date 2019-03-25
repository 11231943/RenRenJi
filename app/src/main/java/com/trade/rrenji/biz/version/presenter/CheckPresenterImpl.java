package com.trade.rrenji.biz.version.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.trade.rrenji.bean.check.NetCheckBean;
import com.trade.rrenji.biz.base.BasePresenter;
import com.trade.rrenji.biz.version.model.CheckModel;
import com.trade.rrenji.biz.version.model.CheckModelImpl;
import com.trade.rrenji.biz.version.ui.view.CheckActivityView;
import com.trade.rrenji.net.XUtils;
import com.trade.rrenji.utils.Contetns;

public class CheckPresenterImpl extends BasePresenter<CheckActivityView> implements CheckPresenter {

    private CheckModel mModel;

    Context mContext;

    public CheckPresenterImpl(Context context) {
        mContext = context;
        mModel = new CheckModelImpl(context);
    }

    @Override
    public void getCheck(Context mContext) {
        mModel.check(mContext, new XUtils.ResultListener() {
            @Override
            public void onResponse(String result) {
                try {
                    if (getActivityView() != null) {
                        getActivityView().hideLoading();
                    }
                    Gson gson = new Gson();
                    final NetCheckBean netShareBean = gson.fromJson(result, NetCheckBean.class);
                    if (Integer.valueOf(netShareBean.getCode()) == Contetns.STATE_OK) {
                        if (getActivityView() != null) {
                            getActivityView().getCheckSuccess(netShareBean);
                        }
                    } else {
                        if (getActivityView() != null) {
                            getActivityView().getCheckError(Integer.valueOf(netShareBean.getCode()), netShareBean.getMsg());
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable error) {
                getActivityView().getCheckError(-10000, "请求错误");

            }
        });
    }
}
