package com.java.tian.controller;

import com.java.tian.sercice.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Create By LiXiaoTian on 2019/12/26 14:53
 */
@Controller
@RequestMapping("/system/user")
public class UserController {

    private String prefix = "admin/user/";

    @Autowired
    private UserService userService;

    //@RequiresPermissions("system:user:view")
    @GetMapping()
    public String user() {
        return prefix + "/list";
    }
}
