package com.luobida.houserice.admin.dto.req.user;

import lombok.Data;

/**
 * @author zhangyonghao
 * 用户登录请求参数
 */
@Data
public class UserLoginReqDTO {
    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;
}
