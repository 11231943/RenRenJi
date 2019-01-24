package com.trade.rrenji.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gelitenight.superrecyclerview.LinearSpacingDecoration;
import com.gelitenight.superrecyclerview.SuperRecyclerView;
import com.trade.rrenji.R;
import com.trade.rrenji.bean.address.NetAddressBean;
import com.trade.rrenji.bean.drying.NetShareBean;
import com.trade.rrenji.bean.home.HomeBean;
import com.trade.rrenji.bean.home.NetHomeBean;
import com.trade.rrenji.biz.base.BaseFragment;
import com.trade.rrenji.biz.drying.presenter.DryActivityPresenter;
import com.trade.rrenji.biz.drying.presenter.DryActivityPresenterImpl;
import com.trade.rrenji.biz.drying.ui.adapter.DryListAdapter;
import com.trade.rrenji.biz.home.presenter.HomeActivityPresenter;
import com.trade.rrenji.biz.home.presenter.HomeActivityPresenterImpl;
import com.trade.rrenji.biz.home.ui.adapter.HomeAdapter;
import com.trade.rrenji.biz.home.ui.view.HomeActivityView;
import com.trade.rrenji.utils.Contetns;
import com.trade.rrenji.utils.StatusBarUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by monster on 23/3/18.
 */
@ContentView(R.layout.fragment_nearby)
public class HomeTabFragment extends BaseFragment implements HomeActivityView {

    private static String TAG = HomeTabFragment.class.getSimpleName();

    @ViewInject(R.id.near_recycler_view)
    public SuperRecyclerView mSuperRecyclerView;

    HomeAdapter mHomeAdapter;

    private int mIndexPage = 1;

    HomeActivityPresenter mHomeActivityPresenter = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = x.view().inject(this, inflater, container);
        StatusBarUtils.setWindowStatusBarColor(getActivity(), R.color.actionbar_bg);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void loadData() {
        mHomeActivityPresenter.getHomeList(getActivity(), mIndexPage);
    }

    @Override
    protected void attachPresenter() {
        mHomeActivityPresenter = new HomeActivityPresenterImpl(getActivity());
        mHomeActivityPresenter.attachView(this);
        init();
    }

    private void init() {
        mHomeAdapter = new HomeAdapter(getActivity());
        mSuperRecyclerView.addItemDecoration(new LinearSpacingDecoration(10, 0));
        mSuperRecyclerView.setAdapter(mHomeAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity()) {
            @Override
            public RecyclerView.LayoutParams generateDefaultLayoutParams() {
                return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
            }
        };
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mSuperRecyclerView.setLayoutManager(layoutManager);

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

    @Override
    protected void detachPresenter() {
        mHomeActivityPresenter.detachView();
        mHomeActivityPresenter = null;
    }

    @Override
    public void getHomeList(NetHomeBean mNetHomeBean) {
        if (mIndexPage == 1) {
            if (mHomeAdapter != null) {
                mHomeAdapter.clear();
            }
        }
        NetHomeBean.DataBean ordersBeans = mNetHomeBean.getData();
        mSuperRecyclerView.finishRefreshing();
        mSuperRecyclerView.setHasMoreData(false);
        mSuperRecyclerView.finishMore(false);
        build(mNetHomeBean.getData());

    }

    private void build(NetHomeBean.DataBean resultBean) {
        List<HomeBean> homeBeanList = new ArrayList<HomeBean>();

        HomeBean adBean = new HomeBean();
        adBean.setType(0);
        adBean.setTopBannerList(resultBean.getTopBannerList());

        HomeBean adBean1 = new HomeBean();
        adBean1.setType(1);
        adBean1.setCategoryList(resultBean.getCategoryList());

        HomeBean adBean2 = new HomeBean();
        adBean2.setType(2);
        adBean2.setHotActivityInfoList(resultBean.getHotActivityInfoList());

        HomeBean adBean3 = new HomeBean();
        adBean3.setType(3);
        adBean3.setHotIphone(resultBean.getHotIphone());

        HomeBean adBean4 = new HomeBean();
        adBean4.setType(4);
        adBean4.setHotAndroid(resultBean.getHotAndroid());

        HomeBean adBean5 = new HomeBean();
        adBean5.setType(5);
        adBean5.setMiddleBannerList(resultBean.getMiddleBannerList());

        HomeBean adBean6 = new HomeBean();
        adBean6.setType(6);
        adBean6.setThousandOptimization(resultBean.getThousandOptimization());

        HomeBean adBean7 = new HomeBean();
        adBean7.setType(7);
        adBean7.setEveryoneCommunityList(resultBean.getEveryoneCommunityList());

        HomeBean adBean8 = new HomeBean();
        adBean8.setType(8);
        adBean8.setEveryoneHome(resultBean.getEveryoneHome());

        HomeBean adBean9 = new HomeBean();
        adBean9.setType(9);
        adBean9.setEveryoneHome(resultBean.getEveryoneHome());

        homeBeanList.add(adBean);
        homeBeanList.add(adBean1);
        homeBeanList.add(adBean2);
        homeBeanList.add(adBean3);
        homeBeanList.add(adBean4);
        homeBeanList.add(adBean5);
        homeBeanList.add(adBean6);
        homeBeanList.add(adBean7);
        homeBeanList.add(adBean8);
        homeBeanList.add(adBean9);
        mHomeAdapter.addAll(homeBeanList);
    }

    @Override
    public void getHomeListError(int code, String msg) {

    }
}
