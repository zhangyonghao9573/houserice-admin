package com.luobida.houserice.admin.controller;

import com.luobida.houserice.admin.common.convention.Result;
import com.luobida.houserice.admin.common.convention.Results;
import com.luobida.houserice.admin.dto.req.user.UserLoginReqDTO;
import com.luobida.houserice.admin.dto.req.user.UserRegisterReqDTO;
import com.luobida.houserice.admin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhangyonghao
 * 用户控制层
 */
@RestController
@RequestMapping("/api/houserice/admin/v1/user/")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * 注册用户
     * @param requestParam
     * @return
     */
    @PostMapping("register")
    public Result register(@RequestBody UserRegisterReqDTO requestParam) {
        userService.register(requestParam);
        return Results.success();
    }

    /**
     * 用户登录
     */
    @PostMapping
    public Result login(@RequestBody UserLoginReqDTO requestParam) {
        return Results.success(userService.login(requestParam));
    }
    @DeleteMapping
    public Result test(UserLoginReqDTO requestParam) {
        return Results.success();
    }
}
