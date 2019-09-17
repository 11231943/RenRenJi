package com.trade.rrenji.biz.coupon.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.gelitenight.superrecyclerview.LinearSpacingDecoration;
import com.gelitenight.superrecyclerview.SuperRecyclerView;
import com.trade.rrenji.R;
import com.trade.rrenji.bean.coupon.NetCouponBean;
import com.trade.rrenji.biz.base.BaseActivity;
import com.trade.rrenji.biz.coupon.presenter.CouponActivityPresenter;
import com.trade.rrenji.biz.coupon.presenter.CouponActivityPresenterImpl;
import com.trade.rrenji.biz.coupon.ui.adapter.CouponAdapter;
import com.trade.rrenji.biz.coupon.ui.view.CouponActivityView;
import com.trade.rrenji.fragment.DryingTabFragment;
import com.trade.rrenji.utils.Contetns;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class CouponActivity extends BaseActivity implements CouponActivityView {

    private static String TAG = DryingTabFragment.class.getSimpleName();
    CouponActivityPresenter mPresenter;
    CouponAdapter mCouponAdapter = null;
    @Bind(R.id.base_activity_recycler_view)
    SuperRecyclerView mSuperRecyclerView;
    private int mIndexPage = 1;
    private int mType = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_activity_super_recyclerview);
        ButterKnife.bind(this);
        init();
        mType = getIntent().getIntExtra("type", -1);
    }

    private void init() {
        setActionBarTitle("优惠券");
        mCouponAdapter = new CouponAdapter(this);
        mCouponAdapter.setOnClickListener(new CouponAdapter.onClickListener() {
            @Override
            public void onClick(NetCouponBean.ResultBean.CouponListBean data) {
                if (mType == 1) {
                    Intent intent = new Intent();
                    intent.putExtra("data", data);
                    setResult(10001, intent);
                }
                finish();
            }
        });
        mSuperRecyclerView.setRecyclerPadding(0, 10, 0, 0);
        mSuperRecyclerView.addItemDecoration(new LinearSpacingDecoration(20, 20));
        mSuperRecyclerView.setAdapter(mCouponAdapter);
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
        mPresenter.getCouponList(this, mIndexPage);
    }

    @Override
    protected void attachPresenter() {
        mPresenter = new CouponActivityPresenterImpl(this);
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
    public void getCouponList(NetCouponBean netShareBean) {
        if (mIndexPage == 1) {
            if (mCouponAdapter != null) {
                mCouponAdapter.clear();
            }
        }
        List<NetCouponBean.ResultBean.CouponListBean> listBeans = netShareBean.getResult().getCouponList();
        mSuperRecyclerView.finishRefreshing();
        mSuperRecyclerView.setHasMoreData(Contetns.hasMoreData(listBeans.size()));
        mSuperRecyclerView.finishMore(!Contetns.hasMoreData(listBeans.size()));
        mCouponAdapter.addAll(listBeans);
    }

    @Override
    public void getCouponListError(int code, String msg) {

    }
}
