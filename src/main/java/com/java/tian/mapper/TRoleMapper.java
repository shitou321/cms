package com.java.tian.mapper;

import com.java.tian.model.TRole;

import java.util.List;

/**
 * Create By LiXiaoTian on 2019/12/18 16:49
 */
public interface TRoleMapper {
    /***
     * 根据userId查询人物权限列表
     * @param userId
     * @return
     */
    List<TRole> selectRoleKeys(Integer userId);
}
