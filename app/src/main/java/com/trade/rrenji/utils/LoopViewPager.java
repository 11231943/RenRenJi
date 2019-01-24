package com.trade.rrenji.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import java.lang.ref.WeakReference;

/**
 * A ViewPager subclass enabling infinte scrolling of the viewPager elements
 * <p/>
 * When used for paginating views (in opposite to fragments), no code changes
 * should be needed only change xml's from <android.support.v4.view.ViewPager>
 * to <com.imbryk.viewPager.LoopViewPager>
 * <p/>
 * If "blinking" can be seen when paginating to first or last view, simply call
 * seBoundaryCaching( true ), or change DEFAULT_BOUNDARY_CASHING to true
 * <p/>
 * When using a FragmentPagerAdapter or FragmentStatePagerAdapter,
 * additional changes in the adapter must be done.
 * The adapter must be prepared to create 2 extra items e.g.:
 * <p/>
 * The original adapter creates 4 items: [0,1,2,3]
 * The modified adapter will have to create 6 items [0,1,2,3,4,5]
 * with mapping realPosition=(position-1)%count
 * [0->3, 1->0, 2->1, 3->2, 4->3, 5->0]
 */
public class LoopViewPager extends ViewPager {
    private final int PERIOD_FOR_AD_CHANGE = 5000;
    private final boolean DEFAULT_BOUNDARY_CASHING = false;

    OnPageChangeListener mOuterPageChangeListener;
    private LoopPagerAdapterWrapper mAdapter;
    private boolean mBoundaryCaching = DEFAULT_BOUNDARY_CASHING;

    boolean mAutoPaging;
    boolean mAutoPagingEnabled;
    Handler mPagingHandler;

    public void setEnableSwipe(boolean enableSwipe) {
        this.enableSwipe = enableSwipe;
    }

    private boolean enableSwipe = true;

    public void setAutoPagingEnabled(boolean autoPagingEnabled) {
        mAutoPagingEnabled = autoPagingEnabled;
    }

    /**
     * helper function which may be used when implementing FragmentPagerAdapter
     *
     * @param position
     * @param count
     * @return (position-1)%count
     */
    public int toRealPosition(int position, int count) {
        position = position - 1;
        if (position < 0) {
            position += count;
        } else {
            position = position % count;
        }
        return position;
    }

    /**
     * If set to true, the boundary views (i.e. first and last) will never be destroyed
     * This may help to prevent "blinking" of some views
     *
     * @param flag
     */
    public void setBoundaryCaching(boolean flag) {
        mBoundaryCaching = flag;
        if (mAdapter != null) {
            mAdapter.setBoundaryCaching(flag);
        }
    }

    @Override
    public void setAdapter(PagerAdapter adapter) {
        mAdapter = new LoopPagerAdapterWrapper(adapter);
        mAdapter.setBoundaryCaching(mBoundaryCaching);
        super.setAdapter(mAdapter);
        setCurrentItem(0, false);
    }

    @Override
    public PagerAdapter getAdapter() {
        return mAdapter != null ? mAdapter.getRealAdapter() : mAdapter;
    }

    @Override
    public int getCurrentItem() {
        return mAdapter != null ? mAdapter.toRealPosition(super.getCurrentItem()) : 0;
    }

    public void setCurrentItem(int item, boolean smoothScroll) {
        int realItem = mAdapter.toInnerPosition(item);
        super.setCurrentItem(realItem, smoothScroll);
    }

    @Override
    public void setCurrentItem(int item) {
        if (getCurrentItem() != item) {
            setCurrentItem(item, true);
        }
    }

    @Override
    public void setOnPageChangeListener(OnPageChangeListener listener) {
        mOuterPageChangeListener = listener;
    }

    public LoopViewPager(Context context) {
        super(context);
        init();
    }

