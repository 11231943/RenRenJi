package com.trade.rrenji.biz.order.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gelitenight.superrecyclerview.LinearSpacingDecoration;
import com.google.gson.Gson;
import com.trade.rrenji.R;
import com.trade.rrenji.bean.order.NetLogisticsBean;
import com.trade.rrenji.bean.order.NetOrderBean;
import com.trade.rrenji.biz.base.BaseActivity;
import com.trade.rrenji.biz.order.presenter.LogisticsPresenter;
import com.trade.rrenji.biz.order.presenter.LogisticsPresenterImpl;
import com.trade.rrenji.biz.order.ui.adapter.OrderStatusAdminAdapter;
import com.trade.rrenji.biz.order.ui.view.LogisticsView;
import com.trade.rrenji.utils.StatusBarUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import butterknife.Bind;
import butterknife.ButterKnife;

@ContentView(R.layout.logistics_main_activity)
public class LogisticsActivity extends BaseActivity implements LogisticsView {

    @Bind(R.id.back_icon)
    RelativeLayout back_icon;
    @Bind(R.id.new_status_txt)
    TextView new_status_txt;
    @Bind(R.id.logistics_id)
    TextView logistics_id;
    @Bind(R.id.logistics_icon)
    ImageView logistics_icon;
    @Bind(R.id.logistics_last_txt)
    TextView logistics_last_txt;
    @Bind(R.id.logistics_last_time)
    TextView logistics_last_time;
    @Bind(R.id.logistics_address_name)
    TextView logistics_address_name;
    @Bind(R.id.logistics_address)
    TextView logistics_address;
    @Bind(R.id.logistics_more)
    TextView logistics_more;
    @Bind(R.id.recycler_view_layout)
    RecyclerView recycler_view_layout;

    /**
     * mType = 0 未发货
     * mType = 1 已发货
     */
    private int mType = -1;
    NetOrderBean.DataBean.ResultListBean mData;
    OrderStatusAdminAdapter mOrderStatusAdminAdapter;
    LogisticsPresenter mPresenter;

