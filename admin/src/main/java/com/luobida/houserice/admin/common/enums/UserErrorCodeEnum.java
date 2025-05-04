package com.luobida.houserice.admin.common.enums;

import com.luobida.houserice.admin.common.convention.errorcode.IErrorCode;

/**
 * 用户错误码
 */
public enum UserErrorCodeEnum implements IErrorCode {

    USER_NULL("B000200", "用户记录不存在"),

    USER_NAME_EXIST("B000201", "用户名已存在"),

    USER_EXIST("B000202", "用户记录已存在"),

    USER_SAVE_ERROR("B000203", "用户记录新增失败");

    UserErrorCodeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    private final String code;
    private final String msg;
    @Override
    public String code() {
        return code;
    }

    @Override
    public String msg() {
        return msg;
    }
}
