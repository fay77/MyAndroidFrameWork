package com.example.fenggao.myandroidframework.wigets.pull;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.example.fenggao.myandroidframework.R;
import com.example.fenggao.myandroidframework.core.BaseListActivity;

/**
 * Created by feng.gao on 2017/5/23.
 */

public class PullToRefreshRecycler extends FrameLayout implements SwipeRefreshLayout.OnRefreshListener {
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    public static final int ACTION_PULL_TO_REFRESH = 1;//下拉刷新状态
    public static final int ACTION_LOAD_MORE_REFRESH = 2; //加载更多状态
    public static final int ACTION_IDEL = 0; //空闲状态
    private OnRecyclerRefreshListener listener;
    private int mCurrentState = ACTION_IDEL; //默认置为空闲状态
    private boolean isLoadMoreEnabled = false; //是否支持加载更多
    private boolean isPullToRefreshEnable = true; //是否支持下拉刷新
    private ILayoutManager mILayoutManager;
    private BaseListAdapter adapter;

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
//        加载更多核心思想就是判断达到屏幕底端的时候去加载
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
//                在这里进行判断加载更多，判断依据 1. 当前状态是空闲  2. 当前控件支持加载更多  3.判断当前页面是否能够加载更多
                if (mCurrentState == ACTION_IDEL && isLoadMoreEnabled && checkIfLoadMore()) {
                    //进入判断，执行我们加载更多的逻辑, 回调给上层现在可以加载更多了 , 改变当前状态 , 并且正在加载更多的时候是不允许下拉刷新的
                    mCurrentState = ACTION_LOAD_MORE_REFRESH;
                    //在这里显示底部加载更多的view
                    adapter.onLoadMoreStateChanged(true);
                    mSwipeRefreshLayout.setEnabled(false);//禁止下拉刷新 ， 这行如果不写即使不会执行逻辑但是下拉刷新的动画还是会有。
                    listener.onRefresh(ACTION_LOAD_MORE_REFRESH);


                }
            }
        });
    }

    public boolean checkIfLoadMore() {
        int position = mILayoutManager.findLastVisiblePosition();
        int totalCount = mILayoutManager.getLayoutManager().getItemCount();
        return totalCount - position < 5;
    }


    //对外暴露的方法设置是否支持下拉刷新与加载更多
    public void enableLoadMore(boolean enable) {
        isLoadMoreEnabled = enable;
    }
    public void enablePullToRefresh(boolean enable) {
        isPullToRefreshEnable = enable;
    }

    public void setLayoutManager(ILayoutManager manager) {
        this.mILayoutManager = manager;
        mRecyclerView.setLayoutManager(manager.getLayoutManager());
    }

    public void addItemDecoration(RecyclerView.ItemDecoration decoration) {
        if (decoration != null) {
            mRecyclerView.addItemDecoration(decoration);
        }
    }

    public void setAdapter(BaseListAdapter adapter) {
        this.adapter = adapter;
          mRecyclerView.setAdapter(adapter);
        mILayoutManager.setUpAdapter(adapter);
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
                //从scroll监听那边回调过来，由于改变了下拉刷新状态，这边要改回来，改回来的时候判断当前是否支持下拉刷新
                //在这里关闭显示更多的view
                adapter.onLoadMoreStateChanged(false);
                if (isPullToRefreshEnable)
                mSwipeRefreshLayout.setEnabled(true);//将下拉刷新重新设置为true
                break;
        }
        mCurrentState = ACTION_IDEL;  //更改状态
    }

    public interface OnRecyclerRefreshListener {
        void onRefresh(int action);
    }
}
