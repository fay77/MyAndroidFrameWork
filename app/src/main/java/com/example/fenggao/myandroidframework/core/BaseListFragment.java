package com.example.fenggao.myandroidframework.core;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
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
 * Created by feng.gao on 2017/5/25.
 */

public abstract class BaseListFragment<T> extends BaseFragment implements PullToRefreshRecycler.OnRecyclerRefreshListener {
    protected BaseListAdapter mAdapter;
    protected ArrayList<T> mDataList;
    protected PullToRefreshRecycler mRecycler;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_base_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecycler = (PullToRefreshRecycler) view.findViewById(R.id.pullToRefreshRecycler);
        setUpAdapter();
        mRecycler.setOnRefreshListener(this);
        mRecycler.setLayoutManager(getLayoutManager());
        mRecycler.addItemDecoration(getItemDecoration());
        mRecycler.setAdapter(mAdapter);
    }

    protected RecyclerView.ItemDecoration getItemDecoration() {
        return new DividerItemDecoration(getContext() , R.drawable.list_divider);
    }

    protected ILayoutManager getLayoutManager() {
        return new MyLinearLayoutManager(getContext());
    }

    protected void setUpAdapter() {
        mAdapter = new ListAdapter();
    }

    @Override
    public void onRefresh(int action) {

    }

    public class ListAdapter extends BaseListAdapter {

        @Override
        protected int getDataCount() {
            return mDataList != null ? mDataList.size() : 0;
        }

        @Override
        protected BaseViewHolder onCreateNormalViewHolder(ViewGroup parent, int viewType) {
            return getViewHolder(parent , viewType) ;
        }

        @Override
        protected int getDataViewType(int position) {
            return getItemType(position);
        }
    }

    protected int getItemType(int position) {
        return 0;
    }

    protected abstract BaseViewHolder getViewHolder(ViewGroup parent, int viewType);
}
