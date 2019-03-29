package com.trade.rrenji.biz.home.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gelitenight.superrecyclerview.LinearSpacingDecoration;
import com.trade.rrenji.R;
import com.trade.rrenji.bean.home.HomeBean;
import com.trade.rrenji.bean.home.NetHomeBean;
import com.trade.rrenji.biz.data.ui.activity.DataListActivity;
import com.trade.rrenji.biz.goods.ui.activity.GoodsDetailActivity;
import com.trade.rrenji.biz.home.ui.activity.HomeCategoryActivity;
import com.trade.rrenji.fragment.RecyclerListAdapter;
import com.trade.rrenji.utils.CollectionUtils;
import com.trade.rrenji.utils.GlideUtils;
import com.trade.rrenji.utils.LoopViewPager;
import com.trade.rrenji.utils.ViewUtils;
import com.viewpagerindicator.LinePageIndicator;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by wheat on 16/1/14.
 */
public class HomeAdapter extends RecyclerListAdapter<HomeBean> {

    //广告ITEM
    public final static int BANNER_ITEM = 0;
    //类别部分
    public final static int CATEGORY_ITEM = 1;
    //热门活动
    public final static int HOT_DATE_ITEM = 2;
    //IPHONE热卖
    public final static int IPHONE_ITEM = 3;
    //IPHONE热卖
    public final static int ANDROID_ITEM = 4;
    //广告
    public final static int AD_ITEM = 5;
    //千元机
    public final static int QIAN_YUAN_ITEM = 6;
    //人人社区
    public final static int RENREN_SHEQU_ITEM = 7;
    //人人之家
    public final static int RENREN_JIA_ITEM = 8;
    //about
    public final static int RENREN_ABOUT_ITEM = 9;

    private Context mContext;

    public HomeAdapter(Context context) {
        super(context);
        mContext = context;
    }

    @Override
    public int getItemViewType(int position) {
        return getItem(position).getType();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        Log.e("onCreateViewHolder", "viewType = " + viewType);
        View convertView = inflater.inflate(createViewByType(viewType), parent, false);
        ViewHolder holder = null;
        switch (viewType) {
            case BANNER_ITEM:
                holder = new BannerViewHolder(convertView);
                break;
            case CATEGORY_ITEM:
                holder = new CategoryViewHolder(convertView);
                break;
            case HOT_DATE_ITEM:
                holder = new HotDataViewHolder(convertView);
                break;
            case IPHONE_ITEM:
                holder = new HotIphoneViewHolder(convertView);
                break;
            case ANDROID_ITEM:
                holder = new HotAndroidViewHolder(convertView);
                break;
            case AD_ITEM:
                holder = new AdViewHolder(convertView);
                break;
            case QIAN_YUAN_ITEM:
                holder = new OptimizationViewHolder(convertView);
                break;
            case RENREN_SHEQU_ITEM:
                holder = new CommunityViewHolder(convertView);
                break;
            case RENREN_JIA_ITEM:
                holder = new RenRenViewHolder(convertView);
                break;
            case RENREN_ABOUT_ITEM:
                holder = new AboutViewHolder(convertView);
                break;

        }
        return holder;
    }

    protected int createViewByType(int type) {
        int resId;
        switch (type) {
            case BANNER_ITEM:
                resId = R.layout.home_banner_data;
                break;
            case CATEGORY_ITEM:
                resId = R.layout.home_category;
                break;
            case HOT_DATE_ITEM:
                resId = R.layout.home_hot_data;
                break;
            case IPHONE_ITEM:
                resId = R.layout.home_hot_iphone_data;
                break;
            case ANDROID_ITEM:
                resId = R.layout.home_hot_android_data;
                break;
            case AD_ITEM:
                resId = R.layout.home_ad_data;
                break;
            case QIAN_YUAN_ITEM:
                resId = R.layout.home_hot_optimization_data;
                break;
            case RENREN_SHEQU_ITEM:
                resId = R.layout.home_hot_community_data;
                break;
            case RENREN_JIA_ITEM:
                resId = R.layout.home_hot_renren_data;
                break;
            case RENREN_ABOUT_ITEM:
                resId = R.layout.home_about_data;
                break;
            default:
                resId = -1;
                return resId;
        }
        return resId;
    }

    public class AboutViewHolder extends ViewHolder {

        public AboutViewHolder(View itemView) {
            super(itemView);

        }

        @Override
        public void bindData(HomeBean data, int position) {
            super.bindData(data, position);
        }
    }

