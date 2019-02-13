package com.rose.data.to.request;

import com.rose.data.base.PageParam;
import lombok.Data;

@Data
public class UserSearchRequest extends PageParam {
    private String uname; //用户名称
}