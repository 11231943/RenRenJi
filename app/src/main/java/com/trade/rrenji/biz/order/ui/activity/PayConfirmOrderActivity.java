package com.trade.rrenji.biz.order.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.gelitenight.superrecyclerview.LinearSpacingDecoration;
import com.jdpaysdk.author.Constants;
import com.jdpaysdk.author.JDPayAuthor;
import com.trade.rrenji.R;
import com.trade.rrenji.bean.address.NetAddressBean;
import com.trade.rrenji.bean.coupon.NetCouponBean;
import com.trade.rrenji.bean.goods.GoodsDetailBean;
import com.trade.rrenji.bean.goods.NetAccessoryListBean;
import com.trade.rrenji.bean.order.LocalOrderInfoBean;
import com.trade.rrenji.bean.order.NetGetUserCreateOrderBean;
import com.trade.rrenji.bean.order.NetPayPlanInfoBean;
import com.trade.rrenji.bean.order.NetResultCreateOrderBean;
import com.trade.rrenji.bean.order.CreateOrderBean.*;
import com.trade.rrenji.bean.order.CreateOrderBean;
import com.trade.rrenji.bean.pay.AuthResult;
import com.trade.rrenji.bean.pay.PayResult;
import com.trade.rrenji.biz.account.ui.activity.LoginActivity;
import com.trade.rrenji.biz.address.ui.activity.AddressAdminActivity;
import com.trade.rrenji.biz.base.BaseActivity;
import com.trade.rrenji.biz.coupon.ui.activity.CouponActivity;
import com.trade.rrenji.biz.order.presenter.GetUserCreateOrderInfoPresenter;
import com.trade.rrenji.biz.order.presenter.GetUserCreateOrderInfoPresenterImpl;
import com.trade.rrenji.biz.order.ui.adapter.PayOrderAdminAdapter;
import com.trade.rrenji.biz.order.ui.view.GetUserCreateOrderInfoView;
import com.trade.rrenji.event.order.GoOrderActivityEvent;
import com.trade.rrenji.utils.Contetns;
import com.trade.rrenji.utils.ViewUtils;
import com.trade.rrenji.view.CommonPopupWindow;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@ContentView(R.layout.pay_confirm_layout)
public class PayConfirmOrderActivity extends BaseActivity implements GetUserCreateOrderInfoView {

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

    @ViewInject(R.id.coupon_txt)
    TextView coupon_txt;


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
    @ViewInject(R.id.checkbox_huabei)
    CheckBox checkbox_huabei;

    @ViewInject(R.id.three_plan_price_layout)
    RelativeLayout three_plan_price_layout;
    @ViewInject(R.id.six_plan_price_layout)
    RelativeLayout six_plan_price_layout;
    @ViewInject(R.id.t_plan_price_layout)
    RelativeLayout t_plan_price_layout;

    @ViewInject(R.id.three_plan_price)
    TextView three_plan_price;
    @ViewInject(R.id.three_plan_price_tip)
    TextView three_plan_price_tip;
    @ViewInject(R.id.six_plan_price)
    TextView six_plan_price;
    @ViewInject(R.id.six_plan_price_tip)
    TextView six_plan_price_tip;
    @ViewInject(R.id.t_plan_price)
    TextView t_plan_price;
    @ViewInject(R.id.t_plan_price_tip)
    TextView t_plan_price_tip;
    /**
     * 优惠券
     */
    @ViewInject(R.id.order_sum_coupon_price)
    TextView order_sum_coupon_price;
    @ViewInject(R.id.order_sum_coupon_txt)
    TextView order_sum_coupon_txt;
    @ViewInject(R.id.main_layout)
    RelativeLayout main_layout;


