package com.rose.repository;

import com.rose.data.entity.TbSysUser;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface SysUserRepository extends CrudRepository<TbSysUser, Long> {

    @Query(value = "select * from tb_sys_user where uname = :uname and upwd = :upwd", nativeQuery = true)
    TbSysUser findUserNormal(@Param(value = "uname") String uname, @Param(value = "upwd") String upwd);

    @Query(value = "select count(1) from tb_sys_user where uname = :uname", nativeQuery = true)
    long countByName(@Param(value = "uname") String uname);

    @Modifying
    @Query(value = "update tb_sys_user set user_state = :userState where id = :id", nativeQuery = true)
    int updateStateById(@Param(value = "id") Long id, @Param(value = "userState") Integer userState);

    @Modifying
    @Query(value = "update tb_sys_user set role_group_id = :roleId where id = :id", nativeQuery = true)
    int updateRole(@Param(value = "id") Long id, @Param(value = "roleId") Long roleId);

    @Modifying
    @Query(value = "update tb_sys_user set upwd = :userNewPassword where id = :id", nativeQuery = true)
    int updateUserPassword(@Param(value = "id") Long id, @Param(value = "userNewPassword") String userNewPassword);
}