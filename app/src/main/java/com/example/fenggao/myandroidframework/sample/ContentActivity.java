package com.example.fenggao.myandroidframework.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.example.fenggao.myandroidframework.R;
import com.example.fenggao.myandroidframework.core.BaseActivity;

/**
 * Created by feng.gao on 2017/5/24.
 */

public class ContentActivity extends BaseActivity {
    private TextView tv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setUpView() {
        tv = (TextView) findViewById(R.id.tv);
    }

    @Override
    protected void setUpContentView() {
        setContentView(R.layout.activity_profile , R.string.profile_title);

    }


    @Override
    protected void setUpData() {
        tv.setText("Profile");

    }
}
