package com.liufuyi.login;

import android.content.Intent;

import com.google.auto.service.AutoService;
import com.liufuyi.base.BaseApplication;
import com.liufuyi.base.LoginAction;

@AutoService({LoginAction.class})
public class LoginService implements LoginAction {
    @Override
    public void login(String request) {

        Intent intent = new Intent(BaseApplication.application,LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        BaseApplication.application.startActivity(intent);
    }
}
