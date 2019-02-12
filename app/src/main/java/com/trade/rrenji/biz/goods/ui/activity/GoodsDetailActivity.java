package com.trade.rrenji.biz.goods.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.trade.rrenji.R;
import com.trade.rrenji.bean.collection.NetCollectionBean;
import com.trade.rrenji.bean.goods.NetGoodsDetailBean;
import com.trade.rrenji.bean.goods.NetGoodsDetailBean.ResultBean.EvaluateListBean;
import com.trade.rrenji.biz.base.BaseActivity;
import com.trade.rrenji.biz.collection.presenter.AddCollectionActivityPresenter;
import com.trade.rrenji.biz.collection.presenter.AddCollectionActivityPresenterImpl;
import com.trade.rrenji.biz.collection.ui.view.AddCollectionActivityView;
import com.trade.rrenji.biz.goods.presenter.GoodsActivityPresenter;
import com.trade.rrenji.biz.goods.presenter.GoodsActivityPresenterImpl;
import com.trade.rrenji.biz.goods.ui.adapter.GoodsBannerAdapter;
import com.trade.rrenji.biz.goods.ui.adapter.RecyclerImageAdapter;
import com.trade.rrenji.biz.goods.ui.view.GoodsActivityView;
import com.trade.rrenji.fragment.DryingTabFragment;
import com.trade.rrenji.utils.CollectionUtils;
import com.trade.rrenji.utils.GlideUtils;
import com.trade.rrenji.utils.LoopViewPager;
import com.viewpagerindicator.CirclePageIndicator;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.List;


@ContentView(R.layout.goods_datail_layout)
public class GoodsDetailActivity extends BaseActivity implements GoodsActivityView, AddCollectionActivityView {

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
    @ViewInject(R.id.reply_no_main_layout)
    RelativeLayout reply_no_main_layout;

    @ViewInject(R.id.show_changjianwenti_btn)
    TextView show_changjianwenti_btn;
    //评论
    @ViewInject(R.id.re_user_image)
    ImageView re_user_image;
    @ViewInject(R.id.re_user_name)
    TextView re_user_name;
    @ViewInject(R.id.re_content)
    TextView re_content;
    @ViewInject(R.id.re_tag)
    TextView re_tag;
    @ViewInject(R.id.re_photo1_layout)
    RelativeLayout re_photo1_layout;
    @ViewInject(R.id.re_photo2_layout)
    RelativeLayout re_photo2_layout;
    @ViewInject(R.id.re_photo3_layout)
    RelativeLayout re_photo3_layout;
    @ViewInject(R.id.re_image_layout)
    LinearLayout re_image_layout;
    @ViewInject(R.id.re_photo1)
    ImageView re_photo1;
    @ViewInject(R.id.re_photo2)
    ImageView re_photo2;
    @ViewInject(R.id.re_photo3)
    ImageView re_photo3;
    @ViewInject(R.id.chengse)
    View chengse;

    @ViewInject(R.id.id_recyclerview)
    RecyclerView id_recyclerview;

    RecyclerImageAdapter mRecyclerImageAdapter;


    ImageView[] imageViewone;

    private String[] xinjie = new String[]{"无使用痕迹，外观无磨损划伤，屏幕显示正常，功能正常，电池损耗正常。",
            "细微使用痕迹或细小划痕，屏幕显示正常，功能正常，电池损耗正常。",
            "有明显使用痕迹或明显磕碰位置（不多于三处），屏幕显示正常，功能正常，电池损耗正常。",
            "有明显使用痕迹或明显磕碰位置（多于三处），屏幕显示正常，功能正常，电池损耗正常。"};

