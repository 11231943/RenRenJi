package com.trade.rrenji.biz.data.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.trade.rrenji.bean.home.NetHotDataBean;
import com.trade.rrenji.biz.data.model.DataModel;
import com.trade.rrenji.biz.data.model.DataModelImpl;
import com.trade.rrenji.biz.data.ui.view.DataActivityListView;
import com.trade.rrenji.biz.base.BasePresenter;
import com.trade.rrenji.net.XUtils;

public class DataActivityListPresenterImpl extends BasePresenter<DataActivityListView> implements DataActivityListPresenter {

    private DataModel mModel;

    Context mContext;

    public DataActivityListPresenterImpl(Context context) {
        mContext = context;
        mModel = new DataModelImpl(context);
    }

    @Override
    public void getHotDataById(Context mContext, String id, int page, int rows) {
        mModel.getHotDataById(mContext, id, page, rows, new XUtils.ResultListener() {
            @Override
            public void onResponse(String result) {
                try {
                    if (getActivityView() != null) {
                        getActivityView().hideLoading();
                    }
                    Gson gson = new Gson();
                    final NetHotDataBean netShareBean = gson.fromJson(result, NetHotDataBean.class);
                    if (Integer.valueOf(netShareBean.getCode()) == 0) {
                        if (getActivityView() != null) {
                            getActivityView().getHotListSuccess(netShareBean);
                        }
                    } else {
                        if (getActivityView() != null) {
                            getActivityView().getHotListError(Integer.valueOf(netShareBean.getCode()), netShareBean.getMsg() + "");
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable error) {
                getActivityView().getHotListError(-10000, "请求错误");

            }
        });

    }
}
