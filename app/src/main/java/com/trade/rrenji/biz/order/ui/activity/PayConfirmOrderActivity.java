package com.trade.rrenji.biz.order.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.gelitenight.superrecyclerview.LinearSpacingDecoration;
import com.trade.rrenji.R;
import com.trade.rrenji.bean.goods.GoodsDetailBean;
import com.trade.rrenji.bean.goods.NetAccessoryListBean;
import com.trade.rrenji.bean.order.LocalOrderInfoBean;
import com.trade.rrenji.bean.order.NetGetUserCreateOrderBean;
import com.trade.rrenji.bean.order.NetOrderBean;
import com.trade.rrenji.biz.base.BaseActivity;
import com.trade.rrenji.biz.order.presenter.GetUserCreateOrderInfoPresenter;
import com.trade.rrenji.biz.order.presenter.GetUserCreateOrderInfoPresenterImpl;
import com.trade.rrenji.biz.order.ui.adapter.PayOrderAdminAdapter;
import com.trade.rrenji.biz.order.ui.view.GetUserCreateOrderInfoView;
import com.trade.rrenji.utils.Contetns;
import com.trade.rrenji.utils.GlideUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.pay_confirm_layout)
public class PayConfirmOrderActivity extends BaseActivity implements GetUserCreateOrderInfoView {

    @ViewInject(R.id.address_name)
    TextView address_name;
    @ViewInject(R.id.address_phone)
    TextView address_phone;
    @ViewInject(R.id.address)
    TextView address;
    @ViewInject(R.id.recycler_view)
    RecyclerView recycler_view;

    @ViewInject(R.id.goods_detail_detail_buy)
    TextView goods_detail_detail_buy;

    GetUserCreateOrderInfoPresenter mPresenter;

    GoodsDetailBean mGoodsDetailBean;
    private List<NetAccessoryListBean.DataBean.ResultListBean> mListBeans;
    PayOrderAdminAdapter mPayOrderAdminAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActionBarTitle("确认付款");
        initData();
    }

    private void initData() {
        mGoodsDetailBean = (GoodsDetailBean) getIntent().getSerializableExtra("GoodsDetailBean");
        mListBeans = (List<NetAccessoryListBean.DataBean.ResultListBean>) getIntent().getSerializableExtra("accessoryList");
        mPresenter.getUserCreateOrderInfoByUserId(this);
        mPayOrderAdminAdapter = new PayOrderAdminAdapter(this);
        recycler_view.addItemDecoration(new LinearSpacingDecoration(20, 0));
        recycler_view.setAdapter(mPayOrderAdminAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recycler_view.setLayoutManager(layoutManager);
        mPayOrderAdminAdapter.addAll(buildData(mGoodsDetailBean, mListBeans));
    }

    private List<LocalOrderInfoBean> buildData(GoodsDetailBean data, List<NetAccessoryListBean.DataBean.ResultListBean> mListBeans) {
        List<LocalOrderInfoBean> mList = new ArrayList<LocalOrderInfoBean>();
        LocalOrderInfoBean localOrderInfoBean = new LocalOrderInfoBean();
        localOrderInfoBean.setOrderId(data.getGoodsCode());
        localOrderInfoBean.setGoodsName(data.getTitle());
        localOrderInfoBean.setImg(data.getGoodsCoverImg());
        localOrderInfoBean.setPayPrice(data.getPrice());
        mList.add(localOrderInfoBean);
        for (NetAccessoryListBean.DataBean.ResultListBean bean : mListBeans) {
            LocalOrderInfoBean orderInfoBean = new LocalOrderInfoBean();
            orderInfoBean.setOrderId(bean.getAccessoryId() + "");
            orderInfoBean.setGoodsName(bean.getAccessoryName());
            orderInfoBean.setImg(bean.getUrl());
            orderInfoBean.setPayPrice(Double.valueOf(bean.getPrice()));
            mList.add(orderInfoBean);
        }
        return mList;
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
