package com.gelitenight.superrecyclerview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.GridLayoutManager.SpanSizeLookup;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class ConGridSpacingDecoration extends RecyclerView.ItemDecoration {
    int mHorizontalSpacing;
    int mVerticalSpacing;
    int mLineWidth = 0;
    private Drawable mDivider;

    public ConGridSpacingDecoration(Context context, int horizontalSpacing, int verticalSpacing, int lineWidth) {
        mLineWidth = lineWidth;
        final TypedArray a = context.obtainStyledAttributes(
                new int[]{android.R.attr.listDivider});
        mDivider = a.getDrawable(0);
        mHorizontalSpacing = horizontalSpacing;
        mVerticalSpacing = verticalSpacing;
        a.recycle();
    }

    public ConGridSpacingDecoration(Context context, int horizontalSpacing, int verticalSpacing) {
    }

    public ConGridSpacingDecoration(int spacing) {
        this(spacing, spacing);
    }

    public ConGridSpacingDecoration(int horizontalSpacing, int verticalSpacing) {
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

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        int left = 0, right = 0, top = 0, bottom = 0, size;
        int childCount = parent.getChildCount();
        GridLayoutManager gridLayoutManager = ((GridLayoutManager) parent.getLayoutManager());
        int spanCount = gridLayoutManager.getSpanCount();

        int cw = (parent.getWidth() - parent.getPaddingLeft() - parent.getPaddingRight()) / spanCount;


        if (childCount > 1) {

            for (int i = 1; i < spanCount; i++) {
                left = cw * i + parent.getPaddingLeft() - mDivider.getIntrinsicWidth() / 2;
                right = cw * i + parent.getPaddingLeft() + mDivider.getIntrinsicWidth() / 2;
                int cel = 0;
                if (childCount % spanCount == 0) {
                    cel = childCount / spanCount;
                } else {
                    cel = childCount / spanCount + 1;
                }
                for (int j = 0; j < cel; j++) {
                    int index = j * spanCount + i;
                    if (index < childCount) {
                        View child = parent.getChildAt(index);
                        top = child.getTop() + mLineWidth / 2;
                        bottom = child.getBottom() - mLineWidth / 2;
                        mDivider.setBounds(left, top, right, bottom);
                        mDivider.draw(c);
                    }
                }
            }
//            for (int i = 0; i < childCount; i++) {
//                View child = parent.getChildAt(i);
//                top = child.getTop() + 10;
//                bottom = child.getBottom() - 10;
//                if (i % spanCount == 0) {
//                    left = cw + parent.getPaddingLeft() - mDivider.getIntrinsicWidth() / 2;
//                    right = cw + parent.getPaddingLeft() + mDivider.getIntrinsicWidth() / 2;
//                }
//
//                mDivider.setBounds(left, top, right, bottom);
//                mDivider.draw(c);
//            }
        }
    }

}
