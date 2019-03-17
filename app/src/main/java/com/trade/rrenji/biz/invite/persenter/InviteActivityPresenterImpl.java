package com.trade.rrenji.biz.invite.persenter;

import android.content.Context;

import com.google.gson.Gson;
import com.trade.rrenji.bean.invite.NetInviteBean;
import com.trade.rrenji.biz.base.BasePresenter;
import com.trade.rrenji.biz.invite.model.InviteModel;
import com.trade.rrenji.biz.invite.model.InviteModelImpl;
import com.trade.rrenji.biz.invite.ui.view.InviteActivityView;
import com.trade.rrenji.net.XUtils;
import com.trade.rrenji.utils.Contetns;

public class InviteActivityPresenterImpl extends BasePresenter<InviteActivityView> implements InviteActivityPresenter {

    private InviteModel mModel;

    Context mContext;

    public InviteActivityPresenterImpl(Context context) {
        mContext = context;
        mModel = new InviteModelImpl(context);
    }

    @Override
    public void getInviteCode(Context context) {
        mModel.getInviteCode(context, new XUtils.ResultListener() {
            @Override
            public void onResponse(String result) {
                try {
                    if (getActivityView() != null) {
                        getActivityView().hideLoading();
                    }
                    Gson gson = new Gson();
                    final NetInviteBean netShareBean = gson.fromJson(result, NetInviteBean.class);
                    if (Integer.valueOf(netShareBean.getCode()) == Contetns.STATE_OK) {
                        if (getActivityView() != null) {
                            getActivityView().getInviteCodeSuccess(netShareBean);
                        }
                    } else {
                        if (getActivityView() != null) {
                            getActivityView().getInviteCodeError(Integer.valueOf(netShareBean.getCode()), netShareBean.getMsg());
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(Throwable error) {
                getActivityView().getInviteCodeError(-10000, "请求错误");
            }
        });
    }
}
