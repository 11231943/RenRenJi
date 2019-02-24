package com.trade.rrenji.biz.order.ui.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.trade.rrenji.R;
import com.trade.rrenji.bean.goods.NetGoodsDetailBean;
import com.trade.rrenji.biz.base.BaseActivity;
import com.trade.rrenji.utils.GlideUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.pre_confirm_layout)
public class PreConfirmOrderActivity extends BaseActivity {

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

    private String mTitle;
    private String mGoodId;
    private String mGoodImg;
    private double mGoodPrice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActionBarTitle("确认订单");
        mTitle = getIntent().getStringExtra("title");
        mGoodId = getIntent().getStringExtra("goodsId");
        mGoodImg = getIntent().getStringExtra("goodsImg");
        mGoodPrice = getIntent().getDoubleExtra("price",-0);
        initData();
    }

    private void initData() {
            GlideUtils.getInstance().loadIcon(this, mGoodImg, R.drawable.ic_launcher, order_image);
            order_name.setText(mTitle);
            order_price.setText("￥" + mGoodPrice);
            order_sum_price.setText("￥" + mGoodPrice);

    }

    @Override
    protected void attachPresenter() {

    }

    @Override
    protected void detachPresenter() {

    }
}
