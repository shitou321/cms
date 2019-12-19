package com.java.tian.sercice.impl;

import com.java.tian.mapper.TUserMapper;
import com.java.tian.model.TUser;
import com.java.tian.sercice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Create By LiXiaoTian on 2019/12/18 16:09
 */
@Service
public class IUserService implements UserService {

    @Autowired
    private TUserMapper tUserMapper;

    /**
     * 用户登录查询实体全部信息
     *
     * @param username
     * @return
     */
    @Override
    public TUser selectUserByUserName(String username) {
        return tUserMapper.selectUserByUserName(username);
    }
}
