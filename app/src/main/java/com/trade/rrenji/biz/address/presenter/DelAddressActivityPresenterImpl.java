package com.trade.rrenji.biz.address.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.trade.rrenji.biz.address.model.AddressModel;
import com.trade.rrenji.biz.address.model.AddressModelImpl;
import com.trade.rrenji.biz.address.ui.view.DelAddressActivityView;
import com.trade.rrenji.biz.base.BasePresenter;
import com.trade.rrenji.biz.base.NetBaseResultBean;
import com.trade.rrenji.net.XUtils;
import com.trade.rrenji.utils.Contetns;

public class DelAddressActivityPresenterImpl extends BasePresenter<DelAddressActivityView> implements DelAddressActivityPresenter {

    private AddressModel mModel;

    Context mContext;

    public DelAddressActivityPresenterImpl(Context context) {
        mContext = context;
        mModel = new AddressModelImpl(context);
    }

    @Override
    public void isUpAddress(Context mContext, String addressId) {
        mModel.isUpAddress(mContext, addressId, new XUtils.ResultListener() {
            @Override
            public void onResponse(String result) {
                try {
                    if (getActivityView() != null) {
                        getActivityView().hideLoading();
                    }
                    Gson gson = new Gson();
                    final NetBaseResultBean netShareBean = gson.fromJson(result, NetBaseResultBean.class);
                    if (Integer.valueOf(netShareBean.getCode()) == Contetns.STATE_OK) {
                        if (getActivityView() != null) {
                            getActivityView().delAddressListSuccess(netShareBean);
                        }
                    } else {
                        if (getActivityView() != null) {
                            getActivityView().delAddressListError(Integer.valueOf(netShareBean.getCode()), netShareBean.getMsg());
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable error) {
                getActivityView().delAddressListError(-10000, "请求错误");
            }
        });

    }

    @Override
    public void delAddressList(Context mContext, String addressId) {
        mModel.delAddress(mContext, addressId, new XUtils.ResultListener() {
            @Override
            public void onResponse(String result) {
                try {
                    if (getActivityView() != null) {
                        getActivityView().hideLoading();
                    }
                    Gson gson = new Gson();
                    final NetBaseResultBean netShareBean = gson.fromJson(result, NetBaseResultBean.class);
                    if (Integer.valueOf(netShareBean.getCode()) == Contetns.STATE_OK) {
                        if (getActivityView() != null) {
                            getActivityView().delAddressListSuccess(netShareBean);
                        }
                    } else {
                        if (getActivityView() != null) {
                            getActivityView().delAddressListError(Integer.valueOf(netShareBean.getCode()), netShareBean.getMsg());
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable error) {
                getActivityView().delAddressListError(-10000, "请求错误");
            }
        });
    }
}