    /**
     * 1-微信；
     * 2-支付宝【包含花呗】；
     * 3-京东【包含京东白条】；
     * 4-蚂蚁花呗分期支付；
     * 5-京东白条分期支付；
     * 6-组合支付
     */
    private int mType = 0;
    private int mPlan = 0;
    //分期集合
    List<NetPayPlanInfoBean.DataBean.AliPayListBean> mPayPlanInfoList;
    private String mAddressId = "0";
    private int mCouponCount = 0;


    GetUserCreateOrderInfoPresenter mPresenter;
    GoodsDetailBean mGoodsDetailBean;
    private List<NetAccessoryListBean.DataBean.ResultListBean> mListBeans;
    PayOrderAdminAdapter mPayOrderAdminAdapter;
    private double mSumPrice;//商品原价，
    private double mPaySumPrice;//显示优惠，分期，价格
    private boolean isUseCoupon;//是否使用优惠券
    private String mCouponId = "";
    private String mCouponPrice = "0";//优惠券金额
    private int mSumCount;

    //---------------------------------------------------------------------------
    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;
    String mAppId = "JDJR110932122001";
    String mMerchant = "110932122002";

    private boolean isLogin = false;
    private int mRequestLoginCode = 10000;//登陆
    private int mRequestCouponCode = 10001;//优惠券
    private int mRequestAddressCode = 10002;//优惠券

    private CommonPopupWindow mPayWindow;
    private TextView zh_sum_price;//金额
    private TextView price_tip_value;//需要支付金额
    private EditText zfb_price;//需要支付金额

    private LinearLayout zh_fenqi_layout;//分期页面
    private RelativeLayout zh_zfb_layout;//支付宝页面

    private EditText hb_price;//花呗需要支付金额
    private EditText zh_jd_price;//京东需要支付金额
    private TextView zh_buy;//支付按钮
    private int mZhStatus = 0;
    private double mZhFrist = 0;//第一次金额

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
                        Toast.makeText(PayConfirmOrderActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                        EventBus.getDefault().post(new GoOrderActivityEvent());
                        Intent intent = new Intent(PayConfirmOrderActivity.this, OrderAllActivity.class);
                        startActivity(intent);
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(PayConfirmOrderActivity.this, "支付失败---" + resultStatus + "---" + resultInfo, Toast.LENGTH_SHORT).show();
                        Log.e("SLX", payResult.toString());
                        EventBus.getDefault().post(new GoOrderActivityEvent());
                        Intent intent = new Intent(PayConfirmOrderActivity.this, OrderActivity.class);
                        startActivity(intent);
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
                        Toast.makeText(PayConfirmOrderActivity.this,
                                "授权成功\n" + String.format("authCode:%s", authResult.getAuthCode()), Toast.LENGTH_SHORT)
                                .show();
                    } else {
                        // 其他状态值则为授权失败
                        Toast.makeText(PayConfirmOrderActivity.this,
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
                                    PayTask alipay = new PayTask(PayConfirmOrderActivity.this);
                                    Map<String, String> result = alipay.payV2(orderBean.getResult().getSign(), true);
                                    Log.i("msp", result.toString());
                                    Message msg = new Message();
                                    msg.what = SDK_PAY_FLAG;
                                    msg.obj = result;
                                    mHandler.sendMessage(msg);
                                } else if (mType == 4) {
                                    PayTask alipay = new PayTask(PayConfirmOrderActivity.this);
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
                                    jdPayAuthor.author(PayConfirmOrderActivity.this, orderId, merchant, appId, signData, extraInfo);
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
        } else if (requestCode == mRequestLoginCode && resultCode == 10000) {
            mPresenter.getUserCreateOrderInfoByUserId(this);
        } else if (requestCode == mRequestCouponCode && resultCode == 10001) {
            isUseCoupon = true;
            NetCouponBean.ResultBean.CouponListBean bean = (NetCouponBean.ResultBean.CouponListBean) data.getSerializableExtra("data");
            mCouponId = String.valueOf(bean.getCouponId());
            coupon_txt.setText("使用优惠劵" + bean.getCouponValue() + "元");
            mCouponPrice = bean.getCouponValue();
            if (mSumPrice - Double.valueOf(mCouponPrice) > 0) {
                java.text.DecimalFormat myformat = new java.text.DecimalFormat("0.00");
                String str = myformat.format((mSumPrice - Double.valueOf(mCouponPrice)));
                pay_sum_price2.setText("￥" + str);
                order_sum_coupon_txt.setVisibility(View.VISIBLE);
                order_sum_coupon_price.setVisibility(View.VISIBLE);
                order_sum_coupon_price.setText("￥" + mCouponPrice);
                mSumPrice = mSumPrice - Double.valueOf(mCouponPrice);
                resetColor();
                mPresenter.getPayPlanInfoList(this, mSumPrice, mGoodsDetailBean.getGoodsCode());
            } else {
                pay_sum_price2.setText("￥0");
                order_sum_coupon_txt.setVisibility(View.VISIBLE);
                order_sum_coupon_price.setVisibility(View.VISIBLE);
                order_sum_coupon_price.setText("￥" + mCouponPrice);
            }
        } else if (requestCode == mRequestAddressCode && resultCode == 10002) {
            NetAddressBean.ResultBean.AddressListBean bean = (NetAddressBean.ResultBean.AddressListBean) data.getSerializableExtra("data");
            address_name.setText(bean.getConsigneeName());
            address_phone.setText(bean.getConsigneeTel());
            address.setText(getResources().getString(R.string.address_show_detail,
                    bean.getProvince(), bean.getCity(), bean.getDistrict(), bean.getLocation()));
            mAddressId = bean.getAddressId();
        }
    }

