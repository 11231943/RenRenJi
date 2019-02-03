package com.trade.rrenji.net;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;
import org.xutils.common.Callback;

import java.io.File;
import java.util.Map;

/**
 * Created by pck on 17-10-12.
 */

public class XUtils {

    public static final String TAG = XUtils.class.getSimpleName();

    private volatile static XUtils instance;

    private XUtils() {
        Handler handler = new Handler(Looper.getMainLooper());
    }

    /**
     * 单例模式
     *
     * @return
     */
    public static XUtils getInstance() {
        if (instance == null) {
            synchronized (XUtils.class) {
                if (instance == null) {
                    instance = new XUtils();
                }
            }
        }
        return instance;
    }

    /**
     * 异步get请求
     *
     * @param url
     * @param maps
     * @param listener
     */
    public void get(String url, Map<String, String> maps, final ResultListener listener) {

        RequestParams params = new RequestParams(url);
        if (maps != null && !maps.isEmpty()) {
            for (Map.Entry<String, String> entry : maps.entrySet()) {
//                params.addQueryStringParameter(entry.getKey(), entry.getValue());
//                params.addParameter();
            }
        }

        x.http().get(params, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                listener.onResponse(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                ex.printStackTrace();
                listener.onError(ex);
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    public void cancelRequest() {

    }

    public void put(String url, Map<String, String> maps, final ResultListener listener) {
        RequestParams params = new RequestParams(url);
        if (maps != null && !maps.isEmpty()) {
            for (Map.Entry<String, String> entry : maps.entrySet()) {
                params.addBodyParameter(entry.getKey(), entry.getValue());
            }
        }
        Log.d(TAG, url + "params : " + params.toString());
        x.http().request(HttpMethod.PUT, params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                listener.onResponse(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                listener.onError(ex);
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }


    /**
     * 异步post请求
     *
     * @param url
     * @param maps
     * @param listener
     */
    public void post(String url, Map<String, String> maps, final ResultListener listener) {
        RequestParams params = new RequestParams(url);
        if (maps != null && !maps.isEmpty()) {
            for (Map.Entry<String, String> entry : maps.entrySet()) {
                params.addBodyParameter(entry.getKey(), entry.getValue());
            }
        }
        Log.d(TAG, url + "params : " + params.toString());
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                listener.onResponse(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                listener.onError(ex);
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    /**
     * 带缓存数据的异步 get请求
     *
     * @param url
     * @param maps
     * @param pnewCache
     * @param listener
     */
    public void getCache(String url, Map<String, String> maps, final boolean pnewCache, final ResultListener listener) {

        RequestParams params = new RequestParams(url);
        if (maps != null && !maps.isEmpty()) {
            for (Map.Entry<String, String> entry : maps.entrySet()) {
                params.addQueryStringParameter(entry.getKey(), entry.getValue());
            }
        }
        x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                listener.onResponse(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }

            @Override
            public boolean onCache(String result) {
                boolean newCache = pnewCache;
                if (newCache) {
                    newCache = !newCache;
                }
                if (!newCache) {
                    newCache = !newCache;
                    listener.onResponse(result);
                }
                return newCache;
            }
        });
    }

    /**
     * 带缓存数据的异步 post请求
     *
     * @param url
     * @param maps
     * @param pnewCache
     * @param listener
     */
    public void postCache(String url, Map<String, String> maps, final boolean pnewCache, final ResultListener listener) {
        RequestParams params = new RequestParams(url);
        if (maps != null && !maps.isEmpty()) {
            for (Map.Entry<String, String> entry : maps.entrySet()) {
                params.addBodyParameter(entry.getKey(), entry.getValue());
            }
        }

        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                listener.onResponse(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }


            @Override
            public void onFinished() {

            }

            @Override
            public boolean onCache(String result) {
                boolean newCache = pnewCache;
                if (newCache) {
                    newCache = !newCache;
                }
                if (!newCache) {
                    newCache = !newCache;
                    listener.onResponse(result);
                }
                return newCache;
            }
        });
    }

    /**
     * 文件上传
     *
     * @param url
     * @param maps
     * @param file
     * @param listener
     */
    public void upLoadFile(String url, Map<String, String> maps, Map<String, File> file, final ResultListener listener) {

        RequestParams params = new RequestParams(url);
        // 有上传文件时使用multipart表单, 否则上传原始文件流.
        params.setMultipart(true);
        if (maps != null && !maps.isEmpty()) {
            for (Map.Entry<String, String> entry : maps.entrySet()) {
                params.addBodyParameter(entry.getKey(), entry.getValue());
            }
        }
        if (file != null) {
            for (Map.Entry<String, File> entry : file.entrySet()) {
                params.addBodyParameter(entry.getKey(), entry.getValue().getAbsoluteFile());
            }
        }

        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                listener.onResponse(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                listener.onError(ex);
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });

    }


    /**
     * 文件下载
     *
     * @param url
     * @param saveFile
     * @param maps
     * @param loadResultListener
     */
    public void downLoadFile(String url, String saveFile, Map<String, String> maps, final XDownLoadResultListener loadResultListener) {

        RequestParams params = new RequestParams(url);
        if (maps != null && !maps.isEmpty()) {
            for (Map.Entry<String, String> entry : maps.entrySet()) {
                params.addBodyParameter(entry.getKey(), entry.getValue());
            }
        }
        params.setAutoRename(true);// 断点续传
        params.setSaveFilePath(saveFile);
        x.http().post(params, new Callback.ProgressCallback<File>() {
            @Override
            public void onSuccess(final File result) {
                loadResultListener.onResponse(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                loadResultListener.onFinished();
            }

            @Override
            public void onWaiting() {

            }

            @Override
            public void onStarted() {

            }

            @Override
            public void onLoading(final long total, final long current, final boolean isDownloading) {
                loadResultListener.onLoading(total, current, isDownloading);
            }
        });

    }

    public interface ResultListener {
        void onResponse(String result);

        void onError(Throwable error);
    }

    public interface XDownLoadResultListener extends ResultListener {

        void onResponse(File result);

        void onLoading(long total, long current, boolean isDownloading);

        void onFinished();
    }
}
