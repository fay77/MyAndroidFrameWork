package com.example.fenggao.myandroidframework.sample;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.example.fenggao.myandroidframework.R;
import com.example.fenggao.myandroidframework.constants.ConstantValues;
import com.example.fenggao.myandroidframework.core.AppStatusTracker;
import com.example.fenggao.myandroidframework.core.BaseActivity;
import com.example.fenggao.myandroidframework.core.CustomApplication;

/**
 * Created by feng.gao on 2017/5/17.
 */

public class WelcomeActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AppStatusTracker.getInstance().setAppStatus(ConstantValues.STATUS_OFFLINE);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setUpView() {

    }

    @Override
    protected void setUpContentView() {
        setContentView(R.layout.activity_welcome ,-1 , MODE_NONE);
    }

    @Override
    protected void setUpData() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
                finish();
            }
        }, 1000);
    }
}
