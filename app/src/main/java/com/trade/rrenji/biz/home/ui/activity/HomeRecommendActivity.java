package com.trade.rrenji.biz.home.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import com.gelitenight.superrecyclerview.SuperRecyclerView;
import com.trade.rrenji.R;
import com.trade.rrenji.bean.category.NetCategoryListBean;
import com.trade.rrenji.biz.base.BaseActivity;
import com.trade.rrenji.biz.home.presenter.HomeRecommendActivityPresenter;
import com.trade.rrenji.biz.home.presenter.HomeRecommendActivityPresenterImpl;
import com.trade.rrenji.biz.home.ui.adapter.HomeRecommendAdapter;
import com.trade.rrenji.biz.home.ui.view.HomeRecommendActivityView;
import com.trade.rrenji.fragment.DryingTabFragment;
import com.trade.rrenji.utils.Contetns;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.List;

@ContentView(R.layout.base_activity_super_recyclerview)
public class HomeRecommendActivity extends BaseActivity implements HomeRecommendActivityView {

    private static String TAG = DryingTabFragment.class.getSimpleName();
    @ViewInject(R.id.base_activity_recycler_view)
    public SuperRecyclerView mSuperRecyclerView;

    HomeRecommendActivityPresenter mPresenter = null;
    HomeRecommendAdapter mCategoryListAdapter;

    private int mType;
    private int mPageIndex = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActionBarTitle("产品列表");
        mType = getIntent().getIntExtra("type", -1);
        init();
    }

    private void init() {
        mCategoryListAdapter = new HomeRecommendAdapter(this);
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
        mPresenter.getNewHomeGoodsMoreByTypes(this, mType, mPageIndex, 20);
    }


    @Override
    protected void attachPresenter() {
        mPresenter = new HomeRecommendActivityPresenterImpl(this);
        mPresenter.attachView(this);
    }

    @Override
    protected void detachPresenter() {
        mPresenter.detachView();
        mPresenter = null;
    }

    @Override
    public void getNewHomeGoodsMoreByTypesSuccess(NetCategoryListBean netShareBean) {
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
    public void getNewHomeGoodsMoreByTypesError(int code, String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
