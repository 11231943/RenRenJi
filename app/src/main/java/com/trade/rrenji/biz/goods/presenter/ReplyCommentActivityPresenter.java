package com.trade.rrenji.biz.goods.presenter;

import android.content.Context;

import com.trade.rrenji.biz.base.Presenter;
import com.trade.rrenji.biz.goods.ui.view.ReplyCommentActivityView;

public interface ReplyCommentActivityPresenter extends Presenter<ReplyCommentActivityView> {

    void getReplyCommentList(Context mContext, String goodsCode, int page);

}
