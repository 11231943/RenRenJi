package com.trade.rrenji.biz.address.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.trade.rrenji.bean.address.NetAddressBean;
import com.trade.rrenji.biz.address.model.AddressModel;
import com.trade.rrenji.biz.address.model.AddressModelImpl;
import com.trade.rrenji.biz.address.ui.view.AddressActivityView;
import com.trade.rrenji.biz.base.BasePresenter;
import com.trade.rrenji.net.XUtils;
import com.trade.rrenji.utils.Contetns;

public class AddressActivityPresenterImpl extends BasePresenter<AddressActivityView> implements AddressActivityPresenter {

    private AddressModel mModel;

    Context mContext;

    public AddressActivityPresenterImpl(Context context) {
        mContext = context;
        mModel = new AddressModelImpl(context);
    }

    @Override
    public void getAddressList(Context mContext, int pageNum) {
        mModel.getAddressList(mContext, pageNum, new XUtils.ResultListener() {
            @Override
            public void onResponse(String result) {
                try {
                    if (getActivityView() != null) {
                        getActivityView().hideLoading();
                    }
                    Gson gson = new Gson();
                    final NetAddressBean netShareBean = gson.fromJson(result, NetAddressBean.class);
                    if (Integer.valueOf(netShareBean.getCode()) == Contetns.STATE_OK) {
                        if (getActivityView() != null) {
                            getActivityView().getAddressListSuccess(netShareBean);
                        }
                    } else {
                        if (getActivityView() != null) {
                            getActivityView().getAddressListError(Integer.valueOf(netShareBean.getCode()), netShareBean.getMsg());
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable error) {
                getActivityView().getAddressListError(-10000, "请求错误");

            }
        });
    }
}
