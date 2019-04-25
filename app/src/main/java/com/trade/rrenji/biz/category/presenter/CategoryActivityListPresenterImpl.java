package com.trade.rrenji.biz.category.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.trade.rrenji.bean.category.NetCategoryListBean;
import com.trade.rrenji.bean.category.NetScreenBean;
import com.trade.rrenji.bean.category.NetScreenListBean;
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
    public void getAttributeProductList(Context mContext, int page, String memory, String color, String network, String condition, String version) {
        mModel.getAttributeProductList(mContext, page, memory, color, network, condition, version, new XUtils.ResultListener() {
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
    public void getClassifyDataByType(final Context mContext, final String id, final String type, int page, int rows) {
        mModel.getClassifyDataByType(mContext, id, type, page, rows, new XUtils.ResultListener() {
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
