package com.trade.rrenji.biz.home.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.trade.rrenji.bean.category.NetCategoryListBean;
import com.trade.rrenji.biz.base.BasePresenter;
import com.trade.rrenji.biz.home.model.HomeModel;
import com.trade.rrenji.biz.home.model.HomeModelImpl;
import com.trade.rrenji.biz.home.ui.view.HomeCategoryActivityView;
import com.trade.rrenji.net.XUtils;

public class HomeCategoryActivityPresenterImpl extends BasePresenter<HomeCategoryActivityView> implements HomeCategoryActivityPresenter {

    private HomeModel mModel;

    Context mContext;

    public HomeCategoryActivityPresenterImpl(Context context) {
        mContext = context;
        mModel = new HomeModelImpl(context);
    }

    @Override
    public void getCategoryDetailById(Context mContext, String categoryId, int pageNum, int rows, int priceSort) {
        mModel.getCategoryDetailById(mContext, categoryId, pageNum, rows, priceSort, new XUtils.ResultListener() {
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
                            getActivityView().getHomeCategorySuccess(netShareBean);
                        }
                    } else {
                        if (getActivityView() != null) {
                            getActivityView().getHomeCategoryError(Integer.valueOf(netShareBean.getCode()), netShareBean.getMsg() + "");
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(Throwable error) {
                getActivityView().getHomeCategoryError(-10000, "请求错误");
            }
        });
    }
}
