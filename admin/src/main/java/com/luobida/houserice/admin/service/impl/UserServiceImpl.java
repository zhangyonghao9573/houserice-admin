package com.luobida.houserice.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.luobida.houserice.admin.common.convention.exception.ClientException;
import com.luobida.houserice.admin.common.convention.exception.ServiceException;
import com.luobida.houserice.admin.dao.entity.UserDao;
import com.luobida.houserice.admin.dao.mapper.UserMapper;
import com.luobida.houserice.admin.dto.req.UserRegisterReqDTO;
import com.luobida.houserice.admin.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import static com.luobida.houserice.admin.common.convention.errorcode.BaseErrorCode.CLIENT_ERROR;
import static com.luobida.houserice.admin.common.enums.UserErrorCodeEnum.*;

/**
 * 用户服务实现类
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDao> implements UserService {

    @Override
    public void register(UserRegisterReqDTO requestParam) {
        if(ObjectUtils.isEmpty(requestParam)) throw new ClientException(CLIENT_ERROR);
        if (hasUserName(requestParam.getUsername())) {
            throw new ServiceException(USER_NAME_EXIST);
        }
        try {
            int inserted = baseMapper.insert(BeanUtil.toBean(requestParam, UserDao.class));
            if (inserted <= 0) {
                throw new ClientException(USER_SAVE_ERROR);
            }
        }  catch (DuplicateKeyException ex) {
            throw new ServiceException(USER_EXIST);
        }
    }

    private boolean hasUserName(String username) {
        LambdaQueryWrapper<UserDao> queryWrapper = Wrappers.lambdaQuery(UserDao.class)
                .eq(UserDao::getUsername, username);
        UserDao userDao = baseMapper.selectOne(queryWrapper);
        if (ObjectUtils.isEmpty(userDao)) {
            return false;
        }
        return true;
    }
}
