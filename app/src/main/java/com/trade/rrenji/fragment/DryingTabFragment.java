package com.trade.rrenji.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.reflect.TypeToken;
import com.trade.rrenji.bean.home.NetHomeBean;
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
//@ContentView(R.layout.fragment_message)
public class DryingTabFragment extends BaseFragment implements DryActivityView {
    public static final String CACHE_KEY = "drying";
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
        mSuperRecyclerView.setRecyclerPadding(0, 0, 0, 0);
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
            Type resultType = new TypeToken<NetShareBean>() {
            }.getType();
            Reservoir.getAsync(CACHE_KEY, resultType, new ReservoirCallback<NetShareBean>() {
                @Override
                public void onSuccess(NetShareBean data) {
                    Log.d(TAG, "onSuccess: load drying cache succeed");
                    initHomeBean(data);
                }

                @Override
                public void onFailure(Exception e) {
                    Log.d(TAG, "onFailure: load nearby cache onFailure");
                }
            });
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

    private void initHomeBean(NetShareBean netShareBean) {
        if (mIndexPage == 1) {
            if (mDryListAdapter != null) {
                mDryListAdapter.clear();
            }
        }
        if (!isFirst) {
            isFirst = true;
        }
        List<NetShareBean.ResultBean.ShareOrdersBean> ordersBeans = netShareBean.getResult().getShareOrders();
        mSuperRecyclerView.finishRefreshing();
        mSuperRecyclerView.setHasMoreData(Contetns.hasMoreData(ordersBeans.size()));
        mSuperRecyclerView.finishMore(!Contetns.hasMoreData(ordersBeans.size()));
        mDryListAdapter.addAll(ordersBeans);

    }

    @Override
    public void getDryingSuccess(NetShareBean netShareBean) {
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
