package com.trade.rrenji.biz.search.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.gelitenight.superrecyclerview.LinearSpacingDecoration;
import com.gelitenight.superrecyclerview.SuperRecyclerView;
import com.trade.rrenji.R;
import com.trade.rrenji.bean.address.NetAddressBean;
import com.trade.rrenji.bean.category.NetCategoryListBean;
import com.trade.rrenji.bean.search.NetSearchBean;
import com.trade.rrenji.bean.search.NetSearchValueBean;
import com.trade.rrenji.biz.base.BaseActivity;
import com.trade.rrenji.biz.search.presenter.SearchActivityPresenter;
import com.trade.rrenji.biz.search.presenter.SearchActivityPresenterImpl;
import com.trade.rrenji.biz.search.ui.adapter.SearchAdapter;
import com.trade.rrenji.biz.search.ui.adapter.SearchValueAdapter;
import com.trade.rrenji.biz.search.ui.view.SearchActivityView;
import com.trade.rrenji.fragment.DryingTabFragment;
import com.trade.rrenji.utils.Contetns;
import com.trade.rrenji.utils.ViewUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.List;


@ContentView(R.layout.search_main_layout)
public class SearchActivity extends BaseActivity implements SearchActivityView {

    private static String TAG = DryingTabFragment.class.getSimpleName();
    @ViewInject(R.id.recycler_view_top)
    public RecyclerView recycler_view_top;

    @ViewInject(R.id.super_recycler_view)
    public SuperRecyclerView mSuperRecyclerView;

    @ViewInject(R.id.search_value)
    public EditText search_value;

    @ViewInject(R.id.btn_search)
    public TextView btn_search;

    SearchActivityPresenter mSearchActivityPresenter;
    SearchAdapter mSearchAdapter;
    SearchValueAdapter mSearchValueAdapter;

    private int mPageIndex = 1;
    private int mPageSize = 20;
    private int mPriceSort = 0;
    private String mGoodsName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(search_value.getText().toString().trim())) {
                    mGoodsName = search_value.getText().toString().trim();
                    loadData();
                    ViewUtils.hideKeyboard(SearchActivity.this);
                } else {
                    Toast.makeText(SearchActivity.this, "请输入您想搜索的手机型号!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        mSearchAdapter = new SearchAdapter(this);
        mSearchActivityPresenter.getHotSearchGoodsNameList(this);
        recycler_view_top.addItemDecoration(new LinearSpacingDecoration(5, 5));
        recycler_view_top.setAdapter(mSearchAdapter);
        recycler_view_top.setLayoutManager(new GridLayoutManager(this, 4));

        mSearchAdapter.setOnClickListener(new SearchAdapter.onClickListener() {
            @Override
            public void onClick(String data) {
                mGoodsName = data;
                loadData();
            }
        });

        mSearchValueAdapter = new SearchValueAdapter(this);
        mSuperRecyclerView.setAdapter(mSearchValueAdapter);
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

    }

    private void loadData() {
        mSearchActivityPresenter.getProductListByGoodsName(this, mPageIndex, mPageSize, mPriceSort, mGoodsName);
    }

    @Override
    protected void attachPresenter() {
        mSearchActivityPresenter = new SearchActivityPresenterImpl(this);
        mSearchActivityPresenter.attachView(this);
    }

    @Override
    protected void detachPresenter() {
        mSearchActivityPresenter.detachView();
        mSearchActivityPresenter = null;
    }

    @Event(value = {R.id.back_layout, R.id.address_layout})
    private void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_layout:
                finish();
                break;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void getHotSearchGoodsNameList(NetSearchBean netShareBean) {
        if (netShareBean.getCode().equals(Contetns.RESPONSE_OK)) {
            mSearchAdapter.addAll(netShareBean.getData().getHotSearchList());
            mSearchAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void getHotSearchGoodsNameListError(int code, String msg) {

    }

    @Override
    public void getProductListByGoodsName(NetSearchValueBean netShareBean) {
        if (mPageIndex == 1 && mSearchValueAdapter != null) {
            mSearchValueAdapter.clear();
        }
        List<NetSearchValueBean.ResultBean.GoodsListBean> listBeans = netShareBean.getResult().getGoodsList();
        if (listBeans != null && listBeans.size() == 0) {
            mSuperRecyclerView.finishRefreshing();
            mSuperRecyclerView.setHasMoreData(false);
            mSuperRecyclerView.finishMore(true);
            Toast.makeText(SearchActivity.this, "搜索无数据!", Toast.LENGTH_SHORT).show();

        } else {
            mSuperRecyclerView.finishRefreshing();
            mSuperRecyclerView.setHasMoreData(Contetns.hasMoreData(listBeans.size()));
            mSuperRecyclerView.finishMore(!Contetns.hasMoreData(listBeans.size()));
            mSearchValueAdapter.addAll(listBeans);
        }

    }

    @Override
    public void getProductListByGoodsNameError(int code, String msg) {
        Toast.makeText(SearchActivity.this, msg, Toast.LENGTH_SHORT).show();
    }
}
