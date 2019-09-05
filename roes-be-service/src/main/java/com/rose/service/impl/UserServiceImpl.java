package com.rose.service.impl;

import com.rose.common.data.base.PageList;
import com.rose.common.data.response.ResponseResultCode;
import com.rose.common.exception.BusinessException;
import com.rose.common.util.Md5Util;
import com.rose.common.util.ValueHolder;
import com.rose.data.entity.TbRoleGroup;
import com.rose.data.entity.TbSysUser;
import com.rose.data.to.request.UserAddRequest;
import com.rose.data.to.request.UserSearchRequest;
import com.rose.repository.RoleGroupRepository;
import com.rose.repository.SysUserRepository;
import com.rose.repository.SysUserRepositoryCustom;
import com.rose.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.Date;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Inject
    private SysUserRepositoryCustom sysUserRepositoryCustom;
    @Inject
    private SysUserRepository sysUserRepository;
    @Inject
    private RoleGroupRepository roleGroupRepository;
    @Inject
    private ValueHolder valueHolder;

    @Override
    public PageList<TbSysUser> search(UserSearchRequest param) throws Exception {
        return sysUserRepositoryCustom.list(param.getUname(), param.getPage(), param.getRows());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(UserAddRequest param) throws Exception {
        long c = sysUserRepository.countByName(param.getUname());
        if (0 < c) {
            throw new BusinessException("用户名重复！");
        }
        TbSysUser user = new TbSysUser();
        BeanUtils.copyProperties(param, user);
        user.setCreateDate(new Date());
        user.setUserState(0);
        user.setUpwd(Md5Util.MD5Encode(user.getUpwd()));
        sysUserRepository.save(user);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void opert(Long id, Integer state) {
        TbSysUser sysUser = sysUserRepository.findOne(id);
        if (sysUser == null) {
            throw new BusinessException("查无此用户！");
        }
        if (sysUser.getUserState() == 2) {
            log.error("【接口 -user/userManage/opert】【该用户已被注销！】【userId：{}】", sysUser.getId());
            throw new BusinessException("查无此用户！");
        }
        if (sysUser.getUserState().equals(state)) {
            if (state == 0) {
                throw new BusinessException("此用户状态原已正常！");
            } else {
                throw new BusinessException("此用户状态原已被冻结！");
            }
        }
        if (state == 2) {
            if (sysUser.getUserState() != 1) {
                throw new BusinessException("注销前请先冻结该用户！");
            }
        }
        int c = sysUserRepository.updateStateById(id, state);
        if (c <= 0) {
            throw new BusinessException(ResponseResultCode.OPERT_ERROR);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateRole(Long id, Long roleId) {
        TbSysUser user = sysUserRepository.findOne(id);
        if (user == null) {
            throw new BusinessException("该用户不存在！");
        }
        if (user.getUserState() == 2) {
            log.error("【接口 -user/userManage/updateRole】【该用户已被注销！】【userId：{}】", id);
            throw new BusinessException("查无此用户！");
        }
        if (user.getUserState() != 0) {
            throw new BusinessException("该用户已被冻结！");
        }
        TbRoleGroup role = roleGroupRepository.findOne(roleId);
        if (role == null) {
            throw new BusinessException("角色组不存在！");
        }
        if (role.getRoleState() != 0) {
            throw new BusinessException("该角色组已被冻结！");
        }
        int c = sysUserRepository.updateRole(id, roleId);
        if (c <= 0) {
            throw new BusinessException(ResponseResultCode.OPERT_ERROR);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void adminUpdateUserPassword(Long id, String userNewPassword) throws Exception {
        int c = sysUserRepository.updateUserPassword(id, Md5Util.MD5Encode(userNewPassword));
        if (c <= 0) {
            throw new BusinessException(ResponseResultCode.OPERT_ERROR);
        }
    }

    @Override
    public TbSysUser getDetail(Long id) {
        TbSysUser user = sysUserRepository.findOne(id);
        if (user == null) {
            throw new BusinessException("查无此用户！");
        }
        if (user.getUserState() == 2) {
            log.error("【接口 -user/userManage/getDetail】【该用户已被注销！】【userId：{}】", id);
            throw new BusinessException("查无此用户！");
        }
        return user;
    }
}