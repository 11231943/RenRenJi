package com.trade.rrenji.biz.order.ui.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import com.gelitenight.superrecyclerview.LinearSpacingDecoration;
import com.gelitenight.superrecyclerview.SuperRecyclerView;
import com.trade.rrenji.R;
import com.trade.rrenji.bean.order.NetOrderBean;
import com.trade.rrenji.biz.base.BaseActivity;
import com.trade.rrenji.biz.base.NetBaseResultBean;
import com.trade.rrenji.biz.order.presenter.OrderActivityPresenter;
import com.trade.rrenji.biz.order.presenter.OrderActivityPresenterImpl;
import com.trade.rrenji.biz.order.ui.adapter.OrderAdminAdapter;
import com.trade.rrenji.biz.order.ui.view.OrderActivityView;
import com.trade.rrenji.fragment.DryingTabFragment;
import com.trade.rrenji.utils.Contetns;
import com.trade.rrenji.utils.SettingUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.List;

@ContentView(R.layout.base_activity_super_recyclerview)
public class OrderActivity extends BaseActivity implements OrderActivityView {

    private static String TAG = DryingTabFragment.class.getSimpleName();
    @ViewInject(R.id.base_activity_recycler_view)
    public SuperRecyclerView mSuperRecyclerView;

    OrderActivityPresenter mPresenter;
    OrderAdminAdapter mOrderAdminAdapter = null;

    private int mIndexPage = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        setActionBarTitle("待付款");
        mSuperRecyclerView.setRecyclerPadding(0, 20, 0, 0);
        mOrderAdminAdapter = new OrderAdminAdapter(this);
        mSuperRecyclerView.addItemDecoration(new LinearSpacingDecoration(25, 5));
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
        mOrderAdminAdapter.setOnClickDelListener(new OrderAdminAdapter.onClickDelListener() {
            @Override
            public void onClick(final NetOrderBean.DataBean.ResultListBean data) {
                new AlertDialog.Builder(OrderActivity.this)
                        .setMessage("是否删除此订单？")
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mPresenter.delOrder(OrderActivity.this, data.getOrderId());
                            }
                        })
                        .setNegativeButton(R.string.cancel, null)
                        .show();
            }
        });
    }

    private void loadData() {
        mPresenter.getOrderList(this, mIndexPage, "1");
    }

    @Override
    protected void attachPresenter() {
        mPresenter = new OrderActivityPresenterImpl(this);
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
    public void delOrderSuccess(NetBaseResultBean netBaseResultBean) {
        if (netBaseResultBean.getCode().equals(Contetns.RESPONSE_OK)) {
            loadData();
            Toast.makeText(this, "删除成功！", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, netBaseResultBean.getMsg(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void delOrderError(int code, String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