    private void initData() {
        mSumPrice = getIntent().getDoubleExtra("mSumPrice", -0);
        mPaySumPrice = mSumPrice;
        mSumCount = getIntent().getIntExtra("mSumCount", -0);
        mGoodsDetailBean = (GoodsDetailBean) getIntent().getSerializableExtra("GoodsDetailBean");
        mListBeans = (List<NetAccessoryListBean.DataBean.ResultListBean>) getIntent().getSerializableExtra("accessoryList");
        pay_sum_price2.setText("￥" + mSumPrice);
        pre_order_sum.setText(getString(R.string.order_mun, mSumCount));
        order_sum_price.setText("￥" + mSumPrice);
        mPresenter.getUserCreateOrderInfoByUserId(this);
        mPresenter.getPayPlanInfoList(this, mSumPrice, mGoodsDetailBean.getGoodsCode());
        mPayOrderAdminAdapter = new PayOrderAdminAdapter(this);
        recycler_view.addItemDecoration(new LinearSpacingDecoration(20, 0));
        recycler_view.setAdapter(mPayOrderAdminAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recycler_view.setLayoutManager(layoutManager);
        mPayOrderAdminAdapter.addAll(buildData(mGoodsDetailBean, mListBeans));
        mPayWindow = new CommonPopupWindow(this, R.layout.pay_zh_layout, Integer.valueOf((ViewUtils.getScreenWidth(this)) + "")
                , ViewUtils.dip2px(this, 240)) {
            @Override
            protected void initView() {
                View view = getContentView();
                zh_sum_price = view.findViewById(R.id.zh_sum_price);//金额
                price_tip_value = view.findViewById(R.id.price_tip_value);
                zfb_price = view.findViewById(R.id.zh_zfb_price);
                zh_fenqi_layout = view.findViewById(R.id.zh_fenqi_layout);
                zh_zfb_layout = view.findViewById(R.id.zh_zfb_layout);
                hb_price = view.findViewById(R.id.hb_price);
                zh_jd_price = view.findViewById(R.id.zh_jd_price);
                zh_buy = view.findViewById(R.id.zh_buy);
                mZhStatus = 0;
                zh_zfb_layout.setVisibility(View.VISIBLE);
                zh_fenqi_layout.setVisibility(View.GONE);
                price_tip_value.setText("￥" + mSumPrice);
            }

            @Override
            protected void initEvent() {
                price_tip_value.setText("￥" + mPaySumPrice);
                zh_jd_price.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        String jdPrice = zh_jd_price.getText().toString();
                        if (!TextUtils.isEmpty(jdPrice)) {
                            hb_price.setText("");
                            if (mZhFrist - Double.valueOf(jdPrice) < 0) {
                                price_tip_value.setText("￥0");
                                zh_jd_price.setText(String.valueOf(mZhFrist));
                                zh_jd_price.setSelection(hb_price.getText().toString().length());
                            } else {
                                java.text.DecimalFormat myformat = new java.text.DecimalFormat("0.00");
                                String str = myformat.format((mZhFrist - Double.valueOf(jdPrice)));
                                price_tip_value.setText("￥" + str);
                            }
                        } else {
                            java.text.DecimalFormat myformat = new java.text.DecimalFormat("0.00");
                            String str = myformat.format(mZhFrist);
                            price_tip_value.setText("￥" + str);
                        }
                    }
                });
                hb_price.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        String zfbPrice = hb_price.getText().toString();
                        if (!TextUtils.isEmpty(zfbPrice)) {
                            zh_jd_price.setText("");
                            if (mZhFrist - Double.valueOf(zfbPrice) < 0) {
                                price_tip_value.setText("￥0");
                                hb_price.setText(String.valueOf(mZhFrist));
                                hb_price.setSelection(hb_price.getText().toString().length());
                            } else {
                                java.text.DecimalFormat myformat = new java.text.DecimalFormat("0.00");
                                String str = myformat.format((mZhFrist - Double.valueOf(zfbPrice)));
                                price_tip_value.setText("￥" + str);
                            }
                        } else {
                            java.text.DecimalFormat myformat = new java.text.DecimalFormat("0.00");
                            String str = myformat.format(mZhFrist);
                            price_tip_value.setText("￥" + str);
                        }
                    }
                });
                zfb_price.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        String zfbPrice = zfb_price.getText().toString();
                        if (!TextUtils.isEmpty(zfbPrice)) {
                            java.text.DecimalFormat myformat = new java.text.DecimalFormat("0.00");
                            String str = myformat.format((mSumPrice - Double.valueOf(zfbPrice)));
                            price_tip_value.setText("￥" + str);
                            mZhFrist = mSumPrice - Double.valueOf(zfbPrice);
                        } else {
                            price_tip_value.setText("￥" + mSumPrice);
                            mZhFrist = 0;
                        }
                    }
                });
                main_layout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mPayWindow.getPopupWindow().dismiss();
                    }
                });
                zh_sum_price.setText("￥" + mSumPrice);
                if (mZhStatus == 0) {
                    zh_buy.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            zh_zfb_layout.setVisibility(View.GONE);
                            zh_fenqi_layout.setVisibility(View.VISIBLE);
                            zh_buy.setText("确定支付");
                            mZhStatus = 1;
                        }
                    });
                } else if (mZhStatus == 1) {

                }
            }

            @Override
            protected void initWindow() {
                super.initWindow();
                PopupWindow instance = getPopupWindow();
                instance.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        WindowManager.LayoutParams lp = getWindow().getAttributes();
                        lp.alpha = 1.0f;
                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                        getWindow().setAttributes(lp);
                        mZhStatus = 0;
                        mZhFrist = 0;
                        zh_buy.setText("下一步");
                        price_tip_value.setText("￥" + mPaySumPrice);
                        zfb_price.setText("");
                        hb_price.setText("");
                        zh_jd_price.setText("");
                        zh_zfb_layout.setVisibility(View.VISIBLE);
                        zh_fenqi_layout.setVisibility(View.GONE);
                    }
                });
            }
        };
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

    @Event(value = {R.id.zfb_layout, R.id.wx_layout, R.id.jd_layout, R.id.zh_layout,
            R.id.huabei_layout, R.id.goods_detail_detail_buy, R.id.three_plan_price_layout
            , R.id.six_plan_price_layout, R.id.t_plan_price_layout, R.id.coupon_layout, R.id.address_layout})
    private void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.address_layout:
                if (isLogin) {
                    Intent intent = new Intent(PayConfirmOrderActivity.this, AddressAdminActivity.class);
                    intent.putExtra("type", 1);
                    startActivityForResult(intent, mRequestAddressCode);
                } else {
                    Intent intent = new Intent(PayConfirmOrderActivity.this, LoginActivity.class);
                    intent.putExtra("type", 1);
                    startActivityForResult(intent, mRequestLoginCode);
                }

                break;
            case R.id.three_plan_price_layout:
                changeCheckbox(R.id.checkbox_huabei);
                onChangeColor(R.id.three_plan_price_layout);
                mType = 4;
                mPlan = 3;
                if (mPayPlanInfoList != null) {
                    mPaySumPrice = Double.parseDouble(mPayPlanInfoList.get(0).getTotal());
                    pay_sum_price2.setText("￥" + mPaySumPrice);
//                    order_sum_price.setText("￥" + mPaySumPrice);
                }
                break;
            case R.id.six_plan_price_layout:
                changeCheckbox(R.id.checkbox_huabei);
                onChangeColor(R.id.six_plan_price_layout);
                mType = 4;
                mPlan = 6;
                if (mPayPlanInfoList != null) {
                    mPaySumPrice = Double.parseDouble(mPayPlanInfoList.get(1).getTotal());
                    pay_sum_price2.setText("￥" + mPaySumPrice);
//                    order_sum_price.setText("￥" + mPaySumPrice);
                }
                break;
            case R.id.t_plan_price_layout:
                changeCheckbox(R.id.checkbox_huabei);
                onChangeColor(R.id.t_plan_price_layout);
                mType = 4;
                mPlan = 12;
                if (mPayPlanInfoList != null) {
                    mPaySumPrice = Double.parseDouble(mPayPlanInfoList.get(2).getTotal());
                    pay_sum_price2.setText("￥" + mPaySumPrice);
//                    order_sum_price.setText("￥" + mPaySumPrice);
                }
                break;
            case R.id.huabei_layout:
                changeCheckbox(R.id.checkbox_huabei);
                onChangeColor(R.id.three_plan_price_layout);
                mType = 4;
                mPlan = 3;
                if (mPayPlanInfoList != null) {
                    mPaySumPrice = Double.parseDouble(mPayPlanInfoList.get(1).getTotal());
                    pay_sum_price2.setText("￥" + mPaySumPrice);
//                    order_sum_price.setText("￥" + mPaySumPrice);
                }
                break;
            case R.id.zfb_layout:
                resetColor();
                changeCheckbox(R.id.checkbox_zfb);
                mType = 2;
                mPlan = 0;
                mPaySumPrice = mSumPrice;
                pay_sum_price2.setText("￥" + mPaySumPrice);
