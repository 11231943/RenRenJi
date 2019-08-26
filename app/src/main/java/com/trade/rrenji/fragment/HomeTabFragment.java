package com.trade.rrenji.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.gelitenight.superrecyclerview.LinearSpacingDecoration;
import com.gelitenight.superrecyclerview.SuperRecyclerView;
import com.google.gson.reflect.TypeToken;
import com.trade.rrenji.R;
import com.trade.rrenji.bean.home.HomeBean;
import com.trade.rrenji.bean.home.NetHomeBean;
import com.trade.rrenji.biz.account.ui.activity.LoginActivity;
import com.trade.rrenji.biz.base.BaseFragment;
import com.trade.rrenji.biz.home.presenter.HomeActivityPresenter;
import com.trade.rrenji.biz.home.presenter.HomeActivityPresenterImpl;
import com.trade.rrenji.biz.home.ui.adapter.HomeAdapter;
import com.trade.rrenji.biz.home.ui.view.HomeActivityView;
import com.trade.rrenji.biz.im.ChatActivity;
import com.trade.rrenji.biz.im.ChatGroupActivity;
import com.trade.rrenji.biz.search.ui.activity.SearchActivity;
import com.trade.rrenji.utils.Contetns;
import com.trade.rrenji.utils.SettingUtils;
import com.trade.rrenji.utils.StatusBarUtils;
import com.trade.rrenji.utils.reservoir.Reservoir;
import com.trade.rrenji.utils.reservoir.ReservoirCallback;

import org.xutils.view.annotation.ViewInject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by monster on 23/3/18.
 */
//@ContentView(R.layout.fragment_nearby)
public class HomeTabFragment extends BaseFragment implements HomeActivityView {
    public static final String CACHE_KEY = "home";
    private static String TAG = HomeTabFragment.class.getSimpleName();

    @ViewInject(R.id.near_recycler_view)
    public SuperRecyclerView mSuperRecyclerView;

    @ViewInject(R.id.search_layout)
    public RelativeLayout search_layout;

    @ViewInject(R.id.more)
    public RelativeLayout more;
    @ViewInject(R.id.icon_service)
    public RelativeLayout iconService;


    HomeAdapter mHomeAdapter;

    private int mIndexPage = 1;

    HomeActivityPresenter mHomeActivityPresenter = null;

    @Override
    protected void initView() {
        Log.e(TAG, "initView");
        mSuperRecyclerView = rootView.findViewById(R.id.near_recycler_view);
        search_layout = rootView.findViewById(R.id.search_layout);
        iconService = rootView.findViewById(R.id.icon_service);
        more = rootView.findViewById(R.id.more);
        init();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        StatusBarUtils.setWindowStatusBarColor(getActivity(), R.color.actionbar_bg);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_nearby;
    }

    //    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        super.onCreateView(inflater,container,savedInstanceState);
//        Log.e(TAG, "onCreateView");
//        View rootView = x.view().inject(this, inflater, container);
//        StatusBarUtils.setWindowStatusBarColor(getActivity(), R.color.actionbar_bg);
////        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
////            getActivity().getWindow().getDecorView().setSystemUiVisibility(
////                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
////        }
//        return rootView;
//    }
//
//    @Override
//    protected void onFragmentVisibleChange(boolean isVisible) {
//        Log.e(TAG, "onFragmentVisibleChange" + isVisible);
//        super.onFragmentVisibleChange(isVisible);
//        init();
//    }

    //
//
//    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate");
        mHomeActivityPresenter = new HomeActivityPresenterImpl(getActivity());
        mHomeActivityPresenter.attachView(this);
        mHomeAdapter = new HomeAdapter(getActivity());
    }

    public static void setTransparentForWindow(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            activity.getWindow()
                    .setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }


