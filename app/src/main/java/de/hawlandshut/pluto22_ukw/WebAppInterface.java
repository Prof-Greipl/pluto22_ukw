package de.hawlandshut.pluto22_ukw;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

public class WebAppInterface {
    Context mContext;

    /** Instantiate the interface and set the context */
    WebAppInterface(Context c) {
        mContext = c;
    }

    /** Show a toast from the web page */
    @JavascriptInterface
    public void showAppVersion() {
        Toast.makeText(mContext, "You run version 5", Toast.LENGTH_SHORT).show();
    }
}