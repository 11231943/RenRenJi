package com.trade.rrenji.biz.sale.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.trade.rrenji.biz.base.BasePresenter;
import com.trade.rrenji.biz.sale.model.SaleModel;
import com.trade.rrenji.biz.sale.model.SaleModelImpl;
import com.trade.rrenji.biz.sale.ui.view.SaleActivityView;
import com.trade.rrenji.net.XUtils;

/**
 * Created by monster on 8/4/18.
 */

public class SaleActivityPresenterImpl extends BasePresenter<SaleActivityView> implements SaleActivityPresenter {

    private SaleModel mModel;

    Context mContext;

    public SaleActivityPresenterImpl(Context context) {
        mContext = context;
        mModel = new SaleModelImpl(context);
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
