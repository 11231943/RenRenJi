package com.trade.rrenji.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.media.ExifInterface;
import android.util.Log;
import java.io.IOException;
import java.io.InputStream;

public class ImageHelper {
	
	/**
     * 保持长宽比缩小Bitmap
     *
     * @param bitmap
     * @param maxWidth
     * @param maxHeight
     * @return
     */
    public static Bitmap resizeBitmap(Bitmap bitmap, int maxWidth, int maxHeight) {

        int originWidth  = bitmap.getWidth();
        int originHeight = bitmap.getHeight();

        if (originWidth < maxWidth && originHeight < maxHeight) {
            return bitmap;
        }

        int width  = originWidth;
        int height = originHeight;

        // 若图片过宽, 则保持长宽比缩放图片
        if (originWidth > maxWidth) {
            width = maxWidth;

            double i = originWidth * 1.0 / maxWidth;
            height = (int) Math.floor(originHeight / i);

            bitmap = Bitmap.createScaledBitmap(bitmap, width, height, false);
        }

        // 若图片过长, 则从上端截取
        if (height > maxHeight) {
            height = maxHeight;
            bitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height);
        }

//        Log.i(TAG, width + " width");
//        Log.i(TAG, height + " height");

        return bitmap;
    }
    
	/**
	 * 缩放图片
	 * @param bitmap 图片资源
	 * @param w 缩放后宽度
	 * @param h 缩放后高度
	 * @return 返回缩放后的图片资源
	 */
	public static Bitmap zoomBitmap(Bitmap bitmap, int w, int h) {
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();
		Matrix matrix = new Matrix();

		float scaleWidht = ((float) w / width);
		float scaleHeight = ((float) h / height);

		matrix.postScale(scaleWidht, scaleHeight);

        return Bitmap.createBitmap(bitmap, 0, 0, width, height,
                matrix, true);
	}
    
	/**
	 * 将图片按指定的尺寸截取
	 * @param bitmap
	 * @param w 截取的宽度
	 * @param h 截取的高度
	 * @return 返回截取后bitmap图片
	 */
	public static Bitmap cropBitmap(Bitmap bitmap, int w, int h) {
		int x = 0, y = 0;
		float newWidth = 0, newHeight = 0;
		float width = bitmap.getWidth();
		float height = bitmap.getHeight();
		
		Bitmap tmpbmp;

		if((float) w/h < width/height){
			newWidth = width*h/height;
			x = (int) ((newWidth - w)/2);
			tmpbmp = zoomBitmap(bitmap, (int)newWidth, h);
		}else{
			newHeight = height*w/width;
			y = (int) ((newHeight - h)/2);
			tmpbmp = zoomBitmap(bitmap, w, (int)(newHeight));
		}
		
        return Bitmap.createBitmap(tmpbmp, x, y, w, h);
	}

    public static int getExifOrientation(String filepath) {
        int degree = 0;
        ExifInterface exif = null;
        try {
            exif = new ExifInterface(filepath);
        } catch (IOException ex) {
            Log.e("cannot read exif", ex.getMessage());
        }
        
        if (exif != null) {
            int orientation = exif.getAttributeInt(
                ExifInterface.TAG_ORIENTATION, -1);
            if (orientation != -1) {
                // We only recognize a subset of orientation tag values.
                switch(orientation) {
                    case ExifInterface.ORIENTATION_ROTATE_90:
                        degree = 90;
                        break;
                    case ExifInterface.ORIENTATION_ROTATE_180:
                        degree = 180;
                        break;
                    case ExifInterface.ORIENTATION_ROTATE_270:
                        degree = 270;
                        break;
                }

            }
        }
        return degree;
    }
    
    public static Bitmap getImage(String filepath, int minSideLength, int maxNumOfPixels) {
    	try{
        	BitmapFactory.Options options = new BitmapFactory.Options();
        	options.inJustDecodeBounds = true;
        	BitmapFactory.decodeFile(filepath, options);
        	options.inJustDecodeBounds = false;  	
        	options.inSampleSize = computeSampleSize(options, minSideLength, maxNumOfPixels);
            options.inScaled = false;
        	return BitmapFactory.decodeFile(filepath, options);
    	}catch(OutOfMemoryError e){
			Log.e("Out of memory error", e.getMessage());
    	}
    	
		return null;
    }

    public static Bitmap getImage(Resources res, int resID, int minSideLength, int maxNumOfPixels) {
        try{
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeResource(res, resID, options);
            options.inJustDecodeBounds = false;
            options.inSampleSize = computeSampleSize(options, minSideLength, maxNumOfPixels);
            options.inScaled = false;
            return BitmapFactory.decodeResource(res, resID, options);
        }catch(OutOfMemoryError e){
			Log.e("Out of memory error", e.getMessage());
        }

        return null;
    }
    
    /**
     * 将字节数组按指定分辨率压缩成位图
     * @param data 字节数组
     * @param density 指定分辨率(px)
     * @return Bitmap位图
     */
    public static Bitmap getImage(byte[] data, int density){
    	try{
        	BitmapFactory.Options options = new BitmapFactory.Options();
        	options.inJustDecodeBounds = true;
        	BitmapFactory.decodeByteArray(data, 0, data.length, options);
        	options.inJustDecodeBounds = false;
        	
        	int multipe = (int) (options.outWidth / (float)density);
        	options.inSampleSize = multipe <= 0 ? 1 : multipe;

			Log.d("getImage",
					"outWidth=" + options.outWidth + ", density=" + density + ",multipe=" + multipe);
        	
        	if(options.outWidth > density){
        		options.inScaled = true;
        		options.inDensity = options.outWidth;
        		options.inTargetDensity = density;
        	}
        	
        	Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length, options);
        	data = null;
        	
        	return bitmap;
    	}catch(OutOfMemoryError e){
    		Log.d("Out of memory error", e.getMessage());
    	}
    	
    	data = null;
		return null;
    }
    
    /**
     * 返回指定图片的宽和高
     * @param filepath 图片路径
     * @return ImageSize对象
     */
    public static ImageSize getImageSize(String filepath){
    	BitmapFactory.Options options = new BitmapFactory.Options();
    	options.inJustDecodeBounds = true;
    	BitmapFactory.decodeFile(filepath, options);
    	
		return new ImageSize(options.outWidth, options.outHeight);
    	
    }

    /**
     * 图片旋转处理
     * @param bitmap 需要处理的图像
     * @param degrees 旋转角度
     * @return Bitmap图像
     */
    public static Bitmap rotate(Bitmap bitmap, int degrees){
    	if(bitmap != null){
        	int width = bitmap.getWidth();
        	int height = bitmap.getHeight();
        	Matrix matrix = new Matrix();
        	matrix.setRotate(degrees, width/2.0F, height/2.0F);
        	Bitmap targetBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
        	
        	if(bitmap != targetBitmap){
        		bitmap.recycle();
        	}
        	
        	return targetBitmap;
    	}
    	
		return null;
    }
    
    /**
     * 按照分辨率的比例生成圆角图片
     * @param context 上下文Context对象
     * @param bitmap 需要处理的图像
     * @param ratio 圆角比例(0-1)
     * @param width 生成图片宽度
     * @param height 生成图片高度
     * @return Bitmap图像
     */
    public static Bitmap getRoundedImage(Context context, Bitmap bitmap,
                                         float ratio, int width, int height){
    	float density = context.getResources().getDisplayMetrics().density;
    	
    	return getRoundedImage(density, bitmap, ratio, width, height);    	
    }
    
    /**
     * 按照分辨率的比例生成圆角图片
     * @param density 屏幕分辨率
     * @param bitmap 需要处理的图像
     * @param ratio 圆角比例(0-1)
     * @param width 生成图片宽度
     * @param height 生成图片高度
     * @return Bitmap图像
     */
    public static Bitmap getRoundedImage(float density, Bitmap bitmap,
                                         float ratio, int width, int height){
    	Bitmap.Config config = Bitmap.Config.ARGB_8888;
    	Bitmap targetBitmap = Bitmap.createBitmap(width, height, config);

    	RectF rectf = new RectF(new Rect(0, 0, width, height));
    	float round = ratio * density;
    	
    	Paint paint = new Paint();
    	paint.setAntiAlias(true);
    	paint.setColor(0xff424242);
    	
    	Canvas canvas = new Canvas(targetBitmap);
    	canvas.drawARGB(0, 0, 0, 0);
    	canvas.drawRoundRect(rectf, round, round, paint);
    	
    	PorterDuff.Mode pdmode = PorterDuff.Mode.SRC_IN;
    	PorterDuffXfermode pdxmode = new PorterDuffXfermode(pdmode);
    	paint.setXfermode(pdxmode);
    	canvas.drawBitmap(bitmap, 0.0F, 0.0F, paint);
    	
    	return targetBitmap;   
    }
    
    /**
     * 生成圆角图片
     * @param bitmap 需要处理的图像
     * @param round 圆角大小
     * @return Bitmap图像
     */
    public static Bitmap getRoundedImage(Bitmap bitmap, float round){
    	int width = bitmap.getWidth();
    	int height = bitmap.getHeight();
    	
    	return getRoundedImage(round, bitmap, 1, width, height);
    }
    
    public static class ImageSize{
    	public int width;
    	public int height;
    	
    	public ImageSize(int width, int height){
    		this.width = width;
    		this.height = height;
    	}
    }
    
	public static int calculateInSampleSize(BitmapFactory.Options options,
			int reqWidth, int reqHeight) {
		// Raw height and width of image
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;

		if (height > reqHeight || width > reqWidth) {
			if (width > height) {
				inSampleSize = Math.round((float) height / (float) reqHeight);
			} else {
				inSampleSize = Math.round((float) width / (float) reqWidth);
			}
		}
		return inSampleSize;
	}
	
	/**
	 * 通过InputStream对图片进行压缩
	 * @param stream1
	 * @param stream2
	 * @param reqWidth
	 * @param reqHeight
	 * @return
	 */
	public static Bitmap decodeSampledBitmapFromInputStream(InputStream stream1,
                                                            InputStream stream2,
                                                            int reqWidth, int reqHeight) {

		// First decode with inJustDecodeBounds=true to check dimensions
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeStream(stream1, null, options);

		// Calculate inSampleSize
		options.inSampleSize = calculateInSampleSize(options, reqWidth,
				reqHeight);

		// Decode bitmap with inSampleSize set
		options.inJustDecodeBounds = false;
		return BitmapFactory.decodeStream(stream2, null, options);
	}
	
	/**
	 * 通过文件对图片进行压缩
	 * @param path
	 * @param reqWidth
	 * @param reqHeight
	 * @return
	 */
	public static Bitmap decodeSampleBitmapFromFile(String path,
                                                    int reqWidth, int reqHeight) {
		
		// First decode with inJustDecodeBounds=true to check dimensions
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(path, options);

		// Calculate inSampleSize
		options.inSampleSize = calculateInSampleSize(options, reqWidth,
				reqHeight);

		// Decode bitmap with inSampleSize set
		options.inJustDecodeBounds = false;
		return BitmapFactory.decodeFile(path, options);
		
	}
	
	public static int computeSampleSize(BitmapFactory.Options options,
	        int minSideLength, int maxNumOfPixels) {  
	    int initialSize = computeInitialSampleSize(options, minSideLength,  
	            maxNumOfPixels);  
	  
	    int roundedSize;  
	    if (initialSize <= 8) {  
	        roundedSize = 1;  
	        while (roundedSize < initialSize) {  
	            roundedSize <<= 1;  
	        }  
	    } else {  
	        roundedSize = (initialSize + 7) / 8 * 8;  
	    }  
	  
	    return roundedSize;  
	}  
	
	private static int computeInitialSampleSize(BitmapFactory.Options options,
	        int minSideLength, int maxNumOfPixels) {  
	    double w = options.outWidth;  
	    double h = options.outHeight;  
	  
	    // 上下限范围  
	    int lowerBound = (maxNumOfPixels == -1) ? 1 : (int) Math.ceil(Math
	            .sqrt(w * h / maxNumOfPixels));  
	    int upperBound = (minSideLength == -1) ? 128 : (int) Math.min(
	            Math.floor(w / minSideLength), Math.floor(h / minSideLength));
	  
	    if (upperBound < lowerBound) {  
	        // return the larger one when there is no overlapping zone.  
	        return lowerBound;  
	    }  
	  
	    if ((maxNumOfPixels == -1) && (minSideLength == -1)) {  
	        return 1;  
	    } else if (minSideLength == -1) {  
	        return lowerBound;  
	    } else {  
	        return upperBound;  
	    }  
	}  
}


























