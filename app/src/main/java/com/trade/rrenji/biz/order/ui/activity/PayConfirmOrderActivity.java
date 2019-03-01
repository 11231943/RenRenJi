package com.trade.rrenji.biz.order.ui.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.trade.rrenji.R;
import com.trade.rrenji.biz.base.BaseActivity;
import com.trade.rrenji.utils.GlideUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.pay_confirm_layout)
public class PayConfirmOrderActivity extends BaseActivity {

    @ViewInject(R.id.address_name)
    TextView address_name;
    @ViewInject(R.id.address_phone)
    TextView address_phone;
    @ViewInject(R.id.address)
    TextView address;

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

    @ViewInject(R.id.goods_detail_detail_buy)
    TextView goods_detail_detail_buy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActionBarTitle("确认付款");
    }

    private void initData(){
//        GlideUtils.getInstance().loadIcon(this, mGoodImg, R.drawable.ic_launcher, order_image);
//        order_name.setText(mTitle);
//        order_price.setText("￥" + mGoodPrice);
    }

    @Override
    protected void attachPresenter() {

    }

    @Override
    protected void detachPresenter() {

    }
}
