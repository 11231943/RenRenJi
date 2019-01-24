package com.gelitenight.superrecyclerview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Handler;
import android.util.TypedValue;

class ProgressCircleDrawable extends RefreshDrawable {

    private static final int MAX_LEVEL = 200;

    private boolean isRunning;
    private RectF mBounds;
    private int mWidth;
    private int mHeight;
    private int mTop;
    private int mOffsetTop;
    private Paint mPaint;
    private Path mPath;
    private float mAngle;
    private Handler mHandler = new Handler();
    private int mLevel;
    private float mDegress;

    ProgressCircleDrawable(Context context, PullRefreshLayout layout) {
        super(context, layout);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(dp2px(CIRCLE_R*2));
        mPaint.setColor(Color.parseColor("#979797"));
        mPath = new Path();
    }

    @Override
    public void setPercent(float percent) {
//        mPaint.setColor(evaluate(percent, mColorSchemeColors[0], mColorSchemeColors[1]));
    }

    @Override
    public void setColorSchemeColors(int[] colorSchemeColors) {
    }

    @Override
    public void offsetTopAndBottom(int offset) {
        mTop += offset;
        mOffsetTop += offset;
        float offsetTop = mOffsetTop - dp2px(20);
        if (offsetTop <= 0) {
            mAngle = 0;
        } else {
            int finalOffset = getRefreshLayout().getFinalOffset() - dp2px(20);
            if (offsetTop > finalOffset) {
                offsetTop = finalOffset;
            }
            mAngle = 360 * (offsetTop / finalOffset);
        }
        invalidateSelf();
    }

    @Override
    public void start() {
        mLevel = 50;
        isRunning = true;
        mHandler.post(mAnimationTask);
    }

    int position = 0;
    private Runnable mAnimationTask = new Runnable() {
        @Override
        public void run() {
            if (isRunning()) {
                mLevel++;
                if (mLevel > MAX_LEVEL)
                    mLevel = 0;
                updateLevel(mLevel);
                invalidateSelf();
                position++;
                mHandler.postDelayed(this, 20);
            }
        }
    };

    private void updateLevel(int level) {

        float percent = level % 50 / 50f;
//        mPaint.setColor(evaluate(percent, startColor, endColor));

        mDegress = 360 * percent;
    }

    @Override
    public void stop() {
        isRunning = false;
        mHandler.removeCallbacks(mAnimationTask);
        mDegress = 0;
    }

    @Override
    public boolean isRunning() {
        return isRunning;
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        mWidth = getRefreshLayout().getFinalOffset();
        mHeight = mWidth;
        mBounds = new RectF(bounds.width() / 2 - mWidth / 2, bounds.top, bounds.width() / 2 + mWidth / 2, bounds.top + mHeight);
//        mBounds.inset(dp2px(15), dp2px(15));
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.save();
//        canvas.translate(0, mTop);
        canvas.rotate(mDegress, mBounds.centerX(), mBounds.centerY());
        drawCircle(canvas);
        canvas.restore();
    }

    private final int COUNT_OF_LINE = 12;
    private final int OUT_CIRCLE_R = dp2px(12);
//    private final int IN_CIRCLE_R = dp2px(8);
    private final int CIRCLE_R = dp2px(1.7f);

    private synchronized void drawCircle(Canvas canvas) {
//        mPath.reset();
//        mPath.arcTo(mBounds, 270, mAngle, true);
//        canvas.drawPath(mPath, mPaint);/

        float startX = mBounds.centerX();
        float startY = mOffsetTop/2;

        float endAngle = mAngle;
        int paceAngel = 360 / COUNT_OF_LINE;
        int count = (int) (endAngle / paceAngel);
        Path mPath = new Path();


        int mOutCircleR;
        int mInCircleR;
        float percentOfAngle = (mAngle / 360.0f);


        if (isRunning) {
            mOutCircleR = (int) (OUT_CIRCLE_R * percentOfAngle);
//            mInCircleR = (int) (IN_CIRCLE_R * percentOfAngle);
        } else {
            mOutCircleR = OUT_CIRCLE_R;
//            mInCircleR = IN_CIRCLE_R;
        }

        mPaint.setAlpha((int) (255 * percentOfAngle));
        for (int i = 0; i < count; i++) {

            double cos = Math.cos(Math.toRadians(paceAngel * i - 90));
            double sin = Math.sin(Math.toRadians(paceAngel * i - 90));

            float targetX = (float) (mOutCircleR * cos + startX);
            float targetY = (float) (mOutCircleR * sin + startY);

//            Log.d("", "startX"+startX + " startY"+startY);
//            Log.d("", "targetX"+targetX + " targetY"+targetY);
//            Log.d("", "mAngle" + mAngle);

//            float paddingX = (float) (mInCircleR * cos + startX);
//            float paddingY = (float) (mInCircleR * sin + startY);


            mPath.moveTo(targetX, targetY);
            mPath.addCircle(targetX, targetY, CIRCLE_R, Path.Direction.CCW);

//            mPath.lineTo(targetX, targetY);
        }
        canvas.drawPath(mPath, mPaint);
//        canvas.drawArc(mBounds, 270, mAngle, true, mPaint);
    }

    private int dp2px(float dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getContext().getResources().getDisplayMetrics());
    }
}
