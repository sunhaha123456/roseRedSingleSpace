package com.rose.data.constant;

public interface SystemConstant {
    // session 活动最大间隔时间
    int SESSION_MAX_INACTIVE_INTERVAL = 3 * 60;

    // session 用户登录验证码 key
    String SESSION_LOGIN_CODE_KEY = "loginCode";

    // session 用户id key（也是是否已登录过的标志）
    String SESSION_USER_ID_KEY = "userId";
}