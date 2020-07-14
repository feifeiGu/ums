package com.oit.oitcloud.enums;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * API统一返回状态码
 */
@AllArgsConstructor
@NoArgsConstructor
public enum ResultCode {
    //状态码
    SUCCESS(1, "success"),
    FAIL(0, "fail"),
    //参数错误1001-1999
    PARAM_IS_INVALID(1001, "参数无效"),
    PARAM_IS_NULL(1002, "参数为空"),
    //用户错误2001-2999
    USER_NOT_LOGIN(2001, "用户未登录"),
    USER_LOGIN_ERROR(2002, "账号不存在或密码错误"),
    USERNAME_IS_NULL(2003, "用户名为空"),
    USER_EXIST(2004, "用户已存在"),
    ROLE_EXIST(2005, "同一公司下的角色已存在"),
    USER_DISABLE(2006, "用户已停用"),
    ;

    private Integer code;
    private String message;

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
