package com.demo.webview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void test1(View v){
        Intent intent = new Intent(this,CustomWebViewActivity.class);
        startActivity(intent);
    }

    public void test2(View v){
        Intent intent = new Intent(this,LayoutWebViewActivity.class);
        startActivity(intent);
    }

    public void test3(View v){
        Intent intent = new Intent(this,DynamicWebViewActivity.class);
        startActivity(intent);
    }

    public void test4(View v){
        Intent intent = new Intent(this,SafeJavaJsBridgeWebViewActivity.class);
        startActivity(intent);
    }


}
