package com.trade.rrenji.biz.goods.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.trade.rrenji.R;
import com.trade.rrenji.bean.drying.NetShareBean;
import com.trade.rrenji.bean.goods.GoodsDetailBean;
import com.trade.rrenji.biz.photo.ShowPhotosActivity;
import com.trade.rrenji.utils.CollectionUtils;
import com.trade.rrenji.utils.GlideUtils;
import com.trade.rrenji.utils.ViewUtils;

import java.util.List;

public class GoodsBannerAdapter extends PagerAdapter {
    private List<GoodsDetailBean.GoodsPicsBean> mCircleAdBeans;
    private int width;
    private ViewGroup mContainer;
    private String[] mPhoto = null;
    private Context mContext;

    public GoodsBannerAdapter(Context context, ViewGroup container, List<GoodsDetailBean.GoodsPicsBean> circleAdBeans) {
        mContainer = container;
        mCircleAdBeans = circleAdBeans;
        mContext = context;
        width = ViewUtils.getScreenWidth(mContext);
        initPhotos(mCircleAdBeans);
        if (getCount() > 0) {
            int height = (int) (width * 0.75);
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
        final GoodsDetailBean.GoodsPicsBean adBean = mCircleAdBeans.get(position);
        if (null == adBean)
            return null;

        ImageView simpleDraweeView = new ImageView(mContext);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        simpleDraweeView.setLayoutParams(params);
        simpleDraweeView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        GlideUtils.getInstance().loadImageUrl(mContext, adBean.getMaxPic(), R.drawable.ic_launcher, simpleDraweeView);


        simpleDraweeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ShowPhotosActivity.class);
                intent.putExtra("photo", mPhoto);
                intent.putExtra("mPostion", position);
                mContext.startActivity(intent);
            }
        });
        container.addView(simpleDraweeView, 0);
        return simpleDraweeView;
    }

    private void initPhotos(List<GoodsDetailBean.GoodsPicsBean> maleBeans) {
        mPhoto = new String[maleBeans.size()];
        for (int i = 0; i < maleBeans.size(); i++) {
            mPhoto[i] = maleBeans.get(i).getMaxPic();
        }
    }
}
