package com.example.fenggao.myandroidframework.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.fenggao.myandroidframework.R;
import com.example.fenggao.myandroidframework.core.BaseListFragment;
import com.example.fenggao.myandroidframework.core.BaseViewHolder;
import com.example.fenggao.myandroidframework.wigets.pull.ILayoutManager;
import com.example.fenggao.myandroidframework.wigets.pull.MyGridLayoutManager;
import com.example.fenggao.myandroidframework.wigets.pull.PullToRefreshRecycler;

import java.util.ArrayList;

/**
 * Created by feng.gao on 2017/5/25.
 */

public class SampleListFragment extends BaseListFragment<String> {

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecycler.setRefreshing(); //手动去刷新数据
    }

    /**
     * 返回null，不需要间隔线
     * @return
     */
    @Override
    protected RecyclerView.ItemDecoration getItemDecoration() {
        return null;
    }

    @Override
    protected BaseViewHolder getViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.activity_sample_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    protected ILayoutManager getLayoutManager() {
        return new MyGridLayoutManager(getContext(), 3);
    }

    @Override
    public void onRefresh(final int action) {
        if (mDataList == null) {
            mDataList = new ArrayList<>();
        }
        mRecycler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (action == PullToRefreshRecycler.ACTION_PULL_TO_REFRESH) {
                    mDataList.clear();
                }
                int size = mDataList.size();
                for (int i = size; i < size + 20 ; i++) {
                    mDataList.add("Sample list item" + i);
                }
                mAdapter.notifyDataSetChanged();
                mRecycler.onRefreshCompleted();
                if (mDataList.size() < 100) {
                    mRecycler.enableLoadMore(true);
                } else {
                    mRecycler.enableLoadMore(false);
                }
            }
        }, 3000);

    }

    public class ViewHolder extends BaseViewHolder {
        private TextView mSampleListItemLabel;

        public ViewHolder(View itemView) {
            super(itemView);
            mSampleListItemLabel = (TextView) itemView.findViewById(R.id.mSampleListItemLabel);
        }

        @Override
        public void onItemClick(View v, int adapterPosition) {

        }

        @Override
        public void onBindViewHolder(int position) {
            mSampleListItemLabel.setText(mDataList.get(position));
        }
    }
}
