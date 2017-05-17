package com.example.fenggao.myandroidframework.core;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.fenggao.myandroidframework.constants.ConstantValues;
import com.example.fenggao.myandroidframework.sample.HomeActivity;

/**
 * Created by feng.gao on 2017/5/17.
 */

public class BaseActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        switch (AppStatusTracker.getInstance().getAppStatus()) {
            case ConstantValues.STATUS_FORCE_KILLED:
                //代表应用被强杀
                protectApp();
                break;
            case ConstantValues.STATUS_KICK_OUT:
                kickOut();
                break;
            case ConstantValues.STATUS_LOGOUT:
            case ConstantValues.STATUS_ONLINE:
            case ConstantValues.STATUS_OFFLINE:
                setUpData();
                break;
        }
    }

    protected void kickOut() {
        // TODO: 2017/5/17 show dialog to confirm
        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra(ConstantValues.KEY_HOME_ACTION, ConstantValues.ACTION_KICK_OUT);
        startActivity(intent);
    }

    protected void setUpData() {

    }

    protected void protectApp() {
        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra(ConstantValues.KEY_HOME_ACTION, ConstantValues.ACTION_BACK_TO_HOME);
        startActivity(intent);

    }
}
