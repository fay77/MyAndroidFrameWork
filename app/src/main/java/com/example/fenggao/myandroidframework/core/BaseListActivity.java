package com.example.fenggao.myandroidframework.core;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.example.fenggao.myandroidframework.R;
import com.example.fenggao.myandroidframework.wigets.pull.PullToRefreshRecycler;

import java.util.ArrayList;

/**
 * Created by feng.gao on 2017/5/22.
 */

public abstract class BaseListActivity<T> extends BaseActivity implements PullToRefreshRecycler.OnRecyclerRefreshListener {
    protected ArrayList<T> mData = new ArrayList<>();
    protected BaseListAdapter mBaseListAdapter;
    protected PullToRefreshRecycler mRecycler;

    @Override
    protected void setUpView() {
        mRecycler = (PullToRefreshRecycler) findViewById(R.id.pullToRefreshRecycler);
    }

    @Override
    protected void setUpContentView() {
        setContentView(R.layout.activity_base_list,-1);
    }

    @Override
    protected void setUpData() {
        mBaseListAdapter = new BaseListAdapter();
        mRecycler.setOnRefreshListener(this);
        mRecycler.setLayoutManager(getLayoutManager());
        mRecycler.addItemDecoration(getItemDecoration());
        mRecycler.setAdapter(mBaseListAdapter);
        mRecycler.setRefreshing();
    }

    /**
     * 默认分割线，竖直的横线，子类可以重写定制自己的分割线
     * @return
     */
    protected RecyclerView.ItemDecoration getItemDecoration() {
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(getResources().getDrawable(R.drawable.list_divider));
        return itemDecoration;
    }


    protected RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(this);
    }


    @Override
    public void onRefresh(int action) {

    }


    public class BaseListAdapter extends RecyclerView.Adapter<BaseViewHolder> {


        @Override
        public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return getViewHolder(parent , viewType);
        }


        @Override
        public void onBindViewHolder(BaseViewHolder holder, int position) {
            holder.onBindViewHolder(position);
        }



        @Override
        public int getItemCount() {
            return mData != null ? mData.size() : 0;
        }

        @Override
        public int getItemViewType(int position) {
            return getItemType(position);
        }
    }

    protected int getItemType(int position) {
        return 0;
    }

    protected abstract BaseViewHolder getViewHolder(ViewGroup parent, int viewType);

}

