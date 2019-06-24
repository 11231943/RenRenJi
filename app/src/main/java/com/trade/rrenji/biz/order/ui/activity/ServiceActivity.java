package com.trade.rrenji.biz.order.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import com.gelitenight.superrecyclerview.LinearSpacingDecoration;
import com.gelitenight.superrecyclerview.SuperRecyclerView;
import com.trade.rrenji.R;
import com.trade.rrenji.bean.order.NetServiceBean;
import com.trade.rrenji.biz.base.BaseActivity;
import com.trade.rrenji.biz.order.presenter.ServiceActivityPresenter;
import com.trade.rrenji.biz.order.presenter.ServiceActivityPresenterImpl;
import com.trade.rrenji.biz.order.ui.adapter.ServiceAdminAdapter;
import com.trade.rrenji.biz.order.ui.view.ServiceActivityView;
import com.trade.rrenji.utils.Contetns;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.List;

@ContentView(R.layout.base_activity_super_recyclerview)
public class ServiceActivity extends BaseActivity implements ServiceActivityView {

    private static String TAG = ServiceActivity.class.getSimpleName();
    @ViewInject(R.id.base_activity_recycler_view)
    public SuperRecyclerView mSuperRecyclerView;

    ServiceActivityPresenter mPresenter;
    ServiceAdminAdapter mOrderAdminAdapter = null;

    private int mIndexPage = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        setActionBarTitle("售后维修");
        mSuperRecyclerView.setRecyclerPadding(0, 10, 0, 0);
        mOrderAdminAdapter = new ServiceAdminAdapter(this);
        mSuperRecyclerView.addItemDecoration(new LinearSpacingDecoration(10, 5));
        mSuperRecyclerView.setAdapter(mOrderAdminAdapter);
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
        mPresenter.getAfterSaleOrderList(this, mIndexPage);
    }

    @Override
    protected void attachPresenter() {
        mPresenter = new ServiceActivityPresenterImpl(this);
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
    public void getAfterSaleOrderListSuccess(NetServiceBean netBaseResultBean) {
        if (mIndexPage == 1) {
            if (mOrderAdminAdapter != null) {
                mOrderAdminAdapter.clear();
            }
        }
        List<NetServiceBean.DataBean.ReListBean> listBeans = netBaseResultBean.getData().getReList();
        mSuperRecyclerView.finishRefreshing();
        mSuperRecyclerView.setHasMoreData(Contetns.hasMoreData(listBeans.size()));
        mSuperRecyclerView.finishMore(!Contetns.hasMoreData(listBeans.size()));
        mOrderAdminAdapter.addAll(listBeans);
    }

    @Override
    public void getAfterSaleOrderListError(int code, String msg) {

    }

}
