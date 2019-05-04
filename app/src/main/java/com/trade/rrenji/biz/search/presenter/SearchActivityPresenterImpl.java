package com.trade.rrenji.biz.search.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.trade.rrenji.bean.search.NetSearchBean;
import com.trade.rrenji.bean.search.NetSearchValueBean;
import com.trade.rrenji.biz.base.BasePresenter;
import com.trade.rrenji.biz.search.model.SearchModel;
import com.trade.rrenji.biz.search.model.SearchModelImpl;
import com.trade.rrenji.biz.search.ui.view.SearchActivityView;
import com.trade.rrenji.net.XUtils;
import com.trade.rrenji.utils.Contetns;

/**
 * Created by monster on 8/4/18.
 */

public class SearchActivityPresenterImpl extends BasePresenter<SearchActivityView> implements SearchActivityPresenter {

    private SearchModel mModel;

    Context mContext;

    public SearchActivityPresenterImpl(Context context) {
        mContext = context;
        mModel = new SearchModelImpl(context);
    }

    @Override
    public void getHotSearchGoodsNameList(Context mContext) {
        mModel.getHotSearchGoodsNameList(mContext, new XUtils.ResultListener() {
            @Override
            public void onResponse(String result) {
                try {
                    if (getActivityView() != null) {
                        getActivityView().hideLoading();
                    }
                    Gson gson = new Gson();
                    final NetSearchBean netShareBean = gson.fromJson(result, NetSearchBean.class);
                    if (Integer.valueOf(netShareBean.getCode()) == Contetns.STATE_OK) {
                        if (getActivityView() != null) {
                            getActivityView().getHotSearchGoodsNameList(netShareBean);
                        }
                    } else {
                        if (getActivityView() != null) {
                            getActivityView().getHotSearchGoodsNameListError(Integer.valueOf(netShareBean.getCode()), netShareBean.getMsg());
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable error) {
                getActivityView().getHotSearchGoodsNameListError(-10000, "请求错误");
            }
        });
    }

    @Override
    public void getProductListByGoodsName(Context mContext, int page, int rows, int priceSort, String goodsName) {
        mModel.getProductListByGoodsName(mContext, page, rows, priceSort, goodsName, new XUtils.ResultListener() {
            @Override
            public void onResponse(String result) {
                try {
                    if (getActivityView() != null) {
                        getActivityView().hideLoading();
                    }
                    Gson gson = new Gson();
                    final NetSearchValueBean netShareBean = gson.fromJson(result, NetSearchValueBean.class);
                    if (Integer.valueOf(netShareBean.getCode()) == Contetns.STATE_OK) {
                        if (getActivityView() != null) {
                            getActivityView().getProductListByGoodsName(netShareBean);
                        }
                    } else {
                        if (getActivityView() != null) {
                            getActivityView().getProductListByGoodsNameError(Integer.valueOf(netShareBean.getCode()), netShareBean.getMsg());
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable error) {
                getActivityView().getProductListByGoodsNameError(-10000, "请求错误");

            }
        });
    }
}
