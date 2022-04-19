package com.agya.dhanoa.flight_track;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Display_Data extends AppCompatActivity {
public WebView webView;
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_data);
        webView =  findViewById(R.id.webview);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        Intent intent = getIntent();
        String data;
        data=  intent.getStringExtra("Search");

        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://www.google.com/search?q="+data);

    }
}