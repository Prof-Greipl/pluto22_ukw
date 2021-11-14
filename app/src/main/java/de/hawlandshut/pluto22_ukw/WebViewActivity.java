package de.hawlandshut.pluto22_ukw;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class WebViewActivity extends AppCompatActivity {

    WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        WebView mWebView = (WebView) findViewById(R.id.webview);
//        mWebView.loadUrl("https://archima-server.firebaseapp.com/staticpages/about.html");
        mWebView.loadUrl("https://www.haw-landshut.de");

    }
}