package com.trade.rrenji.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.trade.rrenji.R;

import jp.wasabeef.glide.transformations.CropCircleTransformation;


/**
 * @author Dragon
 * @date 2016/10/25. 10:10
 * @editor
 * @date
 * @describe
 */
public class GlideUtils {

    private static GlideUtils glideUtils;

    private GlideUtils() {
    }

    public static GlideUtils getInstance() {
        if (glideUtils == null) {
            synchronized (GlideUtils.class) {
                glideUtils = new GlideUtils();
            }
        }
        return glideUtils;
    }


    /**
     * 设置圆形的图片
     *
     * @param url            图片地址
     * @param defalutIconRes 加载失败后的默认图片
     */
    public void setCircularIconUrl(final Context context, String url, int defalutIconRes, final ImageView imgIcon) {
        Glide.with(context)//
                .load("")//
                .asBitmap()//
                .placeholder(defalutIconRes)//占位图
                .centerCrop()//
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(defalutIconRes)//请求失败/错误 显示图
                .into(new BitmapImageViewTarget(imgIcon) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                        circularBitmapDrawable.setCircular(true);
                        imgIcon.setImageDrawable(circularBitmapDrawable);
                    }
                });
    }

    /**
     * 设置圆形的图片
     *
     * @param url 图片地址
     */
    public void setCircularIconUrl(final Context context, String url, final ImageView imgIcon) {
        Glide.with(context)//
                .load("")//
                .asBitmap()//
                .placeholder(R.drawable.ic_launcher)//占位图
                .centerCrop()//
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.drawable.ic_launcher)//请求失败/错误 显示图
                .into(new BitmapImageViewTarget(imgIcon) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                        circularBitmapDrawable.setCircular(true);
                        imgIcon.setImageDrawable(circularBitmapDrawable);
                    }
                });
    }

    /**
     * 设置圆形的图片
     *
     * @param res 图片 res引用
     */
    public void setCircularIconRes(final Context context, final ImageView imgIcon, int res) {
        Glide.with(context)
                .load(res)
                .asBitmap()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(new BitmapImageViewTarget(imgIcon) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                        circularBitmapDrawable.setCircular(true);
                        imgIcon.setImageDrawable(circularBitmapDrawable);
                    }
                });
    }

    public void loadRFeIamge(Context context, String url, int defalutIconRes, final ImageView imgIcon) {
        Glide.with(context)
                .load(url)
                .error(defalutIconRes)//load失敗的Drawable
                .placeholder(defalutIconRes)//loading時候的Drawable
                .diskCacheStrategy(DiskCacheStrategy.ALL) //缓存所有尺寸
                .override(400, 400)
                .centerCrop()
                .into(imgIcon);
    }


    /**
     * 加载图片
     *
     * @param context
     * @param url
     * @param defalutIconRes
     * @param imgIcon
     */
    public void loadIcon(Context context, String url, int defalutIconRes, final ImageView imgIcon) {
        Glide.with(context)
                .load(url)
                .error(defalutIconRes)//load失敗的Drawable
                .placeholder(defalutIconRes)//loading時候的Drawable
                .diskCacheStrategy(DiskCacheStrategy.ALL) //缓存所有尺寸
                .fitCenter()//中心fit, 以原本圖片的長寬為主
                .into(imgIcon);
    }

    public void loadIcon1(Context context, String url, int defalutIconRes, final ImageView imgIcon) {
        Glide.with(context)
                .load(url)
                .bitmapTransform(new CropCircleTransformation(context))
                .error(defalutIconRes)//load失敗的Drawable
                .placeholder(defalutIconRes)//loading時候的Drawable
                .diskCacheStrategy(DiskCacheStrategy.ALL) //缓存所有尺寸
                .fitCenter()//中心fit, 以原本圖片的長寬為主
                .into(imgIcon);
    }

    public void loadIconBig(final Context context, String url, int defalutIconRes, final ImageView imgIcon) {
        Glide.with(context)
                .load(url)
                .asBitmap()
                .error(defalutIconRes)//load失敗的Drawable
                .placeholder(defalutIconRes)//loading時候的Drawable
                .diskCacheStrategy(DiskCacheStrategy.ALL) //缓存所有尺寸
                .fitCenter()//中心fit, 以原本圖片的長寬為主
                .into(new BitmapImageViewTarget(imgIcon) {
                    @Override
                    public void setDrawable(Drawable drawable) {
                        super.setDrawable(drawable);
                        Log.d("Glide", "height" + drawable.getBounds().height());
                        Log.d("Glide", "width" + drawable.getBounds().width());
                        BitmapDrawable bd = (BitmapDrawable) drawable;
                        imgIcon.setImageBitmap(big(bd.getBitmap()));
//                        imgIcon.setImageDrawable(drawable);
                    }
                });
    }

    private static Bitmap big(Bitmap bitmap) {
        Matrix matrix = new Matrix();
        matrix.postScale(1.5f, 1.5f); //长和宽放大缩小的比例
        Bitmap resizeBmp = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        return resizeBmp;
    }

    /**
     * 加载图片
     *
     * @param context
     * @param imgIcon
     */
    public void loadImage(Context context, int id, int defalutIconRes, final ImageView imgIcon) {
        Glide.with(context)
                .load(id)
                .error(defalutIconRes)//load失敗的Drawable
                .placeholder(defalutIconRes)
                .into(imgIcon);
    }

    public void loadIcon1(Context context, int id, int defalutIconRes, final ImageView imgIcon) {
        Glide.with(context)
                .load(id)
                .bitmapTransform(new CropCircleTransformation(context))
                .error(defalutIconRes)//load失敗的Drawable
                .placeholder(defalutIconRes)
                .into(imgIcon);
    }

    /**
     * 加载圆形图片
     *
     * @param context
     * @param string         A file path, or a uri
     * @param defalutIconRes
     * @param ivIcon
     */
    public void loadCircleIcon(final Context context, String string, int defalutIconRes, final ImageView ivIcon) {
        Glide.with(context)
                .load(string)
                .asBitmap()
                .centerCrop()
                .error(defalutIconRes)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(defalutIconRes)
                .into(new BitmapImageViewTarget(ivIcon) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                        circularBitmapDrawable.setCircular(true);
                        ivIcon.setImageDrawable(circularBitmapDrawable);
                    }
                });
    }
}
