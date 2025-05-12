package com.luobida.houserice.admin.common.convention.exception;

import com.luobida.houserice.admin.common.convention.errorcode.IErrorCode;

/**
 * @author zhangyonghao
 * 客户端异常
 */
public class ClientException extends AbstractException{

    public ClientException (IErrorCode errorCode) {
        super(null, null, errorCode);
    }
    public ClientException(IErrorCode errorCode, String errorMsg) {
        super(errorMsg, null, errorCode);
    }

    public ClientException(String message, Throwable throwable, IErrorCode errorCode) {
        super(message, throwable, errorCode);
    }

    @Override
    public String toString() {
        return "ClientException{" +
                "code='" + errorCode + "'," +
                "message='" + errorMsg + "'" +
                '}';
    }
}
