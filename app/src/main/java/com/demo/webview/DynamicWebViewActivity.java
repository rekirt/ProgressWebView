package com.demo.webview;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by lc on 16-2-27.
 */
public class DynamicWebViewActivity extends AppCompatActivity {
    Handler handler;
    WebView wv;
    TextView textView;
    ProgressBar progressBar;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout rootViewLayout = new LinearLayout(this);
        rootViewLayout.setOrientation(LinearLayout.VERTICAL);
        textView = new TextView(this);
        textView.setTextColor(Color.RED);
        //生成水平进度条
//        progressBar = new ProgressBar(this,null,android.R.attr.progressBarStyleHorizontal);
        progressBar = new ProgressBar(this);
        progressBar.setIndeterminate(false);
        progressBar.setHorizontalScrollBarEnabled(true);
        progressBar.setProgressDrawable(getResources().getDrawable(R.drawable.progress));
        progressBar.setIndeterminateDrawable(getResources().getDrawable(android.R.drawable.progress_indeterminate_horizontal));

        wv = new WebView(this);
//        rootViewLayout.addView(textView);
        rootViewLayout.addView(progressBar);
        rootViewLayout.addView(wv);
        setContentView(rootViewLayout);
        wv.getSettings().setAllowFileAccess(true);
        wv.getSettings().setJavaScriptEnabled(true);
        final String url = "http://item.m.jd.com/ware/view.action?wareId=11840656&resourceType=jdapp_share&resourceValue=Wxfriends&utm_source=iosapp&utm_medium=appshare&utm_campaign=t_335139774&utm_term=Wxfriends&from=singlemessage&isappinstalled=0";
        wv.loadUrl(url);
        wv.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                //页面下载完毕,却不代表页面渲染完毕显示出来
                //WebChromeClient中progress==100时也是一样
                if (wv.getContentHeight() != 0) {
                    //这个时候网页才显示
                }
            }
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //自身加载新链接,不做外部跳转
                view.loadUrl(url);
                return true;
            }

        });

        wv.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                //这里将textView换成你的progress来设置进度
//                        if (newProgress == 0) {
//                                        textView.setVisibility(View.VISIBLE);
//                                        progressBar.setVisibility(View.VISIBLE);
//                                }
                textView.setText(newProgress+"");
                textView.postInvalidate();
                progressBar.setProgress(newProgress);
                progressBar.postInvalidate();
//                        if (newProgress == 100) {
//                                        textView.setVisibility(View.GONE);
//                                        progressBar.setVisibility(View.GONE);
//                                }
            }
        });
    }

}
