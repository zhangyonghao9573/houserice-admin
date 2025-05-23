package com.luobida.houserice.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.luobida.houserice.admin.common.cache.DistributedCache.DistributedCache;
import com.luobida.houserice.admin.common.convention.exception.ClientException;
import com.luobida.houserice.admin.common.convention.exception.ServiceException;
import com.luobida.houserice.admin.common.user.UserInfoDTO;
import com.luobida.houserice.admin.common.filter.AbstractChainContext;
import com.luobida.houserice.admin.dao.entity.UserDao;
import com.luobida.houserice.admin.dao.mapper.UserMapper;
import com.luobida.houserice.admin.dto.req.user.UserLoginReqDTO;
import com.luobida.houserice.admin.dto.req.user.UserRegisterReqDTO;
import com.luobida.houserice.admin.dto.resp.user.UserLoginRespDTO;
import com.luobida.houserice.admin.service.UserService;
import com.luobida.houserice.admin.toolkit.TokenUtils;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

import static com.luobida.houserice.admin.common.constant.RedisCacheConstant.LOCK_USER_REGISTER_LOCK;
import static com.luobida.houserice.admin.common.enums.ChainMarkEnum.USER_REGISTER_CHAIN_MARK;
import static com.luobida.houserice.admin.common.enums.UserErrorCodeEnum.*;

/**
 * @author zhangyonghao
 * 用户服务实现类
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDao> implements UserService {

    private final RBloomFilter<String> userRegisterCachePenetrationBloomFilter;
    private final RedissonClient redissonClient;
    private final AbstractChainContext  chainContext;
    private final DistributedCache  distributedCache;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void register(UserRegisterReqDTO requestParam) {
        chainContext.handler(USER_REGISTER_CHAIN_MARK.name(), requestParam);
        RLock lock = redissonClient.getLock(LOCK_USER_REGISTER_LOCK + requestParam.getUsername());
        if (!lock.tryLock()) throw new ServiceException(USER_NAME_EXIST);
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

     public boolean hasUserName(String username) {
        return userRegisterCachePenetrationBloomFilter.contains(username);
    }

    @Override
    public UserLoginRespDTO login(UserLoginReqDTO requestParam) {
        if (!hasUserName(requestParam.getUsername())) {
            throw new ServiceException(USER_NULL);
        }
        LambdaQueryWrapper<UserDao> userDaoQueryWrapper = Wrappers.lambdaQuery(UserDao.class)
                .eq(UserDao::getUsername, requestParam.getUsername())
                .eq(UserDao::getPassword, requestParam.getPassword())
                .eq(UserDao::getDelFlag, UserDao.UN_DELETED);
        UserDao userDao = baseMapper.selectOne(userDaoQueryWrapper);
        if (userDao != null) {
            UserInfoDTO userInfoDTO = UserInfoDTO.builder()
                    .phone(userDao.getPhone())
                    .realName(userDao.getRealName())
                    .username(userDao.getUsername())
                    .build();
            String accessToken = TokenUtils.generateToken(requestParam.getUsername());
            distributedCache.put(accessToken, userInfoDTO, 30, TimeUnit.MINUTES);
            UserLoginRespDTO response = UserLoginRespDTO.builder()
                    .username(userInfoDTO.getUsername())
                    .realName(userInfoDTO.getRealName())
                    .accessToken(accessToken)
                    .build();
            return response;
        }
        throw new ServiceException("密码错误或账号已注销");

    }
}
