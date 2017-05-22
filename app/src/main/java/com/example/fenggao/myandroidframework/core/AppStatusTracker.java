package com.example.fenggao.myandroidframework.core;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.example.fenggao.myandroidframework.constants.ConstantValues;
import com.example.fenggao.myandroidframework.utils.L;

/**
 * Created by feng.gao on 2017/5/17.
 * 单例模式保存App状态
 */

public class AppStatusTracker implements Application.ActivityLifecycleCallbacks {
    private static AppStatusTracker sTracker;
    private int mAppStatus = ConstantValues.STATUS_FORCE_KILLED;
    public static final long MAX_INTERVAL = 5 * 60 * 1000;
    private boolean isForgroung; //判断是在应用内还是应用外
    private int activeCount;
    private long timestamp;
    private boolean isScreenOff;

    private AppStatusTracker(Application application) {
        application.registerActivityLifecycleCallbacks(this);
    }

    public static void init(Application application) {
        sTracker = new AppStatusTracker(application);
    }

    public static AppStatusTracker getInstance() {
        return sTracker;
    }

    public boolean checkIfShowGesture() {
        if (mAppStatus == ConstantValues.STATUS_ONLINE) {
            if (isScreenOff) {
                return true;
            }
            if (System.currentTimeMillis() - timestamp > MAX_INTERVAL) {
                return true;
            }
        }
        return false;
    }

    public int getAppStatus() {
        return mAppStatus;
    }

    public void setAppStatus(int appStatus) {
        mAppStatus = appStatus;
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        L.e(activity.toString() + "onActivityCreated");

    }

    @Override
    public void onActivityStarted(Activity activity) {
        L.e(activity.toString() + "onActivityStarted");
//        每次调用都说明可见的activity又多了一个
        activeCount++;
    }

    @Override
    public void onActivityResumed(Activity activity) {
        L.e(activity.toString() + "onActivityResumed");
        isForgroung = true;
        timestamp = 0l;
        isScreenOff = false;
    }

    @Override
    public void onActivityPaused(Activity activity) {
        L.e(activity.toString() + "onActivityPaused");

    }

    @Override
    public void onActivityStopped(Activity activity) {
        L.e(activity.toString() + "onActivityStopped");
//        每次调用说明可见的activity又少了一个，直到0，说明应用在后台
        activeCount--;
        if (activeCount == 0) {
            isForgroung = false;
            timestamp = System.currentTimeMillis();
        }
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        L.e(activity.toString() + "onActivityDestroyed");

    }
}
