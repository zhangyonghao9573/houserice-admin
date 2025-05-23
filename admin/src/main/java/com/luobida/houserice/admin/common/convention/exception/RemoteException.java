package com.luobida.houserice.admin.common.convention.exception;

import com.luobida.houserice.admin.common.convention.errorcode.IErrorCode;

/**
 * @author zhangyonghao
 * 远程调用异常
 */
public class RemoteException extends AbstractException{

    public RemoteException(String errorMsg, Throwable throwable, IErrorCode errorCode) {
        super(errorMsg, throwable, errorCode);
    }

    @Override
    public String toString() {
        return "RemoteException{" +
                "code='" + errorCode + "'," +
                "message='" + errorMsg + "'" +
                '}';
    }
}
