package com.trade.rrenji.biz.community.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.trade.rrenji.bean.communty.NetCommuntyBean;
import com.trade.rrenji.biz.base.BasePresenter;
import com.trade.rrenji.biz.community.model.CommunityModel;
import com.trade.rrenji.biz.community.model.CommunityModelImpl;
import com.trade.rrenji.biz.community.ui.view.CommunityActivityView;
import com.trade.rrenji.net.XUtils;

public class CommunityActivityPresenterImpl extends BasePresenter<CommunityActivityView> implements CommunityActivityPresenter {

    private CommunityModel mModel;

    Context mContext;

    public CommunityActivityPresenterImpl(Context context) {
        mContext = context;
        mModel = new CommunityModelImpl(context);
    }

    @Override
    public void getEveryoneCommunityList(Context mContext, int page, int rows) {
        mModel.getEveryoneCommunityList(mContext, page, rows, new XUtils.ResultListener() {
            @Override
            public void onResponse(String result) {
                try {
                    if (getActivityView() != null) {
                        getActivityView().hideLoading();
                    }
                    Gson gson = new Gson();
                    final NetCommuntyBean netShareBean = gson.fromJson(result, NetCommuntyBean.class);
                    if (Integer.valueOf(netShareBean.getCode()) == 0) {
                        if (getActivityView() != null) {
                            getActivityView().getEveryoneCommunityListSuccess(netShareBean);
                        }
                    } else {
                        if (getActivityView() != null) {
                            getActivityView().getEveryoneCommunityListError(Integer.valueOf(netShareBean.getCode()), netShareBean.getMsg() + "");
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable error) {
                getActivityView().getEveryoneCommunityListError(-10000, "请求错误");
            }
        });
    }
}
