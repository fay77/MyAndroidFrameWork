package com.example.fenggao.myandroidframework.wigets.pull;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.fenggao.myandroidframework.core.BaseListActivity;

/**
 * Created by feng.gao on 2017/5/24.
 * 用于将gridview多列合并成一行
 */

public class FootSpanSizeLookup extends GridLayoutManager.SpanSizeLookup {
    private BaseListActivity.BaseListAdapter mAdapter;
    private int mSpanCount;

    public FootSpanSizeLookup(BaseListActivity.BaseListAdapter adapter, int spanCount) {
        mAdapter = adapter;
        mSpanCount = spanCount;
    }

    @Override
    public int getSpanSize(int position) {
        if (mAdapter.isFooterView(position)) {
            return mSpanCount;
        }
        return 1;
    }
}
