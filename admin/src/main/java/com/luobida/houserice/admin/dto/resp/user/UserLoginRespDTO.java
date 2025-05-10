package com.luobida.houserice.admin.dto.resp.user;

import lombok.Builder;
import lombok.Data;

/**
 * 用户登录DTO
 */
@Data
@Builder
public class UserLoginRespDTO {
    /**
     * 用户名
     */
    private String username;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * Token
     */
    private String accessToken;}
