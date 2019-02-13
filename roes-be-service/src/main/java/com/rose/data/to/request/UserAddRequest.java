package com.rose.data.to.request;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

@Data
public class UserAddRequest {
    // 用户名
    @NotBlank(message = "用户名不能为空！", groups = BaseInfo.class)
    private String uname;

    // 密码
    @NotBlank(message = "密码不能为空！", groups = BaseInfo.class)
    private String upwd;

    @NotNull(message = "用户角色不能为空！", groups = BaseInfo.class)
    private Long roleGroupId;

    public interface BaseInfo {}
}