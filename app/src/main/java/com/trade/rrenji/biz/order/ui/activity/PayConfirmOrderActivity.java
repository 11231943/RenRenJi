package com.trade.rrenji.biz.order.ui.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.trade.rrenji.R;
import com.trade.rrenji.bean.order.NetGetUserCreateOrderBean;
import com.trade.rrenji.biz.base.BaseActivity;
import com.trade.rrenji.biz.order.presenter.GetUserCreateOrderInfoPresenter;
import com.trade.rrenji.biz.order.presenter.GetUserCreateOrderInfoPresenterImpl;
import com.trade.rrenji.biz.order.ui.view.GetUserCreateOrderInfoView;
import com.trade.rrenji.utils.Contetns;
import com.trade.rrenji.utils.GlideUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.pay_confirm_layout)
public class PayConfirmOrderActivity extends BaseActivity implements GetUserCreateOrderInfoView {

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

    GetUserCreateOrderInfoPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActionBarTitle("确认付款");
        initData();
    }

    private void initData() {
        mPresenter.getUserCreateOrderInfoByUserId(this);
//        GlideUtils.getInstance().loadIcon(this, mGoodImg, R.drawable.ic_launcher, order_image);
//        order_name.setText(mTitle);
//        order_price.setText("￥" + mGoodPrice);
    }

    @Override
    protected void attachPresenter() {
        mPresenter = new GetUserCreateOrderInfoPresenterImpl(this);
        mPresenter.attachView(this);
    }

    @Override
    protected void detachPresenter() {
        mPresenter.detachView();
        mPresenter = null;
    }

    @Override
    public void getUserCreateOrderInfoByUserIdError(int code, String msg) {

    }

    @Override
    public void getUserCreateOrderInfoByUserIdSuccess(NetGetUserCreateOrderBean netGetUserCreateOrderBean) {
        if (netGetUserCreateOrderBean.getCode().equals(Contetns.RESPONSE_OK)) {
            NetGetUserCreateOrderBean.DataBean.AddressBean addressBean = netGetUserCreateOrderBean.getData().getAddress();
            address_name.setText(addressBean.getConsigneeName());
            address_phone.setText(addressBean.getConsigneeTel());
            address.setText(getResources().getString(R.string.address_show_detail,
                    addressBean.getProvince(), addressBean.getCity(), addressBean.getDistrict(), addressBean.getLocation()));

        }
    }
}
