package com.example.sun.umstouristsapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class EvicUms extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evic_ums);

        WebView myWebView = (WebView) findViewById(R.id.webView);
        myWebView.loadUrl("http://www.ums.edu.my/v5/index.php/en/news-archives-2/4469-ums-launches-evic-and-visit-ums-2016-2017");
    }
}