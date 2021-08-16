package com.liufuyi.web;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.liufuyi.web.databinding.ActivityWebBinding;

public class WebActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       ActivityWebBinding webBinding =  DataBindingUtil.setContentView(this,R.layout.activity_web);

        Intent intent = getIntent();
        String url = intent.getStringExtra(Constants.KEY_URL);
        boolean isCanRefresh = intent.getBooleanExtra(Constants.KEY_REFRESH,false);
        Fragment fragmentWebView = FragmentWebView.getInstance(url,isCanRefresh);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment,fragmentWebView).commit();

        Log.v("liufuyi","=======>WebActivity");

    }

    public void setUpdateTitle(String title){

        setTitle(title);
    }
}