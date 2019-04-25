package com.trade.rrenji.biz.goods.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.gelitenight.superrecyclerview.LinearSpacingDecoration;
import com.trade.rrenji.R;
import com.trade.rrenji.bean.collection.NetCollectionBean;
import com.trade.rrenji.bean.drying.NetShareBean;
import com.trade.rrenji.bean.goods.GoodsDetailBean;
import com.trade.rrenji.bean.goods.NetGoodsDetailBean;
import com.trade.rrenji.biz.account.ui.activity.LoginActivity;
import com.trade.rrenji.biz.base.BaseActivity;
import com.trade.rrenji.biz.collection.presenter.AddCollectionActivityPresenter;
import com.trade.rrenji.biz.collection.presenter.AddCollectionActivityPresenterImpl;
import com.trade.rrenji.biz.collection.ui.view.AddCollectionActivityView;
import com.trade.rrenji.biz.drying.ui.adapter.DryListAdapter;
import com.trade.rrenji.biz.goods.presenter.GoodsActivityPresenter;
import com.trade.rrenji.biz.goods.presenter.GoodsActivityPresenterImpl;
import com.trade.rrenji.biz.goods.ui.adapter.GoodsBannerAdapter;
import com.trade.rrenji.biz.goods.ui.adapter.RecyclerImageAdapter;
import com.trade.rrenji.biz.goods.ui.view.GoodsActivityView;
import com.trade.rrenji.biz.order.ui.activity.PreConfirmOrderActivity;
import com.trade.rrenji.event.order.GoOrderActivityEvent;
import com.trade.rrenji.fragment.DryingTabFragment;
import com.trade.rrenji.utils.CollectionUtils;
import com.trade.rrenji.utils.GlideUtils;
import com.trade.rrenji.utils.LoopViewPager;
import com.trade.rrenji.utils.SettingUtils;
import com.trade.rrenji.view.CommonPopupWindow;
import com.viewpagerindicator.CirclePageIndicator;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;


@ContentView(R.layout.goods_datail_layout2)
public class GoodsDetailActivity2 extends BaseActivity implements GoodsActivityView, AddCollectionActivityView {

    private static String TAG = DryingTabFragment.class.getSimpleName();

    GoodsActivityPresenter mPresenter;
    AddCollectionActivityPresenter mAddCollectionPresenter;

    private String mGoodsCode = "";
    @ViewInject(R.id.viewPager)
    public LoopViewPager mLoopViewPager;
    @ViewInject(R.id.indicator)
    public CirclePageIndicator mIndicator;
    @ViewInject(R.id.new_status)
    public ImageView new_status;
    @ViewInject(R.id.goods_detail_detail_param)
    public RelativeLayout goods_detail_detail_param;
    @ViewInject(R.id.view_group)
    public RelativeLayout view_group;
    @ViewInject(R.id.goods_detail_detail_param_name)
    public TextView goods_detail_detail_param_name;
    @ViewInject(R.id.goods_detail_detail_param_price)
    public TextView goods_detail_detail_param_price;
    @ViewInject(R.id.price_yuanjia)
    public TextView price_yuanjia;
    @ViewInject(R.id.price_jieyue_jine)
    public TextView price_jieyue_jine;
    @ViewInject(R.id.goods_detail_detail_param_net)
    public TextView goods_detail_detail_param_net;
    @ViewInject(R.id.goods_detail_detail_param_version)
    public TextView goods_detail_detail_param_version;
    @ViewInject(R.id.collection_icon)
    public ImageView collection_icon;
    @ViewInject(R.id.scroll_view)
    public ScrollView mScrollView;

    @ViewInject(R.id.nested_scroll_view)
    public NestedScrollView mNestedScrollView;


    //收藏
    @ViewInject(R.id.goods_detail_detail_colloect)
    LinearLayout goods_detail_detail_colloect;
    //客服
    @ViewInject(R.id.goods_detail_detail_client_server)
    LinearLayout goods_detail_detail_client_server;
    //点击购买
    @ViewInject(R.id.goods_detail_detail_buy)
    TextView goods_detail_detail_buy;

