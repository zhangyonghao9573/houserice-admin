package com.luobida.houserice.admin.common.filter.userRegister;

import com.luobida.houserice.admin.common.filter.AbstractChainHandler;
import com.luobida.houserice.admin.dto.req.UserRegisterReqDTO;

import static com.luobida.houserice.admin.common.enums.ChainMarkEnum.USER_REGISTER_CHAIN_MARK;

/**
 * 抽象用户注册责任链
 */
public interface UserRegisterChainFilter extends AbstractChainHandler<UserRegisterReqDTO> {
    default String mark() {
        return USER_REGISTER_CHAIN_MARK.name();
    }
}
