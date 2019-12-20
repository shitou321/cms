package com.java.tian.mapper;

import com.java.tian.model.TMenu;

import java.util.List;

/**
 * Create By LiXiaoTian on 2019/12/18 16:48
 */
public interface TMenuMapper {
    /**
     * 根据userId查询人物所拥有目录菜单按钮权限
     *
     * @param userId
     * @return
     */
    List<TMenu> selectPermsByUserId(Integer userId);

    /**
     * 管理员查询全部菜单
     *
     * @return
     */
    List<TMenu> selectMenuNormalAll();

    /**
     * 根据 userId 查询 菜单
     *
     * @param userId
     * @return
     */
    List<TMenu> selectMenusByUserId(Integer userId);
}
