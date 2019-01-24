package com.trade.rrenji.biz.category.ui.apater;

import android.content.Context;
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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gelitenight.superrecyclerview.LinearSpacingDecoration;
import com.trade.rrenji.R;
import com.trade.rrenji.bean.category.CategoryBean;
import com.trade.rrenji.bean.category.NetCategoryBean;
import com.trade.rrenji.fragment.RecyclerListAdapter;
import com.trade.rrenji.utils.CollectionUtils;
import com.trade.rrenji.utils.GlideUtils;
import com.trade.rrenji.utils.LoopViewPager;
import com.viewpagerindicator.LinePageIndicator;

import java.util.ArrayList;
import java.util.List;

public class RightCategoryAdapter extends RecyclerListAdapter<CategoryBean> {

    private Context mContext;

    //广告ITEM
    public final static int BANNER_ITEM = 0;
    //类别部分
    public final static int CATEGORY_ITEM = 1;
    //热门活动
    public final static int HOT_DATE_ITEM = 2;
    //
    public final static int DATE_ITEM = 3;


    public RightCategoryAdapter(Context context) {
        super(context);
        mContext = context;
    }

    @Override
    public int getItemViewType(int position) {
        return getItem(position).getType();
    }

    public void addAllData(List<CategoryBean> beans){
        clear();
        addAll(beans);
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
                holder = new CategoryDataViewHolder(convertView);
                break;
            case HOT_DATE_ITEM:
                holder = new CategoryHotDataViewHolder(convertView);
                break;
            case DATE_ITEM:
                holder = new CategoryBaseDataViewHolder(convertView);
                break;
        }
        return holder;
    }

    protected int createViewByType(int type) {
        int resId;
        switch (type) {
            case BANNER_ITEM:
                resId = R.layout.category_banner_data;
                break;
            case CATEGORY_ITEM:
                resId = R.layout.right_category_data;
                break;
            case HOT_DATE_ITEM:
                resId = R.layout.right_category_hot_data;
                break;
            case DATE_ITEM:
                resId = R.layout.right_category_data;
                break;
            default:
                resId = -1;
                return resId;
        }
        return resId;
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
        public void bindData(CategoryBean data, int position) {
            super.bindData(data, position);
            List<NetCategoryBean.DataBean.ResultListBean.AdvertisementList> mCircleAdBeans = data.getAdvertisementList();
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
            private List<NetCategoryBean.DataBean.ResultListBean.AdvertisementList> mCircleAdBeans;
            private int width;
            private ViewGroup mContainer;

            public SubjectAdAdapter(ViewGroup container, List<NetCategoryBean.DataBean.ResultListBean.AdvertisementList> circleAdBeans) {
                mContainer = container;
                mCircleAdBeans = circleAdBeans;
//                width = ViewUtils.getScreenWidth(getContext());
//
//                if (getCount() > 0) {
//                    int height = (int) (width * 0.3);
//                    ViewGroup.LayoutParams viewPagerParam = mContainer.getLayoutParams();
//                    viewPagerParam.width = width;
//                    viewPagerParam.height = height;
//                    mContainer.setLayoutParams(viewPagerParam);
//                }
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
                final NetCategoryBean.DataBean.ResultListBean.AdvertisementList adBean = mCircleAdBeans.get(position);
                if (null == adBean)
                    return null;

                ImageView simpleDraweeView = new ImageView(mContext);
//                ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
//                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//                simpleDraweeView.setLayoutParams(params);
//                simpleDraweeView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                GlideUtils.getInstance().loadIcon(mContext, adBean.getImgUrl(), R.drawable.ic_launcher, simpleDraweeView);


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

    /**
     * 分类
     */
    public class CategoryDataViewHolder extends ViewHolder {

        RecyclerView mHotDataTypeView;
        CategoryDataAdapter mCategoryAdapter;

        public CategoryDataViewHolder(View itemView) {
            super(itemView);
            mHotDataTypeView = itemView.findViewById(R.id.right_base_recycler_view);
        }

        @Override
        public void bindData(CategoryBean data, int position) {
            super.bindData(data, position);
            if (mCategoryAdapter == null) {
                mCategoryAdapter = new CategoryDataAdapter(mContext);
                mHotDataTypeView.addItemDecoration(new LinearSpacingDecoration(20, 0));
                mHotDataTypeView.setAdapter(mCategoryAdapter);
                mHotDataTypeView.setLayoutManager(new GridLayoutManager(mContext, 3));
            }
            mCategoryAdapter.addAll(data.getCategoryBrandList());
        }

        private class CategoryDataAdapter extends RecyclerView.Adapter<CategoryDataViewItemHolder> {

            Context mContext;
            List<NetCategoryBean.DataBean.ResultListBean.CategoryBrandList> mCategoryList;

            public CategoryDataAdapter(Context context) {
                mContext = context;
            }

            public void addAll(List<NetCategoryBean.DataBean.ResultListBean.CategoryBrandList> categoryList) {

                if (mCategoryList == null) {
                    mCategoryList = new ArrayList<NetCategoryBean.DataBean.ResultListBean.CategoryBrandList>();
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
            public CategoryDataViewItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(mContext).inflate(R.layout.right_category_item, parent, false);
                return new CategoryDataViewItemHolder(view);

            }

            @Override
            public void onBindViewHolder(CategoryDataViewItemHolder holder, int position) {
                NetCategoryBean.DataBean.ResultListBean.CategoryBrandList bean = mCategoryList.get(position);
                GlideUtils.getInstance().loadIcon(mContext, bean.getBrandImg(), R.drawable.ic_launcher, holder.image_src);
            }
        }

        public class CategoryDataViewItemHolder extends RecyclerView.ViewHolder {

            private ImageView image_src;

            public CategoryDataViewItemHolder(View itemView) {
                super(itemView);
                image_src = (ImageView) itemView.findViewById(R.id.image_src);
            }
        }

    }

    /**
     * 热门
     */
    public class CategoryHotDataViewHolder extends ViewHolder {

        RecyclerView mHotDataTypeView;

        CategoryDataAdapter mCategoryAdapter;

        public CategoryHotDataViewHolder(View itemView) {
            super(itemView);
            mHotDataTypeView = itemView.findViewById(R.id.right_hot_recycler_view);
        }

        @Override
        public void bindData(CategoryBean data, int position) {
            super.bindData(data, position);
            if (mCategoryAdapter == null) {
                mCategoryAdapter = new CategoryDataAdapter(mContext);
//                mHotDataTypeView.addItemDecoration(new LinearSpacingDecoration(20, 0));
                mHotDataTypeView.setAdapter(mCategoryAdapter);
                mHotDataTypeView.setLayoutManager(new GridLayoutManager(mContext, 3));
            }
            mCategoryAdapter.addAll(data.getHotProductList());
        }

        private class CategoryDataAdapter extends RecyclerView.Adapter<CategoryDataViewItemHolder> {

            Context mContext;
            List<NetCategoryBean.DataBean.ResultListBean.HotProductListBean> mCategoryList;

            public CategoryDataAdapter(Context context) {
                mContext = context;
            }

            public void addAll(List<NetCategoryBean.DataBean.ResultListBean.HotProductListBean> categoryList) {

                if (mCategoryList == null) {
                    mCategoryList = new ArrayList<NetCategoryBean.DataBean.ResultListBean.HotProductListBean>();
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
            public CategoryDataViewItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(mContext).inflate(R.layout.right_category_hot_data_item, parent, false);
                return new CategoryDataViewItemHolder(view);

            }

            @Override
            public void onBindViewHolder(CategoryDataViewItemHolder holder, int position) {
                NetCategoryBean.DataBean.ResultListBean.HotProductListBean bean = mCategoryList.get(position);
                GlideUtils.getInstance().loadIcon(mContext, bean.getProductImg(), R.drawable.ic_launcher, holder.image_src);
                holder.category_title.setText(bean.getProductName());
            }
        }

        public class CategoryDataViewItemHolder extends RecyclerView.ViewHolder {

            private ImageView image_src;
            private TextView category_title;

            public CategoryDataViewItemHolder(View itemView) {
                super(itemView);
                image_src = (ImageView) itemView.findViewById(R.id.image_pic);
                category_title = (TextView) itemView.findViewById(R.id.category_title);
            }
        }

    }

    /**
     * 基础
     */
    public class CategoryBaseDataViewHolder extends ViewHolder {

        RecyclerView mHotDataTypeView;
        CategoryDataAdapter mCategoryAdapter = null;
        LinearLayout mAndroidLayout;

        public CategoryBaseDataViewHolder(View itemView) {
            super(itemView);
            mHotDataTypeView = itemView.findViewById(R.id.right_base_recycler_view);
            mAndroidLayout = itemView.findViewById(R.id.android_layout);
        }

        @Override
        public void bindData(CategoryBean data, int position) {
            super.bindData(data, position);
            if(data.getName().equals("安卓")){
                mAndroidLayout.setVisibility(View.VISIBLE);
            }else{
                mAndroidLayout.setVisibility(View.GONE);
            }
            if (mCategoryAdapter == null) {
                mCategoryAdapter = new CategoryDataAdapter(mContext);
//                mHotDataTypeView.addItemDecoration(new LinearSpacingDecoration(20, 0));
                mHotDataTypeView.setAdapter(mCategoryAdapter);
                mHotDataTypeView.setLayoutManager(new GridLayoutManager(mContext, 3));
            }
            mCategoryAdapter.addAll(data.getGoodsModelList());
        }

        private class CategoryDataAdapter extends RecyclerView.Adapter<CategoryDataViewItemHolder> {

            Context mContext;
            List<NetCategoryBean.DataBean.ResultListBean.GoodsModelList> mCategoryList;

            public CategoryDataAdapter(Context context) {
                mContext = context;
            }

            public void addAll(List<NetCategoryBean.DataBean.ResultListBean.GoodsModelList> categoryList) {

                if (mCategoryList == null) {
                    mCategoryList = new ArrayList<NetCategoryBean.DataBean.ResultListBean.GoodsModelList>();
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
            public CategoryDataViewItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(mContext).inflate(R.layout.right_category_hot_data_item, parent, false);
                return new CategoryDataViewItemHolder(view);

            }

            @Override
            public void onBindViewHolder(CategoryDataViewItemHolder holder, int position) {
                NetCategoryBean.DataBean.ResultListBean.GoodsModelList bean = mCategoryList.get(position);
                GlideUtils.getInstance().loadIcon(mContext, bean.getGoodsModelPic(), R.drawable.ic_launcher, holder.image_src);
                holder.category_title.setText(bean.getGoodsModelName());
            }
        }

        public class CategoryDataViewItemHolder extends RecyclerView.ViewHolder {

            private ImageView image_src;
            private TextView category_title;

            public CategoryDataViewItemHolder(View itemView) {
                super(itemView);
                image_src = (ImageView) itemView.findViewById(R.id.image_pic);
                category_title = (TextView) itemView.findViewById(R.id.category_title);
            }
        }

    }

}