    //人人之家
    public class RenRenViewHolder extends ViewHolder {

        private TextView hot_renren_text;
        private TextView hot_renren_content;
        private ImageView image_1;
        private ImageView image_2;
        private ImageView image_3;

        public RenRenViewHolder(View itemView) {
            super(itemView);
            hot_renren_text = (TextView) itemView.findViewById(R.id.hot_renren_text);
            hot_renren_content = (TextView) itemView.findViewById(R.id.hot_renren_content);
            image_1 = (ImageView) itemView.findViewById(R.id.image_1);
            image_2 = (ImageView) itemView.findViewById(R.id.image_2);
            image_3 = (ImageView) itemView.findViewById(R.id.image_3);
        }

        @Override
        public void bindData(HomeBean data, int position) {
            super.bindData(data, position);
//            NetHomeBean.DataBean.EveryoneCommunityListBean beans = data.getEveryoneCommunityList().get(0);
//            GlideUtils.getInstance().loadImageUrl(mContext, beans.getHeadImg(), R.drawable.ic_launcher, image_1);
//            GlideUtils.getInstance().loadImageUrl(mContext, beans.getHeadImg().get(1), R.drawable.ic_launcher, image_2);
//            GlideUtils.getInstance().loadImageUrl(mContext, beans.getHeadImg().get(2), R.drawable.ic_launcher, image_3);
//            hot_renren_text.setText(beans.getTitle());
//            hot_renren_content.setText(beans.getContent());
        }
    }

    //人人社区
    public class CommunityViewHolder extends ViewHolder {

        RecyclerView mHotDataTypeView;

        public CommunityViewHolder(View itemView) {
            super(itemView);
            mHotDataTypeView = itemView.findViewById(R.id.hot_optimzation_recycler_view);
        }

        @Override
        public void bindData(HomeBean data, int position) {
            super.bindData(data, position);
            HotCommunityAdapter categoryAdapter = new HotCommunityAdapter(mContext);
            mHotDataTypeView.addItemDecoration(new LinearSpacingDecoration(20, 0));
            mHotDataTypeView.setAdapter(categoryAdapter);
            LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
            mHotDataTypeView.setLayoutManager(layoutManager);
            categoryAdapter.addAll(data.getEveryoneCommunityList());
        }

        private class HotCommunityAdapter extends RecyclerView.Adapter<HotOptimizationTypeViewHolder> {

            Context mContext;
            List<NetHomeBean.DataBean.EveryoneCommunityListBean> mCategoryList;

            public HotCommunityAdapter(Context context) {
                mContext = context;
            }

            public void addAll(List<NetHomeBean.DataBean.EveryoneCommunityListBean> categoryList) {

                if (mCategoryList == null) {
                    mCategoryList = new ArrayList<NetHomeBean.DataBean.EveryoneCommunityListBean>();
                } else {
                    mCategoryList.clear();
                }
                mCategoryList.addAll(categoryList);
                notifyDataSetChanged();
            }

            @Override
            public int getItemCount() {
                return mCategoryList.size();
            }

            @Override
            public HotOptimizationTypeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(mContext).inflate(R.layout.home_hot_community_item, parent, false);
                return new HotOptimizationTypeViewHolder(view);

            }

            @Override
            public void onBindViewHolder(HotOptimizationTypeViewHolder holder, int position) {
                NetHomeBean.DataBean.EveryoneCommunityListBean bean = mCategoryList.get(position);
                GlideUtils.getInstance().loadImageUrl(mContext, bean.getEveryoneCommunityImg(), R.drawable.ic_launcher, holder.hot_community_image);
                holder.community_desc.setText(bean.getTitle());
                holder.hot_community_text.setText(bean.getContent());
            }
        }

        public class HotOptimizationTypeViewHolder extends RecyclerView.ViewHolder {
            private TextView community_desc;
            private TextView hot_community_text;
            private ImageView hot_community_image;

