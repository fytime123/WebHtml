package com.liufuyi.webhtml;

import com.liufuyi.base.BaseApplication;
import com.liufuyi.base.LoadSirAction;
import com.liufuyi.common.ServiceLoaderUtil;

public class TotalApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        LoadSirAction action = ServiceLoaderUtil.load(LoadSirAction.class);
        if (action != null) {
            action.loadSir();
        }
    }
}
