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
import lombok.RequiredArgsConstructor;
import org.redisson.Redisson;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import static com.luobida.houserice.admin.common.constant.RedisCacheConstant.LOCK_USER_REGISTER_LOCK;
import static com.luobida.houserice.admin.common.convention.errorcode.BaseErrorCode.CLIENT_ERROR;
import static com.luobida.houserice.admin.common.enums.UserErrorCodeEnum.*;

/**
 * 用户服务实现类
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDao> implements UserService {

    private final RBloomFilter<String> userRegisterCachePenetrationBloomFilter;
    private final RedissonClient redissonClient;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void register(UserRegisterReqDTO requestParam) {
        if(ObjectUtils.isEmpty(requestParam)) throw new ClientException(CLIENT_ERROR);
        if (hasUserName(requestParam.getUsername())) {
            throw new ServiceException(USER_NAME_EXIST);
        }
        RLock lock = redissonClient.getLock(LOCK_USER_REGISTER_LOCK + requestParam.getUsername());
        if (lock.tryLock()) throw new ServiceException(USER_NAME_EXIST);
        try {
            int inserted = baseMapper.insert(BeanUtil.toBean(requestParam, UserDao.class));
            userRegisterCachePenetrationBloomFilter.add(requestParam.getUsername());
            if (inserted <= 0) {
                throw new ClientException(USER_SAVE_ERROR);
            }
        }  catch (DuplicateKeyException ex) {
            throw new ServiceException(USER_EXIST);
        }finally {
            lock.unlock();
        }
    }

    private boolean hasUserName(String username) {
        return userRegisterCachePenetrationBloomFilter.contains(username);
    }
}
