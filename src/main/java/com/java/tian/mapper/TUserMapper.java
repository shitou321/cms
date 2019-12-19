package com.java.tian.mapper;

import com.java.tian.model.TUser;


/**
 * Create By LiXiaoTian on 2019/12/18 16:21
 */
public interface TUserMapper {
    /*用户登录查询实体全部信息*/
    TUser selectUserByUserName(String username);
}
