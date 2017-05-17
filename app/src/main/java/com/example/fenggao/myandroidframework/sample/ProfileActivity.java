package com.example.fenggao.myandroidframework.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.fenggao.myandroidframework.R;
import com.example.fenggao.myandroidframework.core.BaseActivity;
import com.example.fenggao.myandroidframework.core.CustomApplication;

/**
 * Created by feng.gao on 2017/5/17.
 */

public class ProfileActivity extends BaseActivity {
    private TextView mTextView;

    @Override
    protected void setUpData() {
        super.setUpData();
        setContentView(R.layout.activity_profile);
        mTextView = (TextView) findViewById(R.id.tv);
        mTextView.setText(CustomApplication.mTestNullPointers.toString());
    }
}
