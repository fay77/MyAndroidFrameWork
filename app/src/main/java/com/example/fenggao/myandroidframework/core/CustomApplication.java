package com.example.fenggao.myandroidframework.core;

import android.app.Application;

import java.util.ArrayList;

/**
 * Created by feng.gao on 2017/5/17.
 */

public class CustomApplication extends Application {
    public static ArrayList<String> mTestNullPointers;

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