            public HotOptimizationTypeViewHolder(View itemView) {
                super(itemView);
                community_desc = (TextView) itemView.findViewById(R.id.community_desc);
                hot_community_text = (TextView) itemView.findViewById(R.id.hot_community_text);
                hot_community_image = (ImageView) itemView.findViewById(R.id.hot_community_image);
            }
        }

    }

    //千元机
    public class OptimizationViewHolder extends ViewHolder {

        RecyclerView mHotDataTypeView;

        public OptimizationViewHolder(View itemView) {
            super(itemView);
            mHotDataTypeView = itemView.findViewById(R.id.hot_optimzation_recycler_view);
        }

        @Override
        public void bindData(HomeBean data, int position) {
            super.bindData(data, position);
            HotOptimizationAdapter categoryAdapter = new HotOptimizationAdapter(mContext);
            mHotDataTypeView.addItemDecoration(new LinearSpacingDecoration(20, 0));
            mHotDataTypeView.setAdapter(categoryAdapter);
            LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            mHotDataTypeView.setLayoutManager(layoutManager);
            categoryAdapter.addAll(data.getThousandOptimization());
        }

        private class HotOptimizationAdapter extends RecyclerView.Adapter<HotOptimizationTypeViewHolder> {

            Context mContext;
            List<NetHomeBean.DataBean.ThousandOptimizationBean> mCategoryList;

            public HotOptimizationAdapter(Context context) {
                mContext = context;
            }

            public void addAll(List<NetHomeBean.DataBean.ThousandOptimizationBean> categoryList) {

                if (mCategoryList == null) {
                    mCategoryList = new ArrayList<NetHomeBean.DataBean.ThousandOptimizationBean>();
                } else {
                    mCategoryList.clear();
                }
                mCategoryList.addAll(categoryList);
                notifyDataSetChanged();
            }

            @Override
            public int getItemCount() {
                return mCategoryList.size();
            }

            @Override
            public HotOptimizationTypeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(mContext).inflate(R.layout.home_hot_optimization_item, parent, false);
                return new HotOptimizationTypeViewHolder(view);

            }

            @Override
            public void onBindViewHolder(HotOptimizationTypeViewHolder holder, int position) {
                final NetHomeBean.DataBean.ThousandOptimizationBean bean = mCategoryList.get(position);
                GlideUtils.getInstance().loadImageUrl(mContext, bean.getDiscoverImg(), R.drawable.ic_launcher, holder.hot_data_image);
                holder.hot_iphone_text.setText(bean.getGoodsName());
                holder.iphone_price.setText("￥" + bean.getGoodsPrice() + "");
                if (TextUtils.isEmpty(bean.getVersion())) {
                    holder.iphone_net.setVisibility(View.GONE);
                } else {
                    holder.iphone_net.setVisibility(View.VISIBLE);
                    holder.iphone_net.setText(bean.getVersion());
                }
                holder.iphone_color.setText(bean.getColor());
                holder.phone_size.setText(bean.getMemory());
                holder.main_layout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        GoodsDetailActivity.navToMainActivity(mContext, bean.getGoodsCode());
                    }
                });

            }
        }

        public class HotOptimizationTypeViewHolder extends RecyclerView.ViewHolder {
            private TextView hot_iphone_text;
            private TextView iphone_price;
            private TextView iphone_net;
            private TextView iphone_color;
            private TextView phone_size;
            private ImageView hot_data_image;
            RelativeLayout main_layout;


            public HotOptimizationTypeViewHolder(View itemView) {
                super(itemView);
                hot_iphone_text = (TextView) itemView.findViewById(R.id.hot_iphone_text);
                iphone_price = (TextView) itemView.findViewById(R.id.iphone_price);
                iphone_net = (TextView) itemView.findViewById(R.id.iphone_net);
                iphone_color = (TextView) itemView.findViewById(R.id.iphone_color);
                phone_size = (TextView) itemView.findViewById(R.id.phone_size);
                hot_data_image = (ImageView) itemView.findViewById(R.id.hot_data_image);
                main_layout = (RelativeLayout) itemView.findViewById(R.id.main_layout);

            }
        }

    }

    public class HotAndroidViewHolder extends ViewHolder {

        RecyclerView mHotDataTypeView;

        public HotAndroidViewHolder(View itemView) {
            super(itemView);
            mHotDataTypeView = itemView.findViewById(R.id.hot_android_recycler_view);
        }

        @Override
        public void bindData(HomeBean data, int position) {
            super.bindData(data, position);
            HotAndroidAdapter categoryAdapter = new HotAndroidAdapter(mContext);
            mHotDataTypeView.addItemDecoration(new LinearSpacingDecoration(20, 0));
            mHotDataTypeView.setAdapter(categoryAdapter);
            mHotDataTypeView.setLayoutManager(new LinearLayoutManager(mContext));
            categoryAdapter.addAll(data.getHotAndroid());
        }

        private class HotAndroidAdapter extends RecyclerView.Adapter<HotAndroidTypeViewHolder> {

            Context mContext;
            List<NetHomeBean.DataBean.HotAndroidBean> mCategoryList;

            public HotAndroidAdapter(Context context) {
                mContext = context;
            }

            public void addAll(List<NetHomeBean.DataBean.HotAndroidBean> categoryList) {

                if (mCategoryList == null) {
                    mCategoryList = new ArrayList<NetHomeBean.DataBean.HotAndroidBean>();
                } else {
                    mCategoryList.clear();
                }
                mCategoryList.addAll(categoryList);
                notifyDataSetChanged();
            }

            @Override
            public int getItemCount() {
                return mCategoryList.size();
            }

            @Override
            public HotAndroidTypeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(mContext).inflate(R.layout.home_hot_android_item, parent, false);
                return new HotAndroidTypeViewHolder(view);

            }

            @Override
            public void onBindViewHolder(HotAndroidTypeViewHolder holder, int position) {
                final NetHomeBean.DataBean.HotAndroidBean bean = mCategoryList.get(position);
                GlideUtils.getInstance().loadImageUrl(mContext, bean.getDiscoverImg(), R.drawable.ic_launcher, holder.hot_data_image);
                holder.hot_iphone_text.setText(bean.getGoodsName());
                holder.iphone_original_price.setText("￥" + bean.getOriginalPrice());
                holder.iphone_save_price.setText("" + (bean.getOriginalPrice() - bean.getGoodsPrice()));
                holder.iphone_price.setText("￥" + bean.getGoodsPrice() + "");
                if (TextUtils.isEmpty(bean.getVersion())) {
                    holder.iphone_net.setVisibility(View.GONE);
                } else {
                    holder.iphone_net.setVisibility(View.VISIBLE);
                    holder.iphone_net.setText(bean.getVersion());
                }
                holder.iphone_color.setText(bean.getColor());
                holder.phone_size.setText(bean.getMemory());
                holder.main_layout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        GoodsDetailActivity.navToMainActivity(mContext, bean.getGoodsCode());
                    }
                });

