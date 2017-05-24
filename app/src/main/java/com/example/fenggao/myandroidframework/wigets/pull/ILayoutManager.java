package com.example.fenggao.myandroidframework.wigets.pull;

import android.support.v7.widget.RecyclerView;

/**
 * Created by feng.gao on 2017/5/24.
 * 通过定义接口解决强转的问题
 */

public interface ILayoutManager {
    int findLastVisiblePosition();

    RecyclerView.LayoutManager getLayoutManager();
}
