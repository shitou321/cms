package com.java.tian.sercice;

import com.java.tian.model.TUser;

/**
 * Create By LiXiaoTian on 2019/12/18 16:09
 */
public interface UserService {
    /**
     * 用户登录查询实体全部信息
     *
     * @param username
     * @return
     */
    TUser selectUserByUserName(String username);
}
