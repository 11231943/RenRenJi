package com.trade.rrenji.biz.home.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.trade.rrenji.bean.category.NetCategoryListBean;
import com.trade.rrenji.biz.base.BasePresenter;
import com.trade.rrenji.biz.home.model.HomeModel;
import com.trade.rrenji.biz.home.model.HomeModelImpl;
import com.trade.rrenji.biz.home.ui.view.HomeCategoryActivityView;
import com.trade.rrenji.biz.home.ui.view.HomeRecommendActivityView;
import com.trade.rrenji.net.XUtils;

public class HomeRecommendActivityPresenterImpl extends BasePresenter<HomeRecommendActivityView> implements HomeRecommendActivityPresenter {

    private HomeModel mModel;

    Context mContext;

    public HomeRecommendActivityPresenterImpl(Context context) {
        mContext = context;
        mModel = new HomeModelImpl(context);
    }

    @Override
    public void getNewHomeGoodsMoreByTypes(Context mContext, int type, int pageNum, int rows) {
        mModel.getNewHomeGoodsMoreByTypes(mContext, type, pageNum, rows, new XUtils.ResultListener() {
            @Override
            public void onResponse(String result) {
                try {
                    if (getActivityView() != null) {
                        getActivityView().hideLoading();
                    }
                    Gson gson = new Gson();
                    final NetCategoryListBean netShareBean = gson.fromJson(result, NetCategoryListBean.class);
                    if (Integer.valueOf(netShareBean.getCode()) == 0) {
                        if (getActivityView() != null) {
                            getActivityView().getNewHomeGoodsMoreByTypesSuccess(netShareBean);
                        }
                    } else {
                        if (getActivityView() != null) {
                            getActivityView().getNewHomeGoodsMoreByTypesError(Integer.valueOf(netShareBean.getCode()), netShareBean.getMsg() + "");
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(Throwable error) {
                getActivityView().getNewHomeGoodsMoreByTypesError(-10000, "请求错误");
            }
        });
    }
}
