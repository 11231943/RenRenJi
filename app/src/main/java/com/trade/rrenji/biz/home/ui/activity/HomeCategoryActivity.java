package com.trade.rrenji.biz.home.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gelitenight.superrecyclerview.SuperRecyclerView;
import com.trade.rrenji.R;
import com.trade.rrenji.bean.category.NetCategoryListBean;
import com.trade.rrenji.bean.category.NetScreenBean;
import com.trade.rrenji.biz.base.BaseActivity;
import com.trade.rrenji.biz.category.presenter.CategoryActivityListPresenter;
import com.trade.rrenji.biz.category.presenter.CategoryActivityListPresenterImpl;
import com.trade.rrenji.biz.category.ui.apater.CategoryClassListAdapter;
import com.trade.rrenji.biz.category.ui.apater.PopupAdapter;
import com.trade.rrenji.biz.category.ui.view.CategoryActivityListView;
import com.trade.rrenji.biz.home.presenter.HomeCategoryActivityPresenter;
import com.trade.rrenji.biz.home.presenter.HomeCategoryActivityPresenterImpl;
import com.trade.rrenji.biz.home.ui.adapter.HomeCategoryAdapter;
import com.trade.rrenji.biz.home.ui.view.HomeCategoryActivityView;
import com.trade.rrenji.fragment.DryingTabFragment;
import com.trade.rrenji.utils.Contetns;
import com.trade.rrenji.view.CommonPopupWindow;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
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

    @ViewInject(R.id.view_group)
    public LinearLayout view_group;
    @ViewInject(R.id.goods_type_select_txt)
    public TextView goods_type_select_txt;
    @ViewInject(R.id.select_sort)
    public ImageView select_sort;

    private CommonPopupWindow mWindow;
    HomeCategoryActivityPresenter mPresenter = null;
    HomeCategoryAdapter mCategoryListAdapter;
    private RecyclerView mPopupRecyclerView;
    private TextView cancel_btn;
    PopupAdapter mPopupAdapter;
    NetScreenBean mNetShareBean;

    private String mCategoryId;
    private int mPageIndex = 1;
    private int mPriceSort = 0;//排序

    private String mDefaultColor = "";
    private String mDefaultMemory = "";
    private String mDefaultNetwork = "";
    private String mDefaultCondition = "";
    private String mDefaultVersion = "";
    private boolean isPopup = false;
    private boolean isScreen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActionBarTitle("产品列表");
        mCategoryId = getIntent().getStringExtra("categoryId");
        init();
        initPopupWindow();
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

    private void initPopupWindow() {
        // get the height of screen
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int screenHeight = metrics.heightPixels;
        int widthPixels = metrics.widthPixels;

        // create popup window
        mWindow = new CommonPopupWindow(this, R.layout.category_item_popop_layout, (int) (widthPixels * 0.7), screenHeight + 100) {
            @Override
            protected void initView() {
                View view = getContentView();
                mPopupRecyclerView = view.findViewById(R.id.recycler_view);
                cancel_btn = view.findViewById(R.id.cancel_btn);
            }

            @Override
            protected void initEvent() {
                view_group.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mWindow.getPopupWindow().dismiss();
                    }
                });
                cancel_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        isPopup = false;
                        mPopupAdapter.resetColor();
                        mWindow.getPopupWindow().dismiss();
                        isScreen = false;
                        mPageIndex = 1;
                        loadData();
                    }
                });
            }

            @Override
            protected void initWindow() {
                super.initWindow();
                PopupWindow instance = getPopupWindow();
                instance.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        WindowManager.LayoutParams lp = getWindow().getAttributes();
                        lp.alpha = 1.0f;
                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                        getWindow().setAttributes(lp);
                    }
                });
            }
        };
    }

    @Event(value = {R.id.price_select_layout, R.id.price_sort_layout})
    private void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.price_sort_layout:
                if (mPriceSort == 0) {
                    mPriceSort = 1;
                    select_sort.setImageResource(R.drawable.goods_type_select_sort_up);
                } else if (mPriceSort == 1) {
                    mPriceSort = 2;
                    select_sort.setImageResource(R.drawable.goods_type_select_sort_down);
                } else if (mPriceSort == 2) {
                    mPriceSort = 0;
                    select_sort.setImageResource(R.drawable.goods_type_select_sort);
                }
                mPageIndex = 1;
                loadData();
                break;
        }
    }

    private void loadData() {
        mPresenter.getCategoryDetailById(this, mCategoryId, mPageIndex, 20, mPriceSort);
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
