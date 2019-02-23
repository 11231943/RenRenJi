package com.trade.rrenji.fragment;

import android.os.Bundle;
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
@ContentView(R.layout.fragment_message)
public class DryingTabFragment extends BaseFragment implements DryActivityView {

    private static String TAG = DryingTabFragment.class.getSimpleName();

    @ViewInject(R.id.order_recycler_view)
    public SuperRecyclerView mSuperRecyclerView;

    DryListAdapter mDryListAdapter;

    private int mIndexPage = 1;

    DryActivityPresenter mDryActivityPresenter = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = x.view().inject(this, inflater, container);
        StatusBarUtils.setWindowStatusBarColor(getActivity(), R.color.actionbar_bg);
        return rootView;
    }

    private void init() {
        mDryListAdapter = new DryListAdapter(getActivity());
        mSuperRecyclerView.addItemDecoration(new LinearSpacingDecoration(20, 20));
        mSuperRecyclerView.setAdapter(mDryListAdapter);
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
        mSuperRecyclerView.startRefreshing(true, true);
    }


    private void loadData() {
        mDryActivityPresenter.getDryingList(getActivity(), mIndexPage);
    }


    @Override
    protected void attachPresenter() {
        mDryActivityPresenter = new DryActivityPresenterImpl(getActivity());
        mDryActivityPresenter.attachView(this);
        init();
    }

    @Override
    protected void detachPresenter() {
        mDryActivityPresenter.detachView();
        mDryActivityPresenter = null;
    }

    @Override
    public void getDryingSuccess(NetShareBean netShareBean) {
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
    }

    @Override
    public void getDryingCodeError(int code, String msg) {
        Log.e("getDryingCodeError", "code = " + code + " msg = " + msg);
    }


}
