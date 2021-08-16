package com.liufuyi.web.common;

import android.graphics.Bitmap;
import android.os.Build;
import android.util.Log;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.RequiresApi;

public class CommonWebClient extends WebViewClient {

    private WebCallback webCallback;

    public CommonWebClient(WebCallback webCallback) {
        this.webCallback = webCallback;
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        if (webCallback != null) webCallback.onPageStarted(view, url, favicon);
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        if (webCallback != null) webCallback.onPageFinished(view, url);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
        super.onReceivedError(view, request, error);

        Log.v("liufuyi1","onReceivedError="+error.getDescription().toString()+"  "+request.getUrl());
        if (webCallback != null) webCallback.onReceivedError(view, request, error);
    }
}
