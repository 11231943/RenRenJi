package com.trade.rrenji.biz.photo;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.trade.rrenji.R;
import com.trade.rrenji.biz.base.BaseActivity;
import com.trade.rrenji.utils.GlideUtils;
import com.trade.rrenji.utils.ViewUtils;
import com.viewpagerindicator.CirclePageIndicator;

/**
 * Created by wheat on 15/12/24.
 */

public class ShowPhotosActivity extends BaseActivity {

    @Override
    protected void attachPresenter() {

    }

    @Override
    protected void detachPresenter() {

    }

    private CirclePageIndicator indicator;

    private ViewPager mAdViewPager;

    private CirclePhotoAdapter mCirclePhotoAdapter;

    private String[] photos;

    private int mPostion = 0;

    private RelativeLayout mainLayout;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_circle_photo);
        setActionBarTitle("查看图片");
        findView();
        init();
    }

    private void findView() {
        mAdViewPager = (ViewPager) findViewById(R.id.viewPager);
        indicator = (CirclePageIndicator) findViewById(R.id.indicator);
        mainLayout = (RelativeLayout) findViewById(R.id.main_layout);
    }

    private void init() {
        if (getIntent().getExtras() == null) {
            return;
        }

        photos = (String[]) getIntent().getExtras().getSerializable("photo");
        mPostion = getIntent().getExtras().getInt("mPostion", 0);
        mCirclePhotoAdapter = new CirclePhotoAdapter(mAdViewPager, photos);
        mAdViewPager.setAdapter(mCirclePhotoAdapter);
        indicator.setViewPager(mAdViewPager);
        mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mAdViewPager.setCurrentItem(mPostion);
    }


    private class CirclePhotoAdapter extends PagerAdapter {
        private String[] mCirclePhoto;
        private int width;
        private ViewGroup mContainer;

        public CirclePhotoAdapter(ViewGroup container, String[] circlePhoto) {
            mContainer = container;
            mCirclePhoto = circlePhoto;
            width = ViewUtils.getScreenWidth(getApplication());

            if (getCount() > 0) {
                int height = width * 1;
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
            return mCirclePhoto.length;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            int count = getCount();
            if (position > count - 1) {
                return null;
            }

            if (null == mCirclePhoto)
                return null;
            final String photo = mCirclePhoto[position];

            ImageView simpleDraweeView = new ImageView(getApplication());
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            simpleDraweeView.setLayoutParams(params);
            simpleDraweeView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            GlideUtils.getInstance().loadIcon(ShowPhotosActivity.this, photo, R.drawable.main_recommed_today, simpleDraweeView);
            container.addView(simpleDraweeView, 0);
            simpleDraweeView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
            return simpleDraweeView;
        }
    }


}
