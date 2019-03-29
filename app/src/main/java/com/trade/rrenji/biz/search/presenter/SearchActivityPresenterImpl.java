package com.trade.rrenji.biz.search.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.trade.rrenji.biz.base.BasePresenter;
import com.trade.rrenji.biz.search.model.SearchModel;
import com.trade.rrenji.biz.search.model.SearchModelImpl;
import com.trade.rrenji.biz.search.ui.view.SearchActivityView;
import com.trade.rrenji.net.XUtils;

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
    public void getCollectionList(Context mContext, int pageNum) {
        mModel.getCollectionList(mContext, pageNum, new XUtils.ResultListener() {
            @Override
            public void onResponse(String result) {
                try {
                    if (getActivityView() != null) {
                        getActivityView().hideLoading();
                    }
                    Gson gson = new Gson();
//                    final NetAddressBean netShareBean = gson.fromJson(result, NetAddressBean.class);
//                    if (Integer.valueOf(netShareBean.getCode()) == Contetns.STATE_OK) {
//                        if (getActivityView() != null) {
//                            getActivityView().getAddressListSuccess(netShareBean);
//                        }
//                    } else {
//                        if (getActivityView() != null) {
//                            getActivityView().getAddressListError(Integer.valueOf(netShareBean.getCode()), netShareBean.getMsg());
//                        }
//                    }
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
