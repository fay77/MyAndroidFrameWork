package com.example.fenggao.myandroidframework.core;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.BoolRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.fenggao.myandroidframework.R;
import com.example.fenggao.myandroidframework.constants.ConstantValues;
import com.example.fenggao.myandroidframework.sample.HomeActivity;

/**
 * Created by feng.gao on 2017/5/17.
 */

public abstract class BaseActivity extends Activity implements View.OnClickListener {
    protected Toolbar mToolbar;
    protected TextView mToolbarTv;
    public static final int MODE_BACK = 0;
    public static final int MODE_DRAWER = 1;
    public static final int MODE_NONE = 2;

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

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
//        设置默认为返回
      setContentView(layoutResID , 0 , MODE_BACK);
    }

    public void setContentView(@LayoutRes int layoutResID , int titleResId , int mode) {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbarTv = (TextView) findViewById(R.id.toolbar_title);
        if (titleResId != 0) {
            mToolbarTv.setText(titleResId);
        }
        switch (mode) {
            case MODE_BACK:
//                设置图片
//                mToolbar.setNavigationIcon();
                mToolbar.setNavigationOnClickListener(this);
                break;
            case MODE_DRAWER:
                // TODO: 2017/5/19 弹出抽屉或者隐藏

                break;
            case MODE_NONE:

                break;
        }
    }

    public void setContentView(@LayoutRes int layoutResID , int titleResId) {
      setContentView(layoutResID , titleResId , MODE_BACK);
    }

    @Override
    public void onClick(View v) {
//
    }
}
