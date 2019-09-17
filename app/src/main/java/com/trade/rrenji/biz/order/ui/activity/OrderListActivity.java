package com.trade.rrenji.biz.order.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.gelitenight.superrecyclerview.LinearSpacingDecoration;
import com.gelitenight.superrecyclerview.SuperRecyclerView;
import com.trade.rrenji.R;
import com.trade.rrenji.bean.order.NetOrderBean;
import com.trade.rrenji.biz.base.BaseActivity;
import com.trade.rrenji.biz.base.NetBaseResultBean;
import com.trade.rrenji.biz.order.presenter.OrderActivityPresenter;
import com.trade.rrenji.biz.order.presenter.OrderActivityPresenterImpl;
import com.trade.rrenji.biz.order.ui.adapter.OrderAdminAdapter;
import com.trade.rrenji.biz.order.ui.view.OrderActivityView;
import com.trade.rrenji.utils.Contetns;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class OrderListActivity extends BaseActivity implements OrderActivityView {

    private static String TAG = OrderListActivity.class.getSimpleName();
    OrderActivityPresenter mPresenter;
    OrderAdminAdapter mOrderAdminAdapter = null;
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
        setActionBarTitle("晒单");
        mSuperRecyclerView.setRecyclerPadding(0, 20, 0, 0);
        mOrderAdminAdapter = new OrderAdminAdapter(this);
        mSuperRecyclerView.addItemDecoration(new LinearSpacingDecoration(25, 5));
        mSuperRecyclerView.setAdapter(mOrderAdminAdapter);
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
//        mOrderAdminAdapter.setOnClickConfirmOrderListener(new OrderAdminAdapter.OnClickConfirmOrderListener() {
//            @Override
//            public void onClick(String orderId) {
//                Intent intent = new Intent(OrderListActivity.this, DryingActivity.class);
//                intent.putExtra("orderId", orderId);
//                startActivity(intent);
//            }
//        });
//        mOrderAdminAdapter.setOnClickListener(new OrderAdminAdapter.onClickListener() {
//            @Override
//            public void onClick(NetOrderBean.DataBean.ResultListBean data) {
//                Intent intent = new Intent(OrderListActivity.this, DryingActivity.class);
//                intent.putExtra("orderId", data.getOrderId());
//                startActivity(intent);
//            }
//        });
        mSuperRecyclerView.startRefreshing(true);
    }

    private void loadData() {
        mPresenter.getOrderList(this, mIndexPage, "4");
    }

    @Override
    protected void attachPresenter() {
        mPresenter = new OrderActivityPresenterImpl(this);
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
    public void getOrderListError(int code, String msg) {

    }

    @Override
    public void getOrderListSuccess(NetOrderBean netOrderBean) {
        if (mIndexPage == 1) {
            if (mOrderAdminAdapter != null) {
                mOrderAdminAdapter.clear();
            }
        }
        List<NetOrderBean.DataBean.ResultListBean> listBeans = netOrderBean.getData().getResultList();
        mSuperRecyclerView.finishRefreshing();
        mSuperRecyclerView.setHasMoreData(Contetns.hasMoreData(listBeans.size()));
        mSuperRecyclerView.finishMore(!Contetns.hasMoreData(listBeans.size()));
        mOrderAdminAdapter.addAll(listBeans);
    }

    @Override
    public void delOrderSuccess(NetBaseResultBean netBaseResultBean) {

    }

    @Override
    public void delOrderError(int code, String msg) {

    }
}
