package com.trade.rrenji.biz.goods.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import com.gelitenight.superrecyclerview.LinearSpacingDecoration;
import com.gelitenight.superrecyclerview.SuperRecyclerView;
import com.trade.rrenji.R;
import com.trade.rrenji.bean.goods.RecyCommentBean;
import com.trade.rrenji.biz.base.BaseActivity;
import com.trade.rrenji.biz.goods.presenter.ReplyCommentActivityPresenter;
import com.trade.rrenji.biz.goods.presenter.ReplyCommentActivityPresenterImpl;
import com.trade.rrenji.biz.goods.ui.adapter.ReplyCommentAdapter;
import com.trade.rrenji.biz.goods.ui.view.ReplyCommentActivityView;
import com.trade.rrenji.utils.Contetns;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class GoodsReplyCommentActivity extends BaseActivity implements ReplyCommentActivityView {

    ReplyCommentActivityPresenter mReplyCommentActivityPresenter;
    ReplyCommentAdapter mReplyCommentAdapter;
    @Bind(R.id.base_activity_recycler_view)
    SuperRecyclerView mSuperRecyclerView;
    private int mIndexPage = 1;
    private String mGoodsCode = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_activity_super_recyclerview);
        ButterKnife.bind(this);
        setActionBarTitle("机友评论");
        mGoodsCode = getIntent().getStringExtra("goodsCode");
        init();
    }

    private void init() {
        mSuperRecyclerView.setRecyclerPadding(0, 20, 0, 0);
        mReplyCommentAdapter = new ReplyCommentAdapter(this);
        mSuperRecyclerView.addItemDecoration(new LinearSpacingDecoration(25, 5));
        mSuperRecyclerView.setAdapter(mReplyCommentAdapter);
        mSuperRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mSuperRecyclerView.setOnLoadDataListener(new SuperRecyclerView.OnLoadDataListener() {
            @Override
            public void onRefresh() {
                mIndexPage = 1;
                loadData();
            }

            @Override
            public void onMore() {
                mIndexPage++;
                loadData();
            }
        });
        mSuperRecyclerView.startRefreshing(true);
    }

    private void loadData() {
        mReplyCommentActivityPresenter.getReplyCommentList(this, mGoodsCode, mIndexPage);
    }

    @Override
    protected void attachPresenter() {
        mReplyCommentActivityPresenter = new ReplyCommentActivityPresenterImpl(this);
        mReplyCommentActivityPresenter.attachView(this);
    }

    @Override
    protected void detachPresenter() {
        mReplyCommentActivityPresenter.detachView();
        mReplyCommentActivityPresenter = null;
    }

    @Override
    public void getReplyCommentListSuccess(RecyCommentBean recyCommentBean) {
        if (mIndexPage == 1) {
            if (mReplyCommentAdapter != null) {
                mReplyCommentAdapter.clear();
            }
        }
        List<RecyCommentBean.ResultBean.EvaluatelistBean> listBeans = recyCommentBean.getResult().getEvaluatelist();
        mSuperRecyclerView.finishRefreshing();
        mSuperRecyclerView.setHasMoreData(Contetns.hasMoreData(listBeans.size()));
        mSuperRecyclerView.finishMore(!Contetns.hasMoreData(listBeans.size()));
        mReplyCommentAdapter.addAll(listBeans);
    }

    @Override
    public void getReplyCommentError(int code, String msg) {
        Toast.makeText(this, "网络加载失败", Toast.LENGTH_SHORT).show();
    }
}
