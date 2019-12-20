package com.java.tian.controller;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.java.tian.http.HttpResult;
import com.java.tian.model.TUser;
import com.java.tian.sercice.UserService;
import com.java.tian.utils.IOUtils;
import com.java.tian.utils.PasswordUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Create By LiXiaoTian on 2019/12/18 11:21
 */
@Controller
public class LoginController {

    private final String prefix = "admin/";
    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private Producer producer;
    @Value("${cms.project.Name}")
    private String Name;
    @Value("${cms.project.Brief}")
    private String Brief;
    @Autowired
    private UserService userService;

    /*登录页面*/
    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("Name", Name);
        model.addAttribute("Brief", Brief);
        return prefix + "login";
    }

    /*验证码*/
    @GetMapping("/captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");
        // 生成文字验证码
        String text = producer.createText();
        // 生成图片验证码
        BufferedImage image = producer.createImage(text);
        // 保存到验证码到 session
        request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, text);

        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
        IOUtils.closeQuietly(out);
    }

    /*用户登录*/
    @PostMapping("/login")
    @ResponseBody
    public HttpResult httpLogin(HttpServletRequest request, String username, String password, String captcha, boolean remember) {

        // 从session中获取之前保存的验证码跟前台传来的验证码进行匹配
        Object kaptcha = request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if (kaptcha == null) {
            return HttpResult.error("验证码已失效");
        }
        if (!captcha.equals(kaptcha)) {
            return HttpResult.error("验证码不正确");
        }
        // 用户信息
        TUser user = userService.selectUserByUserName(username);
        // 账号不存在、密码错误
        if (user == null) {
            return HttpResult.error("账号不存在");
        }
        if (!PasswordUtils.matches(user.getSalt(), password, user.getPassword())) {
            return HttpResult.error("密码不正确");
        }
        // 账号锁定
        if ("1".equals(user.getStatus())) {
            return HttpResult.error("账号已被锁定,请联系管理员");
        }
        //账号已删除
        if ("1".equals(user.getDelFlag())) {
            return HttpResult.error("账号已被删除,请联系管理员");
        }

        UsernamePasswordToken token = new UsernamePasswordToken(username, password, remember);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            return HttpResult.ok();
        } catch (Exception e) {
            log.error(e.toString());
            return HttpResult.error("登录认证失败");
        }
    }
}
