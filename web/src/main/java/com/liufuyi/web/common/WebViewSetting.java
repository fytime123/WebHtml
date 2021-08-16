package com.liufuyi.web.common;

import android.os.Build;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class WebViewSetting {

    private volatile static WebViewSetting instance;

    public static WebViewSetting getInstance(){

        if(instance==null){
            synchronized (WebViewSetting.class){
                if(instance==null)
                    instance = new WebViewSetting();
            }
        }
        return instance;
    }

    public void setSetting(WebView webView){

        WebSettings set = webView.getSettings();

        set.setSavePassword(false);
        set.setJavaScriptEnabled(true);
        set.setAllowFileAccess(true);
        set.setAllowFileAccessFromFileURLs(false);
        set.setAllowUniversalAccessFromFileURLs(false);

        set.setLoadsImagesAutomatically(true);
        set.setBlockNetworkImage(false);//解决图片不显示
        //set.setJavaScriptCanOpenWindowsAutomatically(true);


        set.setDefaultTextEncodingName("UTF-8");
        set.setCacheMode(WebSettings.LOAD_NO_CACHE);
        set.setDomStorageEnabled(true);
        set.setDatabaseEnabled(false);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            set.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }

    }


}
