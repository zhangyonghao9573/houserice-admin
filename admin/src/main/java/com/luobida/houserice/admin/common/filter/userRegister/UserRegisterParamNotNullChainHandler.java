package com.luobida.houserice.admin.common.filter.userRegister;

import cn.hutool.core.bean.BeanUtil;
import com.luobida.houserice.admin.common.convention.exception.ClientException;
import com.luobida.houserice.admin.common.enums.UserRegisterErrorCodeEnum;
import com.luobida.houserice.admin.dto.req.UserRegisterReqDTO;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * 检查注册请求参数是否为空
 */
@Component
public class UserRegisterParamNotNullChainHandler implements UserRegisterChainFilter{
    @Override
    public void handle(UserRegisterReqDTO requestParam) {
        if (Objects.isNull(requestParam.getUsername())) {
            throw new ClientException(UserRegisterErrorCodeEnum.USER_NAME_NOTNULL);
        }else if (Objects.isNull(requestParam.getPassword())) {
            throw new ClientException(UserRegisterErrorCodeEnum.PASSWORD_NOTNULL);
        }else if (Objects.isNull(requestParam.getPhone())) {
            throw new ClientException(UserRegisterErrorCodeEnum.PHONE_NOTNULL);
        }else if (Objects.isNull(requestParam.getRealName())) {
            throw new ClientException(UserRegisterErrorCodeEnum.REAL_NAME_NOTNULL);
        }
    }

    @Override
    public int getOrder() {
        return 10;
    }
}
