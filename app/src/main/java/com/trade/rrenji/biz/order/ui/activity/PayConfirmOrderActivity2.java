package com.trade.rrenji.biz.order.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.gelitenight.superrecyclerview.LinearSpacingDecoration;
import com.jdpaysdk.author.Constants;
import com.jdpaysdk.author.JDPayAuthor;
import com.trade.rrenji.R;
import com.trade.rrenji.bean.goods.GoodsDetailBean;
import com.trade.rrenji.bean.goods.NetAccessoryListBean;
import com.trade.rrenji.bean.order.ContinuePayBean;
import com.trade.rrenji.bean.order.CreateOrderBean;
import com.trade.rrenji.bean.order.CreateOrderBean.AccessoriesBean;
import com.trade.rrenji.bean.order.CreateOrderBean.OrderGroupPayListBean;
import com.trade.rrenji.bean.order.LocalOrderInfoBean;
import com.trade.rrenji.bean.order.NetGetUserCreateOrderBean;
import com.trade.rrenji.bean.order.NetOrderBean;
import com.trade.rrenji.bean.order.NetOrderBean.DataBean.ResultListBean;
import com.trade.rrenji.bean.order.NetPayPlanInfoBean;
import com.trade.rrenji.bean.order.NetResultCreateOrderBean;
import com.trade.rrenji.bean.pay.AuthResult;
import com.trade.rrenji.bean.pay.PayResult;
import com.trade.rrenji.biz.base.BaseActivity;
import com.trade.rrenji.biz.order.presenter.GetUserCreateOrderInfoPresenter;
import com.trade.rrenji.biz.order.presenter.GetUserCreateOrderInfoPresenterImpl;
import com.trade.rrenji.biz.order.ui.adapter.PayOrderAdminAdapter;
import com.trade.rrenji.biz.order.ui.view.GetUserCreateOrderInfoView;
import com.trade.rrenji.event.order.GoOrderActivityEvent;
import com.trade.rrenji.utils.Contetns;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@ContentView(R.layout.pay_confirm_layout2)
public class PayConfirmOrderActivity2 extends BaseActivity implements GetUserCreateOrderInfoView {

    /**
     * "payType"
     * // 1-微信；
     * 2-支付宝【包含花呗】；
     * 3-京东【包含京东白条】；
     * 4-蚂蚁花呗分期支付；
     * 5-京东白条分期支付；
     * 6-组合支付
     */

    @ViewInject(R.id.address_name)
    TextView address_name;
    @ViewInject(R.id.address_phone)
    TextView address_phone;
    @ViewInject(R.id.address)
    TextView address;
    @ViewInject(R.id.hit_text)
    TextView hit_text;

    @ViewInject(R.id.pay_sum_price2)
    TextView pay_sum_price2;
    @ViewInject(R.id.pre_order_sum)
    TextView pre_order_sum;
    @ViewInject(R.id.order_sum_price)
    TextView order_sum_price;

    @ViewInject(R.id.recycler_view)
    RecyclerView recycler_view;

    @ViewInject(R.id.goods_detail_detail_buy)
    TextView goods_detail_detail_buy;

    @ViewInject(R.id.zfb_layout)
    RelativeLayout zfb_layout;
    @ViewInject(R.id.wx_layout)
    RelativeLayout wx_layout;
    @ViewInject(R.id.jd_layout)
    RelativeLayout jd_layout;
    @ViewInject(R.id.zh_layout)
    RelativeLayout zh_layout;

    @ViewInject(R.id.checkbox_zfb)
    CheckBox checkbox_zfb;
    @ViewInject(R.id.checkbox_wx)
    CheckBox checkbox_wx;
    @ViewInject(R.id.checkbox_jd)
    CheckBox checkbox_jd;
    @ViewInject(R.id.checkbox_zh)
    CheckBox checkbox_zh;

    private int mType = 0;
    private String mAddressId = "0";
    private int mCouponId;


    GetUserCreateOrderInfoPresenter mPresenter;
    ResultListBean mGoodsDetailBean;
    private List<NetAccessoryListBean.DataBean.ResultListBean> mListBeans;
    PayOrderAdminAdapter mPayOrderAdminAdapter;
    private double mSumPrice;
    private int mSumCount;

