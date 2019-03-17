package com.trade.rrenji.biz.personal.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.trade.rrenji.bean.personal.NetPersonalBean;
import com.trade.rrenji.biz.base.BasePresenter;
import com.trade.rrenji.biz.personal.model.PersonalModel;
import com.trade.rrenji.biz.personal.model.PersonalModelImpl;
import com.trade.rrenji.biz.personal.ui.view.PersonalActivityView;
import com.trade.rrenji.biz.sale.model.SaleModel;
import com.trade.rrenji.biz.sale.model.SaleModelImpl;
import com.trade.rrenji.biz.sale.ui.view.SaleActivityView;
import com.trade.rrenji.net.XUtils;
import com.trade.rrenji.utils.Contetns;

/**
 * Created by monster on 8/4/18.
 */

public class PersonalActivityPresenterImpl extends BasePresenter<PersonalActivityView> implements PersonalActivityPresenter {

    private PersonalModel mModel;

    Context mContext;

    public PersonalActivityPresenterImpl(Context context) {
        mContext = context;
        mModel = new PersonalModelImpl(context);
    }

    @Override
    public void getUserPersonalInfo(Context mContext, String friendId, int page, int rows) {
        mModel.getUserPersonalInfo(mContext, friendId, page, rows, new XUtils.ResultListener() {
            @Override
            public void onResponse(String result) {
                try {
                    if (getActivityView() != null) {
                        getActivityView().hideLoading();
                    }
                    Gson gson = new Gson();
                    final NetPersonalBean netShareBean = gson.fromJson(result, NetPersonalBean.class);
                    if (Integer.valueOf(netShareBean.getCode()) == Contetns.STATE_OK) {
                        if (getActivityView() != null) {
                            getActivityView().getUserPersonalInfo(netShareBean);
                        }
                    } else {
                        if (getActivityView() != null) {
                            getActivityView().getUserPersonalInfoError(Integer.valueOf(netShareBean.getCode()), netShareBean.getMsg());
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable error) {
                getActivityView().getUserPersonalInfoError(-10000, "请求错误");
            }
        });
    }
}
