package com.trade.rrenji.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.trade.rrenji.R;
import com.trade.rrenji.bean.category.CategoryBean;
import com.trade.rrenji.bean.category.NetCategoryBean;
import com.trade.rrenji.biz.base.BaseFragment;
import com.trade.rrenji.biz.category.presenter.CategoryActivityPresenter;
import com.trade.rrenji.biz.category.presenter.CategoryActivityPresenterImpl;
import com.trade.rrenji.biz.category.ui.apater.LeftCategoryAdapter;
import com.trade.rrenji.biz.category.ui.apater.RightCategoryAdapter;
import com.trade.rrenji.biz.category.ui.view.CategoryActivityView;
import com.trade.rrenji.utils.StatusBarUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by monster on 23/3/18.
 */
@ContentView(R.layout.fragment_live)
public class CategoryTabFragment extends BaseFragment implements CategoryActivityView {

    private static String TAG = DryingTabFragment.class.getSimpleName();

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = x.view().inject(this, inflater, container);
        StatusBarUtils.setWindowStatusBarColor(getActivity(), R.color.actionbar_bg);
        return rootView;
    }

    @Override
    protected void attachPresenter() {
        mCategoryActivityPresenter = new CategoryActivityPresenterImpl(getActivity());
        mCategoryActivityPresenter.attachView(this);
        init();
    }

    @Override
    protected void detachPresenter() {
        mCategoryActivityPresenter.detachView();
        mCategoryActivityPresenter = null;
    }

    private void init() {
        loadData();
        mLeftCategoryAdapter = new LeftCategoryAdapter(getActivity());
        left_list.setAdapter(mLeftCategoryAdapter);
        mLeftCategoryAdapter.setOnCheckedChangeListener(new LeftCategoryAdapter.OnCheckedTypeChangeListener() {
            @Override
            public void onCheckedChangeType(String name, int position) {
                selectRightData(name);
                mLeftCategoryAdapter.setPosition(position);
            }
        });

        mRightCategoryAdapter = new RightCategoryAdapter(getActivity());
        right_recycler_view.setLayoutManager(new LinearLayoutManager(getActivity()));
        right_recycler_view.setAdapter(mRightCategoryAdapter);

    }

    @Override
    public void getCategorySuccess(NetCategoryBean netShareBean) {
        mListBeans = netShareBean.getData().getResultList();
        initData(netShareBean.getData().getResultList());
        selectRightData("热卖");
    }

    private void loadData() {
        mCategoryActivityPresenter.getCategory(getActivity());
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

//    private void bindRightData(List<NetCategoryBean.DataBean.ResultListBean> listBeans) {
//        List<CategoryBean> beans = new ArrayList<CategoryBean>();
//        for (NetCategoryBean.DataBean.ResultListBean listBean : listBeans) {
//            if (listBean.getName().equals("热卖")) {
//                CategoryBean categoryBean = new CategoryBean();
//                categoryBean.setType(RightCategoryAdapter.BANNER_ITEM);
//                categoryBean.setAdvertisementList(listBean.getAdvertisementList());
//                beans.add(categoryBean);
//                CategoryBean categoryBean1 = new CategoryBean();
//                categoryBean1.setType(RightCategoryAdapter.HOT_DATE_ITEM);
//                categoryBean1.setHotProductList(listBean.getHotProductList());
//                beans.add(categoryBean1);
//            }
//        }
//        mRightCategoryAdapter.addAll(beans);
//    }

    @Override
    public void getCategoryCodeError(int code, String msg) {

    }
}
