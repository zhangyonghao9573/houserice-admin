package com.luobida.houserice.admin.controller;

import com.luobida.houserice.admin.common.convention.Result;
import com.luobida.houserice.admin.common.convention.Results;
import com.luobida.houserice.admin.dto.req.UserRegisterReqDTO;
import com.luobida.houserice.admin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户控制层
 */
@RestController
@RequestMapping("/api/houserice/admin/v1/user/")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    @PostMapping()
    public Result register(@RequestBody UserRegisterReqDTO requestParam) {
        userService.register(requestParam);
        return Results.success();
    }
}
