package com.liufuyi.web.common;

import android.util.Log;
import android.webkit.ConsoleMessage;
import android.webkit.JsPromptResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

public class CommonWebChromeClient extends WebChromeClient {


    private WebTitleCallback webTitleCallback;

    public CommonWebChromeClient(WebTitleCallback webTitleCallback){
        this.webTitleCallback = webTitleCallback;
    }

    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        super.onProgressChanged(view, newProgress);
    }

    @Override
    public void onReceivedTitle(WebView view, String title) {
        super.onReceivedTitle(view, title);
        if(webTitleCallback!=null){
            webTitleCallback.onReceivedTitle(view, title);
        }
    }

    @Override
    public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
        Log.v("liufuyi1",consoleMessage.message()+"  lineNumber="+consoleMessage.lineNumber());
        return super.onConsoleMessage(consoleMessage);
    }

    @Override
    public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
        return super.onJsPrompt(view, url, message, defaultValue, result);
    }
}
