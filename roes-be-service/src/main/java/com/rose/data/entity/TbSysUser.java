package com.rose.data.entity;

import com.rose.common.data.base.BaseDataIdLong;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;

@ToString(callSuper = true)
@lombok.Data
@Entity
@Table(name = "tb_sys_user")
public class TbSysUser extends BaseDataIdLong implements Serializable {

    // 用户名
    @Column(name = "uname", columnDefinition = "varchar(255) binary COMMENT '用户名'")
    private String uname;

    // 密码
    @Column(name = "upwd", columnDefinition = "varchar(255) binary COMMENT '密码'")
    private String upwd;

    // 角色组id
    @Column(name = "role_group_id", columnDefinition = "Int(20) COMMENT '角色组id'")
    private Long roleGroupId;

    // 0：正常 1：冻结 2：注销
    @Column(name = "user_state", columnDefinition = "Int(1) default 0 COMMENT '用户状态'")
    private Integer userState;

    // 角色名
    @Transient
    private String roleGroupName;
}