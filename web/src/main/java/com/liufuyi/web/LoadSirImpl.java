package com.liufuyi.web;


import com.google.auto.service.AutoService;
import com.kingja.loadsir.core.LoadSir;
import com.liufuyi.base.LoadSirAction;
import com.liufuyi.web.callback.CustomCallback;
import com.liufuyi.web.callback.EmptyCallback;
import com.liufuyi.web.callback.ErrorCallback;
import com.liufuyi.web.callback.LoadingCallback;
import com.liufuyi.web.callback.TimeoutCallback;

@AutoService({LoadSirAction.class})
public class LoadSirImpl implements LoadSirAction {
    @Override
    public void loadSir() {
        LoadSir.beginBuilder()
                .addCallback(new ErrorCallback())//添加各种状态页
                .addCallback(new EmptyCallback())
                .addCallback(new LoadingCallback())
                .addCallback(new TimeoutCallback())
                .addCallback(new CustomCallback())
                .setDefaultCallback(LoadingCallback.class)//设置默认状态页
                .commit();
    }
}
