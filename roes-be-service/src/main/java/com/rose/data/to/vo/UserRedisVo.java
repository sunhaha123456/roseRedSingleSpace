package com.rose.data.to.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 功能：用户信息主要指：token、用户状态
 * @author sunpeng
 * @date 2018
 */
@Data
public class UserRedisVo implements Serializable {
    // 用户token
    private String token;

    // 用户状态 0：正常 1：该用户被冻结 2：该用户所属角色组被冻结
    private Integer userState;

    public UserRedisVo() {
    }

    public UserRedisVo(String token, Integer userState) {
        this.token = token;
        this.userState = userState;
    }
}