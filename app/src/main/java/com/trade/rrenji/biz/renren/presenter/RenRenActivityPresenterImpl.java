package com.trade.rrenji.biz.renren.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.trade.rrenji.bean.communty.NetCommuntyBean;
import com.trade.rrenji.bean.renren.NetRenRebBean;
import com.trade.rrenji.biz.base.BasePresenter;
import com.trade.rrenji.biz.renren.model.RenRenModel;
import com.trade.rrenji.biz.renren.model.RenRenModelImpl;
import com.trade.rrenji.biz.renren.ui.view.RenRenActivityView;
import com.trade.rrenji.net.XUtils;

public class RenRenActivityPresenterImpl extends BasePresenter<RenRenActivityView> implements RenRenActivityPresenter {

    private RenRenModel mModel;

    Context mContext;

    public RenRenActivityPresenterImpl(Context context) {
        mContext = context;
        mModel = new RenRenModelImpl(context);
    }

    @Override
    public void getEveryoneHomeList(Context mContext, int page, int rows) {
        mModel.getEveryoneHomeList(mContext, page, rows, new XUtils.ResultListener() {
            @Override
            public void onResponse(String result) {
                try {
                    if (getActivityView() != null) {
                        getActivityView().hideLoading();
                    }
                    Gson gson = new Gson();
                    final NetRenRebBean netShareBean = gson.fromJson(result, NetRenRebBean.class);
                    if (Integer.valueOf(netShareBean.getCode()) == 0) {
                        if (getActivityView() != null) {
                            getActivityView().getEveryoneHomeListSuccess(netShareBean);
                        }
                    } else {
                        if (getActivityView() != null) {
                            getActivityView().getEveryoneHomeListError(Integer.valueOf(netShareBean.getCode()), netShareBean.getMsg() + "");
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable error) {
                getActivityView().getEveryoneHomeListError(-10000, "请求错误");
            }
        });
    }
}
