package com.trade.rrenji.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gelitenight.superrecyclerview.LinearSpacingDecoration;
import com.gelitenight.superrecyclerview.SuperRecyclerView;
import com.trade.rrenji.R;
import com.trade.rrenji.bean.tech.NetTechBean;
import com.trade.rrenji.biz.base.BaseFragment;
import com.trade.rrenji.biz.tech.presenter.TechActivityPresenter;
import com.trade.rrenji.biz.tech.presenter.TechActivityPresenterImpl;
import com.trade.rrenji.biz.tech.ui.adapter.TechListAdapter;
import com.trade.rrenji.biz.tech.ui.view.TechActivityView;
import com.trade.rrenji.utils.Contetns;
import com.trade.rrenji.utils.StatusBarUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Created by monster on 23/3/18.
 */

@ContentView(R.layout.fragment_goods)
public class TechTabFragment extends BaseFragment implements TechActivityView {

    @ViewInject(R.id.order_recycler_view)
    public SuperRecyclerView mSuperRecyclerView;

    TechListAdapter mTechListAdapter;

    private int mIndexPage = 1;

    TechActivityPresenter mTechActivityPresenter = null;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = x.view().inject(this, inflater, container);
        StatusBarUtils.setWindowStatusBarColor(getActivity(), R.color.actionbar_bg);
        return rootView;
    }

    private void init() {
        mTechListAdapter = new TechListAdapter(getActivity());
        mSuperRecyclerView.addItemDecoration(new LinearSpacingDecoration(0, 0));
        mSuperRecyclerView.setAdapter(mTechListAdapter);
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
        mTechActivityPresenter.getTechList(getActivity(), mIndexPage);
    }

    @Override
    protected void attachPresenter() {
        mTechActivityPresenter = new TechActivityPresenterImpl(getActivity());
        mTechActivityPresenter.attachView(this);
        init();
    }

    @Override
    protected void detachPresenter() {
        mTechActivityPresenter.detachView();
        mTechActivityPresenter = null;
    }

    @Override
    public void getTechSuccess(NetTechBean netShareBean) {
        if (mIndexPage == 1) {
            if (mTechListAdapter != null) {
                mTechListAdapter.clear();
            }
        }
        List<NetTechBean.ResultBean.CommunityListBean> ordersBeans = netShareBean.getResult().getCommunityList();
        mSuperRecyclerView.finishRefreshing();
        mSuperRecyclerView.setHasMoreData(Contetns.hasMoreData(ordersBeans.size()));
        mSuperRecyclerView.finishMore(!Contetns.hasMoreData(ordersBeans.size()));
        mTechListAdapter.addAll(ordersBeans);
    }

    @Override
    public void getTechError(int code, String msg) {
        Log.e("getDryingCodeError", "code = " + code + " msg = " + msg);
    }
}
