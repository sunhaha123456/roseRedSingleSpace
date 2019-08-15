package com.rose.service.impl;

import com.rose.common.data.response.ResponseResultCode;
import com.rose.common.data.response.StringResponse;
import com.rose.common.exception.BusinessException;
import com.rose.common.util.JsonUtil;
import com.rose.common.util.Md5Util;
import com.rose.common.util.StringUtil;
import com.rose.common.util.ValueHolder;
import com.rose.data.constant.SystemConstant;
import com.rose.data.entity.TbRoleGroup;
import com.rose.data.entity.TbSysUser;
import com.rose.data.to.request.UserLoginRequest;
import com.rose.repository.RoleGroupRepository;
import com.rose.repository.SysUserRepository;
import com.rose.service.LoginService;
import com.rose.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;

@Slf4j
@Service
public class LoginServiceImpl implements LoginService {

    @Inject
    private SysUserRepository sysUserRepository;
    @Inject
    private RoleGroupRepository roleGroupRepository;
    @Inject
    private UserService userService;
    @Inject
    private ValueHolder valueHolder;

    @Override
    public StringResponse verify(HttpServletRequest request, UserLoginRequest user) throws Exception {
        // 1、校验验证码
        HttpSession session = request.getSession();
        String codeSession = session.getAttribute(SystemConstant.SESSION_LOGIN_CODE_KEY) + "";
        String codeFront = Md5Util.MD5Encode(user.getCode());
        if (StringUtil.isEmpty(codeSession) || StringUtil.isEmpty(codeFront) || !codeSession.equals(codeFront)) {
            throw new BusinessException(ResponseResultCode.CODE_ERROR);
        }
        // 2、校验用户名和密码，并且用户状态正常
        TbSysUser sysUser = sysUserRepository.findUserNormal(user.getUname(), Md5Util.MD5Encode(user.getUpwd()));
        if (sysUser == null) {
            throw new BusinessException(ResponseResultCode.LOGIN_ERROR);
        }
        if (!Arrays.asList(0, 1, 2).contains(sysUser.getUserState())) {
            throw new BusinessException(ResponseResultCode.USER_STATE_ERROR);
        }
        if (sysUser.getUserState() == 1) {
            throw new BusinessException("用户已被冻结！");
        }
        if (sysUser.getUserState() == 2) {
            throw new BusinessException("用户已被注销！");
        }
        TbRoleGroup role = roleGroupRepository.findOne(sysUser.getRoleGroupId());
        if (role == null) {
            log.error("【接口 -/login/verify】【登录失败，用户所属角色组未查找到！】【用户信息：{}】", JsonUtil.objectToJson(sysUser));
            throw new BusinessException("用户信息异常！");
        }
        if (role.getRoleState() != 0) {
            throw new BusinessException("用户所属角色组已被冻结！");
        }
        session.setAttribute(SystemConstant.SESSION_USER_ID_KEY, sysUser.getId());
        return new StringResponse("验证成功！");
    }

    @Override
    public boolean sessionValidate(HttpServletRequest request) {
        String method = request.getMethod();
        if ("OPTIONS".equals(method.toUpperCase())) {
            return true;
        }
        String url = request.getRequestURI();
        HttpSession session = request.getSession();
        Object sessionUserIdObj = session.getAttribute(SystemConstant.SESSION_USER_ID_KEY);
        if (sessionUserIdObj == null) {
            log.error("Request url：{}，method：{}，拦截此请求：001-用户未登录或登录已失效！", url, method);
            return false;
        }
        Long userId = (Long) sessionUserIdObj;
        valueHolder.setUserIdHolder(userId);
        return true;
    }
}