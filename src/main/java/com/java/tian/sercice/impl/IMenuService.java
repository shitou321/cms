package com.java.tian.sercice.impl;

import com.java.tian.mapper.TMenuMapper;
import com.java.tian.model.TMenu;
import com.java.tian.model.TUser;
import com.java.tian.sercice.MenuService;
import com.java.tian.utils.TreeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Create By LiXiaoTian on 2019/12/20 11:32
 */
@Service
public class IMenuService implements MenuService {


    @Autowired
    private TMenuMapper tMenuMapper;
    @Value("${cms.admin.LoginName}")
    private String LoginName;

    /**
     * 根据userId查询人物所拥有目录菜单按钮权限
     *
     * @param userId
     * @return
     */
    @Override
    public Set<String> selectPermsByUserId(Integer userId) {
        List<TMenu> lists = tMenuMapper.selectPermsByUserId(userId);
        Set<String> set = new HashSet<>();
        for (TMenu list : lists) {
            if (list != null) {
                set.addAll(Arrays.asList(list.getPerms().trim().split(",")));
            }
        }
        return set;
    }

    /**
     * 根据userId查询菜单
     *
     * @param user
     * @return
     */
    @Override
    public List<TMenu> selectMenusByUser(TUser user) {
        List<TMenu> menus = new LinkedList<TMenu>();
        // 管理员显示所有菜单信息
        if (LoginName.equals(user.getLoginName())) {
            menus = tMenuMapper.selectMenuNormalAll();
        } else {
            menus = tMenuMapper.selectMenusByUserId(user.getUserId());
        }
        return TreeUtils.getChildPerms(menus, 0);
    }
}
