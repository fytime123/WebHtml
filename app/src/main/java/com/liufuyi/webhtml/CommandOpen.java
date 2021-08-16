package com.liufuyi.webhtml;

import android.content.Intent;

import com.google.auto.service.AutoService;
import com.liufuyi.base.BaseApplication;
import com.liufuyi.base.GsonUtils;
import com.liufuyi.web.Command;
import com.liufuyi.web.JsParam;
import com.liufuyi.web.MainToJsCallback;

import org.json.JSONException;


@AutoService(Command.class)
public class CommandOpen implements Command {
    @Override
    public String getName() {
        return "openPage";
    }

    @Override
    public void execute(String params, MainToJsCallback callback) {
        try {
            JsParam request = GsonUtils.fromJson(params,JsParam.class);
            Class clazz = Class.forName(request.getParam().toString());
            Intent intent = new Intent(BaseApplication.application, clazz);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            BaseApplication.application.startActivity(intent);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
