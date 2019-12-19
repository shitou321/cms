package com.java.tian.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Create By LiXiaoTian on 2019/12/19 11:03
 */
@Controller
public class IndexController {

    private final String prefix = "admin/";

    @GetMapping("index")
    public String index() {
        return prefix + "index";
    }
}
