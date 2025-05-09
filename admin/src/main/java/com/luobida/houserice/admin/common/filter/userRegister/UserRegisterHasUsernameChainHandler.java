package com.luobida.houserice.admin.common.filter.userRegister;

import com.luobida.houserice.admin.common.convention.exception.ServiceException;
import com.luobida.houserice.admin.dto.req.UserRegisterReqDTO;
import com.luobida.houserice.admin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.luobida.houserice.admin.common.enums.UserErrorCodeEnum.USER_NAME_EXIST;

@RequiredArgsConstructor
@Component
public class UserRegisterHasUsernameChainHandler implements UserRegisterChainFilter {

    private final UserService userService;

    @Override
    public void handle(UserRegisterReqDTO requestParam) {
        if (userService.hasUserName(requestParam.getUsername())) {
            throw new ServiceException(USER_NAME_EXIST);
        }
    }

    @Override
    public int getOrder() {
        return 20;
    }
}

