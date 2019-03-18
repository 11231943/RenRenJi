package com.trade.rrenji.biz.category.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.trade.rrenji.bean.category.NetCategoryBean;
import com.trade.rrenji.biz.base.BasePresenter;
import com.trade.rrenji.biz.category.model.CategoryModel;
import com.trade.rrenji.biz.category.model.CategoryModelImpl;
import com.trade.rrenji.biz.category.ui.view.CategoryActivityView;
import com.trade.rrenji.net.XUtils;

public class CategoryActivityPresenterImpl extends BasePresenter<CategoryActivityView> implements CategoryActivityPresenter {

    private CategoryModel mModel;

    Context mContext;

    public CategoryActivityPresenterImpl(Context context) {
        mContext = context;
        mModel = new CategoryModelImpl(context);
    }

    @Override
    public void getCategoryLeft(Context mContext) {
        mModel.getCategoryLeft(mContext, new XUtils.ResultListener() {
            @Override
            public void onResponse(String result) {
                try {
                    if (getActivityView() != null) {
                        getActivityView().hideLoading();
                    }
                    Gson gson = new Gson();
                    final NetCategoryBean netShareBean = gson.fromJson(result, NetCategoryBean.class);
                    if (Integer.valueOf(netShareBean.getCode()) == 0) {
                        if (getActivityView() != null) {
                            getActivityView().getCategorySuccess(netShareBean);
                        }
                    } else {
                        if (getActivityView() != null) {
                            getActivityView().getCategoryCodeError(Integer.valueOf(netShareBean.getCode()), netShareBean.getMsg() + "");
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable error) {
                getActivityView().getCategoryCodeError(-10000, "请求错误");

            }
        });
    }
}
