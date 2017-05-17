package com.example.fenggao.myandroidframework.constants;

/**
 * Created by feng.gao on 2017/5/17.
 * APP的一些状态
 */

public class ConstantValues {
    public static final int STATUS_FORCE_KILLED = -1; //被强杀
    public static final int STATUS_LOGOUT = 0; //退出
    public static final int STATUS_OFFLINE = 1;//下线
    public static final int STATUS_ONLINE = 2;//上线
    public static final int STATUS_KICK_OUT = 3; //Token失效被挤下线等

    public static final String KEY_HOME_ACTION = "key_home_action";
    public static final int ACTION_BACK_TO_HOME = 0;
    public static final int ACTION_RESTART_APP = 1;
    public static final int ACTION_LOGOUT = 2;
    public static final int ACTION_KICK_OUT = 3;
}
