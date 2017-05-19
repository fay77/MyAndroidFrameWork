package com.example.fenggao.myandroidframework.sample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView

import com.example.fenggao.myandroidframework.R
import com.example.fenggao.myandroidframework.core.BaseActivity
import com.example.fenggao.myandroidframework.core.CustomApplication
import kotlinx.android.synthetic.main.activity_profile.*

/**
 * Created by feng.gao on 2017/5/17.
 */

class ProfileActivity : BaseActivity() {
    override fun setUpData() {
        super.setUpData()
        setContentView(R.layout.activity_profile , R.string.app_name)

    }

}
