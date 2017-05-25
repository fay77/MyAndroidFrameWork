package com.example.fenggao.myandroidframework.sample;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.fenggao.myandroidframework.R;
import com.example.fenggao.myandroidframework.constants.ConstantValues;
import com.example.fenggao.myandroidframework.core.BaseActivity;
import com.example.fenggao.myandroidframework.core.BaseListActivity;
import com.example.fenggao.myandroidframework.core.BaseViewHolder;
import com.example.fenggao.myandroidframework.model.Module;

import java.util.ArrayList;

/**
 * Created by feng.gao on 2017/5/17.
 */

public class HomeActivity extends BaseListActivity<Module> {

    @Override
    protected void setUpTitle(int titleResId) {
        super.setUpTitle(R.string.title_framework_main);
    }

    @Override
    protected void setUpMenu(int menuId) {
        super.setUpMenu(R.menu.menu_home);
    }

    @Override
    protected void setUpData() {
        super.setUpData();
        mRecycler.enablePullToRefresh(false);
        mRecycler.setRefreshing();
    }

    /**
     * 加载数据
     * @param action
     */
    @Override
    public void onRefresh(int action) {
        mData = new ArrayList<>();
        mData.add(new Module("RecycleView基于BaseListActivity\n支持下拉刷新，加载更多", SampleListActivity.class));
        mData.add(new Module("RecycleView基于BaseListFragment\n支持下拉刷新，加载更多", SampleListActivity1.class));
        mRecycler.onRefreshCompleted();

    }

    @Override
    protected BaseViewHolder getViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this).inflate(R.layout.activity_home_item, parent, false);
        return new ViewHolder(view);
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
        switch (item.getItemId()) {
            case R.id.test1:
                startActivity(new Intent(this, SampleListActivity.class));
                break;
        }
        return true;
    }

    public class ViewHolder extends BaseViewHolder {
        private TextView mTextView;
        public ViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.mHomeItemModuleLabel);
        }

        @Override
        public void onItemClick(View v, int adapterPosition) {
            startActivity(new Intent(HomeActivity.this, mData.get(adapterPosition).moduleTargetClass));
        }

        @Override
        public void onBindViewHolder(int position) {
            mTextView.setText(mData.get(position).moduleName);
        }
    }

}
