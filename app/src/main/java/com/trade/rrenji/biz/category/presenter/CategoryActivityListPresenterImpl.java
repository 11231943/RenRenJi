package com.trade.rrenji.biz.category.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.trade.rrenji.bean.category.NetCategoryListBean;
import com.trade.rrenji.biz.base.BasePresenter;
import com.trade.rrenji.biz.category.model.CategoryModel;
import com.trade.rrenji.biz.category.model.CategoryModelImpl;
import com.trade.rrenji.biz.category.ui.view.CategoryActivityListView;
import com.trade.rrenji.net.XUtils;

public class CategoryActivityListPresenterImpl extends BasePresenter<CategoryActivityListView> implements CategoryActivityListPresenter {

    private CategoryModel mModel;

    Context mContext;

    public CategoryActivityListPresenterImpl(Context context) {
        mContext = context;
        mModel = new CategoryModelImpl(context);
    }

    @Override
    public void getClassifyDataByType(final Context mContext, final String id, final String type) {
        mModel.getClassifyDataByType(mContext, id, type, new XUtils.ResultListener() {
            @Override
            public void onResponse(String result) {

                try {
                    if (getActivityView() != null) {
                        getActivityView().hideLoading();
                    }
                    Gson gson = new Gson();

                    if (type.equals("1")) {
                        final NetCategoryListBean netShareBean = gson.fromJson(result, NetCategoryListBean.class);
                        if (Integer.valueOf(netShareBean.getCode()) == 0) {
                            if (getActivityView() != null) {
                                getActivityView().getCategoryListSuccess(netShareBean);
                            }
                        } else {
                            if (getActivityView() != null) {
                                getActivityView().getCategoryCodeListError(Integer.valueOf(netShareBean.getCode()), netShareBean.getMsg() + "");
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable error) {
                getActivityView().getCategoryCodeListError(-10000, "请求错误");
            }
        });
    }
}
