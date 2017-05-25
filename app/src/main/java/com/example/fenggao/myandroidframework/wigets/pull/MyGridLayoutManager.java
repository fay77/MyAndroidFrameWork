package com.example.fenggao.myandroidframework.wigets.pull;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by feng.gao on 2017/5/24.
 */

public class MyGridLayoutManager extends GridLayoutManager implements ILayoutManager {
    public MyGridLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public MyGridLayoutManager(Context context, int spanCount) {
        super(context, spanCount);
    }

    public MyGridLayoutManager(Context context, int spanCount, int orientation, boolean reverseLayout) {
        super(context, spanCount, orientation, reverseLayout);
    }

    @Override
    public int findLastVisiblePosition() {
        return findLastVisibleItemPosition();
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return this;
    }

    @Override
    public void setUpAdapter(BaseListAdapter adapter) {
        FootSpanSizeLookup lookup = new FootSpanSizeLookup(adapter, getSpanCount());
        setSpanSizeLookup(lookup);
    }
}
