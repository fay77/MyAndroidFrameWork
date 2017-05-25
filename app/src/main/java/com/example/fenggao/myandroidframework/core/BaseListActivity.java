package com.example.fenggao.myandroidframework.core;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fenggao.myandroidframework.R;
import com.example.fenggao.myandroidframework.wigets.pull.BaseListAdapter;
import com.example.fenggao.myandroidframework.wigets.pull.ILayoutManager;
import com.example.fenggao.myandroidframework.wigets.pull.MyLinearLayoutManager;
import com.example.fenggao.myandroidframework.wigets.pull.PullToRefreshRecycler;

import java.util.ArrayList;

/**
 * Created by feng.gao on 2017/5/22.
 */

public abstract class BaseListActivity<T> extends BaseActivity implements PullToRefreshRecycler.OnRecyclerRefreshListener {
    protected ArrayList<T> mData;
    protected ListAdapter mAdapter;
    protected PullToRefreshRecycler mRecycler;

    @Override
    protected void setUpView() {
        mRecycler = (PullToRefreshRecycler) findViewById(R.id.pullToRefreshRecycler);
    }

    @Override
    protected void setUpContentView() {
        setContentView(R.layout.activity_base_list, -1);
    }

    @Override
    protected void setUpData() {
        setUpAdapter();
        mRecycler.setOnRefreshListener(this);
        mRecycler.setLayoutManager(getLayoutManager());
        mRecycler.addItemDecoration(getItemDecoration());
        mRecycler.setAdapter(mAdapter);
        mRecycler.setRefreshing();
    }

    /**
     * 子类可以重写该方法，扩展adapter
     */
    protected void setUpAdapter() {
        mAdapter = new ListAdapter();
    }

    /**
     * 默认分割线，竖直的横线，子类可以重写定制自己的分割线
     *
     * @return
     */
    protected RecyclerView.ItemDecoration getItemDecoration() {
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(getResources().getDrawable(R.drawable.list_divider));
        return itemDecoration;
    }


    protected ILayoutManager getLayoutManager() {
        return new MyLinearLayoutManager(this);
    }


    @Override
    public void onRefresh(int action) {

    }


    public class ListAdapter extends BaseListAdapter {

        @Override
        protected int getDataCount() {
            return mData != null ? mData.size() : 0;
        }

        @Override
        protected int getDataViewType(int position) {
            return getItemType(position);
        }

        @Override
        protected BaseViewHolder onCreateNormalViewHolder(ViewGroup parent, int viewType) {
            return getViewHolder(parent, viewType);
        }
    }

    protected abstract BaseViewHolder getViewHolder(ViewGroup parent, int viewType);

    protected int getItemType(int position) {
        return 0;

    }
}

