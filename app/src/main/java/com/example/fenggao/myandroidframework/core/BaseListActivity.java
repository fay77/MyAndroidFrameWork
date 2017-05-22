package com.example.fenggao.myandroidframework.core;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.fenggao.myandroidframework.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by feng.gao on 2017/5/22.
 */

public abstract class BaseListActivity<T> extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {
    protected SwipeRefreshLayout mSwipeRefreshLayout;
    protected RecyclerView mRecyclerView;
    protected ArrayList<T> mData = new ArrayList<>();
    private BaseListAdapter mBaseListAdapter;

    @Override
    protected void setUpView() {
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.mSwipeRefreshLayout);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycleView);
    }

    @Override
    protected void setUpContentView() {
        setContentView(R.layout.activity_sample_list ,-1);
    }

    @Override
    protected void setUpData() {
        mBaseListAdapter = new BaseListAdapter();
        mRecyclerView.setLayoutManager(getLayoutManager());
        mRecyclerView.setAdapter(mBaseListAdapter);
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(true);
                onRefresh();
            }
        });
    }

    protected RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(this);
    }



    public class BaseListAdapter extends RecyclerView.Adapter<BaseViewHolder> {


        @Override
        public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return getViewHolder(parent , viewType);
        }


        @Override
        public void onBindViewHolder(BaseViewHolder holder, int position) {
            holder.onBind(position);
        }



        @Override
        public int getItemCount() {
            return mData != null ? mData.size() : 0;
        }

        @Override
        public int getItemViewType(int position) {
            return super.getItemViewType(position);
        }
    }

    protected abstract BaseViewHolder getViewHolder(ViewGroup parent, int viewType);

}

