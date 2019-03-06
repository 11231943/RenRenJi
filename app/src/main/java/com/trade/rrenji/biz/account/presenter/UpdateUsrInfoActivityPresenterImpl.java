package com.trade.rrenji.biz.account.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.trade.rrenji.R;
import com.trade.rrenji.biz.account.model.AccountModel;
import com.trade.rrenji.biz.account.model.AccountModelImpl;
import com.trade.rrenji.biz.account.ui.view.UpdateUserInfoActivityView;
import com.trade.rrenji.biz.base.BasePresenter;
import com.trade.rrenji.biz.base.NetBaseResultBean;
import com.trade.rrenji.net.XUtils;
import com.trade.rrenji.utils.Contetns;
/**
 * Created by monster on 8/4/18.
 */

public class UpdateUsrInfoActivityPresenterImpl extends BasePresenter<UpdateUserInfoActivityView> implements UpdateUserInfoActivityPresenter {

    private AccountModel mModel;

    Context mContext;

    public UpdateUsrInfoActivityPresenterImpl(Context context) {
        mContext = context;
        mModel = new AccountModelImpl(context);
    }

    @Override
    public void updateUserInfo(final Context mContext, String headImg, String name, String sex, String address) {
        mModel.updateUserInfo(mContext, headImg, name, sex, address, new XUtils.ResultListener() {
            @Override
            public void onResponse(String result) {
                try {
                    if (getActivityView() != null) {
                        getActivityView().hideLoading();
                    }
                    Gson gson = new Gson();
                    final NetBaseResultBean jsonData = gson.fromJson(result, NetBaseResultBean.class);
                    if (jsonData.getCode().equals(Contetns.RESPONSE_OK)) {
                        if (getActivityView() != null) {
                            getActivityView().updateUserInfoSuccess(jsonData);
                        }
                    } else {
                        if (getActivityView() != null) {
                            getActivityView().updateUserInfoError(-1000, "登陆出错！");
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    if (getActivityView() != null) {
                        getActivityView().updateUserInfoError(Contetns.JSON_PART, mContext.getString(R.string.part_json_error));
                    }
                }

            }

            @Override
            public void onError(Throwable error) {

            }
        });
    }
}
