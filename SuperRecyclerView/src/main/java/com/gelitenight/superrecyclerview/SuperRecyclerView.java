package com.gelitenight.superrecyclerview;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.GridLayoutManager.SpanSizeLookup;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.AdapterDataObserver;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewStub;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;

import com.gelitenight.superrecyclerview.EndlessAdapter.LoadingFooterStatus;
import com.gelitenight.superrecyclerview.PullRefreshLayout.OnRefreshListener;

public class SuperRecyclerView extends FrameLayout implements OnRefreshListener {
    private static final int DEFAULT_ROOT_LAYOUT_ID = R.layout.super_recycler_root_default;

    private PullRefreshLayout mPullRefreshLayout;
    private RecyclerView mRecyclerView;
    private LayoutManager mLayoutManager;
    private EndlessAdapter mAdapter;

    protected boolean mClipToPadding;
    protected boolean mClipChildren;
    protected int mPadding;
    protected int mPaddingTop;
    protected int mPaddingBottom;
    protected int mPaddingLeft;
    protected int mPaddingRight;
    protected int mScrollbarStyle;

    private View mRootView;
    private View mEmpty;
    private View mLoading;

    private int mRootLayoutId = DEFAULT_ROOT_LAYOUT_ID;
    private int mEmptyLayoutId = -1;
    private int mLoadingLayoutId = -1;

    private boolean mHasMoreData;

    private OnLoadDataListener mOnLoadDataListener;

    private EndlessRecyclerOnScrollListener mOnScrollListener;

    public class EndlessRecyclerOnScrollListener extends RecyclerView.OnScrollListener {
        private boolean mLoading; // True if we are still waiting for the last set of data to load.
        private int mVisibleThreshold = 5; // The minimum amount of items to have below your current scroll position before loading more.
        int mFirstVisibleItem, mVisibleItemCount, mTotalItemCount;

        public void setLoading(boolean loading) {
            mLoading = loading;
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            if (Math.abs(dy) < ViewConfiguration.get(getContext()).getScaledTouchSlop()) {
                return;
            }

            mVisibleItemCount = recyclerView.getChildCount();
            mTotalItemCount = mLayoutManager.getItemCount();
            mFirstVisibleItem = ((LinearLayoutManager) mLayoutManager)
                    .findFirstVisibleItemPosition();

            if (!mLoading && (mTotalItemCount - mVisibleItemCount)
                    <= (mFirstVisibleItem + mVisibleThreshold)) {
                // End has been reached
                mLoading = true;

                loadMoreData();
            }
        }
    }

    public interface OnLoadDataListener {
        void onRefresh();

        void onMore();
    }

    public SuperRecyclerView(Context context) {
        super(context);
        initView();
    }

