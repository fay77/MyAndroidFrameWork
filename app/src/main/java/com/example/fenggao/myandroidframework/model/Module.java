package com.example.fenggao.myandroidframework.model;

import com.example.fenggao.myandroidframework.core.BaseActivity;

/**
 * Created by feng.gao on 2017/5/25.
 * 模拟的数据类
 */

public class Module {
    public String moduleName;
    public String moduleIcon;

    public Class <? extends BaseActivity> moduleTargetClass;

    public Module(String moduleName, Class<? extends BaseActivity> moduleTargetClass) {
        this.moduleName = moduleName;
        this.moduleTargetClass = moduleTargetClass;
    }
}
