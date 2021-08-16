package com.liufuyi.web;

import android.content.Context;
import android.content.Intent;

import com.google.auto.service.AutoService;
import com.liufuyi.base.WebAction;

@AutoService(WebAction.class)
public class WebActionImpl implements WebAction {
    @Override
    public void openWeb(Context context, String url, boolean isCanRefresh) {

        Intent intent = new Intent(context,WebActivity.class);

        intent.putExtra(Constants.KEY_URL,url);
        intent.putExtra(Constants.KEY_REFRESH,isCanRefresh);

        context.startActivity(intent);
    }
}
