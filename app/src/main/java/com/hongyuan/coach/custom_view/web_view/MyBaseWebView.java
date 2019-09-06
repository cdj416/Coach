package com.hongyuan.coach.custom_view.web_view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;

import com.hongyuan.coach.R;

import ren.yale.android.cachewebviewlib.WebViewCacheInterceptorInst;

/**
 * Created by administer on 2018/9/27.
 */

public class MyBaseWebView extends WebView {
    WebSettings mSettings;

    private WebViewProgressBar progressBar;//进度条的矩形（进度线）
    private Handler handler;
    private WebView mWebView;
    private boolean of;//是否关闭进度条，默认开启
    private boolean isCache;//是否开启缓存，默认不开启
    private boolean isBlockingPicture;//是否开启图片阻塞，已达到加速加载网页内容，默认不开启
    private boolean isGoBack;//是否开启返回键处理，默认不开启
    private boolean isUrlGo = false;//处理当有返回自定义处理url的时候无法加载首页的问题（第一次显示的url不需要去返回拦截）
    private boolean openSaveUrl = false;//是否开启检验链接安全，默认不开启

    public MyBaseWebView(Context context, AttributeSet attrs) {
        super(context,attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.MyBaseWebView);
        //获取是否开启缓存值
        isCache = ta.getBoolean(R.styleable.MyBaseWebView_isCache,false);
        //获取是否开启图片阻塞
        isBlockingPicture = ta.getBoolean(R.styleable.MyBaseWebView_isBlockingPicture,false);
        //获取是否开启进度条(默认开启)
        of = ta.getBoolean(R.styleable.MyBaseWebView_isProgressbar,false);
        //获取是否开启返回键处理
        isGoBack = ta.getBoolean(R.styleable.MyBaseWebView_isGoBack,false);
        //实例化进度条
        progressBar = new WebViewProgressBar(context);
        //设置进度条的size
        progressBar.setLayoutParams(new ViewGroup.LayoutParams
                (ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        //刚开始时候进度条不可见
        progressBar.setVisibility(GONE);
        //把进度条添加到webView里面
        addView(progressBar);
        handler = new Handler();
        mWebView = this;
        //初始化设置
        initSettings();
    }

    private void initSettings() {
        // 初始化设置
        mSettings = this.getSettings();
        mSettings.setJavaScriptEnabled(true);//开启javascript
        mSettings.setDomStorageEnabled(true);//开启DOM
        mSettings.setDefaultTextEncodingName("utf-8");//设置字符编码
        //设置web页面
        mSettings.setAllowFileAccess(true);//设置支持文件流
        mSettings.setSupportZoom(false);// 支持缩放
        mSettings.setBuiltInZoomControls(false);// 支持缩放
        mSettings.setUseWideViewPort(true);// 调整到适合webview大小
        mSettings.setLoadWithOverviewMode(true);// 调整到适合webview大小
        mSettings.setDefaultZoom(WebSettings.ZoomDensity.FAR);// 屏幕自适应网页,如果没有这个，在低分辨率的手机上显示可能会异常
        mSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        //提高网页加载速度，暂时阻塞图片加载，然后网页加载好了，在进行加载图片
        if(isBlockingPicture){
            mSettings.setBlockNetworkImage(true);
        }
        mSettings.setAppCacheEnabled(false);//开启缓存机制
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mWebView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        setWebViewClient(new MyWebClient());
        setWebChromeClient(new MyWebChromeClient());
    }

    /*
    * 手机自带返回键处理
    * */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (isGoBack && keyCode == KeyEvent.KEYCODE_BACK && canGoBack()) {
            goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private class MyWebClient extends WebViewClient {
        @Nullable
        @Override
        public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
            if(isCache){
                return  WebViewCacheInterceptorInst.getInstance().interceptRequest(view, request);
            }else{
                return super.shouldInterceptRequest(view, request);
            }
        }

        @Nullable
        @Override
        public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
            if(isCache){
                return  WebViewCacheInterceptorInst.getInstance().interceptRequest(view,url);
            }else{
                return super.shouldInterceptRequest(view, url);
            }
        }

        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            boolean isRturn = true;
            if(isCache){
                //这里在写一遍是为了避免忘记设置jumpControl对象，能够正常使用
                WebViewCacheInterceptorInst.getInstance().loadUrl(mWebView,request.getUrl().toString());
            }else{
                isRturn = super.shouldOverrideUrlLoading(view, request);
            }
            return isRturn;
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            boolean isRturn = true;
            if(isCache){
                //这里在写一遍是为了避免忘记设置jumpControl对象，能够正常使用
                WebViewCacheInterceptorInst.getInstance().loadUrl(mWebView,url);
            }else{
                isRturn = super.shouldOverrideUrlLoading(view, url);
            }
            return isRturn;
        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            handler.proceed();
        }

        /**
         *  * 页面加载过程中，加载资源回调的方法
         *  *
         *  * @param view
         *  * @param url
         *  
         */
        @Override
        public void onLoadResource(WebView view, String url) {
            super.onLoadResource(view, url);
        }

        /**
         *  * 页面加载完成回调的方法
         *  *
         *  * @param view
         *  * @param url
         *  
         */
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            // 关闭图片加载阻塞
            if(isBlockingPicture){
                view.getSettings().setBlockNetworkImage(false);
            }
        }

