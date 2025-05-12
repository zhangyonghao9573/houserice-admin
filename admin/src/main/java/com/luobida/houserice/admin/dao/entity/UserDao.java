package com.luobida.houserice.admin.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.luobida.houserice.admin.common.dao.BaseDao;
import lombok.Builder;
import lombok.Data;

/**
 * @author zhangyonghao
 * 用户持久层对象
 */
@Data
@TableName("t_user")
@Builder
public class UserDao extends BaseDao {
    /**
     * id
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 手机号
     */
    private String phone;


    /**
     * 注销时间戳
     */
    private Long deletionTime;
}
