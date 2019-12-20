package com.java.tian.controller;

import com.java.tian.model.TMenu;
import com.java.tian.model.TUser;
import com.java.tian.sercice.MenuService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import javax.jws.soap.SOAPBinding;
import java.util.List;

/**
 * Create By LiXiaoTian on 2019/12/19 11:03
 */
@Controller
public class IndexController {

    private final String prefix = "admin/";

    @Value("${cms.ProjectName}")
    private String ProjectName;
    @Autowired
    private MenuService menuService;

    @GetMapping("/index")
    public String index(ModelMap map) {
        //当前用户信息
        TUser user = (TUser) SecurityUtils.getSubject().getPrincipal();
        // 根据用户id取出菜单
        List<TMenu> menus = menuService.selectMenusByUser(user);
        map.put("menus", menus);
        map.put("user", user);
        map.put("ProjectName", ProjectName);
        return prefix + "index";
    }

    @GetMapping("/home")
    public String home() {
        return prefix + "home";
    }

}
