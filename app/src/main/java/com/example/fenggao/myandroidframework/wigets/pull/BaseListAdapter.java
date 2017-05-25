package com.example.fenggao.myandroidframework.wigets.pull;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fenggao.myandroidframework.R;
import com.example.fenggao.myandroidframework.core.BaseViewHolder;

/**
 * Created by feng.gao on 2017/5/25.
 */

public abstract class BaseListAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private static final int VIEW_TYPE_LOAD_MORE_FOOTER = 100;
    private boolean isLoadMoreFooterShown;

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_LOAD_MORE_FOOTER) {
            return onCreateLoadMoreFooterViewHolder(parent);
        }
        return onCreateNormalViewHolder(parent, viewType);
    }

    /**
     * 增加一个判断，如果是瀑布流则去处理footView独立成行显示的问题。
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        if (isLoadMoreFooterShown && position == getItemCount() - 1) {
            if (holder.itemView.getLayoutParams() instanceof StaggeredGridLayoutManager.LayoutParams) {
                StaggeredGridLayoutManager.LayoutParams params = (StaggeredGridLayoutManager.LayoutParams) holder.itemView.getLayoutParams();
                params.setFullSpan(true);
            }
        }
        holder.onBindViewHolder(position);
    }

    /**
     * 如果需要显示更多，则需要在原数据上+1用于显示footView
     * @return
     */
    @Override
    public int getItemCount() {
        return getDataCount() + (isLoadMoreFooterShown ? 1 : 0);

    }

    /**
     * 根据Type值去加载不同的view，主要是底部的footView
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        //需要显示更多并且已经是最后一个条目，则显示加载的view
        if (isLoadMoreFooterShown && position == getItemCount() - 1) {
            return VIEW_TYPE_LOAD_MORE_FOOTER;
        }
        return getDataViewType(position);
    }

    protected abstract int getDataCount();

    protected abstract BaseViewHolder onCreateNormalViewHolder(ViewGroup parent, int viewType);

    protected BaseViewHolder onCreateLoadMoreFooterViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.widget_load_more_footer, parent, false);
        return new LoadMoreFooterViewHolder(view);
    }

    protected int getDataViewType(int position) {
        return 0;
    }

    public void onLoadMoreStateChanged(boolean isShown) {
        this.isLoadMoreFooterShown = isShown;
        if (isShown) {
            notifyItemInserted(getItemCount());
        } else {
            notifyItemRemoved(getItemCount());
        }
    }

    public boolean isLoadMoreFooter(int position) {
        return isLoadMoreFooterShown && position == getItemCount() - 1;
    }

    private class LoadMoreFooterViewHolder extends BaseViewHolder {
        public LoadMoreFooterViewHolder(View view) {
            super(view);
        }

        @Override
        public void onItemClick(View v, int adapterPosition) {

        }

        @Override
        public void onBindViewHolder(int position) {
        }
    }

}
