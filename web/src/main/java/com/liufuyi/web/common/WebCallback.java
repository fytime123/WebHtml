package com.liufuyi.web.common;

import android.graphics.Bitmap;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;

public interface WebCallback extends WebTitleCallback{

    void onPageStarted(WebView view, String url, Bitmap favicon);

    void onPageFinished(WebView view, String url);

    void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error);
}