//                if (bean.getNewLog() == 0) {
//                    GlideUtils.getInstance().loadImage(mContext, R.drawable.icon_nine_new, R.drawable.ic_launcher, holder.hot_iphone_image);
//                } else if (bean.getNewLog() == 1) {
//                    GlideUtils.getInstance().loadImage(mContext, R.drawable.icon_nine_nine, R.drawable.ic_launcher, holder.hot_iphone_image);
//                } else if (bean.getNewLog() == 2) {
//                    GlideUtils.getInstance().loadImage(mContext, R.drawable.icon_nine_five, R.drawable.ic_launcher, holder.hot_iphone_image);
//                } else if (bean.getNewLog() == 3) {
//                    GlideUtils.getInstance().loadImage(mContext, R.drawable.icon_nine, R.drawable.ic_launcher, holder.hot_iphone_image);
//                } else if (bean.getNewLog() == 4) {
//                    GlideUtils.getInstance().loadImage(mContext, R.drawable.icon_nine_new, R.drawable.ic_launcher, holder.hot_iphone_image);
//                }
            }
        }

        public class HotAndroidTypeViewHolder extends RecyclerView.ViewHolder {
            private TextView hot_iphone_text;
            private TextView iphone_original_price;
            private TextView iphone_save_price;
            private TextView iphone_price;
            private TextView iphone_net;
            private TextView iphone_color;
            private TextView phone_size;
            private ImageView hot_iphone_image;
            private ImageView hot_data_image;
            RelativeLayout main_layout;

            public HotAndroidTypeViewHolder(View itemView) {
                super(itemView);
                hot_iphone_text = (TextView) itemView.findViewById(R.id.hot_iphone_text);
                iphone_original_price = (TextView) itemView.findViewById(R.id.iphone_original_price);
                iphone_save_price = (TextView) itemView.findViewById(R.id.iphone_save_price);
                iphone_price = (TextView) itemView.findViewById(R.id.iphone_price);
                iphone_net = (TextView) itemView.findViewById(R.id.iphone_net);
                iphone_color = (TextView) itemView.findViewById(R.id.iphone_color);
                phone_size = (TextView) itemView.findViewById(R.id.phone_size);
                hot_iphone_image = (ImageView) itemView.findViewById(R.id.hot_iphone_image);
                main_layout = (RelativeLayout) itemView.findViewById(R.id.main_layout);
                hot_data_image = (ImageView) itemView.findViewById(R.id.hot_data_image);
            }
        }

    }

    public class HotIphoneViewHolder extends ViewHolder {

        RecyclerView mHotDataTypeView;

        public HotIphoneViewHolder(View itemView) {
            super(itemView);
            mHotDataTypeView = itemView.findViewById(R.id.hot_iphone_recycler_view);
        }

        @Override
        public void bindData(HomeBean data, int position) {
            super.bindData(data, position);
            HotIphoneAdapter categoryAdapter = new HotIphoneAdapter(mContext);
            mHotDataTypeView.addItemDecoration(new LinearSpacingDecoration(20, 0));
            mHotDataTypeView.setAdapter(categoryAdapter);
            mHotDataTypeView.setLayoutManager(new LinearLayoutManager(mContext));
            categoryAdapter.addAll(data.getHotIphone());
        }

        private class HotIphoneAdapter extends RecyclerView.Adapter<HotIphoneTypeViewHolder> {

            Context mContext;
            List<NetHomeBean.DataBean.HotIphoneBean> mCategoryList;

            public HotIphoneAdapter(Context context) {
                mContext = context;
            }

            public void addAll(List<NetHomeBean.DataBean.HotIphoneBean> categoryList) {

                if (mCategoryList == null) {
                    mCategoryList = new ArrayList<NetHomeBean.DataBean.HotIphoneBean>();
                } else {
                    mCategoryList.clear();
                }
                mCategoryList.addAll(categoryList);
                notifyDataSetChanged();
            }

            @Override
            public int getItemCount() {
                return mCategoryList.size();
            }

            @Override
            public HotIphoneTypeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(mContext).inflate(R.layout.home_hot_iphone_item, parent, false);
                return new HotIphoneTypeViewHolder(view);

            }

            @Override
            public void onBindViewHolder(HotIphoneTypeViewHolder holder, int position) {
                final NetHomeBean.DataBean.HotIphoneBean bean = mCategoryList.get(position);
                GlideUtils.getInstance().loadImageUrl(mContext, bean.getDiscoverImg(), R.drawable.ic_launcher, holder.hot_data_image);
                holder.hot_iphone_text.setText(bean.getGoodsName());
                holder.iphone_original_price.setText("￥" + bean.getOriginalPrice());
                holder.iphone_save_price.setText("" + (bean.getOriginalPrice() - bean.getGoodsPrice()));
                holder.iphone_price.setText("￥" + bean.getGoodsPrice() + "");
                if (TextUtils.isEmpty(bean.getVersion())) {
                    holder.iphone_net.setVisibility(View.GONE);
                } else {
                    holder.iphone_net.setVisibility(View.VISIBLE);
                    holder.iphone_net.setText(bean.getVersion());
                }
                holder.iphone_color.setText(bean.getColor());
                holder.phone_size.setText(bean.getMemory());

                holder.main_layout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        GoodsDetailActivity.navToMainActivity(mContext, bean.getGoodsCode());
                    }
                });