//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        Log.e(TAG, "onActivityCreated");
//    }


    private void loadData() {
        mHomeActivityPresenter.getHomeList(getActivity(), mIndexPage);
    }

    @Override
    protected void attachPresenter() {

    }

    private void showNormalDialog() {

        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(getActivity());
        normalDialog.setTitle("提醒");
        normalDialog.setMessage("该功能增在完善中...?");
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                    }
                });
        normalDialog.setNegativeButton("关闭",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                    }
                });
        // 显示
        normalDialog.show();
    }

    private void init() {
        search_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                getActivity().startActivity(intent);

            }
        });
        iconService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(SettingUtils.getInstance().getCurrentUid())) {
                    if (TextUtils.equals(SettingUtils.getInstance().getPhone(), Contetns.ACCOUNT_ADMIN)) {
                        Intent intent = new Intent(getActivity(), ChatGroupActivity.class);
                        getActivity().startActivity(intent);
                    } else {
                        Intent intent = new Intent(getActivity(), ChatActivity.class);
                        intent.putExtra("from", "0");
                        getActivity().startActivity(intent);
                    }
                }else {
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    getActivity().startActivity(intent);
                }
            }
        });
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNormalDialog();
            }
        });
        mSuperRecyclerView.addItemDecoration(new LinearSpacingDecoration(10, 0));
        mSuperRecyclerView.setAdapter(mHomeAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity()) {
            @Override
            public RecyclerView.LayoutParams generateDefaultLayoutParams() {
                return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
            }
        };
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mSuperRecyclerView.setLayoutManager(layoutManager);
        mSuperRecyclerView.setOnLoadDataListener(new SuperRecyclerView.OnLoadDataListener() {
            @Override
            public void onRefresh() {
                mIndexPage = 1;
                isFirst = false;
                loadData();
            }

            @Override
            public void onMore() {
                mIndexPage++;
                loadData();
            }
        });
        if (!isFirst) {
            Type resultType = new TypeToken<NetHomeBean>() {
            }.getType();
            Reservoir.getAsync(CACHE_KEY, resultType, new ReservoirCallback<NetHomeBean>() {
                @Override
                public void onSuccess(NetHomeBean data) {
                    Log.d(TAG, "onSuccess: load nearby cache succeed");
                    initHomeBean(data);
                }

                @Override
                public void onFailure(Exception e) {
                    Log.d(TAG, "onFailure: load nearby cache onFailure");
                }
            });
            mSuperRecyclerView.startRefreshing(true, true);
        }
    }

    @Override
    protected void detachPresenter() {

    }

    private void initHomeBean(NetHomeBean mNetHomeBean) {
        if (mIndexPage == 1) {
            if (mHomeAdapter != null) {
                mHomeAdapter.clear();
            }
        }
        if (!isFirst) {
            isFirst = true;
        }
        NetHomeBean.DataBean ordersBeans = mNetHomeBean.getData();
        mSuperRecyclerView.finishRefreshing();
        mSuperRecyclerView.setHasMoreData(false);
        mSuperRecyclerView.finishMore(false);
        build(ordersBeans);

    }

    @Override
    public void getHomeList(NetHomeBean mNetHomeBean) {
        if (mIndexPage == 1) {
            Reservoir.putAsync(CACHE_KEY, mNetHomeBean, new ReservoirCallback<Void>() {
                @Override
                public void onSuccess(Void data) {
                    Log.d(TAG, "save nearby cache succeed");
                    try {
                        Log.d(TAG, "used bytes: " + Reservoir.bytesUsed());
                    } catch (Exception e) {
                        // ignore
                    }
                }

                @Override
                public void onFailure(Exception e) {
                    Log.e(TAG, "save nearby cache failed", e);
                }
            });
        }
        initHomeBean(mNetHomeBean);
    }

    private void build(NetHomeBean.DataBean resultBean) {
        List<HomeBean> homeBeanList = new ArrayList<HomeBean>();

        HomeBean adBean = new HomeBean();
        adBean.setType(0);
        adBean.setTopBannerList(resultBean.getTopBannerList());

        HomeBean adBean1 = new HomeBean();
        adBean1.setType(1);
        adBean1.setCategoryList(resultBean.getCategoryList());

        HomeBean adBean2 = new HomeBean();
        adBean2.setType(2);
        adBean2.setHotActivityInfoList(resultBean.getHotActivityInfoList());

        HomeBean adBean3 = new HomeBean();
        adBean3.setType(3);
        adBean3.setHotIphone(resultBean.getHotIphone());

        HomeBean adBean4 = new HomeBean();
        adBean4.setType(4);
        adBean4.setHotAndroid(resultBean.getHotAndroid());

        HomeBean adBean5 = new HomeBean();
        adBean5.setType(5);
        adBean5.setMiddleBannerList(resultBean.getMiddleBannerList());

        HomeBean adBean6 = new HomeBean();
        adBean6.setType(6);
        adBean6.setThousandOptimization(resultBean.getThousandOptimization());

        HomeBean adBean7 = new HomeBean();
        adBean7.setType(7);
        adBean7.setEveryoneCommunityList(resultBean.getEveryoneCommunityList());

        HomeBean adBean8 = new HomeBean();
        adBean8.setType(8);
        adBean8.setEveryoneHome(resultBean.getEveryoneHome());

        HomeBean adBean9 = new HomeBean();
        adBean9.setType(9);
        adBean9.setRrjEnsureDetailUrl(resultBean.getRrjEnsureDetailUrl());

        homeBeanList.add(adBean);
        homeBeanList.add(adBean1);
        homeBeanList.add(adBean2);
        homeBeanList.add(adBean3);
        homeBeanList.add(adBean4);
        homeBeanList.add(adBean5);
        homeBeanList.add(adBean6);
        homeBeanList.add(adBean7);
        homeBeanList.add(adBean8);
        homeBeanList.add(adBean9);
        mHomeAdapter.addAll(homeBeanList);
    }

    @Override
    public void getHomeListError(int code, String msg) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mHomeActivityPresenter.detachView();
        mHomeActivityPresenter = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
}
