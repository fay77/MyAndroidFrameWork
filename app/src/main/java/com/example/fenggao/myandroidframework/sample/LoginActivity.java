package com.example.fenggao.myandroidframework.sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.fenggao.myandroidframework.R;
import com.example.fenggao.myandroidframework.core.BaseActivity;

/**
 * Created by feng.gao on 2017/5/17.
 */

public class LoginActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setUpData() {
        super.setUpData();
        setContentView(R.layout.activity_login);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                finish();
            }
        });
    }
}