    String json = "{\n" +
            "    \"code\": \"0\",\n" +
            "    \"msg\": \"SUCCESS\",\n" +
            "    \"result\": {\n" +
            "        \"head\": \"OK\",\n" +
            "        \"expressName\": \"顺丰快递\",\n" +
            "        \"route\": [\n" +
            "            {\n" +
            "                \"acceptTime\": \"2019-04-02 19:51:01\",\n" +
            "                \"remark\": \"顺丰速运 已收取快件\",\n" +
            "                \"accept_address\": \"深圳市\",\n" +
            "                \"opcode\": \"54\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"acceptTime\": \"2019-04-02 20:43:43\",\n" +
            "                \"remark\": \"顺丰速运 已收取快件\",\n" +
            "                \"accept_address\": \"深圳市\",\n" +
            "                \"opcode\": \"50\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"acceptTime\": \"2019-04-02 21:29:24\",\n" +
            "                \"remark\": \"快件在【深圳福田上步大厦营业部】已装车,准备发往 【深圳五和集散中心】\",\n" +
            "                \"accept_address\": \"深圳市\",\n" +
            "                \"opcode\": \"30\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"acceptTime\": \"2019-04-02 21:29:50\",\n" +
            "                \"remark\": \"快件已发车\",\n" +
            "                \"accept_address\": \"深圳市\",\n" +
            "                \"opcode\": \"36\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"acceptTime\": \"2019-04-02 21:56:55\",\n" +
            "                \"remark\": \"快件到达 【深圳五和集散中心】\",\n" +
            "                \"accept_address\": \"深圳市\",\n" +
            "                \"opcode\": \"31\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"acceptTime\": \"2019-04-02 23:22:40\",\n" +
            "                \"remark\": \"快件在【深圳五和集散中心】已装车,准备发往 【东莞沙田集散中心】\",\n" +
            "                \"accept_address\": \"深圳市\",\n" +
            "                \"opcode\": \"30\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"acceptTime\": \"2019-04-02 23:24:00\",\n" +
            "                \"remark\": \"快件已发车\",\n" +
            "                \"accept_address\": \"深圳市\",\n" +
            "                \"opcode\": \"36\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"acceptTime\": \"2019-04-03 00:54:54\",\n" +
            "                \"remark\": \"快件到达 【东莞沙田集散中心】\",\n" +
            "                \"accept_address\": \"东莞市\",\n" +
            "                \"opcode\": \"31\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"acceptTime\": \"2019-04-03 01:24:19\",\n" +
            "                \"remark\": \"快件在【东莞沙田集散中心】已装车,准备发往 【哈尔滨哈平集散中心】\",\n" +
            "                \"accept_address\": \"东莞市\",\n" +
            "                \"opcode\": \"30\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"acceptTime\": \"2019-04-03 01:27:48\",\n" +
            "                \"remark\": \"快件已发车\",\n" +
            "                \"accept_address\": \"东莞市\",\n" +
            "                \"opcode\": \"36\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"acceptTime\": \"2019-04-05 00:40:23\",\n" +
            "                \"remark\": \"快件到达 【哈尔滨哈平集散中心】\",\n" +
            "                \"accept_address\": \"哈尔滨市\",\n" +
            "                \"opcode\": \"31\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"acceptTime\": \"2019-04-05 02:41:59\",\n" +
            "                \"remark\": \"快件在【哈尔滨哈平集散中心】已装车,准备发往下一站\",\n" +
            "                \"accept_address\": \"哈尔滨市\",\n" +
            "                \"opcode\": \"30\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"acceptTime\": \"2019-04-05 05:45:05\",\n" +
            "                \"remark\": \"快件已发车\",\n" +
            "                \"accept_address\": \"哈尔滨市\",\n" +
            "                \"opcode\": \"36\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"acceptTime\": \"2019-04-05 07:43:16\",\n" +
            "                \"remark\": \"快件到达 【哈尔滨市呼兰区裕田小区营业点】\",\n" +
            "                \"accept_address\": \"哈尔滨市\",\n" +
            "                \"opcode\": \"31\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"acceptTime\": \"2019-04-05 07:46:24\",\n" +
            "                \"remark\": \"正在派送途中,请您准备签收(派件人:吕国平，电话:13945654460)\",\n" +
            "                \"accept_address\": \"哈尔滨市\",\n" +
            "                \"opcode\": \"44\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"acceptTime\": \"2019-04-05 07:58:24\",\n" +
            "                \"remark\": \"快件交给吕国平，正在派送途中（联系电话：13945654460）\",\n" +
            "                \"accept_address\": \"哈尔滨市\",\n" +
            "                \"opcode\": \"204\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"acceptTime\": \"2019-04-05 08:57:19\",\n" +
            "                \"remark\": \"已签收,感谢使用顺丰,期待再次为您服务\",\n" +
            "                \"accept_address\": \"哈尔滨市\",\n" +
            "                \"opcode\": \"80\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"acceptTime\": \"2019-04-05 08:57:22\",\n" +
            "                \"remark\": \"在官网\\\"运单资料&签收图\\\",可查看签收人信息\",\n" +
            "                \"accept_address\": \"哈尔滨市\",\n" +
            "                \"opcode\": \"8000\"\n" +
            "            }\n" +
            "        ],\n" +
            "        \"wayBillNo\": \"802498488925\"\n" +
            "    }\n" +
            "}";
    NetLogisticsBean mNetLogisticsBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logistics_main_activity);
        ButterKnife.bind(this);
        StatusBarUtils.setWindowStatusBarColor(this, R.color.mine_red);
        mType = Integer.valueOf(getIntent().getStringExtra("mType"));
        mData = (NetOrderBean.DataBean.ResultListBean) getIntent().getSerializableExtra("data");
        if (mData != null) {
            mPresenter.getOrderExpress(this, mData.getOrderId(), 1);
            try {
                Gson gson = new Gson();
                mNetLogisticsBean = gson.fromJson(json, NetLogisticsBean.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
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
            logistics_id.setText("顺丰快递:" + mNetLogisticsBean.getResult().getWayBillNo());
            logistics_last_txt.setText(mNetLogisticsBean.getResult().getRoute().get(mNetLogisticsBean.getResult().getRoute().size() - 1).getRemark());
            logistics_last_time.setText(mNetLogisticsBean.getResult().getRoute().get(mNetLogisticsBean.getResult().getRoute().size() - 1).getAcceptTime());
            logistics_more.setText("点击查看更多物流详情");
            new_status_txt.setText("快递已到达:" + mNetLogisticsBean.getResult().getRoute().get(mNetLogisticsBean.getResult().getRoute().size() - 1).getAccept_address());
        }
        logistics_address_name.setText(mData.getGoodsName() + "  " + mData.getAddressPhone());
        logistics_address.setText(mData.getAddressDetail());
        mOrderStatusAdminAdapter = new OrderStatusAdminAdapter(this);
        recycler_view_layout.addItemDecoration(new LinearSpacingDecoration(25, 5));
        recycler_view_layout.setAdapter(mOrderStatusAdminAdapter);
        recycler_view_layout.setLayoutManager(new LinearLayoutManager(this));
        mOrderStatusAdminAdapter.add(mData);
        logistics_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogisticsActivity.this, TraceActivity.class);
                intent.putExtra("data", mNetLogisticsBean);
                startActivity(intent);
            }
        });
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
        mPresenter = new LogisticsPresenterImpl(this);
        mPresenter.attachView(this);
    }

    @Override
    protected void detachPresenter() {
        mPresenter.detachView();
        mPresenter = null;
    }

    @Override
    public void getOrderExpressSuccess(NetLogisticsBean netAccessoryListBean) {
        mNetLogisticsBean = netAccessoryListBean;
        logistics_id.setText("顺丰快递:" + mNetLogisticsBean.getResult().getWayBillNo());
        logistics_last_txt.setText(mNetLogisticsBean.getResult().getRoute().get(mNetLogisticsBean.getResult().getRoute().size() - 1).getRemark());
        logistics_last_time.setText(mNetLogisticsBean.getResult().getRoute().get(mNetLogisticsBean.getResult().getRoute().size() - 1).getAcceptTime());
        logistics_more.setText("点击查看更多物流详情");
        new_status_txt.setText("快递已到达:" + mNetLogisticsBean.getResult().getRoute().get(mNetLogisticsBean.getResult().getRoute().size() - 1).getAccept_address());
    }

    @Override
    public void getOrderExpressError(int code, String msg) {

    }
}
