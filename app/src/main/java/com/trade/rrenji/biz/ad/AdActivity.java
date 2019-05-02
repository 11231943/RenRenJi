/*
 * File Name：AdActivity.java
 * Copyright：Copyright 2008-2014 CiWong.Inc. All Rights Reserved.
 * Description： AdActivity.java
 * Modify By：pla-ckpeng
 * Modify Date：2014-2-26
 * Modify Type：Add
 */
package com.trade.rrenji.biz.ad;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.DownloadListener;
import android.webkit.JavascriptInterface;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.trade.rrenji.R;
import com.trade.rrenji.biz.base.BaseActivity;

import java.lang.reflect.Method;

/**
 * 广告页面
 *
 * @author pla-ckpeng
 * @version v.1.0 2014-2-26
 * @since v.1.0
 */
public class AdActivity extends BaseActivity {

    @Override
    protected void attachPresenter() {

    }

    @Override
    protected void detachPresenter() {

    }

    private boolean isOnPause = false;

    private static String TAG = AdActivity.class.getSimpleName();

    /**
     * 分享成功
     */
    private final static int share_succeed = 1;
    /**
     * 分享失败
     */
    private final static int share_fail = 2;
    /**
     * 分享取消
     */
    private final static int share_cancel = 3;
//    protected Handler handler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            if (isFinishing())
//                return;
//            switch (msg.what) {
//                case share_succeed:
//                    Toast.makeText(AdActivity.this, R.string.share_success, Toast.LENGTH_SHORT).show();
//                    break;
//                case share_fail:
//                    Toast.makeText(AdActivity.this, R.string.share_failed, Toast.LENGTH_SHORT).show();
//                    break;
//                case share_cancel:
//                    Toast.makeText(AdActivity.this, R.string.share_cancel, Toast.LENGTH_SHORT).show();
//                    break;
//            }
//        }
//
//    };


    private WebView webView;

    private String url;//

    private ProgressBar loading_progressBar;

    private ImageView close_view;

    private String title;//分享标题

    private String mDescription;//网页描述

    private String mImgUrl;//图片

    private String mLink;//微信分享链接
    /**
     * 分享按钮显示判断,默认不显示加载完url后显示分享
     */
    private boolean isShowShare = true;

    private int type = 0;

    private String id = "";

    public static void start(Context context, String url) {
        Intent starter = new Intent(context, AdActivity.class);
        starter.putExtra("url", url);

        context.startActivity(starter);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setView());
        hardwareAccelerate();
        findViews();
        initEvent();
        loadData();
    }


    /**
     * {@inheritDoc}
     */
    protected int setView() {
        return R.layout.ad_layou;
    }

    /**
     * {@inheritDoc}
     */
    protected void findViews() {
        webView = (WebView) findViewById(R.id.pay_webview);
        loading_progressBar = (ProgressBar) findViewById(R.id.loading_progressBar);
        close_view = (ImageView) findViewById(R.id.close_view);
    }

    /**
     * {@inheritDoc}
     */
    protected void initEvent() {
        url = getIntent().getStringExtra("url");
        Log.e("AdActivity", "url = " + url);

        close_view.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                finish();
            }
        });

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);

        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);

        settings.setBlockNetworkImage(true);
        settings.setDomStorageEnabled(true);

//        settings.setBuiltInZoomControls(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        if (Build.VERSION.SDK_INT >= 11) {
            getWindow().setFlags(android.view.WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                    android.view.WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
        }


        if (VERSION.SDK_INT >= VERSION_CODES.LOLLIPOP) {
            webView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE);
        }
        if (Build.VERSION.SDK_INT >= 11) {
            webView.getSettings().setDisplayZoomControls(false);
        }