    @ViewInject(R.id.repair_record)
    TextView repair_record;
    @ViewInject(R.id.edition)
    TextView edition;
    @ViewInject(R.id.battery_life)
    TextView battery_life;
    @ViewInject(R.id.card)
    TextView card;
    @ViewInject(R.id.inlet)
    TextView inlet;
    @ViewInject(R.id.version)
    TextView version;
    @ViewInject(R.id.goods_detail_name)
    TextView goods_detail_name;
    @ViewInject(R.id.engineer_avatar)
    ImageView engineer_avatar;
    @ViewInject(R.id.engineer_name)
    TextView engineer_name;
    @ViewInject(R.id.engineer_title)
    TextView engineer_title;
    @ViewInject(R.id.engineer_des)
    TextView engineer_des;
    @ViewInject(R.id.testing_desc)
    TextView testing_desc;
    @ViewInject(R.id.testing_chengse)
    TextView testing_chengse;
    @ViewInject(R.id.more_replay_layout)
    RelativeLayout more_replay_layout;
    @ViewInject(R.id.reply_main_layout)
    RelativeLayout reply_main_layout;

    @ViewInject(R.id.reply_recycler_view)
    RecyclerView reply_recycler_view;

    @ViewInject(R.id.reply_no_main_layout)
    RelativeLayout reply_no_main_layout;
    @ViewInject(R.id.show_changjianwenti_btn)
    TextView show_changjianwenti_btn;
    @ViewInject(R.id.id_recyclerview)
    RecyclerView id_recyclerview;
    RecyclerImageAdapter mRecyclerImageAdapter;
    ImageView[] imageViewone;
    private NetGoodsDetailBean mNetGoodsDetailBean;
    private String[] xinjie = new String[]{"无使用痕迹，外观无磨损划伤，屏幕显示正常，功能正常，电池损耗正常。",
            "细微使用痕迹或细小划痕，屏幕显示正常，功能正常，电池损耗正常。",
            "有明显使用痕迹或明显磕碰位置（不多于三处），屏幕显示正常，功能正常，电池损耗正常。",
            "有明显使用痕迹或明显磕碰位置（多于三处），屏幕显示正常，功能正常，电池损耗正常。"};

    private CommonPopupWindow mWindow;
    TextView frequency;
    TextView cpu;
    TextView post;
    TextView front;
    TextView resolvingPower;
    TextView size;
    TextView base_version;
    TextView network;
    TextView color;
    TextView memory;
    TextView model;

    private Handler mHandler = new Handler();

    public static void navToMainActivity(Context context, String goodsId) {
        Intent intent = new Intent(context, GoodsDetailActivity2.class);
        intent.putExtra("goodsCode", goodsId);
        context.startActivity(intent);
    }

