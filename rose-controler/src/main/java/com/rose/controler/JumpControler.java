package com.rose.controler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 功能：跳转 controller
 * @author sunpeng
 * @date 2018
 */
@Slf4j
@Controller
public class JumpControler {
    /**
     * 功能：跳转用户管理页面
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/user/userManage/toUserManage")
    public String toUserManage() throws Exception {
        return "menu/userManage";
    }

    /**
     * 功能：跳转角色管理页面
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/user/roleManage/toRoleManage")
    public String toRoleManage() throws Exception {
        return "menu/roleManage";
    }

    /**
     * 功能：跳转菜单管理页面
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/user/menuManage/toMenuManage")
    public String toMenuManage() throws Exception {
        return "menu/menuManage";
    }
}