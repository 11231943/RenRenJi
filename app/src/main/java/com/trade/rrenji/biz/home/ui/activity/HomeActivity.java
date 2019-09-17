package com.trade.rrenji.biz.home.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.gelitenight.superrecyclerview.LinearSpacingDecoration;
import com.gelitenight.superrecyclerview.SuperRecyclerView;
import com.trade.rrenji.R;
import com.trade.rrenji.bean.home.NetHomeBean;
import com.trade.rrenji.biz.base.BaseActivity;
import com.trade.rrenji.biz.home.presenter.HomeActivityPresenter;
import com.trade.rrenji.biz.home.presenter.HomeActivityPresenterImpl;
import com.trade.rrenji.biz.home.ui.adapter.HomeAdapter;
import com.trade.rrenji.biz.home.ui.view.HomeActivityView;
import com.trade.rrenji.fragment.DryingTabFragment;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import butterknife.Bind;
import butterknife.ButterKnife;


public class HomeActivity extends BaseActivity implements HomeActivityView {
    private static String TAG = DryingTabFragment.class.getSimpleName();
    HomeActivityPresenter mPresenter;
    HomeAdapter mHomeAdapter = null;
    @Bind(R.id.base_activity_recycler_view)
    SuperRecyclerView mSuperRecyclerView;
    private int mIndexPage = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_activity_super_recyclerview);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        setActionBarTitle("我的收藏");
        mHomeAdapter = new HomeAdapter(this);
        mSuperRecyclerView.addItemDecoration(new LinearSpacingDecoration(5, 5));
        mSuperRecyclerView.setAdapter(mHomeAdapter);
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
        mPresenter.getHomeList(this, mIndexPage);
    }

    @Override
    protected void attachPresenter() {
        mPresenter = new HomeActivityPresenterImpl(this);
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
    public void getHomeListError(int code, String msg) {

    }

    @Override
    public void getHomeList(NetHomeBean mNetHomeBean) {

    }
}
