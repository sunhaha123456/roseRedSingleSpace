package com.rose.service.impl;

import com.rose.common.data.response.ResponseResultCode;
import com.rose.common.exception.BusinessException;
import com.rose.data.entity.TbRoleGroup;
import com.rose.repository.RoleGroupRepository;
import com.rose.service.RoleGroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class RoleGroupServiceImpl implements RoleGroupService {

    @Inject
    private RoleGroupRepository roleGroupRepository;

    /**
     * 功能：权限分组列表
     * @param roleState 0：正常 1：冻结 2：查询全部
     * @return
     * @throws Exception
     */
    @Override
    public List<TbRoleGroup> list(Integer roleState) throws Exception {
        if (roleState == 2) {
            return roleGroupRepository.listAll();
        } else {
            return roleGroupRepository.listByState(roleState);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(String roleName, String roleRemark) {
        long c = roleGroupRepository.countByRoleName(roleName);
        if (0 < c) {
            throw new BusinessException("该角色组已存在！");
        }
        TbRoleGroup role = new TbRoleGroup();
        role.setCreateDate(new Date());
        role.setRoleName(roleName);
        role.setRoleRemark(roleRemark);
        role.setRoleState(0);
        roleGroupRepository.save(role);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(Long id, String roleName, String roleRemark) {
        long c1 = roleGroupRepository.countByRoleName(roleName, id);
        if (0 < c1) {
            throw new BusinessException("该角色组已存在！");
        }
        int c2 = roleGroupRepository.updateRole(id, roleName, roleRemark);
        if (c2 <= 0) {
            throw new BusinessException(ResponseResultCode.OPERT_ERROR);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void opert(Long id, Integer state) {
        TbRoleGroup role = roleGroupRepository.findOne(id);
        if (role == null) {
            throw new BusinessException("查无此角色组！");
        }
        if (role.getRoleState().equals(state)) {
            if (state == 0) {
                throw new BusinessException("此角色组状态原已正常！");
            } else {
                throw new BusinessException("此角色组状态原已被冻结！");
            }
        }
        int c = roleGroupRepository.updateStateById(id, state);
        if (c <= 0) {
            throw new BusinessException(ResponseResultCode.OPERT_ERROR);
        }
    }

    @Override
    public TbRoleGroup getDetail(Long id) {
        TbRoleGroup role = roleGroupRepository.findOne(id);
        if (role == null) {
            throw new BusinessException("角色信息错误！");
        }
        return role;
    }
}