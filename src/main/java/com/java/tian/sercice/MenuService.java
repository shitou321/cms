package com.java.tian.sercice;

import com.java.tian.model.TMenu;
import com.java.tian.model.TUser;

import java.util.List;
import java.util.Set;

/**
 * Create By LiXiaoTian on 2019/12/20 11:30
 */
public interface MenuService {
    /**
     * 根据userId查询人物所拥有目录菜单按钮权限
     *
     * @param userId
     * @return
     */
    Set<String> selectPermsByUserId(Integer userId);

    /**
     * 根据userId查询菜单
     *
     * @param user
     * @return
     */
    List<TMenu> selectMenusByUser(TUser user);
}
