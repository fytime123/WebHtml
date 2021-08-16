package com.liufuyi.webhtml;

import android.os.Handler;
import android.os.Looper;
import android.os.RemoteException;

import androidx.appcompat.app.AppCompatActivity;

import com.google.auto.service.AutoService;
import com.liufuyi.base.GsonUtils;
import com.liufuyi.base.LoginAction;
import com.liufuyi.base.LoginEvent;
import com.liufuyi.common.ServiceLoaderUtil;
import com.liufuyi.web.Command;
import com.liufuyi.web.JsParam;
import com.liufuyi.web.MainToJsCallback;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ServiceLoader;

@AutoService(Command.class)
public class CommandLogin implements Command {

    private MainToJsCallback callback;
    private JsParam request;

    public CommandLogin(){
        EventBus.getDefault().register(this);
    }

    @Override
    public String getName() {
        return "login";
    }

    @Override
    public void execute(final String params, MainToJsCallback callback) {
        request = GsonUtils.fromJson(params,JsParam.class);
        this.callback = callback;
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(()->{
            LoginAction action = ServiceLoaderUtil.load(LoginAction.class);
            //LoginAction action = ServiceLoader.load(LoginAction.class).iterator().next();
            if(action!=null){
                action.login(params);
            }
        });


    }

    @Subscribe
    public void doCallback(LoginEvent event)  {
        try {
            callback.onResult(request.getCallbackName(),GsonUtils.toJson(event));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
