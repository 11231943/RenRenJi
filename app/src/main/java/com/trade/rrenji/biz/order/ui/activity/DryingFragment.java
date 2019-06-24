package com.trade.rrenji.biz.order.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.gelitenight.superrecyclerview.LinearSpacingDecoration;
import com.gelitenight.superrecyclerview.SuperRecyclerView;
import com.trade.rrenji.R;
import com.trade.rrenji.bean.order.NetOrderBean;
import com.trade.rrenji.biz.base.BaseFragment;
import com.trade.rrenji.biz.base.NetBaseResultBean;
import com.trade.rrenji.biz.order.presenter.ConfirmOrderActivityPresenter;
import com.trade.rrenji.biz.order.presenter.ConfirmOrderActivityPresenterImpl;
import com.trade.rrenji.biz.order.presenter.OrderActivityPresenter;
import com.trade.rrenji.biz.order.presenter.OrderActivityPresenterImpl;
import com.trade.rrenji.biz.order.ui.adapter.OrderAdminAdapter;
import com.trade.rrenji.biz.order.ui.view.ConfirmActivityView;
import com.trade.rrenji.biz.order.ui.view.OrderActivityView;
import com.trade.rrenji.utils.Contetns;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * 晒单
 */
@ContentView(R.layout.base_activity_super_recyclerview)
public class DryingFragment extends BaseFragment implements OrderActivityView, ConfirmActivityView {

    @ViewInject(R.id.base_activity_recycler_view)
    public SuperRecyclerView mSuperRecyclerView;

    OrderActivityPresenter mPresenter;
    ConfirmOrderActivityPresenter mConfirmOrder;
    OrderAdminAdapter mOrderAdminAdapter = null;
    private int mIndexPage = 1;

    public static DryingFragment newInstance() {
        Bundle args = new Bundle();
        DryingFragment fragment = new DryingFragment();
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
        mOrderAdminAdapter.setType(4);
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
        mOrderAdminAdapter.setOnClickConfirmOrderListener(new OrderAdminAdapter.OnClickConfirmOrderListener() {
            @Override
            public void onClick(String orderId) {
                Intent intent = new Intent(getActivity(), DryingActivity.class);
                intent.putExtra("orderId", orderId);
                intent.putExtra("mAddressDetail", orderId);
                startActivity(intent);
            }
        });
    }

    private void loadData() {
        mPresenter.getOrderList(getActivity(), mIndexPage, "4");
    }


    @Override
    protected void attachPresenter() {
        mPresenter = new OrderActivityPresenterImpl(getActivity());
        mPresenter.attachView(this);
        mConfirmOrder = new ConfirmOrderActivityPresenterImpl(getActivity());
        mConfirmOrder.attachView(this);
        init();
    }

    @Override
    protected void detachPresenter() {
        mPresenter.detachView();
        mPresenter = null;
        mConfirmOrder.detachView();
        mConfirmOrder = null;
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
    public void getConfirmOrderSuccess(NetBaseResultBean netOrderBean) {
        if (netOrderBean.getCode().equals(Contetns.RESPONSE_OK)) {
            Toast.makeText(getActivity(), "收货成功！", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity(), "收货失败！", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void getConfirmOrderError(int code, String msg) {

    }
    @Override
    public void delOrderSuccess(NetBaseResultBean netBaseResultBean) {

    }

    @Override
    public void delOrderError(int code, String msg) {

    }
}
