package com.trade.rrenji.utils;

import org.xutils.image.ImageOptions;

/**
 * Created by monster on 8/4/18.
 */

public class xUtilsImageLoader {

//    x.image().loadFile(url,imageOptions,new Callback.CacheCallback<File>(){
//        @Override
//        public boolean onCache(File result) {
//            //在这里可以做图片另存为等操作
//            Log.i("JAVA","file："+result.getPath()+result.getName());
//            return true; //相信本地缓存返回true
//        }
//        @Override
//        public void onSuccess(File result) {
//        }
//        @Override
//        public void onError(Throwable ex, boolean isOnCallback) {
//        }
//        @Override
//        public void onCancelled(CancelledException cex) {
//        }
//        @Override
//        public void onFinished() {
//        }
//    });
//x.image().loadDrawable(url, imageOptions, new Callback.CommonCallback<Drawable>() {
//        @Override
//        public void onSuccess(Drawable result) {
//            imageView.setImageDrawable(result);
//        }
//        @Override
//        public void onError(Throwable ex, boolean isOnCallback) {
//        }
//        @Override
//        public void onCancelled(CancelledException cex) {
//        }
//        @Override
//        public void onFinished() {
//        }
//    });

//
//    x.image().bind(imageView, "assets://test.gif", imageOptions);
//
//// local file
//x.image().bind(imageView, new File("/sdcard/test.gif").toURI().toString(), imageOptions);
//x.image().bind(imageView, "/sdcard/test.gif", imageOptions);
//x.image().bind(imageView, "file:///sdcard/test.gif", imageOptions);
//x.image().bind(imageView, "file:/sdcard/test.gif", imageOptions);


//    public static ImageOptions getImageOptions(){
//        //通过ImageOptions.Builder().set方法设置图片的属性
//        ImageOptions imageOptions= new ImageOptions.Builder().setFadeIn(true).build(); //淡入效果//ImageOptions.Builder()的一些其他属性：
//                 .setCircular(true) //设置图片显示为圆形
//                .setSquare(true) //设置图片显示为正方形
//                .setCrop(true).setSize(200,200) //设置大小
//                .setAnimation(animation) //设置动画
//                .setFailureDrawable(Drawable failureDrawable) //设置加载失败的动画
//                .setFailureDrawableId(int failureDrawable) //以资源id设置加载失败的动画
//                .setLoadingDrawable(Drawable loadingDrawable) //设置加载中的动画
//                .setLoadingDrawableId(int loadingDrawable) //以资源id设置加载中的动画
//                .setIgnoreGif(false) //忽略Gif图片
//                .setParamsBuilder(ParamsBuilder paramsBuilder) //在网络请求中添加一些参数
//                .setRaduis(int raduis) //设置拐角弧度
//                .setUseMemCache(true) //设置使用MemCache，默认true
//    }

}
