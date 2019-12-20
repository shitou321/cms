package com.java.tian.sercice.impl;

import com.java.tian.mapper.TRoleMapper;
import com.java.tian.model.TRole;
import com.java.tian.sercice.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Create By LiXiaoTian on 2019/12/20 11:31
 */
@Service
public class IRoleService implements RoleService {

    @Autowired
    private TRoleMapper tRoleMapper;

    /***
     * 根据userId查询人物权限列表
     * @param userId
     * @return
     */
    @Override
    public Set<String> selectRoleKeys(Integer userId) {
        List<TRole> lists = tRoleMapper.selectRoleKeys(userId);
        Set<String> set = new HashSet<>();
        for (TRole list : lists) {
            if (list != null) {
                set.addAll(Arrays.asList(list.getRoleKey().trim().split(",")));
            }
        }
        return set;
    }
}
