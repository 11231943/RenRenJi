package com.trade.rrenji.biz.collection.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.trade.rrenji.bean.collection.NetCollectionListBean;
import com.trade.rrenji.biz.base.BasePresenter;
import com.trade.rrenji.biz.collection.model.CollectionModel;
import com.trade.rrenji.biz.collection.model.CollectionModelImpl;
import com.trade.rrenji.biz.collection.ui.view.CollectionActivityView;
import com.trade.rrenji.net.XUtils;
import com.trade.rrenji.utils.Contetns;

/**
 * Created by monster on 8/4/18.
 */

public class CollectionActivityPresenterImpl extends BasePresenter<CollectionActivityView> implements CollectionActivityPresenter {

    private CollectionModel mModel;

    Context mContext;

    public CollectionActivityPresenterImpl(Context context) {
        mContext = context;
        mModel = new CollectionModelImpl(context);
    }

    @Override
    public void getCollectionList(Context mContext, int currentPage) {
        mModel.getCollectionList(mContext, currentPage, new XUtils.ResultListener() {
            @Override
            public void onResponse(String result) {
                try {
                    if (getActivityView() != null) {
                        getActivityView().hideLoading();
                    }
                    Gson gson = new Gson();
                    final NetCollectionListBean netShareBean = gson.fromJson(result, NetCollectionListBean.class);
                    if (Integer.valueOf(netShareBean.getCode()) == Contetns.STATE_OK) {
                        if (getActivityView() != null) {
                            getActivityView().getCollectionListSuccess(netShareBean);
                        }
                    } else {
                        if (getActivityView() != null) {
                            getActivityView().getCollectionListError(Integer.valueOf(netShareBean.getCode()), netShareBean.getMsg());
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable error) {
                getActivityView().getCollectionListError(-10000, "请求错误");
            }
        });
    }

}
