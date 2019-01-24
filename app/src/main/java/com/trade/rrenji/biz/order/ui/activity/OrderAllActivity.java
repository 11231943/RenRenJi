package com.trade.rrenji.biz.order.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.trade.rrenji.R;
import com.trade.rrenji.bean.order.OrderClassify;
import com.trade.rrenji.biz.base.ActionBarHelper;
import com.trade.rrenji.biz.base.BaseActivity;
import com.trade.rrenji.utils.CollectionUtils;
import com.trade.rrenji.utils.TabLayout;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_order_all_layout)
public class OrderAllActivity extends BaseActivity {

    private static String TAG = OrderAllActivity.class.getSimpleName();

    @ViewInject(R.id.goods_tabs)
    public TabLayout mTabLayout;
    @ViewInject(R.id.pager)
    public ViewPager mPager;
    @ViewInject(R.id.toolbar)
    public Toolbar mToolbar;

    private TabAdapter mTabAdapter;
    List<OrderClassify> mList = null;
    private int mCurrentItem = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        mToolbar.setTitle("");
        ActionBarHelper.CustomViewTitle title = new ActionBarHelper.CustomViewTitle(this, mToolbar);
        title.setTitle(R.string.my_order_title);
        title.addToTarget();
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.back_btn);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mList = new ArrayList<OrderClassify>();
        OrderClassify orderClassify1 = new OrderClassify();
        orderClassify1.setType(0);
        orderClassify1.setName("待发货");
        mList.add(orderClassify1);
        OrderClassify orderClassify2 = new OrderClassify();
        orderClassify2.setType(1);
        orderClassify2.setName("待收货");
        mList.add(orderClassify2);
        OrderClassify orderClassify3 = new OrderClassify();
        orderClassify3.setType(2);
        orderClassify3.setName("晒单");
        mList.add(orderClassify3);
        initTabs(mList);
    }

    private void initTabs(List<OrderClassify> list) {
        mTabAdapter = new TabAdapter(getSupportFragmentManager(), list);
        mPager.setAdapter(mTabAdapter);
        mPager.setCurrentItem(mCurrentItem);
        mTabLayout.setupWithViewPager(mPager);
    }

    private void loadData() {

    }

    @Override
    protected void attachPresenter() {

    }

    @Override
    protected void detachPresenter() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    private static class TabAdapter extends FragmentStatePagerAdapter {

        private List<OrderClassify> mList;

        public TabAdapter(FragmentManager manager, List<OrderClassify> list) {
            super(manager);
            mList = list;
        }

        @Override
        public int getCount() {
            return CollectionUtils.getSize(mList);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mList.get(position).getName();
        }

        @Override
        public Fragment getItem(int position) {

            OrderClassify classify = mList.get(position);
            switch (classify.getType()) {
                case 0:
                    return DeliverFragment.newInstance();
                case 1:
                    return ReceivedFragment.newInstance();
                case 2:
                    return DryingFragment.newInstance();
                default:
                    break;
            }
            return null;
        }
    }


}
