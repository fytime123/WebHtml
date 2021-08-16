package com.liufuyi.web.common;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.liufuyi.base.GsonUtils;
import com.liufuyi.web.JsParam;
import com.liufuyi.web.mainprocess.CommandDispatcher;

public class CommonWebView extends WebView {

    public CommonWebView(@NonNull  Context context) {
        super(context);
        init();
    }

    public CommonWebView(@NonNull  Context context, @Nullable  AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CommonWebView(@NonNull  Context context, @Nullable  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public CommonWebView(@NonNull  Context context, @Nullable  AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init(){
        WebViewSetting.getInstance().setSetting(this);

        addJavascriptInterface(this,"webxPlugin");
        CommandDispatcher.getInstance().initAidlConnection();
    }

    public void registerWebViewCallback(WebCallback callback){
        setWebViewClient(new CommonWebClient(callback));
        setWebChromeClient(new CommonWebChromeClient(callback));
    }


    @JavascriptInterface
    public void doNativeAction(String jsParams){

        Log.v("liufuyi1",jsParams);

        if( jsParams!=null) {
            CommandDispatcher.getInstance().executeCommand(this,jsParams);
        }
    }

}
