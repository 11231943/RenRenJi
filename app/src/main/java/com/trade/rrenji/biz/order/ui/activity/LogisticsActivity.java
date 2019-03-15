package com.trade.rrenji.biz.order.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gelitenight.superrecyclerview.LinearSpacingDecoration;
import com.trade.rrenji.R;
import com.trade.rrenji.bean.order.NetOrderBean;
import com.trade.rrenji.biz.base.BaseActivity;
import com.trade.rrenji.biz.order.ui.adapter.OrderStatusAdminAdapter;
import com.trade.rrenji.utils.StatusBarUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.logistics_main_activity)
public class LogisticsActivity extends BaseActivity {
    @ViewInject(R.id.back_icon)
    public RelativeLayout back_icon;

    @ViewInject(R.id.logistics_id)
    public TextView logistics_id;
    @ViewInject(R.id.logistics_last_txt)
    public TextView logistics_last_txt;
    @ViewInject(R.id.logistics_last_time)
    public TextView logistics_last_time;

    @ViewInject(R.id.logistics_address_name)
    public TextView logistics_address_name;
    @ViewInject(R.id.logistics_address)
    public TextView logistics_address;
    @ViewInject(R.id.logistics_more)
    public TextView logistics_more;
    @ViewInject(R.id.new_status_txt)
    public TextView new_status_txt;


    @ViewInject(R.id.recycler_view_layout)
    public RecyclerView recycler_view_layout;

    /**
     * mType = 0 未发货
     * mType = 1 已发货
     */
    private int mType = -1;
    NetOrderBean.DataBean.ResultListBean mData;
    OrderStatusAdminAdapter mOrderStatusAdminAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtils.setWindowStatusBarColor(this, R.color.mine_red);
        mType = Integer.valueOf(getIntent().getStringExtra("mType"));
        mData = (NetOrderBean.DataBean.ResultListBean) getIntent().getSerializableExtra("data");
        init();
    }

    private void init() {
        if (mType == 2) {
            logistics_id.setText("顺丰快递:-----");
            logistics_last_txt.setText("人人机正在马不停蹄给您打包发货");
            logistics_last_time.setText("请耐心等待");
            logistics_more.setText("-----");
            new_status_txt.setText("打包中...");
        } else if (mType == 3) {
            logistics_id.setText("顺丰快递:666999495827");
            logistics_last_txt.setText("快件正在从深圳发往福建的途中…");
            logistics_last_time.setText("2019-3-19 15:23:16");
            logistics_more.setText("点击查看更多物流详情");
            new_status_txt.setText("快件正在从深圳发往福建的途中");
        }
        logistics_address_name.setText(mData.getGoodsName() + "  " + mData.getAddressPhone());
        logistics_address.setText(mData.getAddressDetail());
        mOrderStatusAdminAdapter = new OrderStatusAdminAdapter(this);
        recycler_view_layout.addItemDecoration(new LinearSpacingDecoration(25, 5));
        recycler_view_layout.setAdapter(mOrderStatusAdminAdapter);
        recycler_view_layout.setLayoutManager(new LinearLayoutManager(this));
        mOrderStatusAdminAdapter.add(mData);
    }

    @Event(value = {R.id.back_icon})
    private void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_icon:
                finish();
                break;
        }

    }

    @Override
    protected void attachPresenter() {

    }

    @Override
    protected void detachPresenter() {

    }
}
