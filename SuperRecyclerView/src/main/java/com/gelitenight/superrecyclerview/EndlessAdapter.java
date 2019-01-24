package com.gelitenight.superrecyclerview;

import android.support.v7.widget.GridLayoutManager.SpanSizeLookup;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.AdapterDataObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gelitenight.superrecyclerview.util.CollectionUtils;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

public class EndlessAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    private static int HEADER_MASK = 0x010000;
    private static int FOOTER_MASK = 0x020000;


    public enum LoadingFooterStatus {UNKNOWN, VISIBLE, GONE}

    private RecyclerView.Adapter<VH> mAdapter;
    private List<View> mHeaderViews;
    private List<View> mFooterViews;
    private LoadingFooterStatus mHasLoadingFooter;
    private boolean mEmptyShowHeaderFooter;

    public EndlessAdapter(RecyclerView.Adapter<VH> adapter) {
        this(adapter, false);
    }

    public EndlessAdapter(RecyclerView.Adapter<VH> adapter, boolean emptyShowHeaderFooter) {
        mAdapter = adapter;
        mAdapter.registerAdapterDataObserver(new AdapterDataObserver() {
            @Override
            public void onChanged() {
                notifyDataSetChanged();
            }

            @Override
            public void onItemRangeChanged(int positionStart, int itemCount) {
                notifyItemRangeChanged(posOri2Cur(positionStart), itemCount);
            }

            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                notifyItemRangeInserted(posOri2Cur(positionStart), itemCount);

            }

            @Override
            public void onItemRangeRemoved(int positionStart, int itemCount) {
                notifyItemRangeRemoved(posOri2Cur(positionStart), itemCount);
            }

            @Override
            public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
                notifyItemMoved(posOri2Cur(fromPosition), posOri2Cur(toPosition));
            }
        });

        mHeaderViews = new ArrayList<>();
        mFooterViews = new ArrayList<>();
        mHasLoadingFooter = LoadingFooterStatus.UNKNOWN;
        mEmptyShowHeaderFooter = emptyShowHeaderFooter;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

    // Create new views (invoked by the layout manager)
    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType < 0) {
            viewType = -viewType;
            if ((viewType & HEADER_MASK) == HEADER_MASK) {
                return (VH) new ViewHolder(mHeaderViews.get(viewType ^ HEADER_MASK));
            } else if ((viewType & FOOTER_MASK) == FOOTER_MASK) {
                return (VH) new ViewHolder(mFooterViews.get(viewType ^ FOOTER_MASK));
            } else {
                throw new InvalidParameterException("Unknown viewType: " + viewType);
            }
        }
        if (viewType == R.layout.refresh_foot) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view = inflater.inflate(R.layout.refresh_foot, parent, false);
            return (VH) new ViewHolder(view);
        }

        return mAdapter.onCreateViewHolder(parent, viewType);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(VH holder, int position) {
        if (!isHeaderOrFooter(position)) {
            mAdapter.onBindViewHolder(holder, position - CollectionUtils.getSize(mHeaderViews));
        }
    }

    @Override
    public int getItemCount() {
        int itemCount = mAdapter.getItemCount();

        itemCount += CollectionUtils.getSize(mHeaderViews);
        itemCount += CollectionUtils.getSize(mFooterViews);
        if (mHasLoadingFooter == LoadingFooterStatus.VISIBLE) {
            itemCount++;
        }

        return itemCount;
    }

    public boolean isEmpty() {
        return mEmptyShowHeaderFooter ? getItemCount() == 0 : mAdapter.getItemCount() == 0;
    }

    /**
     * 当mAdapter里为空时需不需要显示footer和header
     *
     * @param emptyShowHeaderFooter <code>true</code> 需要显示, <code>false</code> 不需要显示
     */
    public void setEmptyShowHeaderFooter(boolean emptyShowHeaderFooter) {
        mEmptyShowHeaderFooter = emptyShowHeaderFooter;
        notifyDataSetChanged();
    }

    /**
     * 需不需要loading footer
     *
     * @param hasLoadingFooter 需不需要loading footer
     * @return <code>true</code> if data set changed, <code>false</code> otherwise
     */
    public boolean setHasLoadingFooter(LoadingFooterStatus hasLoadingFooter) {
        if (mHasLoadingFooter != hasLoadingFooter) {
            mHasLoadingFooter = hasLoadingFooter;
            switch (hasLoadingFooter) {
                case VISIBLE:
                    notifyDataSetChanged();
                    break;
                case GONE:
                    notifyItemRemoved(getItemCount());
                    break;
                default:
                    notifyDataSetChanged();
                    break;
            }
            return true;
        }

        return false;
    }

    public void addHeaderView(View view) {
        mHeaderViews.add(view);
        notifyDataSetChanged();
    }

    public boolean removeHeaderView(View view) {
        boolean result = mHeaderViews.remove(view);
        if (result) {
            notifyDataSetChanged();
        }

        return result;
    }

    public void addFooterView(View view) {
        mFooterViews.add(view);
        notifyDataSetChanged();
    }

    public boolean removeFooterView(View view) {
        boolean result = mFooterViews.remove(view);
        if (result) {
            notifyDataSetChanged();
        }

        return result;
    }

    @Override
    public int getItemViewType(int position) {
        if (position < CollectionUtils.getSize(mHeaderViews)) {
            return -(HEADER_MASK | position);
        }

        if (mHasLoadingFooter == LoadingFooterStatus.VISIBLE
                && position == getItemCount() - 1) {
            return R.layout.refresh_foot;
        }

        int footerPos = position - CollectionUtils.getSize(mHeaderViews) - mAdapter.getItemCount();
        if (footerPos >= 0) {
            return -(FOOTER_MASK | footerPos);
        }

        return mAdapter.getItemViewType(position - CollectionUtils.getSize(mHeaderViews));
    }

    public int getSpanSize(int position, int spanCount, SpanSizeLookup oriLoopup) {
        return isHeaderOrFooter(position) ? spanCount :
                oriLoopup.getSpanSize(posCur2Ori(position));
    }

    public int getSpanIndex(int position, int spanCount, SpanSizeLookup oriLoopup) {
        return isHeaderOrFooter(position) ? 0 :
                oriLoopup.getSpanIndex(posCur2Ori(position), spanCount);
    }

    public boolean isHeaderOrFooter(int position) {
        if (position < CollectionUtils.getSize(mHeaderViews)) {
            return true;
        }

        if (mHasLoadingFooter == LoadingFooterStatus.VISIBLE
                && position == getItemCount() - 1) {
            return true;
        }

        int footerPos = position - CollectionUtils.getSize(mHeaderViews) - mAdapter.getItemCount();
        if (footerPos >= 0) {
            return true;
        }

        return false;
    }

    private int posOri2Cur(int position) {
        return position + CollectionUtils.getSize(mHeaderViews);
    }

    private int posCur2Ori(int position) {
        return position - CollectionUtils.getSize(mHeaderViews);
    }
}
