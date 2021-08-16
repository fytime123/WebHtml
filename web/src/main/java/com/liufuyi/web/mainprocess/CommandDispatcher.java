package com.liufuyi.web.mainprocess;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.webkit.WebView;

import com.liufuyi.base.BaseApplication;
import com.liufuyi.base.GsonUtils;
import com.liufuyi.web.CommandWebToMain;
import com.liufuyi.web.JsParam;
import com.liufuyi.web.MainToJsCallback;

public class CommandDispatcher implements ServiceConnection {

    private volatile static CommandDispatcher instance;

    private CommandWebToMain commandWebToMain;

    public static CommandDispatcher getInstance() {
        if (instance == null) {
            synchronized (CommandDispatcher.class) {
                if (instance == null)
                    instance = new CommandDispatcher();
            }
        }

        return instance;
    }

    public void initAidlConnection(){
        Intent intent = new Intent(BaseApplication.application,MainProcessService.class);
        BaseApplication.application.bindService(intent,this, Context.BIND_AUTO_CREATE);
    }


    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        commandWebToMain = CommandWebToMain.Stub.asInterface(service);
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        commandWebToMain = null;
        initAidlConnection();
    }

    @Override
    public void onBindingDied(ComponentName name) {
        commandWebToMain = null;
        initAidlConnection();
    }

    public void executeCommand(final WebView webView, String params)  {
        try {
            if(commandWebToMain!=null){
                commandWebToMain.handleWebCommand(params, new MainToJsCallback.Stub(){

                    @Override
                    public void onResult(String callbackName, String response) throws RemoteException {

                        handleCallback(webView,callbackName,response);
                    }
                });
            }
        }catch (RemoteException e){
            e.printStackTrace();
        }
    }

    private void handleCallback(WebView webView,String callbackName, String response){

        webView.post(()->{
            String js = "javascript:webxjs.callback('"+callbackName+"',"+response+")";
            webView.evaluateJavascript(js,null);
        });

    }
}
