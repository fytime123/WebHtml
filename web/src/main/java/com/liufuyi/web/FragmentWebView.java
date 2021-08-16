package com.liufuyi.web;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;
import com.liufuyi.web.callback.ErrorCallback;
import com.liufuyi.web.callback.LoadingCallback;
import com.liufuyi.web.common.CommonWebChromeClient;
import com.liufuyi.web.common.CommonWebClient;
import com.liufuyi.web.common.WebCallback;
import com.liufuyi.web.databinding.FrameWebLayoutBinding;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

public class FragmentWebView extends Fragment implements WebCallback {


    private String url;
    private FrameWebLayoutBinding dataBinding;
    private LoadService loadService;

    private boolean isLoadError;
    private boolean isCanRefresh;


    public static FragmentWebView getInstance(String url, boolean isCanRefresh) {
        Bundle bundle = new Bundle();
        bundle.putString(Constants.KEY_URL, url);
        bundle.putBoolean(Constants.KEY_REFRESH, isCanRefresh);

        FragmentWebView fragmentWebView = new FragmentWebView();
        fragmentWebView.setArguments(bundle);

        return fragmentWebView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null) {
            url = bundle.getString(Constants.KEY_URL);
            isCanRefresh = bundle.getBoolean(Constants.KEY_REFRESH);
        }

        Log.v("liufuyi1","===>url="+url);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        dataBinding = DataBindingUtil.inflate(inflater, R.layout.frame_web_layout, container, false);

        loadService = LoadSir.getDefault().register(dataBinding.smartRefreshLayout, new Callback.OnReloadListener() {
            @Override
            public void onReload(View v) {
                // 重新加载逻辑
                loadService.showCallback(LoadingCallback.class);
                dataBinding.commonWebView.reload();
            }
        });

        dataBinding.smartRefreshLayout.setEnableRefresh(isCanRefresh);
        dataBinding.smartRefreshLayout.setEnableLoadMore(false);
        dataBinding.smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                dataBinding.commonWebView.reload();
            }
        });
        dataBinding.commonWebView.registerWebViewCallback(this);
        dataBinding.commonWebView.loadUrl(url);

        Log.v("liufuyi1","url="+url);
        return loadService.getLoadLayout();
    }


    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        if (loadService != null) {
            loadService.showCallback(LoadingCallback.class);
        }
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        if (isLoadError) {
            dataBinding.smartRefreshLayout.setEnableRefresh(true);
        } else {
            dataBinding.smartRefreshLayout.setEnableRefresh(isCanRefresh);
        }
        //dataBinding.smartRefreshLayout.finishRefresh();
        dataBinding.smartRefreshLayout.finishRefresh(true);

        if (loadService != null) {
            if (isLoadError) {
                loadService.showCallback(ErrorCallback.class);
            } else {
                loadService.showSuccess();
            }
        }

        isLoadError = false;
    }

    @Override
    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
        isLoadError = true;
        dataBinding.smartRefreshLayout.setEnableRefresh(true);
        dataBinding.smartRefreshLayout.finishRefresh();
        loadService.showCallback(ErrorCallback.class);
    }

    @Override
    public void onReceivedTitle(WebView view, String title) {
        Activity activity = getActivity();
        if (activity instanceof WebActivity) {
            ((WebActivity) activity).setUpdateTitle(title);
        }
    }
}
