package com.example.fenggao.myandroidframework.sample;

import android.content.Intent;
import android.view.MenuItem;
import android.view.View;

import com.example.fenggao.myandroidframework.R;
import com.example.fenggao.myandroidframework.constants.ConstantValues;
import com.example.fenggao.myandroidframework.core.BaseActivity;
import com.example.fenggao.myandroidframework.core.CustomApplication;

import java.util.ArrayList;

/**
 * Created by feng.gao on 2017/5/17.
 */

public class HomeActivity extends BaseActivity {

    @Override
    protected void setUpData() {
        super.setUpData();
        setContentView(R.layout.activity_main , R.string.app_name , R.menu.menu_home , MODE_HOME);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, ProfileActivity.class));
            }
        });
    }

    @Override
    protected void protectApp() {
        startActivity(new Intent(this, WelcomeActivity.class));
        finish();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent.getStringExtra(ConstantValues.KEY_HOME_ACTION).equals(ConstantValues.ACTION_BACK_TO_HOME)) {
            startActivity(new Intent(this, WelcomeActivity.class));
            finish();
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return super.onMenuItemClick(item);
    }
}
