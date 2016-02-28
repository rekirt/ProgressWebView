package com.demo.webview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by lc on 16-2-27.
 */
public class CustomWebViewActivity extends AppCompatActivity {
    ProgressWebView pw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_webview);
        pw= (ProgressWebView)findViewById(R.id.pw);
        pw.getSettings().setJavaScriptEnabled(true);
//        pw.setDownloadListener(new DownloadListener() {
//            @Override
//            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
//                if (url != null && url.startsWith("http://"))
//                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
//            }
//        });
        pw.loadUrl("http://item.m.jd.com/ware/view.action?wareId=11840656&resourceType=jdapp_share&resourceValue=Wxfriends&utm_source=iosapp&utm_medium=appshare&utm_campaign=t_335139774&utm_term=Wxfriends&from=singlemessage&isappinstalled=0");
    }

    @Override
    public void onBackPressed() {
        if(pw.canGoBack()){
            pw.goBack();
        }else{
            super.onBackPressed();
        }
    }
}
