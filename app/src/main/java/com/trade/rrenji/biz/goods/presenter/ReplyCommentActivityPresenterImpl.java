package com.trade.rrenji.biz.goods.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.trade.rrenji.bean.goods.RecyCommentBean;
import com.trade.rrenji.biz.base.BasePresenter;
import com.trade.rrenji.biz.goods.model.GoodsModel;
import com.trade.rrenji.biz.goods.model.GoodsModelImpl;
import com.trade.rrenji.biz.goods.ui.view.ReplyCommentActivityView;
import com.trade.rrenji.net.XUtils;
import com.trade.rrenji.utils.Contetns;

public class ReplyCommentActivityPresenterImpl extends BasePresenter<ReplyCommentActivityView> implements ReplyCommentActivityPresenter {

    private GoodsModel mModel;

    Context mContext;

    public ReplyCommentActivityPresenterImpl(Context context) {
        mContext = context;
        mModel = new GoodsModelImpl(context);
    }

    @Override
    public void getReplyCommentList(Context mContext, String goodsCode, int page) {
        mModel.getReplyComment(mContext, goodsCode, page, new XUtils.ResultListener() {
            @Override
            public void onResponse(String result) {
                try {
                    if (getActivityView() != null) {
                        getActivityView().hideLoading();
                    }
                    Gson gson = new Gson();
                    final RecyCommentBean netShareBean = gson.fromJson(result, RecyCommentBean.class);
                    if (netShareBean.getCode().equals(Contetns.RESPONSE_OK)) {
                        if (getActivityView() != null) {
                            getActivityView().getReplyCommentListSuccess(netShareBean);
                        }
                    } else {
                        if (getActivityView() != null) {
                            getActivityView().getReplyCommentError(Integer.valueOf(netShareBean.getCode()), netShareBean.getMsg());
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable error) {
                if (getActivityView() != null) {
                    getActivityView().getReplyCommentError(-10000, "请求错误");
                }
            }
        });
    }
}
