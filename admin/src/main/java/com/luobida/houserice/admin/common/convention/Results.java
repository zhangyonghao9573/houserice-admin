package com.luobida.houserice.admin.common.convention;

import com.luobida.houserice.admin.common.convention.errorcode.BaseErrorCode;
import com.luobida.houserice.admin.common.convention.errorcode.IErrorCode;
import com.luobida.houserice.admin.common.convention.exception.AbstractException;

import java.util.Optional;

public final class Results {

    /**
     * 构造成功响应
     */
    public static Result<Void> success() {
        return new Result<Void>()
                .setCode(Result.SUCCESS_CODE);
    }

    /**
     * 构造带返回数据的成功响应
     */
    public static <T> Result<T> success(T data) {
        return new Result<T>()
                .setCode(Result.SUCCESS_CODE)
                .setData(data);
    }

    /**
     * 构造错误响应
     */
    public static Result<Void> failure() {
        return new Result<Void>()
                .setCode(BaseErrorCode.SERVICE_ERROR.code())
                .setMsg(BaseErrorCode.SERVICE_ERROR.msg());
    }

    /**
     * 通过 {@link AbstractException} 构建失败响应
     */
    public static Result<Void> failure(AbstractException ex) {
        String errorCode = Optional.ofNullable(ex.getErrorCode())
                .orElse(BaseErrorCode.SERVICE_ERROR.code());
        String errorMessage = Optional.ofNullable(ex.getErrorMsg())
                .orElse(BaseErrorCode.SERVICE_ERROR.msg());
        return new Result<Void>()
                .setCode(errorCode)
                .setMsg(errorMessage);
    }
    /**
     * 构造错误响应
     */
    public static Result<Void> failure(IErrorCode errorCode) {
        return new Result<Void>()
                .setCode(errorCode.code())
                .setMsg(errorCode.msg());
    }

    public static Result<Void> failure(String errorCode, String errorMsg) {
        return new Result<Void>()
                .setCode(errorCode)
                .setMsg(errorMsg);
    }
}
