package com.luobida.houserice.admin.common.convention.exception;

import com.luobida.houserice.admin.common.convention.errorcode.BaseErrorCode;
import com.luobida.houserice.admin.common.convention.errorcode.IErrorCode;

import java.util.Optional;

public class ServiceException extends AbstractException{


    public ServiceException(String message) {
        this(message, null, BaseErrorCode.SERVICE_ERROR);
    }

    public ServiceException(IErrorCode errorCode) {
        this(null, errorCode);
    }

    public ServiceException(String message, IErrorCode errorCode) {
        this(message, null, errorCode);
    }

    public ServiceException(String message, Throwable throwable, IErrorCode errorCode) {
        super(Optional.ofNullable(message).orElse(errorCode.msg()), throwable, errorCode);
    }

    @Override
    public String toString() {
        return "ServiceException{" +
                "code='" + errorCode + "'," +
                "message='" + errorMsg + "'" +
                '}';
    }
}
