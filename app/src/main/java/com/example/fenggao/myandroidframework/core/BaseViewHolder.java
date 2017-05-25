package com.example.fenggao.myandroidframework.core;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by feng.gao on 2017/5/22.
 */

public abstract class BaseViewHolder extends RecyclerView.ViewHolder {


    public BaseViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick(v, getAdapterPosition());
            }
        });
    }

    public abstract void onItemClick(View v, int adapterPosition);

    public abstract void onBindViewHolder(int position);
}
