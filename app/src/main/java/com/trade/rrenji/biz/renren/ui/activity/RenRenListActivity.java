package com.trade.rrenji.biz.renren.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import com.gelitenight.superrecyclerview.LinearSpacingDecoration;
import com.gelitenight.superrecyclerview.SuperRecyclerView;
import com.trade.rrenji.R;
import com.trade.rrenji.bean.renren.NetRenRebBean;
import com.trade.rrenji.biz.base.BaseActivity;
import com.trade.rrenji.biz.renren.presenter.RenRenActivityPresenter;
import com.trade.rrenji.biz.renren.presenter.RenRenActivityPresenterImpl;
import com.trade.rrenji.biz.renren.ui.apater.HotCommunityAdapter;
import com.trade.rrenji.biz.renren.ui.view.RenRenActivityView;
import com.trade.rrenji.fragment.DryingTabFragment;
import com.trade.rrenji.utils.Contetns;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RenRenListActivity extends BaseActivity implements RenRenActivityView {

    private static String TAG = DryingTabFragment.class.getSimpleName();
    RenRenActivityPresenter mPresenter = null;
    HotCommunityAdapter mCategoryListAdapter;
    @Bind(R.id.base_activity_recycler_view)
    SuperRecyclerView mSuperRecyclerView;
    private int mPage = 1;
    private int mRows = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_activity_super_recyclerview);
        ButterKnife.bind(this);
        setActionBarTitle("人人之家");
        init();
    }

    private void init() {
        mCategoryListAdapter = new HotCommunityAdapter(this);
        mSuperRecyclerView.addItemDecoration(new LinearSpacingDecoration(5, 5));
        mSuperRecyclerView.setAdapter(mCategoryListAdapter);
        mSuperRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mSuperRecyclerView.setOnLoadDataListener(new SuperRecyclerView.OnLoadDataListener() {
            @Override
            public void onRefresh() {
                mPage = 1;
                loadData();
            }

            @Override
            public void onMore() {
                mPage++;
                loadData();
            }
        });
        mSuperRecyclerView.startRefreshing(true);
    }

    private void loadData() {
        mPresenter.getEveryoneHomeList(this, mPage, mRows);
    }


    @Override
    protected void attachPresenter() {
        mPresenter = new RenRenActivityPresenterImpl(this);
        mPresenter.attachView(this);
    }

    @Override
    protected void detachPresenter() {
        mPresenter.detachView();
        mPresenter = null;
    }

    @Override
    public void getEveryoneHomeListSuccess(NetRenRebBean netShareBean) {
        if (mPage == 1 && mCategoryListAdapter != null) {
            mCategoryListAdapter.clear();
        }
        List<NetRenRebBean.DataBean.EveryoneHomeListBean> listBeans = netShareBean.getData().getEveryoneHomeList();
        mSuperRecyclerView.finishRefreshing();
        mSuperRecyclerView.setHasMoreData(Contetns.hasMoreData(listBeans.size()));
        mSuperRecyclerView.finishMore(!Contetns.hasMoreData(listBeans.size()));
        mCategoryListAdapter.addAll(listBeans);
    }

    @Override
    public void getEveryoneHomeListError(int code, String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}
