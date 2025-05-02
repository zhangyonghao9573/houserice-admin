package com.luobida.houserice.admin.common.convention.exception;

import com.luobida.houserice.admin.common.convention.errorcode.IErrorCode;
import lombok.Data;
import org.springframework.util.StringUtils;

import java.util.Optional;

/**
 * 抽象项目中的三类异常，客户端异常、服务端异常和远程服务调用异常
 */
@Data
public abstract class AbstractException extends RuntimeException{
    public final String errorCode;

    public final String errorMsg;

    public AbstractException(String errorMsg, Throwable throwable, IErrorCode errorCode) {
        super(errorMsg, throwable);
        this.errorCode = errorCode.code();
        this.errorMsg = Optional.ofNullable(StringUtils.hasLength(errorMsg) ? errorMsg : null).orElse(errorCode.msg());
    }
}
