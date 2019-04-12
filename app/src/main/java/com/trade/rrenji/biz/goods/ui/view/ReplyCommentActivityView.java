package com.trade.rrenji.biz.goods.ui.view;

import com.trade.rrenji.bean.goods.RecyCommentBean;
import com.trade.rrenji.biz.base.BaseView;

public interface ReplyCommentActivityView extends BaseView {

    void getReplyCommentListSuccess(RecyCommentBean recyCommentBean);

    void getReplyCommentError(int code, String msg);
}
