package com.luobida.houserice.admin.service;

import com.luobida.houserice.admin.dto.req.UserRegisterReqDTO;

/**
 * 用户服务接口
 */
public interface UserService {

    /**
     * 用户注册
     * @param requestParam
     */
    void register(UserRegisterReqDTO requestParam);

    /**
     * 判断用户名是否存在
     * @param username
     * @return
     */
    boolean hasUserName(String username);

}
