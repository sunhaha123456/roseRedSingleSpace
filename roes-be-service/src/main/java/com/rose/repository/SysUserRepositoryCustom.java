package com.rose.repository;

import com.rose.common.data.base.PageList;
import com.rose.common.repository.BaseRepository;
import com.rose.data.entity.TbSysUser;

public interface SysUserRepositoryCustom extends BaseRepository {
    /**
     * 功能：用户条件分页查询
     * @param uname
     * @param pageNo
     * @param pageSize
     * @return
     * @throws Exception
     */
    PageList<TbSysUser> list(String uname, Integer pageNo, Integer pageSize) throws Exception;
}