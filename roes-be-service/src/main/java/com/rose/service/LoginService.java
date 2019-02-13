package com.rose.service;

import com.rose.common.data.response.StringResponse;
import com.rose.data.to.request.UserLoginRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * 功能：登录 service
 * @author sunpeng
 * @date 2018
 */
public interface LoginService {
    /**
     * 功能：登录
     * @param request
     * @param param
     * @return
     * @throws Exception
     */
    StringResponse verify(HttpServletRequest request, UserLoginRequest param) throws Exception;

    /**
     * 功能：session 校验
     * @return true：通过校验，false：未通过校验
     */
    boolean sessionValidate(HttpServletRequest request);
}