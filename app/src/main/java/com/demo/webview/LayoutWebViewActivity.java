package com.demo.webview;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;

/**
 * Created by lc on 16-2-27.
 */
public class LayoutWebViewActivity extends AppCompatActivity {

    WebView webView;
    ProgressBar bar;
    ImageView iv_error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_webview);
        bar = (ProgressBar)findViewById(R.id.myProgressBar);
        webView = (WebView)findViewById(R.id.myWebView);
        iv_error= (ImageView)findViewById(R.id.iv_error);
        webView.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    bar.setVisibility(View.GONE);
                    webView.setVisibility(View.VISIBLE);
                } else {
                    if (View.GONE == bar.getVisibility()) {
                        bar.setVisibility(View.VISIBLE);
                    }
                    bar.setProgress(newProgress);
                }
                super.onProgressChanged(view, newProgress);
            }

        });
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            /**
             * API 19不调用
             * @param view
             * @param request
             * @param error
             */
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                Log.e("test", "receivererror1");
//                webView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);

            }

            /**
             * 自定义出错界面
             * @param view
             * @param errorCode
             * @param description
             * @param failingUrl
             */
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                Log.e("test", "receivererror2");
                webView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);//清空页面
                iv_error.setVisibility(View.VISIBLE);
            }

            /**
             * API23以上才会被调用
             * @param view
             * @param request
             * @param errorResponse
             */
            @Override
            public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
                super.onReceivedHttpError(view, request, errorResponse);
                Log.e("test", "receivererror3");
            }

            //加快html装载速度
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if(!webView.getSettings().getLoadsImagesAutomatically()) {
                    webView.getSettings().setLoadsImagesAutomatically(true);
                }
            }
        });

        webView.getSettings().setJavaScriptEnabled(true);
//        webView.getSettings().setDatabaseEnabled(true);
//        webView.getSettings().setAppCacheEnabled(true);
//        webView.getSettings().setDomStorageEnabled(true);
//        webView.getSettings().setAllowContentAccess(true);
//        webView.getSettings().setAllowFileAccess(true);
//        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);

        //加快html装载速度
        if(Build.VERSION.SDK_INT >= 19) {
            webView.getSettings().setLoadsImagesAutomatically(true);
        } else {
            webView.getSettings().setLoadsImagesAutomatically(false);
        }


        final String url = "http://item.m.jd.com/ware/view.action?wareId=11840656&resourceType=jdapp_share&resourceValue=Wxfriends&utm_source=iosapp&utm_medium=appshare&utm_campaign=t_335139774&utm_term=Wxfriends&from=singlemessage&isappinstalled=0";
        webView.loadUrl(url);
    }


}
