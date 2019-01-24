package com.trade.rrenji.biz.coupon.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.gelitenight.superrecyclerview.LinearSpacingDecoration;
import com.gelitenight.superrecyclerview.SuperRecyclerView;
import com.trade.rrenji.R;
import com.trade.rrenji.bean.address.NetAddressBean;
import com.trade.rrenji.biz.base.BaseActivity;
import com.trade.rrenji.biz.coupon.presenter.CouponActivityPresenter;
import com.trade.rrenji.biz.coupon.presenter.CouponActivityPresenterImpl;
import com.trade.rrenji.biz.coupon.ui.adapter.CouponAdapter;
import com.trade.rrenji.biz.coupon.ui.view.CouponActivityView;
import com.trade.rrenji.fragment.DryingTabFragment;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;


@ContentView(R.layout.base_activity_super_recyclerview)
public class CouponActivity extends BaseActivity implements CouponActivityView {

    private static String TAG = DryingTabFragment.class.getSimpleName();
    @ViewInject(R.id.base_activity_recycler_view)
    public SuperRecyclerView mSuperRecyclerView;

    CouponActivityPresenter mPresenter;
    CouponAdapter mCouponAdapter = null;


    private int mIndexPage = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        setActionBarTitle("优惠券");
        mCouponAdapter = new CouponAdapter(this);
        mSuperRecyclerView.addItemDecoration(new LinearSpacingDecoration(5, 5));
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
    public void getCouponList(NetAddressBean netShareBean) {

    }

    @Override
    public void getCouponListError(int code, String msg) {

    }
}
