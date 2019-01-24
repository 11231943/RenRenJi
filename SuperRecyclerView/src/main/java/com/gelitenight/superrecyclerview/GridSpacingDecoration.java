package com.gelitenight.superrecyclerview;

import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.GridLayoutManager.SpanSizeLookup;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class GridSpacingDecoration extends RecyclerView.ItemDecoration {
    int mHorizontalSpacing;
    int mVerticalSpacing;

    public GridSpacingDecoration(int spacing) {
        this(spacing, spacing);
    }

    public GridSpacingDecoration(int horizontalSpacing, int verticalSpacing) {
        mHorizontalSpacing = horizontalSpacing;
        mVerticalSpacing = verticalSpacing;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
            RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        GridLayoutManager gridLayoutManager = ((GridLayoutManager) parent.getLayoutManager());
        int spanCount = gridLayoutManager.getSpanCount();
        /* INVALID SPAN */
        if (spanCount < 1) return;

        int itemPos = parent.getChildAdapterPosition(view);
        SpanSizeLookup spanSizeLookup = gridLayoutManager.getSpanSizeLookup();
        int spanIndexLeft = spanSizeLookup.getSpanIndex(itemPos, spanCount);
        int spanIndexRight = spanIndexLeft - 1 + spanSizeLookup.getSpanSize(itemPos);

        if (spanSizeLookup.getSpanGroupIndex(itemPos, spanCount) == 0) {
            // first line
            outRect.top = 0;
        } else {
            outRect.top = mVerticalSpacing;
        }

        outRect.bottom = 0;
        outRect.left = mHorizontalSpacing * spanIndexLeft / spanCount;
        outRect.right = mHorizontalSpacing * (spanCount - spanIndexRight - 1) / spanCount;
    }
}
