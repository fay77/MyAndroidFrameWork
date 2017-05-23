package com.example.fenggao.myandroidframework.sample;

import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.fenggao.myandroidframework.R;
import com.example.fenggao.myandroidframework.core.BaseListActivity;
import com.example.fenggao.myandroidframework.core.BaseViewHolder;

/**
 * Created by feng.gao on 2017/5/22.
 */

public class SampleListActivity extends BaseListActivity<String>  {

    @Override
    protected void setUpTitle(int titleResId) {
        super.setUpTitle(R.string.sample_list_title);
    }

    @Override
    public void onRefresh(int action) {
        mData.clear();
        for (int i = 0; i < 50; i++) {
            mData.add("Sample list item" + i);
        }
        mRecycler.onRefreshCompleted();
    }

    @Override
    protected BaseViewHolder getViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_sample_list_item, parent ,false);
        return new SampleViewHolder(view);
    }


    private class SampleViewHolder extends BaseViewHolder {
        TextView mTextView;

        private SampleViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.mSampleListItemLabel);
        }

        @Override
        public void onBindViewHolder(int position) {
            mTextView.setText(mData.get(position));
        }
    }
}