//        webView.getSettings().setPluginsEnabled(true);
//        webView.getSettings().setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
        if (Build.VERSION.SDK_INT >= 17) {
            webView.addJavascriptInterface(new JavaScriptInterface(), "extract");
        }
        webView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                loading_progressBar.setProgress(progress);
                if (progress == 100) {
                    title = webView.getTitle();
                    Log.e(TAG, "title==================================" + title);
                    if (!TextUtils.isEmpty(title))
                        setActionBarTitle(title);
                    loading_progressBar.setVisibility(View.GONE);
                } else {
                    loading_progressBar.setVisibility(View.VISIBLE);
                }
            }
        });
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.startsWith("http")) return false;

                Intent intent;
                try {
                    intent = Intent.parseUri(url, Intent.URI_INTENT_SCHEME);
                    startActivity(intent);
                    return true;
                } catch (Exception e) {
                    return false;
                }
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                view.getSettings().setBlockNetworkImage(false);
                testMethod();
                super.onPageFinished(view, url);
            }

            @Override
            public void onReceivedSslError(WebView view,
                                           SslErrorHandler handler, SslError error) {
                handler.proceed();
            }

        });
        webView.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
                Uri uri = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        webView.loadUrl(url);

    }

    class JavaScriptInterface {
        @JavascriptInterface
        public void description(String description) {
//            LogUtils.e("JavaScriptInterface", description);
            mDescription = description;
        }

        @JavascriptInterface
        public void imgUrl(String imgUrl) {
//            LogUtils.e("JavaScriptInterface", imgUrl);
            mImgUrl = imgUrl;
        }

        @JavascriptInterface
        public void link(String link) {
//            LogUtils.e("JavaScriptInterface", link);
            mLink = link;
        }
    }

    private void testMethod() {
        String call = "javascript:description()";
        String imgUrl = "javascript: imgUrl()";
        String link = "javascript:link()";
        webView.loadUrl(call);
        webView.loadUrl(imgUrl);
        webView.loadUrl(link);
    }

//    public void setActionBarTitle(String title) {
//        ActionBar actionBar = getSupportActionBar();
//        if (mCustomTitle == null) {
//            mCustomTitle = new CustomViewTitle(this, actionBar);
//        }
//        mCustomTitle.setTitle(title);
//        mCustomTitle.addToTarget();
//    }


//    public void show(String url, String title) {
//        if (isFinishing()) {
//            return;
//        }
//        String str = getString(R.string.app_name_seo);
//        String content = "";
//        String imgUrl = "";
//
//        if (!TextUtils.isEmpty(mDescription)) {
//            content = mDescription;
//        } else {
//            content = title;
//            title = str;
//        }
//        if (!TextUtils.isEmpty(mImgUrl)) {
//            imgUrl = mImgUrl;
//        } else {
//            imgUrl = null;
//        }
//        if (!TextUtils.isEmpty(mLink)) {
//            url = mLink;
//        }
//        ShareFragment.show(getSupportFragmentManager(), R.id.container, null,
//                title, content, url,
//                imgUrl);
//    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        if (isShowShare)
//            getMenuInflater().inflate(R.menu.share, menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.share:
//                show(url, title);
//                return true;
//            case R.id.open_in_browser:
//                if (TextUtils.isEmpty(url)) {
//                    return false;
//                }
//
//                String realUrl = url;
//                if (!realUrl.startsWith("http://") && !realUrl.startsWith("https://")) {
//                    realUrl = "http://" + realUrl;
//                }
//                Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(realUrl));
//                if (myIntent.resolveActivity(getPackageManager()) != null) {
//                    startActivity(myIntent);
//                }
//                return true;
//            case android.R.id.home:
//                finish();
//                return false;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        EventBus.getDefault().post(new ShareResultEvent(requestCode, resultCode, data));
    }

    //硬件加速
    private void hardwareAccelerate() {
//        if (this.getPhoneSDKInt() >= 14) {
//            getWindow().setFlags(0x1000000, 0x1000000);
//        }
    }

    public int getPhoneSDKInt() {
        int version = 0;
        try {
            version = VERSION.SDK_INT;
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return version;
    }


    /**
     * 该处的处理尤为重要:
     * 应该在内置缩放控件消失以后,再执行mWebView.destroy()
     * 否则报错WindowLeaked
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (webView != null) {
            webView.loadUrl("about:blank");
            webView.stopLoading();
            webView.setWebChromeClient(null);
            webView.setWebViewClient(null);
            webView.setVisibility(View.GONE);

            ((ViewGroup) webView.getParent()).removeView(webView);
            webView.destroy();
            webView = null;
        }
        isOnPause = false;
    }


    /**
     * {@inheritDoc}
     */
    protected void loadData() {
    }

    public void onClick(View arg0) {
    }


    /**
     * 当Activity执行onPause()时让WebView执行pause
     */
    @Override
    public void onPause() {
        super.onPause();
        try {
            if (webView != null) {
                Method method = webView.getClass().getMethod("onPause");
                if (method != null)
                    method.invoke(webView, (Object[]) null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            isOnPause = true;
        }

        if (webView != null) {
            webView.pauseTimers();
        }
    }

    /**
     * 当Activity执行onResume()时让WebView执行resume
     */
    @Override
    public void onResume() {
        super.onResume();
        try {
            if (isOnPause) {
                if (webView != null) {
                    Method method = webView.getClass().getMethod("onResume");
                    if (method != null)
                        method.invoke(webView, (Object[]) null);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            isOnPause = false;
        }

        if (webView != null) {
            webView.resumeTimers();
        }
    }
}