    private boolean isCollection = false;
    DryListAdapter mDryListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        mGoodsCode = getIntent().getStringExtra("goodsCode");
        loadData();
        initPopupWindow();
        EventBus.getDefault().register(this);
    }

    private void initPopupWindow() {
        // get the height of screen
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int screenHeight = metrics.heightPixels;
        // create popup window
        mWindow = new CommonPopupWindow(this, R.layout.popup_list, ViewGroup.LayoutParams.MATCH_PARENT, (int) (screenHeight * 0.6)) {
            @Override
            protected void initView() {
                View view = getContentView();
                mScrollView = (ScrollView) view.findViewById(R.id.scroll_view);
                frequency = (TextView) view.findViewById(R.id.frequency);
                cpu = (TextView) view.findViewById(R.id.cpu);
                post = (TextView) view.findViewById(R.id.post);
                front = (TextView) view.findViewById(R.id.front);
                resolvingPower = (TextView) view.findViewById(R.id.resolvingPower);
                size = (TextView) view.findViewById(R.id.size);
                base_version = (TextView) view.findViewById(R.id.version);
                network = (TextView) view.findViewById(R.id.network);
                color = (TextView) view.findViewById(R.id.color);
                memory = (TextView) view.findViewById(R.id.memory);
                model = (TextView) view.findViewById(R.id.model);
            }

            @Override
            protected void initEvent() {
                mScrollView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mWindow.getPopupWindow().dismiss();
                    }
                });
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
                    }
                });
            }
        };
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(GoOrderActivityEvent event) {
        finish();
    }

    private void init() {
        setActionBarTitle("商品详情");
    }

    private void loadData() {
        if (!TextUtils.isEmpty(mGoodsCode)) {
            mPresenter.getGoodsDetail(this, mGoodsCode);
        }
    }

    @Override
    protected void attachPresenter() {
        mPresenter = new GoodsActivityPresenterImpl(this);
        mPresenter.attachView(this);
        mAddCollectionPresenter = new AddCollectionActivityPresenterImpl(this);
        mAddCollectionPresenter.attachView(this);
    }

    @Override
    protected void detachPresenter() {
        mPresenter.detachView();
        mPresenter = null;
        mAddCollectionPresenter.detachView();
        mAddCollectionPresenter = null;
    }

    @Event(value = {R.id.goods_detail_detail_colloect, R.id.goods_detail_detail_buy, R.id.goods_detail_detail_param
            , R.id.more_replay_layout})
    private void onViewClicked(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.more_replay_layout:
                intent = new Intent(this, GoodsReplyCommentActivity.class);
                intent.putExtra("goodsCode", mGoodsCode);
                startActivity(intent);
                break;
            case R.id.goods_detail_detail_colloect:
                if (TextUtils.isEmpty(SettingUtils.getInstance().getCurrentUid())) {
                    intent = new Intent(this, LoginActivity.class);
                    startActivity(intent);
                } else {
                    if (!isCollection) {
                        mAddCollectionPresenter.addCollection(this, mGoodsCode);
                    } else {
                        Toast.makeText(GoodsDetailActivity2.this, "已添加到收藏", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.goods_detail_detail_buy:
                mLoopViewPager.setAutoPagingEnabled(true);
                intent = new Intent(this, PreConfirmOrderActivity.class);
                intent.putExtra("mNetGoodsDetailBean", mNetGoodsDetailBean.getResult());
//                intent.putExtra("goodsId", mNetGoodsDetailBean.getResult().getGoodsCode());
//                intent.putExtra("title", mNetGoodsDetailBean.getResult().getTitle());
//                intent.putExtra("goodsImg", mNetGoodsDetailBean.getResult().getGoodsCoverImg());
//                intent.putExtra("price", mNetGoodsDetailBean.getResult().getPrice());
                startActivity(intent);
                break;
            case R.id.goods_detail_detail_param:
                PopupWindow win = mWindow.getPopupWindow();
                win.setAnimationStyle(R.style.animTranslate);
                mWindow.showAtLocation(view_group, Gravity.BOTTOM, 0, 0);
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 0.3f;
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                getWindow().setAttributes(lp);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void addCollection(NetCollectionBean netShareBean) {
        if (netShareBean.getCode().equals("0")) {
            isCollection = true;
            GlideUtils.getInstance().loadIcon(this, R.drawable.photo_show_thumb_hover, R.drawable.ic_launcher, collection_icon);
            Toast.makeText(this, "添加收藏成功!", Toast.LENGTH_SHORT).show();
        } else {
            isCollection = false;
            Toast.makeText(this, "添加收藏失败!", Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void addCollectionError(int code, String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getGoodsDetail(NetGoodsDetailBean netGoodsDetailBean) {

        mNetGoodsDetailBean = netGoodsDetailBean;

        GoodsDetailBean detailBean = netGoodsDetailBean.getResult();
        //轮播
        initViewPage(detailBean.getGoodsPics());
        //机友评价
        initBase(detailBean);
        //加载机型信息
        initBaseModel(detailBean);
        //加载底部图片
        initRecycler(detailBean.getGoodsPics());

    }

    private void initBaseModel(GoodsDetailBean detailBean) {
        GoodsDetailBean.SpecificationBean bean = detailBean.getSpecification();
        frequency.setText(bean.getHardware().getFrequency());
        cpu.setText(bean.getHardware().getCpu());
        post.setText(bean.getCamera().getPost());
        front.setText(bean.getCamera().getFront());
        resolvingPower.setText(bean.getScreen().getResolvingPower());
        size.setText(bean.getScreen().getSize());
        base_version.setText(bean.getBase().getVersion());
        network.setText(bean.getBase().getNetwork());
        color.setText(bean.getBase().getColor());
        memory.setText(bean.getBase().getMemory());
        model.setText(bean.getBase().getModel());
    }

    private void initRecycler(List<GoodsDetailBean.GoodsPicsBean> beans) {
        mRecyclerImageAdapter = new RecyclerImageAdapter(this);
        id_recyclerview.addItemDecoration(new LinearSpacingDecoration(20, 20));
        id_recyclerview.setLayoutManager(new LinearLayoutManager(this) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        id_recyclerview.setAdapter(mRecyclerImageAdapter);
        mRecyclerImageAdapter.addAll(beans);
    }

    @Override
    public void getGoodsDetailError(int code, String msg) {

    }

    private void initBase(GoodsDetailBean resultBean) {
        if (resultBean.isIsCollection()) {
            GlideUtils.getInstance().loadIcon(this, R.drawable.photo_show_thumb_hover, R.drawable.photo_show_thumb_default, collection_icon);
        } else {
            GlideUtils.getInstance().loadIcon(this, R.drawable.photo_show_thumb_default, R.drawable.photo_show_thumb_default, collection_icon);
        }
        testing_desc.setText(resultBean.getGoodsDesc());
        //标题
        goods_detail_detail_param_name.setText(resultBean.getTitle());
        //简单信息
        if (null != resultBean && resultBean.getSimpleSpecifications().size() >= 2) {
            goods_detail_detail_param_net.setText("网络：" + resultBean.getSimpleSpecifications().get(0));
            goods_detail_detail_param_version.setText("版本：" + resultBean.getSimpleSpecifications().get(1));
        }
        //商品价格
        goods_detail_detail_param_price.setText("￥" + resultBean.getPrice());
        price_yuanjia.setText("￥" + resultBean.getOriginalPrice());

        new_status.setVisibility(View.VISIBLE);
        if (resultBean.getConditionId() == 0) {
            new_status.setVisibility(View.GONE);
        } else if (resultBean.getConditionId() == 1) {
            new_status.setImageResource(R.drawable.new_three);
        } else if (resultBean.getConditionId() == 2) {
            new_status.setImageResource(R.drawable.new_two);
        } else if (resultBean.getConditionId() == 3) {
            new_status.setImageResource(R.drawable.new_one);
        }
        double jieyeu = Double.valueOf(resultBean.getOriginalPrice()) - Double.valueOf(resultBean.getPrice());
        price_jieyue_jine.setText(String.valueOf(jieyeu));
        goods_detail_name.setText(resultBean.getTitle());
        //检测报告
        engineer_des.setText(resultBean.getQualityEngineer().getDesc());
        engineer_title.setText(resultBean.getQualityEngineer().getTitle());
        engineer_name.setText(resultBean.getQualityEngineer().getName());
        String tempStr = "";
        if (resultBean.getConditionId() == 1 || resultBean.getConditionId() == 0) {
            tempStr = xinjie[0];
        } else if (resultBean.getConditionId() == 2) {
            tempStr = xinjie[1];
        } else if (resultBean.getConditionId() == 3) {
            tempStr = xinjie[2];
        } else if (resultBean.getConditionId() == 4) {
            tempStr = xinjie[3];
        }
        testing_chengse.setText(tempStr);
        GlideUtils.getInstance().loadImageUrl(this, resultBean.getQualityEngineer().getHeadUrl(), R.drawable.user_head_default_gray, engineer_avatar);

        //本机描述
        if (resultBean.getSpecification().getNativeDesc() != null) {
            GoodsDetailBean.SpecificationBean.NativeDescBean nativeDescBean = resultBean.getSpecification().getNativeDesc();
            repair_record.setText(nativeDescBean.getRepairRecord());
            edition.setText(nativeDescBean.getEdition());
            battery_life.setText(nativeDescBean.getBatteryLife());
            card.setText(nativeDescBean.getCard());
            inlet.setText(nativeDescBean.getInlet());
            version.setText(nativeDescBean.getVersion());
        }


        //基友评论
        if (resultBean.getEvaluateList().size() > 0) {
            reply_no_main_layout.setVisibility(View.GONE);
            reply_main_layout.setVisibility(View.VISIBLE);
            final List<GoodsDetailBean.EvaluateListBean> listBeans = resultBean.getEvaluateList();
            mDryListAdapter = new DryListAdapter(this);
            reply_recycler_view.setAdapter(mDryListAdapter);
            reply_recycler_view.setLayoutManager(new LinearLayoutManager(this));
            List<NetShareBean.ResultBean.ShareOrdersBean> beans = new ArrayList<NetShareBean.ResultBean.ShareOrdersBean>();
            NetShareBean.ResultBean.ShareOrdersBean shareOrdersBean = new NetShareBean.ResultBean.ShareOrdersBean();

            shareOrdersBean.setUserName(listBeans.get(0).getUserName());
            shareOrdersBean.setLocation(listBeans.get(0).getLocation());
            shareOrdersBean.setComment(listBeans.get(0).getComment());
            shareOrdersBean.setPhoneDesc(listBeans.get(0).getPhoneDesc());
            shareOrdersBean.setShareTime(listBeans.get(0).getShareTime());
            shareOrdersBean.setUserId(listBeans.get(0).getUserId());
            shareOrdersBean.setUserImg(listBeans.get(0).getUserImg());

            List<NetShareBean.ResultBean.ShareOrdersBean.SharePicturesBean> sharePictures = new ArrayList<NetShareBean.ResultBean.ShareOrdersBean.SharePicturesBean>();
            for (int i = 0; i < listBeans.get(0).getSharePictures().size(); i++) {
                NetShareBean.ResultBean.ShareOrdersBean.SharePicturesBean bean = new NetShareBean.ResultBean.ShareOrdersBean.SharePicturesBean();
                bean.setLargePic(listBeans.get(0).getSharePictures().get(i).getLargePic());
                bean.setMinPic(listBeans.get(0).getSharePictures().get(i).getMinPic());
                sharePictures.add(bean);
            }
            shareOrdersBean.setSharePictures(sharePictures);
            beans.add(shareOrdersBean);
            mDryListAdapter.addAll(beans);

            reply_recycler_view.setLayoutManager(new LinearLayoutManager(this) {
                @Override
                public boolean canScrollVertically() {
                    return false;
                }
            });
            reply_recycler_view.setNestedScrollingEnabled(false);
            reply_recycler_view.setHasFixedSize(true);
            reply_recycler_view.setFocusable(false);
        } else {
            reply_no_main_layout.setVisibility(View.VISIBLE);
            reply_main_layout.setVisibility(View.GONE);
        }

    }

    private void initViewPage(List<GoodsDetailBean.GoodsPicsBean> beans) {
        List<GoodsDetailBean.GoodsPicsBean> mCircleAdBeans = beans;
        GoodsBannerAdapter mSubjectAdAdapter = new GoodsBannerAdapter(this, mLoopViewPager, mCircleAdBeans);
        mLoopViewPager.setAdapter(mSubjectAdAdapter);
        mIndicator.setViewPager(mLoopViewPager);

        if (!CollectionUtils.isEmpty(mCircleAdBeans)) {
            if (mCircleAdBeans.size() <= 1) {
                mIndicator.setVisibility(View.GONE);
                mLoopViewPager.setEnableSwipe(false);
            } else {
                mLoopViewPager.setEnableSwipe(true);
                mIndicator.setVisibility(View.VISIBLE);

                Log.d("AdViewHolder", "enable auto paging");
                mLoopViewPager.setAutoPagingEnabled(true);
            }
        } else {
            mIndicator.setVisibility(View.GONE);
            mLoopViewPager.setVisibility(View.GONE);
        }
    }


}
