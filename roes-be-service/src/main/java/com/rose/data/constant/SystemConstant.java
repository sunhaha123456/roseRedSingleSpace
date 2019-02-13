package com.rose.data.constant;

public interface SystemConstant {
    // session 活动最大间隔时间
    int SESSION_MAX_INACTIVE_INTERVAL = 8 * 60;

    // session 用户登录标志 key
    String SESSION_LOGIN_SUCCESS_KEY = "loginFlag";

    // session 用户登录标志 value
    String SESSION_LOGIN_SUCCESS_VALUE = "1";

    // session 用户登录验证码 key
    String SESSION_LOGIN_CODE_KEY = "loginCode";
}