//                if (bean.getNewLog() == 0) {
//                    GlideUtils.getInstance().loadImage(mContext, R.drawable.icon_nine_new, R.drawable.ic_launcher, holder.hot_iphone_image);
//                } else if (bean.getNewLog() == 1) {
//                    GlideUtils.getInstance().loadImage(mContext, R.drawable.icon_nine_nine, R.drawable.ic_launcher, holder.hot_iphone_image);
//                } else if (bean.getNewLog() == 2) {
//                    GlideUtils.getInstance().loadImage(mContext, R.drawable.icon_nine_five, R.drawable.ic_launcher, holder.hot_iphone_image);
//                } else if (bean.getNewLog() == 3) {
//                    GlideUtils.getInstance().loadImage(mContext, R.drawable.icon_nine, R.drawable.ic_launcher, holder.hot_iphone_image);
//                } else if (bean.getNewLog() == 4) {
//                    GlideUtils.getInstance().loadImage(mContext, R.drawable.icon_nine_new, R.drawable.ic_launcher, holder.hot_iphone_image);
//                }
            }
        }

        public class HotIphoneTypeViewHolder extends RecyclerView.ViewHolder {
            private TextView hot_iphone_text;
            private TextView iphone_original_price;
            private TextView iphone_save_price;
            private TextView iphone_price;
            private TextView iphone_net;
            private TextView iphone_color;
            private TextView phone_size;
            private ImageView hot_iphone_image;
            private ImageView hot_data_image;
            RelativeLayout main_layout;

            public HotIphoneTypeViewHolder(View itemView) {
                super(itemView);
                hot_iphone_text = (TextView) itemView.findViewById(R.id.hot_iphone_text);
                iphone_original_price = (TextView) itemView.findViewById(R.id.iphone_original_price);
                iphone_save_price = (TextView) itemView.findViewById(R.id.iphone_save_price);
                iphone_price = (TextView) itemView.findViewById(R.id.iphone_price);
                iphone_net = (TextView) itemView.findViewById(R.id.iphone_net);
                iphone_color = (TextView) itemView.findViewById(R.id.iphone_color);
                phone_size = (TextView) itemView.findViewById(R.id.phone_size);
                hot_iphone_image = (ImageView) itemView.findViewById(R.id.hot_iphone_image);
                hot_data_image = (ImageView) itemView.findViewById(R.id.hot_data_image);
                main_layout = (RelativeLayout) itemView.findViewById(R.id.main_layout);
            }
        }

    }

    public class HotDataViewHolder extends ViewHolder {

        RecyclerView mHotDataTypeView;

        public HotDataViewHolder(View itemView) {
            super(itemView);
            mHotDataTypeView = itemView.findViewById(R.id.hot_data_recycler_view);
        }

        @Override
        public void bindData(HomeBean data, int position) {
            super.bindData(data, position);
            HotDataAdapter categoryAdapter = new HotDataAdapter(mContext);
            mHotDataTypeView.addItemDecoration(new LinearSpacingDecoration(20, 0));
            mHotDataTypeView.setAdapter(categoryAdapter);
            mHotDataTypeView.setLayoutManager(new LinearLayoutManager(mContext));
            categoryAdapter.addAll(data.getHotActivityInfoList());
        }

        private class HotDataAdapter extends RecyclerView.Adapter<HotDataTypeViewHolder> {

            Context mContext;
            List<NetHomeBean.DataBean.HotActivityInfoListBean> mCategoryList;

            public HotDataAdapter(Context context) {
                mContext = context;
            }

            public void addAll(List<NetHomeBean.DataBean.HotActivityInfoListBean> categoryList) {

                if (mCategoryList == null) {
                    mCategoryList = new ArrayList<NetHomeBean.DataBean.HotActivityInfoListBean>();
                } else {
                    mCategoryList.clear();
                }
                mCategoryList.addAll(categoryList);
                notifyDataSetChanged();
            }

            @Override
            public int getItemCount() {
                return mCategoryList.size();
            }

            @Override
            public HotDataTypeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(mContext).inflate(R.layout.home_hot_data_item, parent, false);
                return new HotDataTypeViewHolder(view);

            }

            @Override
            public void onBindViewHolder(HotDataTypeViewHolder holder, int position) {
                final NetHomeBean.DataBean.HotActivityInfoListBean bean = mCategoryList.get(position);
                GlideUtils.getInstance().loadImageUrl(mContext, bean.getHotActivityImg(), R.drawable.ic_launcher, holder.mHotImg);
                holder.mHotStr.setText(bean.getContent());
                holder.mHotImg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mContext, DataListActivity.class);
                        intent.putExtra("id", bean.getId());
                        mContext.startActivity(intent);
                    }
                });

            }
        }

        public class HotDataTypeViewHolder extends RecyclerView.ViewHolder {
            private TextView mHotStr;
            private ImageView mHotImg;

            public HotDataTypeViewHolder(View itemView) {
                super(itemView);
                mHotStr = (TextView) itemView.findViewById(R.id.hot_data_text);
                mHotImg = (ImageView) itemView.findViewById(R.id.hot_data_image);
            }
        }

    }

    public class CategoryViewHolder extends ViewHolder {

        RecyclerView mCategoryTypeView;
        CategoryAdapter mCategoryAdapter = null;

        private int[] mRes = new int[]{R.drawable.icon_iphone, R.drawable.icon_android, R.drawable.icon_watch, R.drawable.icon_parts, R.drawable.icon_ipad, R.drawable.icon_new, R.drawable.icon_book, R.drawable.icon_bargain};
        private String[] mType = new String[]{"IPhone", "安卓", "手表", "配件", "IPad", "新机", "笔记本", "砍价"};
        List<NetHomeBean.DataBean.CategoryListBean> categoryList;

        public CategoryViewHolder(View itemView) {
            super(itemView);
            mCategoryTypeView = itemView.findViewById(R.id.category_type_recycler_view);
            categoryList = new ArrayList<NetHomeBean.DataBean.CategoryListBean>();
            bindData();
        }

        @Override
        public void bindData(HomeBean data, int position) {
            super.bindData(data, position);
            if (mCategoryAdapter == null) {
                mCategoryAdapter = new CategoryAdapter(mContext);
                mCategoryTypeView.addItemDecoration(new LinearSpacingDecoration(20, 0));
                mCategoryTypeView.setAdapter(mCategoryAdapter);
                mCategoryTypeView.setLayoutManager(new GridLayoutManager(mContext, 4));
            }
            mCategoryAdapter.addAll(data.getCategoryList());
        }

        private void bindData() {
            for (int i = 0; i < mRes.length; i++) {
                NetHomeBean.DataBean.CategoryListBean bean = new NetHomeBean.DataBean.CategoryListBean();
                bean.setId(String.valueOf(i + 1));
                bean.setCategoryImg(mRes[i]);
                bean.setCategoryName(mType[i]);
                categoryList.add(bean);
            }
        }

        private class CategoryAdapter extends RecyclerView.Adapter<CategoryTypeViewHolder> {

            Context mContext;
            List<NetHomeBean.DataBean.CategoryListBean> mCategoryList;

            public CategoryAdapter(Context context) {
                mContext = context;
            }

            public void addAll(List<NetHomeBean.DataBean.CategoryListBean> categoryList) {

                if (mCategoryList == null) {
                    mCategoryList = new ArrayList<NetHomeBean.DataBean.CategoryListBean>();
                } else {
                    mCategoryList.clear();
                }
                mCategoryList.addAll(categoryList);
                notifyDataSetChanged();
            }

            @Override
            public int getItemCount() {
                return mCategoryList.size();
            }

            @Override
            public CategoryTypeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(mContext).inflate(R.layout.category_type_item, parent, false);
                return new CategoryTypeViewHolder(view);

            }

            @Override
            public void onBindViewHolder(CategoryTypeViewHolder holder, int position) {
               final NetHomeBean.DataBean.CategoryListBean bean = mCategoryList.get(position);
                GlideUtils.getInstance().loadIcon(mContext, mRes[position], R.drawable.ic_launcher, holder.mCateTypeImg);
                holder.mCateTypeStr.setText(bean.getCategoryName());
                holder.mCateTypeImg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mContext, HomeCategoryActivity.class);
                        intent.putExtra("categoryId", bean.getId());
                        mContext.startActivity(intent);
                    }
                });
            }
        }

        public class CategoryTypeViewHolder extends RecyclerView.ViewHolder {
            private TextView mCateTypeStr;
            private ImageView mCateTypeImg;

            public CategoryTypeViewHolder(View itemView) {
                super(itemView);
                mCateTypeStr = (TextView) itemView.findViewById(R.id.type_name);
                mCateTypeImg = (ImageView) itemView.findViewById(R.id.type_image);
            }
        }

    }

    public class BannerViewHolder extends ViewHolder {

        LoopViewPager mViewPager;
        LinePageIndicator mIndicator;
        SubjectAdAdapter mSubjectAdAdapter;

        public BannerViewHolder(View itemView) {
            super(itemView);
            mViewPager = (LoopViewPager) itemView.findViewById(R.id.viewPager);
            mIndicator = (LinePageIndicator) itemView.findViewById(R.id.indicator);
        }

        @Override
        public void bindData(HomeBean data, int position) {
            super.bindData(data, position);
            List<NetHomeBean.DataBean.TopBannerListBean> mCircleAdBeans = data.getTopBannerList();
            mSubjectAdAdapter = new SubjectAdAdapter(mViewPager, mCircleAdBeans);
            mViewPager.setAdapter(mSubjectAdAdapter);
            mIndicator.setViewPager(mViewPager);

            if (!CollectionUtils.isEmpty(mCircleAdBeans)) {
                if (mCircleAdBeans.size() <= 1) {
                    mIndicator.setVisibility(View.GONE);
                    mViewPager.setEnableSwipe(false);
                } else {
                    mViewPager.setEnableSwipe(true);
                    mIndicator.setVisibility(View.VISIBLE);

                    Log.d("AdViewHolder", "enable auto paging");
                    mViewPager.setAutoPagingEnabled(true);
                }
            } else {
                mIndicator.setVisibility(View.GONE);
                mViewPager.setVisibility(View.GONE);
            }
        }

        private class SubjectAdAdapter extends PagerAdapter {
            private List<NetHomeBean.DataBean.TopBannerListBean> mCircleAdBeans;
            private int width;
            private ViewGroup mContainer;

            public SubjectAdAdapter(ViewGroup container, List<NetHomeBean.DataBean.TopBannerListBean> circleAdBeans) {
                mContainer = container;
                mCircleAdBeans = circleAdBeans;
                width = ViewUtils.getScreenWidth(getContext());

                if (getCount() > 0) {
                    int height = (int) (width * 0.6);
                    ViewGroup.LayoutParams viewPagerParam = mContainer.getLayoutParams();
                    viewPagerParam.width = width;
                    viewPagerParam.height = height;
                    mContainer.setLayoutParams(viewPagerParam);
                }
            }

            @Override
            public boolean isViewFromObject(View arg0, Object arg1) {
                return arg0 == arg1;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }

            @Override
            public int getCount() {
                return CollectionUtils.getSize(mCircleAdBeans);
            }

            @Override
            public Object instantiateItem(ViewGroup container, final int position) {
                int count = getCount();
                if (position > count - 1) {
                    return null;
                }
                final NetHomeBean.DataBean.TopBannerListBean adBean = mCircleAdBeans.get(position);
                if (null == adBean)
                    return null;

                ImageView simpleDraweeView = new ImageView(mContext);
                ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                simpleDraweeView.setLayoutParams(params);
                simpleDraweeView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                GlideUtils.getInstance().loadImageUrl(mContext, adBean.getAdImg(), R.drawable.ic_launcher, simpleDraweeView);


                simpleDraweeView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                container.addView(simpleDraweeView, 0);
                return simpleDraweeView;
            }
        }
    }

    public class AdViewHolder extends ViewHolder {

        LoopViewPager mViewPager;
        SubjectAdAdapter mSubjectAdAdapter;

        public AdViewHolder(View itemView) {
            super(itemView);
            mViewPager = (LoopViewPager) itemView.findViewById(R.id.viewPager);
        }

        @Override
        public void bindData(HomeBean data, int position) {
            super.bindData(data, position);
            List<NetHomeBean.DataBean.MiddleBannerListBean> mCircleAdBeans = data.getMiddleBannerList();
            mSubjectAdAdapter = new SubjectAdAdapter(mViewPager, mCircleAdBeans);
            mViewPager.setAdapter(mSubjectAdAdapter);

            if (!CollectionUtils.isEmpty(mCircleAdBeans)) {
                if (mCircleAdBeans.size() <= 1) {
                    mViewPager.setEnableSwipe(false);
                } else {
                    mViewPager.setEnableSwipe(true);

                    Log.d("AdViewHolder", "enable auto paging");
                    mViewPager.setAutoPagingEnabled(true);
                }
            } else {
                mViewPager.setVisibility(View.GONE);
            }
        }

        private class SubjectAdAdapter extends PagerAdapter {
            private List<NetHomeBean.DataBean.MiddleBannerListBean> mCircleAdBeans;
            private int width;
            private ViewGroup mContainer;

            public SubjectAdAdapter(ViewGroup container, List<NetHomeBean.DataBean.MiddleBannerListBean> circleAdBeans) {
                mContainer = container;
                mCircleAdBeans = circleAdBeans;
                width = ViewUtils.getScreenWidth(getContext());

                if (getCount() > 0) {
                    int height = (int) (width * 0.33);
                    ViewGroup.LayoutParams viewPagerParam = mContainer.getLayoutParams();
                    viewPagerParam.width = width;
                    viewPagerParam.height = height;
                    mContainer.setLayoutParams(viewPagerParam);
                }
            }

            @Override
            public boolean isViewFromObject(View arg0, Object arg1) {
                return arg0 == arg1;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }

            @Override
            public int getCount() {
                return CollectionUtils.getSize(mCircleAdBeans);
            }

            @Override
            public Object instantiateItem(ViewGroup container, final int position) {
                int count = getCount();
                if (position > count - 1) {
                    return null;
                }
                final NetHomeBean.DataBean.MiddleBannerListBean adBean = mCircleAdBeans.get(position);
                if (null == adBean)
                    return null;

                ImageView simpleDraweeView = new ImageView(mContext);
                ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                simpleDraweeView.setLayoutParams(params);
                simpleDraweeView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                GlideUtils.getInstance().loadImageUrl(mContext, adBean.getAdImg(), R.drawable.ic_launcher, simpleDraweeView);


                simpleDraweeView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                container.addView(simpleDraweeView, 0);
                return simpleDraweeView;
            }
        }
    }
}
