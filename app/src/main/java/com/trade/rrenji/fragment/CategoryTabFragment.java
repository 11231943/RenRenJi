package com.trade.rrenji.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.gson.reflect.TypeToken;
import com.trade.rrenji.R;
import com.trade.rrenji.bean.category.CategoryBean;
import com.trade.rrenji.bean.category.NetCategoryBean;
import com.trade.rrenji.bean.tech.NetTechBean;
import com.trade.rrenji.biz.base.BaseFragment;
import com.trade.rrenji.biz.category.presenter.CategoryActivityPresenter;
import com.trade.rrenji.biz.category.presenter.CategoryActivityPresenterImpl;
import com.trade.rrenji.biz.category.ui.apater.LeftCategoryAdapter;
import com.trade.rrenji.biz.category.ui.apater.RightCategoryAdapter;
import com.trade.rrenji.biz.category.ui.view.CategoryActivityView;
import com.trade.rrenji.utils.StatusBarUtils;
import com.trade.rrenji.utils.reservoir.Reservoir;
import com.trade.rrenji.utils.reservoir.ReservoirCallback;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by monster on 23/3/18.
 */
//@ContentView(R.layout.fragment_live)
public class CategoryTabFragment extends BaseFragment implements CategoryActivityView {

    private static String TAG = CategoryTabFragment.class.getSimpleName();
    public static final String CACHE_KEY = "category";

    @ViewInject(R.id.left_list)
    public ListView left_list;

    @ViewInject(R.id.right_recycler_view)
    public RecyclerView right_recycler_view;

    CategoryActivityPresenter mCategoryActivityPresenter;

    LeftCategoryAdapter mLeftCategoryAdapter;

    RightCategoryAdapter mRightCategoryAdapter;

    private List<String> mLeft = new ArrayList<String>();

    List<NetCategoryBean.DataBean.ResultListBean> mListBeans;

    @Override
    protected void initView() {
        left_list = rootView.findViewById(R.id.left_list);
        right_recycler_view = rootView.findViewById(R.id.right_recycler_view);
        init();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_live;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCategoryActivityPresenter = new CategoryActivityPresenterImpl(getActivity());
        mCategoryActivityPresenter.attachView(this);
        mLeftCategoryAdapter = new LeftCategoryAdapter(getActivity());
        mRightCategoryAdapter = new RightCategoryAdapter(getActivity());
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        StatusBarUtils.setWindowStatusBarColor(getActivity(), R.color.actionbar_bg);
    }


    @Override
    protected void attachPresenter() {


    }

    @Override
    protected void detachPresenter() {

    }

    private void init() {
        if (!isFirst) {
            Type resultType = new TypeToken<NetCategoryBean>() {
            }.getType();
            Reservoir.getAsync(CACHE_KEY, resultType, new ReservoirCallback<NetCategoryBean>() {
                @Override
                public void onSuccess(NetCategoryBean data) {
                    Log.d(TAG, "onSuccess: load category cache succeed");
                    initHomeBean(data);
                }

                @Override
                public void onFailure(Exception e) {
                    Log.d(TAG, "onFailure: load category cache onFailure");
                }
            });
            loadData();
        }
        left_list.setAdapter(mLeftCategoryAdapter);
        mLeftCategoryAdapter.setOnCheckedChangeListener(new LeftCategoryAdapter.OnCheckedTypeChangeListener() {
            @Override
            public void onCheckedChangeType(String name, int position) {
                selectRightData(name);
                mLeftCategoryAdapter.setPosition(position);
            }
        });
        right_recycler_view.setLayoutManager(new LinearLayoutManager(getActivity()));
        right_recycler_view.setAdapter(mRightCategoryAdapter);
    }

    private void initHomeBean(NetCategoryBean netShareBean) {
        if (!isFirst) {
            isFirst = true;
            mListBeans = netShareBean.getData().getResultList();
            initData(netShareBean.getData().getResultList());
            selectRightData("热卖");
        }
    }

    @Override
    public void getCategorySuccess(NetCategoryBean netShareBean) {
        if (!isFirst) {
            isFirst = true;
            Reservoir.putAsync(CACHE_KEY, netShareBean, new ReservoirCallback<Void>() {
                @Override
                public void onSuccess(Void data) {
                    Log.d(TAG, "save category cache succeed");
                    try {
                        Log.d(TAG, "used bytes: " + Reservoir.bytesUsed());
                    } catch (Exception e) {
                        // ignore
                    }
                }

                @Override
                public void onFailure(Exception e) {
                    Log.e(TAG, "save category cache failed", e);
                }
            });

        }
        mListBeans = netShareBean.getData().getResultList();
        initData(netShareBean.getData().getResultList());
        selectRightData("热卖");
    }

    private void loadData() {
        mCategoryActivityPresenter.getCategoryLeft(getActivity());
    }


    private void initData(List<NetCategoryBean.DataBean.ResultListBean> listBeans) {
        mLeft.clear();
        for (NetCategoryBean.DataBean.ResultListBean listBean : listBeans) {
            mLeft.add(listBean.getName());
        }
        mLeftCategoryAdapter.addData(mLeft);
//        bindRightData(listBeans);
    }

    private void selectRightData(String name) {
        if (mListBeans != null) {
            List<CategoryBean> beans = new ArrayList<CategoryBean>();
            for (NetCategoryBean.DataBean.ResultListBean listBean : mListBeans) {
                if (listBean.getName().equals(name)) {
                    if (listBean.getAdvertisementList().size() > 0) {
                        CategoryBean categoryBean = new CategoryBean();
                        categoryBean.setName(name);
                        categoryBean.setType(RightCategoryAdapter.BANNER_ITEM);
                        categoryBean.setAdvertisementList(listBean.getAdvertisementList());
                        beans.add(categoryBean);
                    }
                    if (listBean.getCategoryBrandList().size() > 0) {
                        CategoryBean categoryBean2 = new CategoryBean();
                        categoryBean2.setName(name);
                        categoryBean2.setType(RightCategoryAdapter.CATEGORY_ITEM);
                        categoryBean2.setCategoryBrandList(listBean.getCategoryBrandList());
                        beans.add(categoryBean2);
                    }
                    if (listBean.getHotProductList().size() > 0) {
                        CategoryBean categoryBean1 = new CategoryBean();
                        categoryBean1.setName(name);
                        categoryBean1.setType(RightCategoryAdapter.HOT_DATE_ITEM);
                        categoryBean1.setHotProductList(listBean.getHotProductList());
                        beans.add(categoryBean1);
                    }
                    if (listBean.getGoodsModelList().size() > 0) {
                        CategoryBean categoryBean3 = new CategoryBean();
                        categoryBean3.setName(name);
                        categoryBean3.setType(RightCategoryAdapter.DATE_ITEM);
                        categoryBean3.setGoodsModelList(listBean.getGoodsModelList());
                        beans.add(categoryBean3);
                    }
                }
            }
            mRightCategoryAdapter.addAllData(beans);
        }

    }


    @Override
    public void getCategoryCodeError(int code, String msg) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isFirst = false;
        mCategoryActivityPresenter.detachView();
        mCategoryActivityPresenter = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
}
