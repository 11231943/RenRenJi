package com.trade.rrenji.biz.home.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.trade.rrenji.bean.category.NetCategoryListBean;
import com.trade.rrenji.bean.category.NetScreenBean;
import com.trade.rrenji.bean.category.NetScreenListBean;
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

    @Override
    public void getModelAttr(Context mContext, String id) {
        mModel.getModelAttr(mContext, id, new XUtils.ResultListener() {
            @Override
            public void onResponse(String result) {
                try {
                    if (getActivityView() != null) {
                        getActivityView().hideLoading();
                    }
                    Gson gson = new Gson();
                    final NetScreenBean netShareBean = gson.fromJson(result, NetScreenBean.class);
                    if (Integer.valueOf(netShareBean.getCode()) == 0) {
                        if (getActivityView() != null) {
                            getActivityView().getModelAttrSuccess(netShareBean);
                        }
                    } else {
                        if (getActivityView() != null) {
                            getActivityView().getModelAttrError(Integer.valueOf(netShareBean.getCode()), netShareBean.getMsg() + "");
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    getActivityView().getModelAttrError(-10000, "请求错误");
                }
            }

            @Override
            public void onError(Throwable error) {
                getActivityView().getModelAttrError(-10000, "请求错误");
            }
        });
    }

    @Override
    public void getAttributeProductList(Context mContext, int priceSort, int page, String model, String memory, String color, String network, String condition, String version) {
        mModel.getAttributeProductList(mContext, priceSort, page, model, memory, color, network, condition, version, new XUtils.ResultListener() {
            @Override
            public void onResponse(String result) {
                try {
                    if (getActivityView() != null) {
                        getActivityView().hideLoading();
                    }
                    Gson gson = new Gson();
                    final NetScreenListBean netShareBean = gson.fromJson(result, NetScreenListBean.class);
                    if (Integer.valueOf(netShareBean.getCode()) == 0) {
                        if (getActivityView() != null) {
                            getActivityView().getAttributeProductListSuccess(netShareBean);
                        }
                    } else {
                        if (getActivityView() != null) {
                            getActivityView().getAttributeProductListError(Integer.valueOf(netShareBean.getCode()), netShareBean.getMsg() + "");
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    getActivityView().getAttributeProductListError(-10000, "请求错误");
                }
            }

            @Override
            public void onError(Throwable error) {

            }
        });
    }

}