        /**
         *  * 页面开始加载调用的方法
         *  *
         *  * @param view
         *  * @param url
         *  * @param favicon
         *  
         */
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);
        }

        @Override
        public void onScaleChanged(WebView view, float oldScale, float newScale) {
            super.onScaleChanged(view, oldScale, newScale);
            mWebView.requestFocus();
            mWebView.requestFocusFromTouch();
        }
    }

    /**
     * 自定义WebChromeClient
     */
    private class MyWebChromeClient extends WebChromeClient {
        /**
         *  * 进度改变的回掉
         *  *
         *  * @param view  WebView
         *  * @param newProgress 新进度
         *  
         */
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if(!of){
                if (newProgress == 100) {
                    progressBar.setProgress(100);
                    handler.postDelayed(runnable, 200);//0.2秒后隐藏进度条
                } else if (progressBar.getVisibility() == GONE) {
                    progressBar.setVisibility(VISIBLE);
                }
                //设置初始进度10，这样会显得效果真一点，总不能从1开始吧
                if (newProgress < 10) {
                    newProgress = 10;
                }
                //不断更新进度
                progressBar.setProgress(newProgress);
                super.onProgressChanged(view, newProgress);
            }
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
        }
    }


    /**
     * 刷新界面（此处为加载完成后进度消失）
     */
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            progressBar.setVisibility(View.GONE);
        }
    };

    /*
    * 是否开启安全链接的检验
    * */
    public void setOpenSaveUrl(boolean openSaveUrl){
        this.openSaveUrl = openSaveUrl;
    }

    /*
    * 是否开启关闭进度条
    * */
    public void setIsOf(boolean of){
        this.of = of;
    }

    /*
    * 是否开启了网页本地缓存机制
    * */
    public boolean getIsCache(){
        return this.isCache;
    }

    /*
    * 设置是否开启网页本地存储机制
    * */
    public void setIsCache(boolean isCache){
        this.isCache = isCache;
    }

    /*
    * 设置是否开启缓存
    * */
    public void setIsBlockingPicture(boolean isBlockingPicture){
        this.isBlockingPicture = isBlockingPicture;
        if(isBlockingPicture){
            mSettings.setBlockNetworkImage(true);
        }else{
            mSettings.setBlockNetworkImage(false);
        }
    }

    /*
    * 设置是否开启页面返回键处理
    * */
    public void setIsGoBack(boolean isGoBack){
        this.isGoBack = isGoBack;
    }

    /*
    * 一次性设置各个功能
    * */
    public void setAll(boolean of, boolean isCache, boolean isBlockingPicture, boolean isGoBack, boolean openSaveUrl){
        setIsOf(of);
        setIsCache(isCache);
        setIsBlockingPicture(isBlockingPicture);
        setIsGoBack(isGoBack);
        setOpenSaveUrl(openSaveUrl);
    }

    /*
    * 退出webview需要做的销毁清除处理
    * */
    public void closeWebView(){
        getSettings().setJavaScriptEnabled(false);
        clearCache(true);
        setWebChromeClient(null);
        setWebViewClient(null);
        clearHistory();
        destroy();
    }


    /*
    * 下面是一些可以控制跳转得接口
    * */
    public interface JumpControl{
        void jump(WebView view, String url);
    }
    private JumpControl jumpControl;
    //初始化控制对象
    public void setJumpControl(JumpControl jumpControl){
        this.jumpControl = jumpControl;
    }
}