    //---------------------------------------------------------------------------
    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;
    String mAppId = "JDJR110932122001";
    String mMerchant = "110932122002";

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        Toast.makeText(PayConfirmOrderActivity2.this, "支付成功", Toast.LENGTH_SHORT).show();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(PayConfirmOrderActivity2.this, "支付失败---" + resultStatus + "---" + resultInfo, Toast.LENGTH_SHORT).show();
                        Log.e("SLX", payResult.toString());
                    }
                    finish();
                    break;
                }
                case SDK_AUTH_FLAG: {
                    @SuppressWarnings("unchecked")
                    AuthResult authResult = new AuthResult((Map<String, String>) msg.obj, true);
                    String resultStatus = authResult.getResultStatus();
                    // 判断resultStatus 为“9000”且result_code
                    // 为“200”则代表授权成功，具体状态码代表含义可参考授权接口文档
                    if (TextUtils.equals(resultStatus, "9000") && TextUtils.equals(authResult.getResultCode(), "200")) {
                        // 获取alipay_open_id，调支付时作为参数extern_token 的value
                        // 传入，则支付账户为该授权账户
                        Toast.makeText(PayConfirmOrderActivity2.this,
                                "授权成功\n" + String.format("authCode:%s", authResult.getAuthCode()), Toast.LENGTH_SHORT)
                                .show();
                    } else {
                        // 其他状态值则为授权失败
                        Toast.makeText(PayConfirmOrderActivity2.this,
                                "授权失败" + String.format("authCode:%s", authResult.getAuthCode()), Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
                default:
                    break;
            }
        }
    };


    private Handler mUIHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    final NetResultCreateOrderBean orderBean = (NetResultCreateOrderBean) msg.obj;
                    if (orderBean != null) {
                        Runnable payRunnable = new Runnable() {
                            @Override
                            public void run() {
                                if (mType == 2) {
                                    PayTask alipay = new PayTask(PayConfirmOrderActivity2.this);
                                    Map<String, String> result = alipay.payV2(orderBean.getResult().getSign(), true);
                                    Log.i("msp", result.toString());
                                    Message msg = new Message();
                                    msg.what = SDK_PAY_FLAG;
                                    msg.obj = result;
                                    mHandler.sendMessage(msg);
                                } else if (mType == 3) {
                                    JDPayAuthor jdPayAuthor = new JDPayAuthor();
                                    String orderId = orderBean.getResult().getOrderId();
                                    String merchant = mMerchant;
                                    String appId = mAppId;
                                    String signData = orderBean.getResult().getSign();
                                    String extraInfo = "";
                                    //json数据格式
                                    jdPayAuthor.author(PayConfirmOrderActivity2.this, orderId, merchant, appId, signData, extraInfo);
                                }
                            }
                        };
                        Thread payThread = new Thread(payRunnable);
                        payThread.start();
                    }

                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActionBarTitle("确认付款");
        initData();
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(GoOrderActivityEvent event) {
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (Constants.PAY_RESPONSE_CODE == resultCode) {//返回信息接收
            String result = data.getStringExtra(JDPayAuthor.JDPAY_RESULT);
            Log.e("onActivityResult", result);
        }
    }

    private void initData() {
        hit_text.setVisibility(View.GONE);
        mSumPrice = getIntent().getDoubleExtra("mSumPrice", -0);
        mSumCount = getIntent().getIntExtra("mSumCount", -0);
        mGoodsDetailBean = (ResultListBean) getIntent().getSerializableExtra("GoodsDetailBean");
        pay_sum_price2.setText("￥" + mSumPrice);
        pre_order_sum.setText(getString(R.string.order_mun, mSumCount));
        order_sum_price.setText("￥" + mSumPrice);
        mPresenter.getUserCreateOrderInfoByUserId(this);
        mPresenter.getPayPlanInfoList(this, mSumPrice, mGoodsDetailBean.getOrderId());
        mPayOrderAdminAdapter = new PayOrderAdminAdapter(this);
        recycler_view.addItemDecoration(new LinearSpacingDecoration(20, 0));
        recycler_view.setAdapter(mPayOrderAdminAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recycler_view.setLayoutManager(layoutManager);
        mPayOrderAdminAdapter.addAll(buildData(mGoodsDetailBean));
    }

    private List<LocalOrderInfoBean> buildData(ResultListBean data) {
        List<LocalOrderInfoBean> mList = new ArrayList<LocalOrderInfoBean>();
        LocalOrderInfoBean localOrderInfoBean = new LocalOrderInfoBean();
        localOrderInfoBean.setOrderId(data.getOrderId());
        localOrderInfoBean.setGoodsName(data.getGoodsName());
        localOrderInfoBean.setImg(data.getGoodsImg());
        localOrderInfoBean.setGoodsPrice(data.getGoodsPrice());
        localOrderInfoBean.setPayPrice(data.getOrderSum());
        mList.add(localOrderInfoBean);
        for (ResultListBean.AccessoryListBean bean : data.getAccessoryList()) {
            LocalOrderInfoBean orderInfoBean = new LocalOrderInfoBean();
            orderInfoBean.setOrderId(bean.getAccessoryId() + "");
            orderInfoBean.setGoodsName(bean.getAccessoryName());
            orderInfoBean.setImg(bean.getImageUrl());
            orderInfoBean.setPayPrice(Double.valueOf(bean.getPayPrice()));
            mList.add(orderInfoBean);
        }
        return mList;
    }

    @Event(value = {R.id.zfb_layout, R.id.wx_layout, R.id.jd_layout, R.id.zh_layout, R.id.goods_detail_detail_buy})
    private void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.zfb_layout:
                changeCheckbox(R.id.checkbox_zfb);
                mType = 2;
                break;
            case R.id.wx_layout:
                changeCheckbox(R.id.checkbox_wx);
                mType = 1;
                break;
            case R.id.jd_layout:
                changeCheckbox(R.id.checkbox_jd);
                mType = 3;
                break;
            case R.id.zh_layout:
                changeCheckbox(R.id.checkbox_zh);
                break;
            case R.id.goods_detail_detail_buy:
                onPayOrder();
                break;
        }
    }

    private void onPayOrder() {
        ContinuePayBean createOrderBean = new ContinuePayBean();
        createOrderBean.setPayType(String.valueOf(mType));
        createOrderBean.setOrderType("1");
        createOrderBean.setOrderId(mGoodsDetailBean.getOrderId());
        List<OrderGroupPayListBean> mOrderGroupPayListBean = new ArrayList<OrderGroupPayListBean>();
        createOrderBean.setOrderGroupPayList(mOrderGroupPayListBean);
        mPresenter.newContinuePay(this, createOrderBean);
    }

    private void changeCheckbox(int id) {
        checkbox_zfb.setChecked(false);
        checkbox_wx.setChecked(false);
        checkbox_jd.setChecked(false);
        checkbox_zh.setChecked(false);
        switch (id) {
            case R.id.checkbox_zfb:
                checkbox_zfb.setChecked(true);
                break;
            case R.id.checkbox_wx:
                checkbox_wx.setChecked(true);
                break;
            case R.id.checkbox_jd:
                checkbox_jd.setChecked(true);
                break;
            case R.id.checkbox_zh:
                checkbox_zh.setChecked(true);
                break;
        }
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
            mAddressId = addressBean.getAddressId();

        }
    }

    @Override
    public void createOrder(NetResultCreateOrderBean netResultCreateOrderBean) {
        Log.d("createOrder", "key : " + netResultCreateOrderBean.getResult().getSign());
        Message message = new Message();
        message.what = 0;
        message.obj = netResultCreateOrderBean;
        mUIHandler.sendMessage(message);
        Log.e("GoodsConfirmOrder", netResultCreateOrderBean.toString());
    }

    @Override
    public void createOrderError(int code, String msg) {
        Log.d("createOrderError", "msg : " + msg);
        Toast.makeText(PayConfirmOrderActivity2.this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getPayPlanInfoListError(int code, String msg) {

    }

    @Override
    public void getPayPlanInfoListSuccess(NetPayPlanInfoBean netPayPlanInfoBean) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
