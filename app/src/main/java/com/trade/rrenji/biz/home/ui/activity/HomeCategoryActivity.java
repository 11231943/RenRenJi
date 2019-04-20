package com.trade.rrenji.biz.home.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.gelitenight.superrecyclerview.SuperRecyclerView;
import com.trade.rrenji.R;
import com.trade.rrenji.bean.category.NetCategoryListBean;
import com.trade.rrenji.biz.base.BaseActivity;
import com.trade.rrenji.biz.category.presenter.CategoryActivityListPresenter;
import com.trade.rrenji.biz.category.presenter.CategoryActivityListPresenterImpl;
import com.trade.rrenji.biz.category.ui.apater.CategoryClassListAdapter;
import com.trade.rrenji.biz.category.ui.view.CategoryActivityListView;
import com.trade.rrenji.biz.home.presenter.HomeCategoryActivityPresenter;
import com.trade.rrenji.biz.home.presenter.HomeCategoryActivityPresenterImpl;
import com.trade.rrenji.biz.home.ui.adapter.HomeCategoryAdapter;
import com.trade.rrenji.biz.home.ui.view.HomeCategoryActivityView;
import com.trade.rrenji.fragment.DryingTabFragment;
import com.trade.rrenji.utils.Contetns;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.List;

@ContentView(R.layout.home_activity_category_super_recyclerview)
public class HomeCategoryActivity extends BaseActivity implements HomeCategoryActivityView {

    private static String TAG = DryingTabFragment.class.getSimpleName();
    @ViewInject(R.id.base_activity_recycler_view)
    public SuperRecyclerView mSuperRecyclerView;

    @ViewInject(R.id.price_select_layout)
    public RelativeLayout price_select_layout;
    @ViewInject(R.id.price_sort_layout)
    public RelativeLayout price_sort_layout;

    HomeCategoryActivityPresenter mPresenter = null;
    HomeCategoryAdapter mCategoryListAdapter;

    private String mCategoryId;
    private int mPageIndex = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActionBarTitle("产品列表");
        mCategoryId = getIntent().getStringExtra("categoryId");
        init();
    }

    private void init() {
        mCategoryListAdapter = new HomeCategoryAdapter(this);
//        mSuperRecyclerView.addItemDecoration(new LinearSpacingDecoration(5, 5));
        mSuperRecyclerView.setAdapter(mCategoryListAdapter);
        mSuperRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mSuperRecyclerView.setOnLoadDataListener(new SuperRecyclerView.OnLoadDataListener() {
            @Override
            public void onRefresh() {
                mPageIndex = 1;
                loadData();
            }

            @Override
            public void onMore() {
                mPageIndex++;
                loadData();
            }
        });
        mSuperRecyclerView.startRefreshing(true);
    }

    private void loadData() {
        mPresenter.getCategoryDetailById(this, mCategoryId, mPageIndex, 20);
    }


    @Override
    protected void attachPresenter() {
        mPresenter = new HomeCategoryActivityPresenterImpl(this);
        mPresenter.attachView(this);
    }

    @Override
    protected void detachPresenter() {
        mPresenter.detachView();
        mPresenter = null;
    }

    @Override
    public void getHomeCategorySuccess(NetCategoryListBean netShareBean) {
        if (mPageIndex == 1 && mCategoryListAdapter != null) {
            mCategoryListAdapter.clear();
        }
        List<NetCategoryListBean.DataBean.ResultListBean> listBeans = netShareBean.getData().getResultList();
        mSuperRecyclerView.finishRefreshing();
        mSuperRecyclerView.setHasMoreData(Contetns.hasMoreData(listBeans.size()));
        mSuperRecyclerView.finishMore(!Contetns.hasMoreData(listBeans.size()));
        mCategoryListAdapter.addAll(listBeans);
    }

    @Override
    public void getHomeCategoryError(int code, String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
