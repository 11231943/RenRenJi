package com.trade.rrenji;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.trade.rrenji.biz.base.BaseActivity;
import com.trade.rrenji.fragment.HomeTabFragment;
import com.trade.rrenji.fragment.CategoryTabFragment;
import com.trade.rrenji.fragment.TechTabFragment;
import com.trade.rrenji.fragment.DryingTabFragment;
import com.trade.rrenji.fragment.MineFragment;
import com.trade.rrenji.utils.FragmentTabHost;
import com.trade.rrenji.utils.TabLayout;
import com.trade.rrenji.utils.ViewUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity {

    public String[] tabTags = new String[]{"nearby", "live", "rainbow", "message", "mine"};
    public Class[] tabCls = new Class[]{HomeTabFragment.class, CategoryTabFragment.class,
            TechTabFragment.class, DryingTabFragment.class, MineFragment.class};
    int[] tabCustomView = new int[]{R.layout.main_tab_near, R.layout.main_tab_live,
            R.layout.main_tab_discovered, R.layout.main_tab_drying, R.layout.main_tab_mime};

    @ViewInject(android.R.id.tabhost)
    public FragmentTabHost mTabHost;
    @ViewInject(R.id.main_tabs)
    public TabLayout mTabLayout;
    @ViewInject(R.id.msgCount)
    public TextView msgCount;
    @ViewInject(R.id.msgCountLayout)
    public View msgCountLayout;
    private int mCurrentItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initEvent();
    }

    protected void initEvent() {
        mTabHost.setup(this, getSupportFragmentManager(), R.id.container, false);
        for (int i = 0; i < tabTags.length; i++) {
            mTabHost.addTab(mTabHost.newTabSpec(tabTags[i]).setIndicator(tabTags[i]),
                    tabCls[i], null);
            mTabLayout.addTab(mTabLayout.newTab().setTag(tabTags[i]));
            mTabLayout.getTabAt(i).setCustomView(tabCustomView[i]);
        }

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mCurrentItem = tab.getPosition();
                mTabHost.setCurrentTabByTag((String) tab.getTag());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
//        locateMsgCount();
//        updateUnreadNumView(12);
    }

    /**
     * 未读数标签的位置
     */
    private void locateMsgCount() {
        final int tabCount = 5;     // 总tab的数量
        final int prevTabCount = 3; // 消息tab之前的tab的数量
        int leftPadding = (int) (ViewUtils.getScreenWidth(this)
                * (1 + prevTabCount * 2) / (tabCount * 2)
                + getResources().getDimension(R.dimen.indicate_text_offset));
        msgCountLayout.setPadding(leftPadding, 0, 0, 0);
    }

    /**
     * 更新消息数
     *
     * @param unreadMsgNum
     */
    public void updateUnreadNumView(final int unreadMsgNum) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (unreadMsgNum <= 0) {
                    msgCount.setVisibility(View.GONE);
                    msgCount.setText("");
                } else if (unreadMsgNum <= 999) {
                    msgCount.setVisibility(View.VISIBLE);
                    msgCount.setText(String.valueOf(unreadMsgNum));
                } else {
                    msgCount.setVisibility(View.VISIBLE);
                    msgCount.setText("999+");
                }
            }
        });

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

    @Override
    public void showLoading(String msg) {
        super.showLoading(msg);
    }

    @Override
    public void hideLoading() {
        super.hideLoading();
    }

    @Override
    public void showError(String msg) {
        super.showError(msg);
    }

    @Override
    public void showEmpty(String msg) {
        super.showEmpty(msg);
    }
}
