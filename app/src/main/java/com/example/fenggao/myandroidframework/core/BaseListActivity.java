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
import com.example.fenggao.myandroidframework.wigets.pull.ILayoutManager;
import com.example.fenggao.myandroidframework.wigets.pull.MyLinearLayoutManager;
import com.example.fenggao.myandroidframework.wigets.pull.PullToRefreshRecycler;

import java.util.ArrayList;

/**
 * Created by feng.gao on 2017/5/22.
 */

public abstract class BaseListActivity<T> extends BaseActivity implements PullToRefreshRecycler.OnRecyclerRefreshListener {
    protected ArrayList<T> mData ;
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


    protected ILayoutManager getLayoutManager() {
        return new MyLinearLayoutManager(this);
    }


    @Override
    public void onRefresh(int action) {

    }


    public class BaseListAdapter extends RecyclerView.Adapter<BaseViewHolder> {
        private static final int VIEW_TYPE_LOAD_MORE_FOOTER = 100;
        private boolean isShowLoadMoreFooter;


        @Override
        public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (viewType == VIEW_TYPE_LOAD_MORE_FOOTER) {
                return getLoadMoreFooter(parent);
            }
            return getViewHolder(parent , viewType);
        }

        protected BaseViewHolder getLoadMoreFooter(ViewGroup parent) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.widget_load_more_footer, parent , false);
            return new LoadMoreFooterViewHolder(view);
        }


        @Override
        public void onBindViewHolder(BaseViewHolder holder, int position) {
            //如果是瀑布流，将底部的view设置为一整行独立显示，setFullSpan设置为true即可。
            if (isShowLoadMoreFooter && position == getItemCount() - 1){
                if (holder.itemView.getLayoutParams() instanceof StaggeredGridLayoutManager.LayoutParams) {
                    StaggeredGridLayoutManager.LayoutParams params = (StaggeredGridLayoutManager.LayoutParams) holder.itemView.getLayoutParams();
                    params.setFullSpan(true);
                }
            }
            holder.onBindViewHolder(position);

        }



        @Override
        public int getItemCount() {
            return mData != null ? mData.size() + (isShowLoadMoreFooter ? 1 : 0) : 0;
        }

        @Override
        public int getItemViewType(int position) {
            //需要显示更多并且已经是最后一个条目，则显示加载的view
            if (isShowLoadMoreFooter && position == getItemCount() - 1) {
                return VIEW_TYPE_LOAD_MORE_FOOTER;
            }
            return getItemType(position);
        }

        public void showLoadMoreFoot(boolean isShowLoadMoreFooter) {
            this.isShowLoadMoreFooter = isShowLoadMoreFooter;
            if (isShowLoadMoreFooter) {
                //增加底部item
                notifyItemInserted(getItemCount());
            } else {
                notifyItemRemoved(getItemCount());
            }
        }

            //  返回是否已经达到底部
        public boolean isFooterView(int position) {
            return isShowLoadMoreFooter && position == getItemCount() - 1;
        }

        private class LoadMoreFooterViewHolder extends BaseViewHolder {
            private LoadMoreFooterViewHolder(View view) {
                super(view);
            }

            @Override
            public void onBindViewHolder(int position) {

            }
        }
    }

    protected int getItemType(int position) {
        return 0;
    }

    protected abstract BaseViewHolder getViewHolder(ViewGroup parent, int viewType);

}

