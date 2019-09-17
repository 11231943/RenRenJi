package com.trade.rrenji.biz.sale.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.gelitenight.superrecyclerview.LinearSpacingDecoration;
import com.gelitenight.superrecyclerview.SuperRecyclerView;
import com.trade.rrenji.R;
import com.trade.rrenji.bean.address.NetAddressBean;
import com.trade.rrenji.biz.base.BaseActivity;
import com.trade.rrenji.biz.sale.presenter.SaleActivityPresenter;
import com.trade.rrenji.biz.sale.presenter.SaleActivityPresenterImpl;
import com.trade.rrenji.biz.sale.ui.adapter.SaleAdapter;
import com.trade.rrenji.biz.sale.ui.view.SaleActivityView;
import com.trade.rrenji.fragment.DryingTabFragment;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import butterknife.Bind;
import butterknife.ButterKnife;


public class SaleActivity extends BaseActivity implements SaleActivityView {

    private static String TAG = DryingTabFragment.class.getSimpleName();
    SaleActivityPresenter mPresenter;
    SaleAdapter mSaleAdapter = null;
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
        mSaleAdapter = new SaleAdapter(this);
        mSuperRecyclerView.addItemDecoration(new LinearSpacingDecoration(5, 5));
        mSuperRecyclerView.setAdapter(mSaleAdapter);
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
        mPresenter.getCollectionList(this, mIndexPage);
    }

    @Override
    protected void attachPresenter() {
        mPresenter = new SaleActivityPresenterImpl(this);
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
    public void getCollectionList(NetAddressBean netShareBean) {

    }

    @Override
    public void getCollectionListError(int code, String msg) {

    }
}