    public static void navToMainActivity(Context context, String goodsId) {
        Intent intent = new Intent(context, GoodsDetailActivity.class);
        intent.putExtra("goodsCode", goodsId);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        mGoodsCode = getIntent().getStringExtra("goodsCode");
        loadData();
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

    @Event(value = {R.id.goods_detail_detail_colloect, R.id.goods_detail_detail_buy})
    private void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.goods_detail_detail_colloect:
                mAddCollectionPresenter.addCollection(this, mGoodsCode);
                break;
            case R.id.goods_detail_detail_buy:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void addCollection(NetCollectionBean netShareBean) {
        if (netShareBean.getCode().equals("0")) {
            Toast.makeText(this, "添加收藏成功!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "添加收藏失败!", Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void addCollectionError(int code, String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getGoodsDetail(NetGoodsDetailBean netGoodsDetailBean) {
        //轮播
        initViewPage(netGoodsDetailBean.getResult().getGoodsPics());
        //机友评价
        initBase(netGoodsDetailBean.getResult());
        initRecycler(netGoodsDetailBean.getResult().getGoodsPics());
    }

    private void initRecycler(List<NetGoodsDetailBean.ResultBean.GoodsPicsBean> beans) {
        mRecyclerImageAdapter = new RecyclerImageAdapter(this);
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

    private void initBase(NetGoodsDetailBean.ResultBean resultBean) {

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
        int jieyeu = Integer.valueOf(resultBean.getOriginalPrice()) - Integer.valueOf(resultBean.getPrice());
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
        GlideUtils.getInstance().loadIcon(this, resultBean.getQualityEngineer().getHeadUrl(), R.drawable.user_head_default_gray, engineer_avatar);

        //本机描述
        if (resultBean.getSpecification().getNativeDesc() != null) {
            NetGoodsDetailBean.ResultBean.SpecificationBean.NativeDescBean nativeDescBean = resultBean.getSpecification().getNativeDesc();
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
            final List<NetGoodsDetailBean.ResultBean.EvaluateListBean> listBeans = resultBean.getEvaluateList();
            if (listBeans != null && listBeans.size() > 0) {
                final EvaluateListBean bean = listBeans.get(0);

                if (!TextUtils.isEmpty(bean.getUserName())) {
                    if (bean.getUserName().endsWith("0")) {
                        GlideUtils.getInstance().loadIcon1(this, R.drawable.user_default_icon_0, R.drawable.user_default_icon, re_user_image);
                    } else if (bean.getUserName().endsWith("1")) {
                        GlideUtils.getInstance().loadIcon1(this, R.drawable.user_default_icon_1, R.drawable.user_default_icon, re_user_image);
                    } else if (bean.getUserName().endsWith("2")) {
                        GlideUtils.getInstance().loadIcon1(this, R.drawable.user_default_icon_2, R.drawable.user_default_icon, re_user_image);

                    } else if (bean.getUserName().endsWith("3")) {
                        GlideUtils.getInstance().loadIcon1(this, R.drawable.user_default_icon_3, R.drawable.user_default_icon, re_user_image);

                    } else if (bean.getUserName().endsWith("4")) {
                        GlideUtils.getInstance().loadIcon1(this, R.drawable.user_default_icon_4, R.drawable.user_default_icon, re_user_image);

                    } else if (bean.getUserName().endsWith("5")) {
                        GlideUtils.getInstance().loadIcon1(this, R.drawable.user_default_icon_5, R.drawable.user_default_icon, re_user_image);

                    } else if (bean.getUserName().endsWith("6")) {
                        GlideUtils.getInstance().loadIcon1(this, R.drawable.user_default_icon_6, R.drawable.user_default_icon, re_user_image);

                    } else if (bean.getUserName().endsWith("7")) {
                        GlideUtils.getInstance().loadIcon1(this, R.drawable.user_default_icon_7, R.drawable.user_default_icon, re_user_image);

                    } else if (bean.getUserName().endsWith("8")) {
                        GlideUtils.getInstance().loadIcon1(this, R.drawable.user_default_icon_8, R.drawable.user_default_icon, re_user_image);

                    } else if (bean.getUserName().endsWith("9")) {
                        GlideUtils.getInstance().loadIcon1(this, R.drawable.user_default_icon_9, R.drawable.user_default_icon, re_user_image);
                    }
                }
                re_user_name.setText(bean.getUserName());
                re_content.setText(bean.getGoodsDesc());
                re_tag.setText(bean.getGoodsDesc());
                re_image_layout.setVisibility(View.VISIBLE);
                if (bean.getSharePicList().size() >= 3) {
                    re_photo3_layout.setVisibility(View.VISIBLE);
                    re_photo2_layout.setVisibility(View.VISIBLE);
                    re_photo1_layout.setVisibility(View.VISIBLE);
                    GlideUtils.getInstance().loadIcon(this, bean.getSharePicList().get(0).getMaxPic(), R.drawable.user_head_default_gray, re_photo1);
                    GlideUtils.getInstance().loadIcon(this, bean.getSharePicList().get(1).getMaxPic(), R.drawable.user_head_default_gray, re_photo2);
                    GlideUtils.getInstance().loadIcon(this, bean.getSharePicList().get(2).getMaxPic(), R.drawable.user_head_default_gray, re_photo3);
                    re_photo1_layout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
//                            showImageActivity(GoodsDetailsActivity.this, bean.sharePicList.get(0).maxPic);
                        }
                    });
                    re_photo2_layout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
//                            showImageActivity(GoodsDetailsActivity.this, bean.sharePicList.get(1).maxPic);
                        }
                    });
                    re_photo3_layout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
//                            showImageActivity(GoodsDetailsActivity.this, bean.sharePicList.get(2).maxPic);
                        }
                    });
                } else if (bean.getSharePicList().size() == 2) {
                    re_photo3_layout.setVisibility(View.INVISIBLE);
                    re_photo2_layout.setVisibility(View.VISIBLE);
                    re_photo1_layout.setVisibility(View.VISIBLE);
                    GlideUtils.getInstance().loadIcon(this, bean.getSharePicList().get(0).getMaxPic(), R.drawable.user_head_default_gray, re_photo1);
                    GlideUtils.getInstance().loadIcon(this, bean.getSharePicList().get(1).getMaxPic(), R.drawable.user_head_default_gray, re_photo2);
                    re_photo1_layout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
//                            showImageActivity(GoodsDetailsActivity.this, bean.sharePicList.get(0).maxPic);
                        }
                    });
                    re_photo2_layout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
//                            showImageActivity(GoodsDetailsActivity.this, bean.sharePicList.get(1).maxPic);
                        }
                    });
                } else if (bean.getSharePicList().size() == 1) {
                    re_photo3_layout.setVisibility(View.INVISIBLE);
                    re_photo2_layout.setVisibility(View.INVISIBLE);
                    re_photo1_layout.setVisibility(View.VISIBLE);
                    GlideUtils.getInstance().loadIcon(this, bean.getSharePicList().get(0).getMaxPic(), R.drawable.user_head_default_gray, re_photo1);
                    re_photo1_layout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
//                            showImageActivity(GoodsDetailsActivity.this, bean.sharePicList.get(0).maxPic);
                        }
                    });
                } else {
                    re_image_layout.setVisibility(View.GONE);
                }
            }
        } else {
            reply_no_main_layout.setVisibility(View.VISIBLE);
            reply_main_layout.setVisibility(View.GONE);
        }
    }

    private void initViewPage(List<NetGoodsDetailBean.ResultBean.GoodsPicsBean> beans) {
        List<NetGoodsDetailBean.ResultBean.GoodsPicsBean> mCircleAdBeans = beans;
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
