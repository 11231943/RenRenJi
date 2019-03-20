package com.trade.rrenji.biz.category.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import com.gelitenight.superrecyclerview.SuperRecyclerView;
import com.trade.rrenji.R;
import com.trade.rrenji.bean.category.NetCategoryListBean;
import com.trade.rrenji.biz.base.BaseActivity;
import com.trade.rrenji.biz.category.presenter.CategoryActivityListPresenter;
import com.trade.rrenji.biz.category.presenter.CategoryActivityListPresenterImpl;
import com.trade.rrenji.biz.category.ui.apater.CategoryClassListAdapter;
import com.trade.rrenji.biz.category.ui.apater.CategoryListAdapter;
import com.trade.rrenji.biz.category.ui.view.CategoryActivityListView;
import com.trade.rrenji.fragment.DryingTabFragment;
import com.trade.rrenji.utils.Contetns;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.List;

@ContentView(R.layout.base_activity_super_recyclerview)
public class CategoryClassListActivity extends BaseActivity implements CategoryActivityListView {

    private static String TAG = DryingTabFragment.class.getSimpleName();
    @ViewInject(R.id.base_activity_recycler_view)
    public SuperRecyclerView mSuperRecyclerView;

    CategoryActivityListPresenter mPresenter = null;
    CategoryClassListAdapter mCategoryListAdapter;

    private String mId;
    private String mType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActionBarTitle("产品列表");
        mId = getIntent().getStringExtra("id");
        mType = getIntent().getStringExtra("type");
        init();
    }

    private void init() {
        mCategoryListAdapter = new CategoryClassListAdapter(this);
//        mSuperRecyclerView.addItemDecoration(new LinearSpacingDecoration(5, 5));
        mSuperRecyclerView.setAdapter(mCategoryListAdapter);
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
        mPresenter.getClassifyDataByType(this, mId, mType);
    }


    @Override
    protected void attachPresenter() {
        mPresenter = new CategoryActivityListPresenterImpl(this);
        mPresenter.attachView(this);
    }

    @Override
    protected void detachPresenter() {
        mPresenter.detachView();
        mPresenter = null;
    }

    @Override
    public void getCategoryListSuccess(NetCategoryListBean netShareBean) {
        if (mCategoryListAdapter != null) {
            mCategoryListAdapter.clear();
        }
        List<NetCategoryListBean.DataBean.ResultListBean> listBeans = netShareBean.getData().getResultList();
        mSuperRecyclerView.finishRefreshing();
        mSuperRecyclerView.setHasMoreData(Contetns.hasMoreData(listBeans.size()));
        mSuperRecyclerView.finishMore(!Contetns.hasMoreData(listBeans.size()));
        mCategoryListAdapter.addAll(listBeans);
    }

    @Override
    public void getCategoryCodeListError(int code, String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
