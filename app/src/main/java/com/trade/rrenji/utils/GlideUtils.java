package com.trade.rrenji.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


/**
 * @author Dragon
 * @date 2016/10/25. 10:10
 * @editor
 * @date
 * @describe
 */
public class GlideUtils {

    public static final int CROP_OPTIONS_SQUARE_ONLY = 1;

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

    public static Bitmap getBitmap(Context context, int r) {
        InputStream is = null;
        try {
            is = context.getResources().openRawResource(r);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = false;
            options.inSampleSize = 2;   //缩放比例
            Bitmap btp = BitmapFactory.decodeStream(is, null, options);
            return btp;
        } catch (Exception e) {
            return null;
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static Bitmap getScaledRotatedBitmap(Context context, String filePath,
                                                int maxSize, int minSize) {
        int screenWidth = ViewUtils.getScreenWidth(context);
        int screenHeight = ViewUtils.getScreenHeight(context);
        if (screenHeight > maxSize) screenHeight = maxSize;
        if (screenWidth > maxSize) screenWidth = maxSize;
        if (screenHeight < minSize) screenHeight = minSize;
        if (screenWidth < minSize) screenWidth = minSize;
        int minSideLength = screenWidth <= screenHeight ? screenWidth : screenHeight;
        Bitmap bitmap = ImageHelper.getImage(filePath, minSideLength,
                screenWidth * screenHeight);
        if (bitmap != null)
            bitmap = ImageHelper.rotate(bitmap, ImageHelper.getExifOrientation(filePath));
        return bitmap;
    }
    public static File compressAndSaveToFile(Bitmap bitmap, String filePath,
                                             int maxSize, int maxQuality)
            throws IOException {
        File file = new File(filePath);
        int quality = maxQuality;
        double size;
        int height = bitmap.getHeight();
        int width = bitmap.getWidth();
        FileOutputStream stream = null;
        try {
            do {
                stream = new FileOutputStream(file);
                BufferedOutputStream bos = new BufferedOutputStream(stream);
                if (quality != maxQuality)
                    bitmap = Bitmap.createScaledBitmap(bitmap, width, height, true);
                bitmap.compress(Bitmap.CompressFormat.JPEG, quality, bos);
                bos.flush();
                bos.close();
                size = file.length() / 1024.0;
                quality -= 5;
                height *= 0.8;
                width *= 0.8;
            } while (size > maxSize);
        } finally {
            closeOutputStream(stream);
        }

        return file;
    }
    public static void closeOutputStream(OutputStream ous) {
        if (ous == null)
            return;

        try {
            ous.flush();
            ous.close();
        } catch (IOException e) {
            Log.e("GlideUtils", "closeOutputStream " + e);
        }
    }
}
