package com.trade.rrenji.biz.collection.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.trade.rrenji.bean.collection.NetCollectionBean;
import com.trade.rrenji.biz.base.BasePresenter;
import com.trade.rrenji.biz.collection.model.CollectionModel;
import com.trade.rrenji.biz.collection.model.CollectionModelImpl;
import com.trade.rrenji.biz.collection.ui.view.AddCollectionActivityView;
import com.trade.rrenji.net.XUtils;
import com.trade.rrenji.utils.Contetns;

/**
 * Created by monster on 8/4/18.
 */

public class AddCollectionActivityPresenterImpl extends BasePresenter<AddCollectionActivityView> implements AddCollectionActivityPresenter {

    private CollectionModel mModel;

    Context mContext;

    public AddCollectionActivityPresenterImpl(Context context) {
        mContext = context;
        mModel = new CollectionModelImpl(context);
    }

    @Override
    public void addCollection(Context mContext, String goodsCode) {
        mModel.addCollection(mContext, goodsCode, new XUtils.ResultListener() {
            @Override
            public void onResponse(String result) {
                try {
                    if (getActivityView() != null) {
                        getActivityView().hideLoading();
                    }
                    Gson gson = new Gson();
                    final NetCollectionBean netShareBean = gson.fromJson(result, NetCollectionBean.class);
                    if (Integer.valueOf(netShareBean.getCode()) == Contetns.STATE_OK) {
                        if (getActivityView() != null) {
                            getActivityView().addCollection(netShareBean);
                        }
                    } else {
                        if (getActivityView() != null) {
                            getActivityView().addCollectionError(Integer.valueOf(netShareBean.getCode()), netShareBean.getMsg());
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable error) {
                getActivityView().addCollectionError(-10000, "请求错误");
            }
        });

    }
}