    public LoopViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        super.setOnPageChangeListener(onPageChangeListener);
        addOnAttachStateChangeListener(new OnAttachStateChangeListener() {
            @Override
            public void onViewAttachedToWindow(View v) {
                Log.d("LoopViewPager","onViewAttachedToWindow");
                mAutoPaging = mAutoPagingEnabled;
                if (mAutoPaging) {
                    mPagingHandler.removeMessages(0);
                    mPagingHandler.sendEmptyMessageDelayed(0, PERIOD_FOR_AD_CHANGE);
                }
            }

            @Override
            public void onViewDetachedFromWindow(View v) {
                Log.d("LoopViewPager","onViewDetachedFromWindow");
                mAutoPaging = false;
                mPagingHandler.removeMessages(0);
            }
        });
        mPagingHandler = new AutoPagingHandler(this);
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN
                        || event.getAction() == MotionEvent.ACTION_MOVE) {
                    mAutoPaging = false;
                    mPagingHandler.removeMessages(0);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    mAutoPaging = mAutoPagingEnabled;
                    if (mAutoPaging) {
                        mPagingHandler.removeMessages(0);
                        mPagingHandler.sendEmptyMessageDelayed(0, PERIOD_FOR_AD_CHANGE);
                    }
                }
                return false;
            }
        });
    }

    private OnPageChangeListener onPageChangeListener = new OnPageChangeListener() {
        private float mPreviousOffset = -1;
        private float mPreviousPosition = -1;

        @Override
        public void onPageSelected(int position) {

            int realPosition = mAdapter.toRealPosition(position);
            if (mPreviousPosition != realPosition) {
                mPreviousPosition = realPosition;
                if (mOuterPageChangeListener != null) {
                    mOuterPageChangeListener.onPageSelected(realPosition);
                }
            }
        }

        @Override
        public void onPageScrolled(int position, float positionOffset,
                int positionOffsetPixels) {
            int realPosition = position;
            if (mAdapter != null) {
                realPosition = mAdapter.toRealPosition(position);

                if (positionOffset == 0
                        && mPreviousOffset == 0
                        && (position == 0 || position == mAdapter.getCount() - 1)) {
                    setCurrentItem(realPosition, false);
                }
            }

            mPreviousOffset = positionOffset;
            if (mOuterPageChangeListener != null) {
                if (realPosition != mAdapter.getRealCount() - 1) {
                    mOuterPageChangeListener.onPageScrolled(realPosition,
                            positionOffset, positionOffsetPixels);
                } else {
                    if (positionOffset > .5) {
                        mOuterPageChangeListener.onPageScrolled(0, 0, 0);
                    } else {
                        mOuterPageChangeListener.onPageScrolled(realPosition,
                                0, 0);
                    }
                }
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            if (mAdapter != null) {
                int position = LoopViewPager.super.getCurrentItem();
                int realPosition = mAdapter.toRealPosition(position);
                if (state == ViewPager.SCROLL_STATE_IDLE
                        && (position == 0 || position == mAdapter.getCount() - 1)) {
                    setCurrentItem(realPosition, false);
                }
            }
            if (mOuterPageChangeListener != null) {
                mOuterPageChangeListener.onPageScrollStateChanged(state);
            }
        }
    };

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (enableSwipe && mAdapter.getCount() > 1)
            return super.onInterceptTouchEvent(ev);
        else
            return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (enableSwipe && mAdapter.getCount() > 1)
            return super.onTouchEvent(ev);
        else
            return false;
    }

    private void nextPage() {
        if (!mAutoPaging) {
            return;
        }

        if (mAdapter != null && mAdapter.getRealCount() > 1) {
            int next = (getCurrentItem() + 1) % mAdapter.getRealCount();
            setCurrentItem(next, true);
        }

        if (mAutoPaging) {
            mPagingHandler.removeMessages(0);
            mPagingHandler.sendEmptyMessageDelayed(0, PERIOD_FOR_AD_CHANGE);
        }
    }

    private static class AutoPagingHandler extends Handler {
        private WeakReference<LoopViewPager> mLoopViewPagerWeakReference;

        public AutoPagingHandler(LoopViewPager loopViewPager) {
            super();
            mLoopViewPagerWeakReference = new WeakReference<>(loopViewPager);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            LoopViewPager loopViewPager = mLoopViewPagerWeakReference.get();
            if (loopViewPager != null) {
                loopViewPager.nextPage();
            }
        }
    }
}