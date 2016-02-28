package com.demo.webview;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

/**
 * Created by lc on 16-2-27.
 */
public class ProgressWebView extends WebView {

    private ProgressBar mProgressBar;


    public ProgressWebView(Context context) {
        super(context);
        init(context);
    }

    public ProgressWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ProgressWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mProgressBar = new ProgressBar(context,null,android.R.attr.progressBarStyleHorizontal);
        mProgressBar.setLayoutParams(new WebView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 50, 0, 0));
        mProgressBar.setBackgroundColor(Color.BLUE);
        addView(mProgressBar);
        setWebChromeClient(new WebChromeClient());
        setWebViewClient(new WebViewClient());
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

//    @Override
//    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
//        LayoutParams lp = (LayoutParams)mProgressBar.getLayoutParams();
//        lp.x = l;
//        lp.y = t;
//        mProgressBar.setLayoutParams(lp);
//        super.onScrollChanged(l, t, oldl, oldt);
//    }






















}
