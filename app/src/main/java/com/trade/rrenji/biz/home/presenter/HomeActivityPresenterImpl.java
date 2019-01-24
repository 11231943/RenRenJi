package com.trade.rrenji.biz.home.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.trade.rrenji.bean.home.NetHomeBean;
import com.trade.rrenji.biz.base.BasePresenter;
import com.trade.rrenji.biz.home.model.HomeModel;
import com.trade.rrenji.biz.home.model.HomeModelImpl;
import com.trade.rrenji.biz.home.ui.view.HomeActivityView;
import com.trade.rrenji.net.XUtils;
import com.trade.rrenji.utils.Contetns;

/**
 * Created by monster on 8/4/18.
 */

public class HomeActivityPresenterImpl extends BasePresenter<HomeActivityView> implements HomeActivityPresenter {

    private HomeModel mModel;

    Context mContext;

    public HomeActivityPresenterImpl(Context context) {
        mContext = context;
        mModel = new HomeModelImpl(context);
    }

    @Override
    public void getHomeList(Context mContext, int pageNum) {
        mModel.getHomeList(mContext, pageNum, new XUtils.ResultListener() {
            @Override
            public void onResponse(String result) {
                try {
                    if (getActivityView() != null) {
                        getActivityView().hideLoading();
                    }
                    Gson gson = new Gson();
                    final NetHomeBean netShareBean = gson.fromJson(result, NetHomeBean.class);
                    if (Integer.valueOf(netShareBean.getCode()) == 200) {
                        if (getActivityView() != null) {
                            getActivityView().getHomeList(netShareBean);
                        }
                    } else {
                        if (getActivityView() != null) {
                            getActivityView().getHomeListError(Integer.valueOf(netShareBean.getCode()), netShareBean.getMsg());
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable error) {
                getActivityView().getHomeListError(-10000, "请求错误");
            }
        });
    }

}
