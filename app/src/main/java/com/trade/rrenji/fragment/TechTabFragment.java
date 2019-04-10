package com.trade.rrenji.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gelitenight.superrecyclerview.LinearSpacingDecoration;
import com.gelitenight.superrecyclerview.SuperRecyclerView;
import com.google.gson.reflect.TypeToken;
import com.trade.rrenji.R;
import com.trade.rrenji.bean.drying.NetShareBean;
import com.trade.rrenji.bean.tech.NetTechBean;
import com.trade.rrenji.biz.base.BaseFragment;
import com.trade.rrenji.biz.tech.presenter.TechActivityPresenter;
import com.trade.rrenji.biz.tech.presenter.TechActivityPresenterImpl;
import com.trade.rrenji.biz.tech.ui.adapter.TechListAdapter;
import com.trade.rrenji.biz.tech.ui.view.TechActivityView;
import com.trade.rrenji.utils.Contetns;
import com.trade.rrenji.utils.StatusBarUtils;
import com.trade.rrenji.utils.reservoir.Reservoir;
import com.trade.rrenji.utils.reservoir.ReservoirCallback;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by monster on 23/3/18.
 */

//@ContentView(R.layout.fragment_goods)
public class TechTabFragment extends BaseFragment implements TechActivityView {
    private static String TAG = TechTabFragment.class.getSimpleName();
    public static final String CACHE_KEY = "tech";
    @ViewInject(R.id.order_recycler_view)
    public SuperRecyclerView mSuperRecyclerView;

    TechListAdapter mTechListAdapter;

    private int mIndexPage = 1;

    TechActivityPresenter mTechActivityPresenter = null;

    @Override
    protected void initView() {
        mSuperRecyclerView = rootView.findViewById(R.id.order_recycler_view);
        init();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_goods;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTechActivityPresenter = new TechActivityPresenterImpl(getActivity());
        mTechActivityPresenter.attachView(this);
        mTechListAdapter = new TechListAdapter(getActivity());
    }

    private void init() {
        mSuperRecyclerView.addItemDecoration(new LinearSpacingDecoration(0, 0));
        mSuperRecyclerView.setAdapter(mTechListAdapter);
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
            Type resultType = new TypeToken<NetTechBean>() {
            }.getType();
            Reservoir.getAsync(CACHE_KEY, resultType, new ReservoirCallback<NetTechBean>() {
                @Override
                public void onSuccess(NetTechBean data) {
                    Log.d(TAG, "onSuccess: load tech cache succeed");
                    initHomeBean(data);
                }

                @Override
                public void onFailure(Exception e) {
                    Log.d(TAG, "onFailure: load tech cache onFailure");
                }
            });
            mSuperRecyclerView.startRefreshing(true, true);
        }
    }

    private void initHomeBean(NetTechBean netShareBean) {
        if (mIndexPage == 1) {
            if (mTechListAdapter != null) {
                mTechListAdapter.clear();
            }
        }
        if (!isFirst) {
            isFirst = true;
        }
        List<NetTechBean.ResultBean.CommunityListBean> ordersBeans = netShareBean.getResult().getCommunityList();
        mSuperRecyclerView.finishRefreshing();
        mSuperRecyclerView.setHasMoreData(Contetns.hasMoreData(ordersBeans.size()));
        mSuperRecyclerView.finishMore(!Contetns.hasMoreData(ordersBeans.size()));
        mTechListAdapter.addAll(ordersBeans);
    }


    private void loadData() {
        mTechActivityPresenter.getTechList(getActivity(), mIndexPage);
    }

    @Override
    protected void attachPresenter() {

    }

    @Override
    protected void detachPresenter() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mTechActivityPresenter.detachView();
        mTechActivityPresenter = null;
    }

    @Override
    public void getTechSuccess(NetTechBean netShareBean) {
        if (mIndexPage == 1) {
            Reservoir.putAsync(CACHE_KEY, netShareBean, new ReservoirCallback<Void>() {
                @Override
                public void onSuccess(Void data) {
                    Log.d(TAG, "save nearby cache succeed");
                    try {
                        Log.d(TAG, "used bytes: " + Reservoir.bytesUsed());
                    } catch (Exception e) {
                        // ignore
                    }
                }

                @Override
                public void onFailure(Exception e) {
                    Log.e(TAG, "save nearby cache failed", e);
                }
            });
        }
        initHomeBean(netShareBean);
    }

    @Override
    public void getTechError(int code, String msg) {
        Log.e("getDryingCodeError", "code = " + code + " msg = " + msg);
    }


}
