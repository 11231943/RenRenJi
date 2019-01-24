package com.trade.rrenji.biz.drying.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.trade.rrenji.bean.account.LoginJsonBean;
import com.trade.rrenji.bean.drying.NetShareBean;
import com.trade.rrenji.biz.base.BasePresenter;
import com.trade.rrenji.biz.drying.model.DryModel;
import com.trade.rrenji.biz.drying.model.DryModelImpl;
import com.trade.rrenji.biz.drying.ui.view.DryActivityView;
import com.trade.rrenji.net.XUtils;
import com.trade.rrenji.utils.Contetns;

public class DryActivityPresenterImpl extends BasePresenter<DryActivityView> implements DryActivityPresenter {

    private DryModel mModel;

    Context mContext;

    public DryActivityPresenterImpl(Context context) {
        mContext = context;
        mModel = new DryModelImpl(context);
    }

    @Override
    public void getDryingList(Context mContext, int pageNum) {
        mModel.getDryList(mContext, pageNum, new XUtils.ResultListener() {
            @Override
            public void onResponse(String result) {
                try {
                    if (getActivityView() != null) {
                        getActivityView().hideLoading();
                    }
                    Gson gson = new Gson();
                    final NetShareBean netShareBean = gson.fromJson(result, NetShareBean.class);
                    if (Integer.valueOf(netShareBean.getCode()) == Contetns.STATE_OK) {
                        if (getActivityView() != null) {
                            getActivityView().getDryingSuccess(netShareBean);
                        }
                    } else {
                        if (getActivityView() != null) {
                            getActivityView().getDryingCodeError(Integer.valueOf(netShareBean.getCode()), netShareBean.getMsg());
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable error) {
                getActivityView().getDryingCodeError(-10000, "请求错误");

            }
        });
    }
}
