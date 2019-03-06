package com.trade.rrenji.biz.collection.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import com.gelitenight.superrecyclerview.LinearSpacingDecoration;
import com.gelitenight.superrecyclerview.SuperRecyclerView;
import com.trade.rrenji.R;
import com.trade.rrenji.bean.collection.NetCollectionListBean;
import com.trade.rrenji.biz.base.BaseActivity;
import com.trade.rrenji.biz.base.NetBaseResultBean;
import com.trade.rrenji.biz.collection.presenter.CollectionActivityPresenter;
import com.trade.rrenji.biz.collection.presenter.CollectionActivityPresenterImpl;
import com.trade.rrenji.biz.collection.ui.adapter.CollectionAdapter;
import com.trade.rrenji.biz.collection.ui.view.CollectionActivityView;
import com.trade.rrenji.fragment.DryingTabFragment;
import com.trade.rrenji.utils.Contetns;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.List;


@ContentView(R.layout.base_activity_super_recyclerview)
public class CollectionActivity extends BaseActivity implements CollectionActivityView {

    private static String TAG = DryingTabFragment.class.getSimpleName();
    @ViewInject(R.id.base_activity_recycler_view)
    public SuperRecyclerView mSuperRecyclerView;

    CollectionActivityPresenter mPresenter;
    CollectionAdapter mCollectionAdapter = null;


    private int mPageIndex = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        setActionBarTitle("我的收藏");
        mCollectionAdapter = new CollectionAdapter(this);
        mSuperRecyclerView.addItemDecoration(new LinearSpacingDecoration(5, 5));
        mSuperRecyclerView.setAdapter(mCollectionAdapter);
        mSuperRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mSuperRecyclerView.setOnLoadDataListener(new SuperRecyclerView.OnLoadDataListener() {
            @Override
            public void onRefresh() {
                loadData();
            }

            @Override
            public void onMore() {
                loadData();
            }
        });
        mSuperRecyclerView.startRefreshing(true);
        mCollectionAdapter.setOnClickDelListener(new CollectionAdapter.OnClickDelListener() {
            @Override
            public void onClick(String id) {
                mPresenter.delCollection(CollectionActivity.this, id);
            }
        });
    }

    private void loadData() {
        mPresenter.getCollectionList(this, mPageIndex);
    }

    @Override
    protected void attachPresenter() {
        mPresenter = new CollectionActivityPresenterImpl(this);
        mPresenter.attachView(this);
    }

    @Override
    protected void detachPresenter() {
        mPresenter.detachView();
        mPresenter = null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void getCollectionListSuccess(NetCollectionListBean netShareBean) {
        if (mPageIndex == 1) {
            if (mCollectionAdapter != null) {
                mCollectionAdapter.clear();
            }
        }
        List<NetCollectionListBean.ResultBean> listBeans = netShareBean.getResult();
        mSuperRecyclerView.finishRefreshing();
        mSuperRecyclerView.setHasMoreData(Contetns.hasMoreData(listBeans.size()));
        mSuperRecyclerView.finishMore(!Contetns.hasMoreData(listBeans.size()));
        mCollectionAdapter.addAll(listBeans);
    }

    @Override
    public void getCollectionListError(int code, String msg) {

    }

    @Override
    public void delCollectionSuccess(NetBaseResultBean netBaseResultBean) {
        if (netBaseResultBean.getCode().equals(Contetns.RESPONSE_OK)) {
            Toast.makeText(this, "取消收藏成功", Toast.LENGTH_SHORT).show();
            loadData();
        }else{
            Toast.makeText(this, "取消收藏失败", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void delCollectionError(int code, String msg) {
        Toast.makeText(this, "取消收藏失败", Toast.LENGTH_SHORT).show();
    }
}
