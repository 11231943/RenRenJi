package com.trade.rrenji.biz.community.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import com.gelitenight.superrecyclerview.LinearSpacingDecoration;
import com.gelitenight.superrecyclerview.SuperRecyclerView;
import com.trade.rrenji.R;
import com.trade.rrenji.bean.communty.NetCommuntyBean;
import com.trade.rrenji.biz.base.BaseActivity;
import com.trade.rrenji.biz.community.presenter.CommunityActivityPresenter;
import com.trade.rrenji.biz.community.presenter.CommunityActivityPresenterImpl;
import com.trade.rrenji.biz.community.ui.apater.HotCommunityAdapter;
import com.trade.rrenji.biz.community.ui.view.CommunityActivityView;
import com.trade.rrenji.fragment.DryingTabFragment;
import com.trade.rrenji.utils.Contetns;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.List;

@ContentView(R.layout.base_activity_super_recyclerview)
public class CommunityListActivity extends BaseActivity implements CommunityActivityView {

    private static String TAG = DryingTabFragment.class.getSimpleName();
    @ViewInject(R.id.base_activity_recycler_view)
    public SuperRecyclerView mSuperRecyclerView;

    CommunityActivityPresenter mPresenter = null;
    HotCommunityAdapter mCategoryListAdapter;
    private int mPage = 1;
    private int mRows = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActionBarTitle("人人社区");
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
        mPresenter.getEveryoneCommunityList(this, mPage, mRows);
    }


    @Override
    protected void attachPresenter() {
        mPresenter = new CommunityActivityPresenterImpl(this);
        mPresenter.attachView(this);
    }

    @Override
    protected void detachPresenter() {
        mPresenter.detachView();
        mPresenter = null;
    }

    @Override
    public void getEveryoneCommunityListSuccess(NetCommuntyBean netShareBean) {
        if (mPage == 1 && mCategoryListAdapter != null) {
            mCategoryListAdapter.clear();
        }
        List<NetCommuntyBean.DataBean.EveryoneCommunityListBean> listBeans = netShareBean.getData().getEveryoneCommunityList();
        mSuperRecyclerView.finishRefreshing();
        mSuperRecyclerView.setHasMoreData(Contetns.hasMoreData(listBeans.size()));
        mSuperRecyclerView.finishMore(!Contetns.hasMoreData(listBeans.size()));
        mCategoryListAdapter.addAll(listBeans);
    }

    @Override
    public void getEveryoneCommunityListError(int code, String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}
