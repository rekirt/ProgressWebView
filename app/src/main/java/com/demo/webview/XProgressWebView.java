package com.demo.webview;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

/**
 * Created by lc on 16-2-27.
 */
public class XProgressWebView extends LinearLayout {

    private ProgressBar mProgressBar;
    private WebView mWebView;

    public XProgressWebView(Context context) {
        super(context);
        init(context);
    }

    public XProgressWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public XProgressWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        setOrientation(VERTICAL);
        mProgressBar = new ProgressBar(context,null,android.R.attr.progressBarStyleHorizontal);
        mProgressBar.setLayoutParams(new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 10));
        mProgressBar.setBackgroundColor(Color.BLUE);
        addView(mProgressBar);
        mWebView = new WebView(context);
        mWebView.setLayoutParams(new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT));
        mWebView.setWebChromeClient(new WebChromeClient());
        mWebView.setWebViewClient(new WebViewClient());
    }

    public class WebViewClient extends android.webkit.WebViewClient{
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
//            if(url.startsWith("http://") || url.startsWith("https://") ){
                view.loadUrl(url);
//            }
            return true;
        }
    }

    public class WebChromeClient extends android.webkit.WebChromeClient{
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if(newProgress==100){
                mProgressBar.setVisibility(GONE);
            }else{
                if(mProgressBar.getVisibility()==GONE){
                    mProgressBar.setVisibility(VISIBLE);
                }
                mProgressBar.setProgress(newProgress);
            }
            super.onProgressChanged(view, newProgress);
        }
    }















}
