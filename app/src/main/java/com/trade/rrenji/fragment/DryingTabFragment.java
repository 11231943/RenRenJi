package com.trade.rrenji.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trade.rrenji.biz.base.BaseFragment;
import com.gelitenight.superrecyclerview.LinearSpacingDecoration;
import com.gelitenight.superrecyclerview.SuperRecyclerView;
import com.trade.rrenji.R;
import com.trade.rrenji.bean.drying.NetShareBean;
import com.trade.rrenji.biz.drying.presenter.DryActivityPresenter;
import com.trade.rrenji.biz.drying.presenter.DryActivityPresenterImpl;
import com.trade.rrenji.biz.drying.ui.adapter.DryListAdapter;
import com.trade.rrenji.biz.drying.ui.view.DryActivityView;
import com.trade.rrenji.utils.Contetns;
import com.trade.rrenji.utils.StatusBarUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Created by monster on 23/3/18.
 */
//@ContentView(R.layout.fragment_message)
public class DryingTabFragment extends BaseFragment implements DryActivityView {

    private static String TAG = DryingTabFragment.class.getSimpleName();

    @ViewInject(R.id.order_recycler_view)
    public SuperRecyclerView mSuperRecyclerView;

    DryListAdapter mDryListAdapter;

    private int mIndexPage = 1;

    DryActivityPresenter mDryActivityPresenter = null;

    @Override
    protected void initView() {
        mSuperRecyclerView = rootView.findViewById(R.id.order_recycler_view);
        init();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDryActivityPresenter = new DryActivityPresenterImpl(getActivity());
        mDryActivityPresenter.attachView(this);
        mDryListAdapter = new DryListAdapter(getActivity());
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_message;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        StatusBarUtils.setWindowStatusBarColor(getActivity(), R.color.actionbar_bg);
    }


    private void init() {
        if (!isFirst) {
            mSuperRecyclerView.addItemDecoration(new LinearSpacingDecoration(20, 20));
        }
        mSuperRecyclerView.setAdapter(mDryListAdapter);
        mSuperRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mSuperRecyclerView.setOnLoadDataListener(new SuperRecyclerView.OnLoadDataListener() {
            @Override
            public void onRefresh() {
                isFirst = false;
                mIndexPage = 1;
                loadData();
            }

            @Override
            public void onMore() {
                mIndexPage++;
                loadData();
            }
        });
        if (!isFirst) {
            mSuperRecyclerView.startRefreshing(true, true);
        }
    }


    private void loadData() {
        mDryActivityPresenter.getDryingList(getActivity(), mIndexPage);
    }


    @Override
    protected void attachPresenter() {
    }

    @Override
    protected void detachPresenter() {

    }

    @Override
    public void getDryingSuccess(NetShareBean netShareBean) {
        if (!isFirst) {
            isFirst = true;
            if (mIndexPage == 1) {
                if (mDryListAdapter != null) {
                    mDryListAdapter.clear();
                }
            }
            List<NetShareBean.ResultBean.ShareOrdersBean> ordersBeans = netShareBean.getResult().getShareOrders();
            mSuperRecyclerView.finishRefreshing();
            mSuperRecyclerView.setHasMoreData(Contetns.hasMoreData(ordersBeans.size()));
            mSuperRecyclerView.finishMore(!Contetns.hasMoreData(ordersBeans.size()));
            mDryListAdapter.addAll(ordersBeans);
        } else {
            List<NetShareBean.ResultBean.ShareOrdersBean> ordersBeans = netShareBean.getResult().getShareOrders();
            mSuperRecyclerView.finishRefreshing();
            mSuperRecyclerView.setHasMoreData(Contetns.hasMoreData(ordersBeans.size()));
            mSuperRecyclerView.finishMore(!Contetns.hasMoreData(ordersBeans.size()));
            mDryListAdapter.addAll(ordersBeans);
        }
    }

    @Override
    public void getDryingCodeError(int code, String msg) {
        Log.e("getDryingCodeError", "code = " + code + " msg = " + msg);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mDryActivityPresenter.detachView();
        mDryActivityPresenter = null;
    }
}
