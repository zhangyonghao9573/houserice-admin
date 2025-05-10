package com.luobida.houserice.admin.common.dto;

import lombok.Builder;
import lombok.Data;

/**
 * 用户信息
 */
@Data
@Builder
public class UserInfoDTO {
    private String username;

    private String realName;

    private String phone;

    private String accessToken;

}
