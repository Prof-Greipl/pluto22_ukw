package de.hawlandshut.pluto22_ukw;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Base64;
import android.webkit.WebView;

public class WebViewActivity extends AppCompatActivity {

    WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        WebView mWebView = (WebView) findViewById(R.id.webview);
        
//        mWebView.loadUrl("https://archima-server.firebaseapp.com/staticpages/about.html");
        // mWebView.loadUrl("https://www.haw-landshut.de");
        String unencodedHtml =
                "<html><body>'%23' is the percent code for ‘#‘ </body></html>";
        String encodedHtml = Base64.encodeToString(unencodedHtml.getBytes(),
                Base64.NO_PADDING);
        mWebView.loadData(encodedHtml, "text/html", "base64");

    }
}