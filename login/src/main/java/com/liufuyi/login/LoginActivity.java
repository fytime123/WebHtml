package com.liufuyi.login;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.liufuyi.base.LoginEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.UUID;


public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView textView = findViewById(R.id.testTextView);
        textView.setOnClickListener(view->{
            LoginEvent event = new LoginEvent();
            event.setName("liufuyi");
            event.setToken(UUID.randomUUID().toString());
            EventBus.getDefault().post(event);
            finish();
        });
    }
}
