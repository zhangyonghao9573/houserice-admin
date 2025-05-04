package com.luobida.houserice.admin.service;

import com.luobida.houserice.admin.dto.req.UserRegisterReqDTO;

/**
 * 用户服务接口
 */
public interface UserService {
    void register(UserRegisterReqDTO requestParam);
}