//                order_sum_price.setText("￥" + mPaySumPrice);
                break;
            case R.id.wx_layout:
                changeCheckbox(R.id.checkbox_wx);
                mType = 1;
                mPlan = 0;
                mPaySumPrice = mSumPrice;
                pay_sum_price2.setText("￥" + mPaySumPrice);
//                order_sum_price.setText("￥" + mPaySumPrice);
                break;
            case R.id.jd_layout:
                resetColor();
                changeCheckbox(R.id.checkbox_jd);
                mType = 3;
                mPaySumPrice = mSumPrice;
                pay_sum_price2.setText("￥" + mPaySumPrice);
//                order_sum_price.setText("￥" + mPaySumPrice);
                break;
            case R.id.zh_layout:
                resetColor();
                mPlan = 0;
                mType = 6;
                changeCheckbox(R.id.checkbox_zh);
                mPaySumPrice = mSumPrice;
                pay_sum_price2.setText("￥" + mPaySumPrice);
//                order_sum_price.setText("￥" + mPaySumPrice);
                break;
            case R.id.goods_detail_detail_buy:
                if (isLogin) {
                    if (mType == 6) {
                        PopupWindow win = mPayWindow.getPopupWindow();
                        win.setAnimationStyle(R.style.animTranslate);
                        mPayWindow.showAtLocation(main_layout, Gravity.CENTER, 0, -100);
                        WindowManager.LayoutParams lp = getWindow().getAttributes();
                        lp.alpha = 0.3f;
                        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                        getWindow().setAttributes(lp);
                        price_tip_value.setText("￥" + mSumPrice);
                    } else {
                        onPayOrder();
                    }
                } else {
                    Intent intent = new Intent(PayConfirmOrderActivity.this, LoginActivity.class);
                    intent.putExtra("type", 1);
                    startActivityForResult(intent, mRequestLoginCode);
                }
                break;
            case R.id.coupon_layout:
                if (mCouponCount > 0) {
                    Intent intent = new Intent(PayConfirmOrderActivity.this, CouponActivity.class);
                    intent.putExtra("type", 1);
                    startActivityForResult(intent, mRequestCouponCode);
                }
                break;
        }
    }

    /**
     * 重置花呗分期
     */
    private void resetColor() {
        three_plan_price_layout.setBackgroundResource(R.drawable.goods_paln_item_bg);
        t_plan_price_layout.setBackgroundResource(R.drawable.goods_paln_item_bg);
        six_plan_price_layout.setBackgroundResource(R.drawable.goods_paln_item_bg);
        three_plan_price.setTextColor(Color.parseColor("#000000"));
        three_plan_price_tip.setTextColor(Color.parseColor("#cccccc"));
        six_plan_price.setTextColor(Color.parseColor("#000000"));
        six_plan_price_tip.setTextColor(Color.parseColor("#cccccc"));
        t_plan_price.setTextColor(Color.parseColor("#000000"));
        t_plan_price_tip.setTextColor(Color.parseColor("#cccccc"));
    }

    private void onChangeColor(int id) {
        resetColor();
        switch (id) {
            case R.id.three_plan_price_layout:
                three_plan_price_layout.setBackgroundResource(R.drawable.goods_paln_item_p_bg);
                three_plan_price.setTextColor(Color.parseColor("#fd5252"));
                three_plan_price_tip.setTextColor(Color.parseColor("#fd5252"));
                break;
            case R.id.six_plan_price_layout:
                six_plan_price_layout.setBackgroundResource(R.drawable.goods_paln_item_p_bg);
                six_plan_price.setTextColor(Color.parseColor("#fd5252"));
                six_plan_price_tip.setTextColor(Color.parseColor("#fd5252"));
                break;
            case R.id.t_plan_price_layout:
                t_plan_price_layout.setBackgroundResource(R.drawable.goods_paln_item_p_bg);
                t_plan_price.setTextColor(Color.parseColor("#fd5252"));
                t_plan_price_tip.setTextColor(Color.parseColor("#fd5252"));
                break;
        }
    }

    private void onPayOrder() {
        CreateOrderBean createOrderBean = new CreateOrderBean();
        createOrderBean.setAddressId(mAddressId);
        createOrderBean.setCouponId(mCouponId);
        createOrderBean.setExpressType(1);
        createOrderBean.setExtraServer(1);
        createOrderBean.setGoodsCount(1);
        createOrderBean.setGoodsId(String.valueOf(mGoodsDetailBean.getGoodsId()));
        createOrderBean.setPayType(String.valueOf(mType));
        createOrderBean.setPlan(String.valueOf(mPlan));
        createOrderBean.setTotal(String.valueOf(mPaySumPrice));
        createOrderBean.setUserOrderMessage("");
        List<AccessoriesBean> mAccessories = new ArrayList<AccessoriesBean>();
        for (NetAccessoryListBean.DataBean.ResultListBean beans : mListBeans) {
            AccessoriesBean accessoriesBean = new AccessoriesBean();
            accessoriesBean.setAccessoryId(beans.getAccessoryId());
            accessoriesBean.setCount(1);
            accessoriesBean.setPayPrice(beans.getPrice());
            mAccessories.add(accessoriesBean);
        }
        createOrderBean.setAccessories(mAccessories);
        List<OrderGroupPayListBean> mOrderGroupPayListBean = new ArrayList<OrderGroupPayListBean>();
        createOrderBean.setOrderGroupPayList(mOrderGroupPayListBean);
        mPresenter.createOrder(this, createOrderBean);
    }

    private void changeCheckbox(int id) {
        checkbox_zfb.setChecked(false);
        checkbox_wx.setChecked(false);
        checkbox_jd.setChecked(false);
        checkbox_zh.setChecked(false);
        checkbox_huabei.setChecked(false);
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
            case R.id.checkbox_huabei:
                checkbox_huabei.setChecked(true);
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
    public void getPayPlanInfoListSuccess(NetPayPlanInfoBean netPayPlanInfoBean) {
        if (netPayPlanInfoBean.getCode().equals(Contetns.RESPONSE_OK)) {
            mPayPlanInfoList = netPayPlanInfoBean.getData().getAliPayList();
            three_plan_price.setText("￥" + mPayPlanInfoList.get(0).getFirstPay() + "×" + mPayPlanInfoList.get(0).getPlan());
            three_plan_price_tip.setText("(含手续费￥" + mPayPlanInfoList.get(0).getPlanFee() + "/期)");
            six_plan_price.setText("￥" + mPayPlanInfoList.get(1).getFirstPay() + "×" + mPayPlanInfoList.get(1).getPlan());
            six_plan_price_tip.setText("(含手续费￥" + mPayPlanInfoList.get(1).getPlanFee() + "/期)");
            t_plan_price.setText("￥" + mPayPlanInfoList.get(2).getFirstPay() + "×" + mPayPlanInfoList.get(2).getPlan());
            t_plan_price_tip.setText("(含手续费￥" + mPayPlanInfoList.get(2).getPlanFee() + "/期)");
        }
    }

    @Override
    public void getPayPlanInfoListError(int code, String msg) {

    }

    @Override
    public void getUserCreateOrderInfoByUserIdError(int code, String msg) {

    }

    @Override
    public void getUserCreateOrderInfoByUserIdSuccess(NetGetUserCreateOrderBean netGetUserCreateOrderBean) {
        if (netGetUserCreateOrderBean.getCode().equals(Contetns.RESPONSE_OK)) {
            hit_text.setVisibility(View.GONE);
            isLogin = true;
            NetGetUserCreateOrderBean.DataBean.AddressBean addressBean = netGetUserCreateOrderBean.getData().getAddress();
            address_name.setText(addressBean.getConsigneeName());
            address_phone.setText(addressBean.getConsigneeTel());
            address.setText(getResources().getString(R.string.address_show_detail,
                    addressBean.getProvince(), addressBean.getCity(), addressBean.getDistrict(), addressBean.getLocation()));
            mAddressId = addressBean.getAddressId();
            mCouponCount = netGetUserCreateOrderBean.getData().getCouponCount();
            if (mCouponCount > 0) {
                coupon_txt.setText(mCouponCount + "张优惠券");
                coupon_txt.setTextColor(Color.parseColor("#fd5252"));
            } else {
                coupon_txt.setText("暂无优惠券");
                coupon_txt.setTextColor(Color.parseColor("#000000"));
            }
        } else if (netGetUserCreateOrderBean.getCode().equals("666")) {
            address_name.setText("");
            address_phone.setText("");
            address.setText("");
            coupon_txt.setText("暂无优惠券");
            coupon_txt.setTextColor(Color.parseColor("#000000"));
            hit_text.setVisibility(View.VISIBLE);
            isLogin = false;
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
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
