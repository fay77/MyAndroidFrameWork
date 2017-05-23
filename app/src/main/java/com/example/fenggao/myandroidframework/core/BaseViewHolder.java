package com.example.fenggao.myandroidframework.core;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by feng.gao on 2017/5/22.
 */

public abstract class BaseViewHolder extends RecyclerView.ViewHolder {


    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void onBindViewHolder(int position);
}
