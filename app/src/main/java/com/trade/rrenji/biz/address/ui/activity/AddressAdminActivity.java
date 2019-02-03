package com.trade.rrenji.biz.address.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;

import com.gelitenight.superrecyclerview.LinearSpacingDecoration;
import com.gelitenight.superrecyclerview.SuperRecyclerView;
import com.trade.rrenji.R;
import com.trade.rrenji.bean.address.NetAddressBean;
import com.trade.rrenji.biz.address.presenter.AddressActivityPresenter;
import com.trade.rrenji.biz.address.presenter.AddressActivityPresenterImpl;
import com.trade.rrenji.biz.address.ui.adapter.AddressAdminAdapter;
import com.trade.rrenji.biz.address.ui.view.AddressActivityView;
import com.trade.rrenji.biz.base.BaseActivity;
import com.trade.rrenji.fragment.DryingTabFragment;
import com.trade.rrenji.utils.Contetns;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.List;

@ContentView(R.layout.base_activity_super_recyclerview)
public class AddressAdminActivity extends BaseActivity implements AddressActivityView {

    private static String TAG = DryingTabFragment.class.getSimpleName();
    @ViewInject(R.id.base_activity_recycler_view)
    public SuperRecyclerView mSuperRecyclerView;

    AddressActivityPresenter mPresenter;
    AddressAdminAdapter mAddressAdminAdapter = null;


    private int mIndexPage = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_address, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_address_btn:
                Intent intent =new Intent(this,UpdateAddressActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void init() {
        setActionBarTitle("地址管理");
        mAddressAdminAdapter = new AddressAdminAdapter(this);
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
    }

    private void loadData() {
        mPresenter.getAddressList(this, mIndexPage);
    }

    @Override
    protected void attachPresenter() {
        mPresenter = new AddressActivityPresenterImpl(this);
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
    public void getAddressListSuccess(NetAddressBean netShareBean) {
        if (mIndexPage == 1) {
            if(mAddressAdminAdapter!=null){
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
}
