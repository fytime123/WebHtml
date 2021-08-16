package com.liufuyi.webhtml;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.liufuyi.base.WebAction;
import com.liufuyi.common.ServiceLoaderUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.testTextView).setOnClickListener(view -> {

            WebAction webAction = ServiceLoaderUtil.load(WebAction.class);
            if (webAction != null)
                webAction.openWeb(this, "file:///android_asset/demo.html", true);//"file:///android_asset/demo.html"

        });
    }
}