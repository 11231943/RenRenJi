package com.trade.rrenji.biz.address.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.gelitenight.superrecyclerview.LinearSpacingDecoration;
import com.gelitenight.superrecyclerview.SuperRecyclerView;
import com.trade.rrenji.R;
import com.trade.rrenji.bean.address.NetAddressBean;
import com.trade.rrenji.biz.address.presenter.AddressActivityPresenter;
import com.trade.rrenji.biz.address.presenter.AddressActivityPresenterImpl;
import com.trade.rrenji.biz.address.presenter.DelAddressActivityPresenter;
import com.trade.rrenji.biz.address.presenter.DelAddressActivityPresenterImpl;
import com.trade.rrenji.biz.address.ui.adapter.AddressAdminAdapter;
import com.trade.rrenji.biz.address.ui.view.AddressActivityView;
import com.trade.rrenji.biz.address.ui.view.DelAddressActivityView;
import com.trade.rrenji.biz.base.BaseActivity;
import com.trade.rrenji.biz.base.NetBaseResultBean;
import com.trade.rrenji.event.address.GoAddressActivityEvent;
import com.trade.rrenji.fragment.DryingTabFragment;
import com.trade.rrenji.utils.Contetns;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AddressAdminActivity extends BaseActivity implements AddressActivityView, DelAddressActivityView {

    private static String TAG = DryingTabFragment.class.getSimpleName();
    AddressActivityPresenter mPresenter;
    DelAddressActivityPresenter mDelAddressActivityPresenter;
    AddressAdminAdapter mAddressAdminAdapter = null;
    @Bind(R.id.base_activity_recycler_view)
    SuperRecyclerView mSuperRecyclerView;

    private int mIndexPage = 1;
    private int mType = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_activity_super_recyclerview);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        init();
        mType = getIntent().getIntExtra("type", -1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_address, menu);
        return true;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(GoAddressActivityEvent event) {
        mSuperRecyclerView.startRefreshing(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_address_btn:
                Intent intent = new Intent(this, UpdateAddressActivity.class);
                intent.putExtra("type", 0);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void init() {
        setActionBarTitle("地址管理");
        mAddressAdminAdapter = new AddressAdminAdapter(this);
        mAddressAdminAdapter.setOnClickListener(new AddressAdminAdapter.onClickListener() {
            @Override
            public void onClick(NetAddressBean.ResultBean.AddressListBean data) {
                if (mType == 1) {
                    Intent intent = new Intent();
                    intent.putExtra("data", data);
                    setResult(10002, intent);
                    finish();
                }
            }
        });
        mAddressAdminAdapter.setOnClickUpdateListener(new AddressAdminAdapter.OnClickUpdateListener() {
            @Override
            public void onClick(NetAddressBean.ResultBean.AddressListBean data, int type) {
                Intent intent = new Intent(AddressAdminActivity.this, UpdateAddressActivity.class);
                intent.putExtra("type", 1);
                intent.putExtra("address", data);
                startActivity(intent);
            }
        });
        mSuperRecyclerView.addItemDecoration(new LinearSpacingDecoration(5, 5));
        mSuperRecyclerView.setAdapter(mAddressAdminAdapter);
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
        mAddressAdminAdapter.setOnClickDelListener(new AddressAdminAdapter.OnClickDelListener() {
            @Override
            public void onClick(String addressId) {
                mDelAddressActivityPresenter.delAddressList(AddressAdminActivity.this, addressId);
            }
        });
        mAddressAdminAdapter.setOnClickSetDefaultListener(new AddressAdminAdapter.OnClickSetDefaultListener() {
            @Override
            public void onClick(String addressId, int type) {
                if (type == 1) {
                    mDelAddressActivityPresenter.isNotUpAddress(AddressAdminActivity.this, addressId);
                } else {
                    mDelAddressActivityPresenter.isUpAddress(AddressAdminActivity.this, addressId);
                }
            }
        });
    }

    private void loadData() {
        mPresenter.getAddressList(this, mIndexPage);
    }

    @Override
    protected void attachPresenter() {
        mPresenter = new AddressActivityPresenterImpl(this);
        mPresenter.attachView(this);
        mDelAddressActivityPresenter = new DelAddressActivityPresenterImpl(this);
        mDelAddressActivityPresenter.attachView(this);
    }

    @Override
    protected void detachPresenter() {
        mPresenter.detachView();
        mPresenter = null;
        mDelAddressActivityPresenter.detachView();
        mDelAddressActivityPresenter = null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void delAddressListSuccess(NetBaseResultBean netShareBean) {
        if (netShareBean.getCode().equals("0")) {
            Toast.makeText(this, "删除成功!", Toast.LENGTH_SHORT);
            mIndexPage = 1;
            mPresenter.getAddressList(this, mIndexPage);
        } else {
            Toast.makeText(this, "删除失败!", Toast.LENGTH_SHORT);
        }
    }

    @Override
    public void delAddressListError(int code, String msg) {
        Toast.makeText(this, "删除失败!", Toast.LENGTH_SHORT);
    }

    @Override
    public void getAddressListSuccess(NetAddressBean netShareBean) {
        if (mIndexPage == 1) {
            if (mAddressAdminAdapter != null) {
                mAddressAdminAdapter.clear();
            }
        }
        List<NetAddressBean.ResultBean.AddressListBean> listBeans = netShareBean.getResult().getAddressList();
        mSuperRecyclerView.finishRefreshing();
        mSuperRecyclerView.setHasMoreData(Contetns.hasMoreData(listBeans.size()));
        mSuperRecyclerView.finishMore(!Contetns.hasMoreData(listBeans.size()));
        mAddressAdminAdapter.addAll(listBeans);
    }

    @Override
    public void getAddressListError(int code, String msg) {

    }

    @Override
    public void isUpAddressSuccess(NetBaseResultBean netShareBean) {
        if (netShareBean.getCode().equals(Contetns.RESPONSE_OK)) {
            loadData();
            Toast.makeText(this, "设置默认地址成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "设置默认地址成功", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void isUpAddressError(int code, String msg) {
        Toast.makeText(this, "设置默认地址成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void isNotUpAddressError(int code, String msg) {
        Toast.makeText(this, "操作失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void isNotUpAddressSuccess(NetBaseResultBean netShareBean) {
        if (netShareBean.getCode().equals(Contetns.RESPONSE_OK)) {
            loadData();
            Toast.makeText(this, "取消默认地址成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "取消默认地址成功", Toast.LENGTH_SHORT).show();
        }
    }
}
