package com.example.fenggao.myandroidframework.sample;

import com.example.fenggao.myandroidframework.R;
import com.example.fenggao.myandroidframework.core.BaseActivity;

/**
 * Created by feng.gao on 2017/5/25.
 */

public class SampleListActivity1 extends BaseActivity {
    private SampleListFragment mSampleListFragment;
    @Override
    protected void setUpView() {
        mSampleListFragment = new SampleListFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.mSampleListFragmentLayout, mSampleListFragment).commit();
    }

    @Override
    protected void setUpContentView() {
        setContentView(R.layout.activity_sample_list_1 , R.string.title_recycler_fragment);
    }

    @Override
    protected void setUpData() {

    }
}
