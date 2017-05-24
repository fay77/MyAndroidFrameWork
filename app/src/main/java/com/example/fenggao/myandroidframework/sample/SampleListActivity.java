package com.example.fenggao.myandroidframework.sample;

import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.fenggao.myandroidframework.R;
import com.example.fenggao.myandroidframework.core.BaseListActivity;
import com.example.fenggao.myandroidframework.core.BaseViewHolder;
import com.example.fenggao.myandroidframework.wigets.pull.ILayoutManager;
import com.example.fenggao.myandroidframework.wigets.pull.MyGridLayoutManager;
import com.example.fenggao.myandroidframework.wigets.pull.MyLinearLayoutManager;
import com.example.fenggao.myandroidframework.wigets.pull.MyStaggeredGridLayoutManager;
import com.example.fenggao.myandroidframework.wigets.pull.PullToRefreshRecycler;

import java.util.ArrayList;

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
        if (mData == null) {
            mData = new ArrayList<>();
        }
        if (action == PullToRefreshRecycler.ACTION_PULL_TO_REFRESH) {
            mData.clear();
        }
        int size = mData.size();
        for (int i = size; i < size + 20 ; i++) {
            mData.add("Sample list item" + i);
        }
        mBaseListAdapter.notifyDataSetChanged();
        mRecycler.onRefreshCompleted();
        if (mData.size() < 100) {
            mRecycler.enableLoadMore(true);
        } else {
            mRecycler.enableLoadMore(false);
        }
    }

    @Override
    protected ILayoutManager getLayoutManager() {
        return new MyStaggeredGridLayoutManager(3 , MyStaggeredGridLayoutManager.VERTICAL);
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
