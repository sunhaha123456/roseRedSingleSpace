package com.rose.controler;

import com.rose.common.data.base.PageList;
import com.rose.common.data.response.ResponseResultCode;
import com.rose.common.exception.BusinessException;
import com.rose.common.util.JsonUtil;
import com.rose.data.entity.TbSysUser;
import com.rose.data.to.request.UserAddRequest;
import com.rose.data.to.request.UserSearchRequest;
import com.rose.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

/**
 * 功能：用户管理 controller
 * @author sunpeng
 * @date 2018
 */
@Slf4j
@RestController
@RequestMapping("/user/userManage")
public class UserManageControler {

    @Inject
    private UserService userService;

    @PostMapping(value= "/search")
    public PageList<TbSysUser> search(@RequestBody UserSearchRequest param) throws Exception {
        return userService.search(param);
    }

    @PostMapping(value= "/add")
    public void add(@RequestBody @Validated(UserAddRequest.BaseInfo.class) UserAddRequest param, BindingResult result) throws Exception {
        if (result.hasErrors()) {
            log.info("【接口 -/user/userManage/add】【参数错误】【前端入参：{}】", JsonUtil.objectToJson(param));
            throw new BusinessException(ResponseResultCode.PARAM_ERROR);
        }
        userService.add(param);
    }

    /**
     * 功能：操作用户状态
     * @param id
     * @param state 0：解冻恢复正常 1：冻结 2：注销（只有冻结后，才能注销）
     * @throws Exception
     */
    @GetMapping(value= "/opert")
    public void opert(@RequestParam Long id, @RequestParam Integer state) throws Exception {
        userService.opert(id, state);
    }

    @GetMapping(value= "/updateRole")
    public void updateRole(@RequestParam Long id, @RequestParam Long roleId) throws Exception {
        userService.updateRole(id, roleId);
    }

    @GetMapping(value= "/getDetail")
    public TbSysUser getDetail(@RequestParam Long id) throws Exception {
        return userService.getDetail(id);
    }
}