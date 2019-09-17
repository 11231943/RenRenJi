package com.trade.rrenji.biz.data.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import com.gelitenight.superrecyclerview.SuperRecyclerView;
import com.trade.rrenji.R;
import com.trade.rrenji.bean.home.NetHotDataBean;
import com.trade.rrenji.biz.base.BaseActivity;
import com.trade.rrenji.biz.data.presenter.DataActivityListPresenter;
import com.trade.rrenji.biz.data.presenter.DataActivityListPresenterImpl;
import com.trade.rrenji.biz.data.ui.apater.DataListAdapter;
import com.trade.rrenji.biz.data.ui.view.DataActivityListView;
import com.trade.rrenji.fragment.DryingTabFragment;
import com.trade.rrenji.utils.Contetns;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

@ContentView(R.layout.base_activity_super_recyclerview)
public class DataListActivity extends BaseActivity implements DataActivityListView {

    private static String TAG = DryingTabFragment.class.getSimpleName();
    DataActivityListPresenter mPresenter = null;
    DataListAdapter mDataListAdapter;
    @Bind(R.id.base_activity_recycler_view)
    SuperRecyclerView mSuperRecyclerView;

    private String mId;
    private int mPage = 1;
    private int mPageSize = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_activity_super_recyclerview);
        ButterKnife.bind(this);
        setActionBarTitle("热门活动");
        mId = getIntent().getStringExtra("id");
        init();
    }

    private void init() {
        mDataListAdapter = new DataListAdapter(this);
//        mSuperRecyclerView.addItemDecoration(new LinearSpacingDecoration(5, 5));
        mSuperRecyclerView.setAdapter(mDataListAdapter);
        mSuperRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mSuperRecyclerView.setOnLoadDataListener(new SuperRecyclerView.OnLoadDataListener() {
            @Override
            public void onRefresh() {
                loadData();
            }

            @Override
            public void onMore() {
                loadData();
            }
        });
        mSuperRecyclerView.startRefreshing(true);
    }

    private void loadData() {
        mPresenter.getHotDataById(this, mId, mPage, mPageSize);
    }


    @Override
    protected void attachPresenter() {
        mPresenter = new DataActivityListPresenterImpl(this);
        mPresenter.attachView(this);
    }

    @Override
    protected void detachPresenter() {
        mPresenter.detachView();
        mPresenter = null;
    }

    @Override
    public void getHotListSuccess(NetHotDataBean netShareBean) {
        if (mDataListAdapter != null) {
            mDataListAdapter.clear();
        }
        List<NetHotDataBean.DataBean.ResultListBean> listBeans = netShareBean.getData().getResultList();
        mSuperRecyclerView.finishRefreshing();
        mSuperRecyclerView.setHasMoreData(Contetns.hasMoreData(listBeans.size()));
        mSuperRecyclerView.finishMore(!Contetns.hasMoreData(listBeans.size()));
        mDataListAdapter.addAll(listBeans);
    }

    @Override
    public void getHotListError(int code, String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}
