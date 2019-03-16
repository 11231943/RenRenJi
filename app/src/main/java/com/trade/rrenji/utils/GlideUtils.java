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
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
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

    public void loadRFeIamge(Context context, String url, int defalutIconRes, final ImageView imgIcon) {
        RequestOptions options = new RequestOptions()
                .error(defalutIconRes)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .override(400,400)
                .centerCrop()
                .placeholder(defalutIconRes);
        Glide.with(context).load(url).apply(options).into(imgIcon);
    }

    /**
     * 加载图片
     *
     * @param context
     * @param url
     * @param defalutIconRes
     * @param imgIcon
     */
    public void loadImageUrl(Context context, String url, int defalutIconRes, final ImageView imgIcon) {
        RequestOptions options = new RequestOptions()
                .error(defalutIconRes)
                .placeholder(defalutIconRes)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .fitCenter();
        Glide.with(context).load(url).apply(options).into(imgIcon);
    }


    public void loadIcon(Context context, int id, int defalutIconRes, final ImageView imgIcon) {
        RequestOptions options = new RequestOptions()
                .error(defalutIconRes)
                .placeholder(defalutIconRes);
        Glide.with(context).load(id).apply(options).into(imgIcon);

    }

    /**
     * 加载圆形图片
     */
    public void loadCircleIcon(final Context context, String string, int defalutIconRes, final ImageView ivIcon) {
        RequestOptions options = new RequestOptions()
                .error(defalutIconRes)
                .placeholder(defalutIconRes)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .fitCenter();
        Glide.with(context).load(string).apply(options.circleCrop()).into(ivIcon);
    }
}
