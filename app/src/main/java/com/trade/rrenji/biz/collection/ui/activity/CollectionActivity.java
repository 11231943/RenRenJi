package com.trade.rrenji.biz.collection.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.gelitenight.superrecyclerview.LinearSpacingDecoration;
import com.gelitenight.superrecyclerview.SuperRecyclerView;
import com.trade.rrenji.R;
import com.trade.rrenji.bean.address.NetAddressBean;
import com.trade.rrenji.bean.collection.NetCollectionListBean;
import com.trade.rrenji.biz.base.BaseActivity;
import com.trade.rrenji.biz.collection.presenter.CollectionActivityPresenter;
import com.trade.rrenji.biz.collection.presenter.CollectionActivityPresenterImpl;
import com.trade.rrenji.biz.collection.ui.adapter.CollectionAdapter;
import com.trade.rrenji.biz.collection.ui.view.CollectionActivityView;
import com.trade.rrenji.fragment.DryingTabFragment;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;


@ContentView(R.layout.base_activity_super_recyclerview)
public class CollectionActivity extends BaseActivity implements CollectionActivityView {

    private static String TAG = DryingTabFragment.class.getSimpleName();
    @ViewInject(R.id.base_activity_recycler_view)
    public SuperRecyclerView mSuperRecyclerView;

    CollectionActivityPresenter mPresenter;
    CollectionAdapter mCollectionAdapter = null;


    private int mPageIndex = 0;

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

    }

    @Override
    public void getCollectionListError(int code, String msg) {

    }
}
