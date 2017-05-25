package com.example.fenggao.myandroidframework.core;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.BoolRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.fenggao.myandroidframework.R;
import com.example.fenggao.myandroidframework.constants.ConstantValues;
import com.example.fenggao.myandroidframework.sample.HomeActivity;
import com.example.fenggao.myandroidframework.utils.L;

/**
 * Created by feng.gao on 2017/5/17.
 */

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener, Toolbar.OnMenuItemClickListener {
    protected Toolbar mToolbar;
    protected TextView mToolbarTv;
    public static final int MODE_BACK = 0;
    public static final int MODE_DRAWER = 1;
    public static final int MODE_NONE = 2;
    public static final int MODE_HOME = 3;

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
                setUpContentView();
                setUpView();
                setUpData();
                break;
        }
    }

    protected abstract void setUpView();

    protected abstract void setUpContentView();

    protected abstract void setUpData();


    protected void kickOut() {
        // TODO: 2017/5/17 show dialog to confirm
        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra(ConstantValues.KEY_HOME_ACTION, ConstantValues.ACTION_KICK_OUT);
        startActivity(intent);
    }


    protected void protectApp() {
        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra(ConstantValues.KEY_HOME_ACTION, ConstantValues.ACTION_BACK_TO_HOME);
        startActivity(intent);

    }

//    一个参数
    @Override
    public void setContentView(@LayoutRes int layoutResID) {
//        设置默认为返回
      setContentView(layoutResID , -1 , -1 ,  MODE_BACK);
    }

//    两个参数

    public void setContentView(@LayoutRes int layoutResID , int titleResId) {
      setContentView(layoutResID , titleResId , MODE_BACK);
    }


//三个参数
    public void setContentView(@LayoutRes int layoutResID , int titleResId , int mode) {
        setContentView(layoutResID , titleResId , -1 , mode);
    }

//    最终调用这个4参数的

    public void setContentView(@LayoutRes int layoutResID , int titleResId , int menuId , int mode) {
        super.setContentView(layoutResID);
        setUpToolbar(titleResId, menuId, mode);
    }

    @Override
    public void onClick(View v) {
//
    }

    protected void setUpToolbar(int titleResId , int menuId , int mode) {
        if (mode != MODE_NONE) {
            mToolbar = (Toolbar) findViewById(R.id.toolbar);
            mToolbarTv = (TextView) findViewById(R.id.toolbar_title);

            if (mode == MODE_BACK) {
                mToolbar.setNavigationIcon(R.drawable.img_back);
            }
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onNavigationBtnClicked();
                }


            });
        }
        setUpTitle(titleResId);
        setUpMenu(menuId);
    }

    protected void onNavigationBtnClicked() {
        finish();
    }

    protected void setUpMenu(int menuId) {
        if (mToolbar != null) {
            mToolbar.getMenu().clear();
            if (menuId > 0) {
                mToolbar.inflateMenu(menuId);
                mToolbar.setOnMenuItemClickListener(this);
            }
        }
    }

    protected void setUpTitle(int titleResId) {
        if (titleResId > 0 && mToolbarTv != null) {
            mToolbarTv.setText(titleResId);
        }
    }

    @Override
    protected void onStart() {
        if (AppStatusTracker.getInstance().checkIfShowGesture()) {
//            需要展示锁屏
            L.d("need show gesture");
        }
        super.onStart();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }
}
