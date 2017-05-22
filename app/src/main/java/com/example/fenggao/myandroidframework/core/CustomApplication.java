package com.example.fenggao.myandroidframework.core;

import android.app.Application;

import java.util.ArrayList;

/**
 * Created by feng.gao on 2017/5/17.
 */

public class CustomApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AppStatusTracker.init(this);
    }
}
