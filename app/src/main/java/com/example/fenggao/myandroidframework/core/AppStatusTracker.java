package com.example.fenggao.myandroidframework.core;

import com.example.fenggao.myandroidframework.constants.ConstantValues;

/**
 * Created by feng.gao on 2017/5/17.
 * 单例模式保存App状态
 */

public class AppStatusTracker {
    private static AppStatusTracker sTracker;
    private int mAppStatus = ConstantValues.STATUS_FORCE_KILLED;

    private AppStatusTracker() {

    }

    public static AppStatusTracker getInstance() {
        if (sTracker == null) {
            sTracker = new AppStatusTracker();
        }
        return sTracker;
    }

    public int getAppStatus() {
        return mAppStatus;
    }

    public void setAppStatus(int appStatus) {
        mAppStatus = appStatus;
    }
}
