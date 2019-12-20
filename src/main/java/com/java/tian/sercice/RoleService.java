package com.java.tian.sercice;

import java.util.Set;

/**
 * Create By LiXiaoTian on 2019/12/20 11:31
 */
public interface RoleService {
    /***
     * 根据userId查询人物权限列表
     * @param userId
     * @return
     */
    Set<String> selectRoleKeys(Integer userId);
}
