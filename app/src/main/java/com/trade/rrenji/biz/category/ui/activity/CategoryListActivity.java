package com.trade.rrenji.biz.category.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
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
import com.trade.rrenji.bean.category.BaseBean;
import com.trade.rrenji.bean.category.NetCategoryListBean;
import com.trade.rrenji.bean.category.NetScreenBean;
import com.trade.rrenji.bean.category.NetScreenListBean;
import com.trade.rrenji.bean.category.PopupCategoryBean;
import com.trade.rrenji.biz.base.BaseActivity;
import com.trade.rrenji.biz.category.presenter.CategoryActivityListPresenter;
import com.trade.rrenji.biz.category.presenter.CategoryActivityListPresenterImpl;
import com.trade.rrenji.biz.category.ui.apater.CategoryListAdapter;
import com.trade.rrenji.biz.category.ui.apater.PopupAdapter;
import com.trade.rrenji.biz.category.ui.view.CategoryActivityListView;
import com.trade.rrenji.fragment.DryingTabFragment;
import com.trade.rrenji.utils.Contetns;
import com.trade.rrenji.view.CommonPopupWindow;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_category_super_recyclerview)
public class CategoryListActivity extends BaseActivity implements CategoryActivityListView {

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
    CategoryActivityListPresenter mPresenter = null;
    CategoryListAdapter mCategoryListAdapter;
    private CommonPopupWindow mWindow;
    private RecyclerView mPopupRecyclerView;
    private TextView cancel_btn;
    PopupAdapter mPopupAdapter;
    NetScreenBean mNetShareBean;
    private String mId;
    private String mType;
    private boolean isScreen = false;
    private String mDefaultColor = "";
    private String mDefaultMemory = "";
    private String mDefaultNetwork = "";
    private String mDefaultCondition = "";
    private String mDefaultVersion = "";
    private int mPage = 1;
    private int mRows = 20;
    private int mPriceSort = 0;
    private boolean isPopup = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActionBarTitle("产品列表");
        mId = getIntent().getStringExtra("id");
        mType = getIntent().getStringExtra("type");
        init();
        initPopupWindow();
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
                        mPage = 1;
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

    private void init() {
        mPresenter.getModelAttr(this, mId);
        mCategoryListAdapter = new CategoryListAdapter(this);
        mSuperRecyclerView.setAdapter(mCategoryListAdapter);
        mSuperRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mSuperRecyclerView.setOnLoadDataListener(new SuperRecyclerView.OnLoadDataListener() {
            @Override
            public void onRefresh() {
                if (isScreen) {
                    mPage = 1;
                    lodDataScreen();
                } else {
                    loadData();
                }
            }

            @Override
            public void onMore() {
                mPage++;
                if (isScreen) {
                    lodDataScreen();
                } else {
                    loadData();
                }

            }
        });
        mSuperRecyclerView.startRefreshing(true);

    }

    private void loadData() {
        mPresenter.getClassifyDataByType(this, mId, mType, mPage, mRows, mPriceSort);
    }

    private void lodDataScreen() {
        mPresenter.getAttributeProductList(this, mPriceSort, mPage, mId, mDefaultMemory, mDefaultColor, mDefaultNetwork, mDefaultCondition, mDefaultVersion);
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

    @Event(value = {R.id.price_select_layout, R.id.price_sort_layout})
    private void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.price_select_layout:
                PopupWindow win = mWindow.getPopupWindow();
                win.setAnimationStyle(R.style.animTranslate_right);
                mWindow.showAtLocation(view_group, Gravity.RIGHT, 0, 0);
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 0.3f;
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                getWindow().setAttributes(lp);
                break;
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
                if (isPopup) {
                    lodDataScreen();
                } else {
                    loadData();
                }
                break;
        }

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

    @Override
    public void getModelAttrSuccess(NetScreenBean netShareBean) {
        mNetShareBean = netShareBean;
        if (mPopupAdapter != null) {
            mPopupAdapter.clear();
        } else {
            mPopupAdapter = new PopupAdapter(CategoryListActivity.this);
            mPopupAdapter.setOnClickListener(new PopupAdapter.onClickListener() {
                @Override
                public void onClick(BaseBean data) {
                    isScreen = true;
                    if (data.getType() == 0) {
                        mDefaultCondition = data.getId();
                    } else if (data.getType() == 1) {
                        mDefaultMemory = data.getId();
                    } else if (data.getType() == 2) {
                        mDefaultColor = data.getId();
                    } else if (data.getType() == 3) {
                        mDefaultNetwork = data.getId();
                    } else if (data.getType() == 4) {
                        mDefaultNetwork = data.getId();
                    }
                    isPopup = true;
                    lodDataScreen();
                }

                @Override
                public void onCancelClick(BaseBean data) {
                    isScreen = true;
                    if (data.getType() == 0) {
                        mDefaultCondition = "";
                    } else if (data.getType() == 1) {
                        mDefaultMemory = "";
                    } else if (data.getType() == 2) {
                        mDefaultColor = "";
                    } else if (data.getType() == 3) {
                        mDefaultNetwork = "";
                    } else if (data.getType() == 4) {
                        mDefaultNetwork = "";
                    }
                    isPopup = true;
                    if (TextUtils.isEmpty(mDefaultCondition) && TextUtils.isEmpty(mDefaultMemory)
                            && TextUtils.isEmpty(mDefaultColor) && TextUtils.isEmpty(mDefaultNetwork)) {
                        loadData();
                    } else {
                        lodDataScreen();
                    }

                }
            });
            mPopupRecyclerView.setAdapter(mPopupAdapter);
            mPopupRecyclerView.setLayoutManager(new LinearLayoutManager(getApplication()));
        }
        buildScreen(mNetShareBean);
    }