    public SuperRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(attrs);
        initView();
    }

    public SuperRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initAttrs(attrs);
        initView();
    }

    protected void initAttrs(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.superrecyclerview);
        try {
//            mRootLayoutId = a.getResourceId(
//                R.styleable.superrecyclerview_layoutRoot, DEFAULT_ROOT_LAYOUT_ID);
            mEmptyLayoutId = a.getResourceId(
                    R.styleable.superrecyclerview_layoutEmpty, -1);
            mLoadingLayoutId = a.getResourceId(
                    R.styleable.superrecyclerview_layoutLoading, -1);

            mClipToPadding = a.getBoolean(R.styleable.superrecyclerview_recyclerClipToPadding,
                    false);
            mClipChildren = a.getBoolean(R.styleable.superrecyclerview_recyclerClipChildren, true);
            mPadding = (int) a.getDimension(R.styleable.superrecyclerview_recyclerPadding, -1.0f);
            mPaddingTop = (int) a.getDimension(R.styleable.superrecyclerview_recyclerPaddingTop,
                    0.0f);
            mPaddingBottom = (int) a.getDimension(
                    R.styleable.superrecyclerview_recyclerPaddingBottom, 0.0f);
            mPaddingLeft = (int) a.getDimension(R.styleable.superrecyclerview_recyclerPaddingLeft,
                    0.0f);
            mPaddingRight = (int) a.getDimension(R.styleable.superrecyclerview_recyclerPaddingRight,
                    0.0f);
            mScrollbarStyle = a.getInt(R.styleable.superrecyclerview_scrollbarStyle, -1);
        } finally {
            a.recycle();
        }
    }

    private void initView() {
        if (isInEditMode()) {
            return;
        }

        mRootView = LayoutInflater.from(getContext()).inflate(mRootLayoutId, this);

        ViewStub emptyStub = (ViewStub) mRootView.findViewById(R.id.empty);
        if (emptyStub != null) {
            emptyStub.setVisibility(View.GONE);
            if (mEmptyLayoutId > 0) {
                emptyStub.setLayoutResource(mEmptyLayoutId);
                mEmpty = emptyStub.inflate();
                mEmpty.setVisibility(View.GONE);
            }
        }

        ViewStub loadingStub = (ViewStub) mRootView.findViewById(R.id.loading);
        if (loadingStub != null) {
            loadingStub.setVisibility(View.GONE);
            if (mLoadingLayoutId > 0) {
                loadingStub.setLayoutResource(mLoadingLayoutId);
                mLoading = loadingStub.inflate();
                mLoading.setVisibility(View.GONE);
            }
        }

        mPullRefreshLayout = (PullRefreshLayout) mRootView.findViewById(R.id.pull_refresh_layout);
        if (mPullRefreshLayout == null) {
            throw new IllegalArgumentException("Your content must have a PullToRefresh whose " +
                    "id attribute is 'pull_refresh_layout'");
        }
        mPullRefreshLayout.setOnRefreshListener(this);

        initRecyclerView();
    }

    private void initRecyclerView() {
        mRecyclerView = (RecyclerView) mRootView.findViewById(R.id.recycler_view);
        if (mRecyclerView == null) {
            throw new IllegalArgumentException("Your content must have a RecyclerView whose " +
                    "id attribute is 'recycler_view'");
        }

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        mOnScrollListener = new EndlessRecyclerOnScrollListener();
        mRecyclerView.addOnScrollListener(mOnScrollListener);

//        mRecyclerView.setItemAnimator(new FadeInUpAnimator());
        mRecyclerView.setClipToPadding(mClipToPadding);
        mRecyclerView.setClipChildren(mClipChildren);
        if (mPadding != -1.0f) {
            mRecyclerView.setPadding(mPadding, mPadding, mPadding, mPadding);
        } else {
            mRecyclerView.setPadding(mPaddingLeft, mPaddingTop, mPaddingRight, mPaddingBottom);
        }

        if (mScrollbarStyle != -1) {
            mRecyclerView.setScrollBarStyle(mScrollbarStyle);
        }
    }

    static class SavedState extends BaseSavedState {
        boolean hasMoreData;

        SavedState(Parcelable superState) {
            super(superState);
        }

        private SavedState(Parcel in) {
            super(in);
            hasMoreData = in.readByte() == 1;
        }

        @Override
        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeByte((byte) (hasMoreData ? 1 : 0));
        }

        @Override
        public String toString() {
            return "SuperRecyclerView.SavedState{"
                    + Integer.toHexString(System.identityHashCode(this))
                    + " hasMoreData=" + hasMoreData + "}";
        }

        public static final Creator<SavedState> CREATOR
                = new Creator<SavedState>() {
            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in);
            }

            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();
        SavedState ss = new SavedState(superState);
        ss.hasMoreData = mHasMoreData;
        return ss;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if (!(state instanceof SavedState)) {
            super.onRestoreInstanceState(state);
            return;
        }

        SavedState ss = (SavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());

        mHasMoreData = ss.hasMoreData;
    }

    private void checkIfEmpty() {
        showOrHideEmptyView(mAdapter == null || mAdapter.isEmpty());
    }

    /**
     * Set the layout manager to the recycler
     *
     * @param layoutManager
     */
    public void setLayoutManager(LayoutManager layoutManager) {
        mLayoutManager = layoutManager;
        if (mLayoutManager instanceof GridLayoutManager) {
            final GridLayoutManager manager = (GridLayoutManager) mLayoutManager;
            final SpanSizeLookup lookup = manager.getSpanSizeLookup();
            manager.setSpanSizeLookup(new SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return mAdapter.getSpanSize(position, manager.getSpanCount(), lookup);
                }

                @Override
                public int getSpanIndex(int position, int spanCount) {
                    return mAdapter.getSpanIndex(position, spanCount, lookup);
                }
            });
        }
        mRecyclerView.setLayoutManager(mLayoutManager);
    }

    public LayoutManager getLayoutManager() {
        return mLayoutManager;
    }

    public RecyclerView.Adapter getAdapter() {
        return mAdapter;
    }

    public <VH extends RecyclerView.ViewHolder> void setAdapter(RecyclerView.Adapter<VH> adapter) {
        if (adapter == null) {
            mRecyclerView.setAdapter(null);
            return;
        }

        mAdapter = new EndlessAdapter<>(adapter);
        if (mRecyclerView == null) {
            return;
        }

        mAdapter.registerAdapterDataObserver(new AdapterDataObserver() {
            @Override
            public void onChanged() {
                checkIfEmpty();
            }

            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                checkIfEmpty();
            }

            @Override
            public void onItemRangeRemoved(int positionStart, int itemCount) {
                checkIfEmpty();
            }
        });
        mRecyclerView.setAdapter(mAdapter);
    }

    public void addItemDecoration(ItemDecoration decor) {
        mRecyclerView.addItemDecoration(decor);
    }

    public void removeItemDecoration(ItemDecoration decor) {
        mRecyclerView.removeItemDecoration(decor);
    }

    public void smoothScrollToPosition(int position) {
        mRecyclerView.smoothScrollToPosition(position);
    }

    public void scrollToPosition(int position) {
        mRecyclerView.scrollToPosition(position);
    }

    public void setRecyclerOnTouchListener(OnTouchListener l) {
        mRecyclerView.setOnTouchListener(l);
    }

    public final View findChildViewUnder(float x, float y) {
        return mRecyclerView.findChildViewUnder(x, y);
    }

    public int getChildLayoutPosition(View child) {
        return mRecyclerView.getChildLayoutPosition(child);
    }

    public void addOnScrollListener(OnScrollListener listener) {
        mRecyclerView.addOnScrollListener(listener);
    }

    public int computeVerticalScrollOffset() {
        return mRecyclerView.computeVerticalScrollOffset();
    }

    public void setRecyclerPadding(int left, int top, int right, int bottom) {
        mRecyclerView.setPadding(left, top, right, bottom);
    }

    @Override
    public void setScrollBarStyle(int style) {
        mRecyclerView.setScrollBarStyle(style);
    }

    public void setHasMoreData(boolean hasMoreData) {
        mHasMoreData = hasMoreData;
    }

    public void loadMoreData() {
        if (mHasMoreData && mOnLoadDataListener != null) {
            mOnLoadDataListener.onMore();
        }
    }

    @Override
    public void onRefresh() {
        mOnScrollListener.setLoading(true);
        if (mOnLoadDataListener != null) {
            mOnLoadDataListener.onRefresh();
        }
    }

    public View inflateView(@LayoutRes int layoutId) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(layoutId, mRecyclerView, false);
        return view;
    }

    public void addHeaderView(View view) {
        mAdapter.addHeaderView(view);
    }

    public void addFooterView(View view) {
        mAdapter.addFooterView(view);
    }

    public View addHeaderView(@LayoutRes int layoutId) {
        View header = inflateView(layoutId);
        addHeaderView(header);
        return header;
    }

    public View addFooterView(@LayoutRes int layoutId) {
        View footer = inflateView(layoutId);
        addFooterView(footer);
        return footer;
    }

    public boolean removeFooterView(View view) {
        return mAdapter.removeFooterView(view);
    }

    public boolean removeHeaderView(View view) {
        return mAdapter.removeHeaderView(view);
    }

    /**
     * 当mAdapter里为空时需不需要显示footer和header
     *
     * @param emptyShowHeaderFooter <br/>
     *                              <code>true</code> 数据为空时，显示footer和header，隐藏empty view <br />
     *                              <code>false</code> 数据为空时，隐藏footer和header，显示empty view
     */
    public void setEmptyShowHeaderFooter(boolean emptyShowHeaderFooter) {
        mAdapter.setEmptyShowHeaderFooter(emptyShowHeaderFooter);
    }

    public void setOnLoadDataListener(
            OnLoadDataListener onLoadDataListener) {
        mOnLoadDataListener = onLoadDataListener;
    }

    private void showLoadingView() {
        if (mLoading != null) {
            mLoading.setVisibility(View.VISIBLE);
        }
        if (mEmpty != null) {
            mEmpty.setVisibility(View.GONE);
        }
    }

    private boolean isHiding = false;

    private void hideLoadingView() {
        if (!isHiding && mLoading != null
                && mLoading.getVisibility() == View.VISIBLE) {
            isHiding = true;
            Animation animFadeOut = AnimationUtils.loadAnimation(getContext(),
                    android.R.anim.fade_out);
            animFadeOut.setAnimationListener(new AnimationListener() {
                @Override
                public void onAnimationEnd(Animation animation) {
                    mLoading.setVisibility(View.GONE);
                    isHiding = false;
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }

                @Override
                public void onAnimationStart(Animation animation) {
                }
            });
            mLoading.startAnimation(animFadeOut);
        }
    }

    private void showOrHideEmptyView(boolean isEmpty) {
        hideLoadingView();
        if (mEmpty != null) {
            mEmpty.setVisibility(isEmpty ? View.VISIBLE : View.GONE);
        }
    }

    public boolean isRefreshing() {
        return mPullRefreshLayout.isRefreshing();
    }

    public void startRefreshing(boolean loadingView) {
        startRefreshing(loadingView, !loadingView);
    }

    public void startRefreshing(boolean loadingView, boolean loadingHeader) {
        if (loadingView) {
            showLoadingView();
        }
        mPullRefreshLayout.startRefresh(loadingHeader);
    }

    public void finishRefreshing() {
        mPullRefreshLayout.setRefreshing(false);
    }

    public void finishMore() {
        finishMore(true);
    }

    public void finishMore(boolean loadMoreSuccess) {
        mOnScrollListener.setLoading(false);
        if (loadMoreSuccess && mHasMoreData) {
            mAdapter.setHasLoadingFooter(LoadingFooterStatus.VISIBLE);
            mOnScrollListener.onScrolled(mRecyclerView, 0,
                    ViewConfiguration.get(getContext()).getScaledTouchSlop());
        } else {
            mAdapter.setHasLoadingFooter(LoadingFooterStatus.GONE);
        }
    }

    public void disablePullRefresh() {
        mPullRefreshLayout.disablePullRefresh();
    }
}
