package com.example.fenggao.myandroidframework.utils;

import android.util.Log;

import com.example.fenggao.myandroidframework.BuildConfig;

/**
 * Created by feng.gao on 2017/5/22.
 */

public class L {
    private static final String TAG = "gaofeng";
    public static boolean DEBUG = BuildConfig.DEBUG;

    public static void d(String msg) {
        if (DEBUG) {
            Log.d(TAG, msg);
        }
    }

    public static void e(String msg) {
        if (DEBUG) {
            Log.e(TAG, msg);
        }
    }
}
