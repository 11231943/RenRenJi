package com.trade.rrenji.biz.order.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gelitenight.superrecyclerview.LinearSpacingDecoration;
import com.trade.rrenji.R;
import com.trade.rrenji.bean.goods.NetAccessoryListBean;
import com.trade.rrenji.bean.home.NetHomeBean;
import com.trade.rrenji.bean.order.NetOrderBean;
import com.trade.rrenji.biz.base.BaseActivity;
import com.trade.rrenji.biz.home.ui.adapter.HomeAdapter;
import com.trade.rrenji.biz.order.presenter.AccessoryInfoPresenter;
import com.trade.rrenji.biz.order.presenter.AccessoryInfoPresenterImpl;
import com.trade.rrenji.biz.order.ui.view.AccessoryInfoView;
import com.trade.rrenji.fragment.RecyclerListAdapter;
import com.trade.rrenji.utils.GlideUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.pre_confirm_layout)
public class PreConfirmOrderActivity extends BaseActivity implements AccessoryInfoView {

    @ViewInject(R.id.order_image)
    ImageView order_image;
    @ViewInject(R.id.order_name)
    TextView order_name;
    @ViewInject(R.id.order_color)
    TextView order_color;
    @ViewInject(R.id.order_size)
    TextView order_size;
    @ViewInject(R.id.order_price)
    TextView order_price;
    @ViewInject(R.id.order_mun)
    TextView order_mun;
    @ViewInject(R.id.pre_order_sum)
    TextView pre_order_sum;

    @ViewInject(R.id.order_sum_price)
    TextView order_sum_price;

    @ViewInject(R.id.confirm_btn)
    TextView confirm_btn;

    @ViewInject(R.id.recycler_view)
    RecyclerView recyclerView;


    private String mTitle;
    private String mGoodCode;
    private String mGoodImg;
    private double mGoodPrice;
    AccessoryInfoPresenter mPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActionBarTitle("确认订单");
        mTitle = getIntent().getStringExtra("title");
        mGoodCode = getIntent().getStringExtra("goodsId");
        mGoodImg = getIntent().getStringExtra("goodsImg");
        mGoodPrice = getIntent().getDoubleExtra("price", -0);
        initData();
    }

    private void initData() {
        GlideUtils.getInstance().loadIcon(this, mGoodImg, R.drawable.ic_launcher, order_image);
        order_name.setText(mTitle);
        order_price.setText("￥" + mGoodPrice);
        order_sum_price.setText("￥" + mGoodPrice);
        confirm_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PreConfirmOrderActivity.this, PayConfirmOrderActivity.class);
                startActivity(intent);
            }
        });
        mPresenter.getAccessoryInfo(this, mGoodCode);

    }

    @Override
    public void getAccessoryInfoSuccess(NetAccessoryListBean netAccessoryListBean) {
        AccessoryInfoAdapter accessoryInfoAdapter = new AccessoryInfoAdapter(this);
        recyclerView.addItemDecoration(new LinearSpacingDecoration(20, 0));
        recyclerView.setAdapter(accessoryInfoAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        accessoryInfoAdapter.addAll(netAccessoryListBean.getData().getResultList());
        accessoryInfoAdapter.notifyDataSetChanged();
    }

    @Override
    public void getAccessoryInfoError(int code, String msg) {

    }

    @Override
    protected void attachPresenter() {
        mPresenter = new AccessoryInfoPresenterImpl(this);
        mPresenter.attachView(this);
    }

    @Override
    protected void detachPresenter() {
        mPresenter.detachView();
        mPresenter = null;
    }

    /**
     * 配件适配器
     */
    public class AccessoryInfoAdapter extends RecyclerListAdapter<NetAccessoryListBean.DataBean.ResultListBean> {

        private Activity mContext;

        private List<NetAccessoryListBean.DataBean.ResultListBean> mAccessoryList;

        public AccessoryInfoAdapter(Activity context) {
            super(context);
            mContext = context;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.accessory_item, parent, false);
            return new AccessoryViewHolder(view);
        }

        public class AccessoryViewHolder extends ViewHolder {

            TextView accessory_text;
            TextView accessory_price;
            ImageView accessory_image;

            public AccessoryViewHolder(View itemView) {
                super(itemView);
                accessory_image = (ImageView) itemView.findViewById(R.id.accessory_image);
                accessory_text = (TextView) itemView.findViewById(R.id.accessory_text);
                accessory_price = (TextView) itemView.findViewById(R.id.accessory_price);

            }

            @Override
            public void bindData(NetAccessoryListBean.DataBean.ResultListBean data, int position) {
                super.bindData(data, position);
                GlideUtils.getInstance().loadIcon(mContext, data.getUrl(), R.drawable.ic_launcher, accessory_image);
                accessory_text.setText(data.getAccessoryName());
                accessory_price.setText("￥" + data.getPrice());
            }
        }
    }
}
