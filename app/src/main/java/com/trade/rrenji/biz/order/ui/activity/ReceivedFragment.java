package com.trade.rrenji.biz.order.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gelitenight.superrecyclerview.LinearSpacingDecoration;
import com.gelitenight.superrecyclerview.SuperRecyclerView;
import com.trade.rrenji.R;
import com.trade.rrenji.bean.order.NetOrderBean;
import com.trade.rrenji.biz.base.BaseFragment;
import com.trade.rrenji.biz.order.presenter.OrderActivityPresenter;
import com.trade.rrenji.biz.order.presenter.OrderActivityPresenterImpl;
import com.trade.rrenji.biz.order.ui.adapter.OrderAdminAdapter;
import com.trade.rrenji.biz.order.ui.view.OrderActivityView;
import com.trade.rrenji.utils.Contetns;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * 待收货
 */
@ContentView(R.layout.base_activity_super_recyclerview)
public class ReceivedFragment extends BaseFragment  implements OrderActivityView {

    @ViewInject(R.id.base_activity_recycler_view)
    public SuperRecyclerView mSuperRecyclerView;

    OrderActivityPresenter mPresenter;
    OrderAdminAdapter mOrderAdminAdapter = null;
    private int mIndexPage = 1;

    public static ReceivedFragment newInstance() {
        Bundle args = new Bundle();
        ReceivedFragment fragment = new ReceivedFragment();
        fragment.setArguments(args);
        return fragment;
    }

    protected void initView() {

    }

    @Override
    protected int getLayoutResource() {
        return 0;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = x.view().inject(this, inflater, container);
        return rootView;
    }

    private void init() {
        mSuperRecyclerView.setRecyclerPadding(0, 20, 0, 0);
        mOrderAdminAdapter = new OrderAdminAdapter(getActivity());
        mSuperRecyclerView.addItemDecoration(new LinearSpacingDecoration(25, 5));
        mSuperRecyclerView.setAdapter(mOrderAdminAdapter);
        mSuperRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
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
        mPresenter.getOrderList(getActivity(), mIndexPage, "3");
    }


    @Override
    protected void attachPresenter() {
        mPresenter = new OrderActivityPresenterImpl(getActivity());
        mPresenter.attachView(this);
        init();
    }

    @Override
    protected void detachPresenter() {
        mPresenter.detachView();
        mPresenter = null;
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
}