    private void buildScreen(NetScreenBean netShareBean) {
        List<PopupCategoryBean> beans = new ArrayList<PopupCategoryBean>();

        PopupCategoryBean condition1 = new PopupCategoryBean();
        condition1.setType(0);
        condition1.setTypeName("成色");

        PopupCategoryBean condition = new PopupCategoryBean();
        condition.setType(1);
        condition.setSize(netShareBean.getResult().getCondition().size());
        List<BaseBean> baseConditionBean1 = new ArrayList<>();
        for (int i = 0; i < netShareBean.getResult().getCondition().size(); i++) {
            NetScreenBean.ResultBean.ConditionBean conditionBean = netShareBean.getResult().getCondition().get(i);
            BaseBean bean = new BaseBean();
            bean.setId(conditionBean.getId());
            bean.setName(conditionBean.getName());
            bean.setType(0);
            bean.setSelected(false);
            baseConditionBean1.add(bean);
        }
        condition.setBaseBeans(baseConditionBean1);

        PopupCategoryBean memory1 = new PopupCategoryBean();
        memory1.setType(0);
        memory1.setTypeName("内存");

        PopupCategoryBean memory = new PopupCategoryBean();
        memory.setType(1);
        memory.setSize(netShareBean.getResult().getMemory().size());
        List<BaseBean> baseMemoryBean = new ArrayList<>();
        for (int i = 0; i < netShareBean.getResult().getMemory().size(); i++) {
            NetScreenBean.ResultBean.MemoryBean memoryBean = netShareBean.getResult().getMemory().get(i);
            BaseBean bean = new BaseBean();
            bean.setId(memoryBean.getId());
            bean.setName(memoryBean.getName());
            bean.setType(1);
            bean.setSelected(false);
            baseMemoryBean.add(bean);
        }
        memory.setBaseBeans(baseMemoryBean);

        PopupCategoryBean color1 = new PopupCategoryBean();
        color1.setType(0);
        color1.setTypeName("颜色");

        PopupCategoryBean color = new PopupCategoryBean();
        color.setType(1);
        color.setSize(netShareBean.getResult().getColor().size());
        List<BaseBean> baseColorBean = new ArrayList<>();
        for (int i = 0; i < netShareBean.getResult().getColor().size(); i++) {
            NetScreenBean.ResultBean.ColorBean colorBean = netShareBean.getResult().getColor().get(i);
            BaseBean bean = new BaseBean();
            bean.setId(colorBean.getId());
            bean.setType(2);
            bean.setSelected(false);
            bean.setName(colorBean.getName());
            baseColorBean.add(bean);

        }
        color.setBaseBeans(baseColorBean);

        PopupCategoryBean version1 = new PopupCategoryBean();
        version1.setType(0);
        version1.setTypeName("网络");

        PopupCategoryBean version = new PopupCategoryBean();
        version.setType(1);
        version.setSize(netShareBean.getResult().getVersion().size());
        List<BaseBean> baseVersionBean = new ArrayList<>();
        for (int i = 0; i < netShareBean.getResult().getVersion().size(); i++) {
            NetScreenBean.ResultBean.VersionBean versionBean = netShareBean.getResult().getVersion().get(i);
            BaseBean bean = new BaseBean();
            bean.setId(versionBean.getId());
            bean.setType(3);
            bean.setSelected(false);
            bean.setName(versionBean.getName());
            baseVersionBean.add(bean);
        }
        version.setBaseBeans(baseVersionBean);
        PopupCategoryBean network1 = new PopupCategoryBean();
        network1.setType(0);
        network1.setTypeName("版本");

        PopupCategoryBean network = new PopupCategoryBean();
        network.setType(1);
        network.setSize(netShareBean.getResult().getNetwork().size());
        List<BaseBean> baseNetworkBean = new ArrayList<>();
        for (int i = 0; i < netShareBean.getResult().getNetwork().size(); i++) {
            NetScreenBean.ResultBean.NetworkBean networkBean = netShareBean.getResult().getNetwork().get(i);
            BaseBean bean = new BaseBean();
            bean.setId(networkBean.getId());
            bean.setType(4);
            bean.setSelected(false);
            bean.setName(networkBean.getName());
            baseNetworkBean.add(bean);
        }
        network.setBaseBeans(baseNetworkBean);

        beans.add(condition1);
        beans.add(condition);
        beans.add(memory1);
        beans.add(memory);
        beans.add(color1);
        beans.add(color);
        beans.add(version1);
        beans.add(version);
        beans.add(network1);
        beans.add(network);
        mPopupAdapter.addAllData(beans);
    }

    @Override
    public void getModelAttrError(int code, String msg) {

    }

    @Override
    public void getAttributeProductListSuccess(NetScreenListBean netShareBean) {
        if (mCategoryListAdapter != null) {
            mCategoryListAdapter.clear();
        }
        List<NetCategoryListBean.DataBean.ResultListBean> listBeans = netShareBean.getResult().getProductList();
        mSuperRecyclerView.finishRefreshing();
        mSuperRecyclerView.setHasMoreData(Contetns.hasMoreData(listBeans.size()));
        mSuperRecyclerView.finishMore(!Contetns.hasMoreData(listBeans.size()));
        mCategoryListAdapter.addAll(listBeans);
    }

    @Override
    public void getAttributeProductListError(int code, String msg) {

    }
}
