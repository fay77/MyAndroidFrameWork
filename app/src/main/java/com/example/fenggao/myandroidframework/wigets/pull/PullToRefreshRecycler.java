package com.example.fenggao.myandroidframework.wigets.pull;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.example.fenggao.myandroidframework.R;

/**
 * Created by feng.gao on 2017/5/23.
 */

public class PullToRefreshRecycler extends FrameLayout implements SwipeRefreshLayout.OnRefreshListener {
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    public static final int ACTION_PULL_TO_REFRESH = 1;
    public static final int ACTION_LOAD_MORE_REFRESH = 2;
    private OnRecyclerRefreshListener listener;
    private int mCurrentState;
    public PullToRefreshRecycler(@NonNull Context context) {
        super(context);
        setUpView();
    }

    public PullToRefreshRecycler(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setUpView();
    }

    public PullToRefreshRecycler(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setUpView();
    }

    private void setUpView() {
        LayoutInflater.from(getContext()).inflate(R.layout.widget_pull_to_refresh, this, true);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.mSwipeRefreshLayout);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycleView);
    }

    public void setLayoutManager(RecyclerView.LayoutManager manager) {
        mRecyclerView.setLayoutManager(manager);
    }

    public void addItemDecoration(RecyclerView.ItemDecoration decoration) {
        if (decoration != null) {
            mRecyclerView.addItemDecoration(decoration);
        }
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        mRecyclerView.setAdapter(adapter);
    }

    public void setRefreshing() {
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(true);
                onRefresh();
            }
        });
    }

    public void setOnRefreshListener(OnRecyclerRefreshListener listener) {
        this.listener = listener;
    }

    @Override
    public void onRefresh() {
        mCurrentState = ACTION_PULL_TO_REFRESH;
        listener.onRefresh(ACTION_PULL_TO_REFRESH);

    }

    public void onRefreshCompleted() {
        switch (mCurrentState) {
            case ACTION_PULL_TO_REFRESH:
                mSwipeRefreshLayout.setRefreshing(false);
                break;
            case ACTION_LOAD_MORE_REFRESH:

                break;
        }
    }

    public interface OnRecyclerRefreshListener {
        void onRefresh(int action);
    }
}
