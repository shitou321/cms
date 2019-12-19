package com.java.tian.shiro;

import com.java.tian.sercice.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.java.tian.model.TUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;

import java.util.HashSet;
import java.util.Set;

/**
 * Create by Lixiaotian  on 2019/11/20 11:38
 */

public class ShiroRealm extends AuthorizingRealm {

    private static final Logger log = LoggerFactory.getLogger(ShiroRealm.class);

    @Autowired
    private UserService userService;

    /**
     * 自定义认证授权
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        log.info("------------权限认证---------");
        TUser user = (TUser) getAvailablePrincipal(principals);
        System.out.printf("==========" + user.toString());
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // 角色列表
        Set<String> roles = new HashSet<String>();
        // 功能列表
        Set<String> menus = new HashSet<String>();
/*      List<Role> list = this.iRoleService.findUserRole(user.getNickname());
        Set<String> set = new HashSet<String>();
        for (Role r : list) {
            set.add(r.getName());
        }
        Set<String> perms = new HashSet<String>();
        List<Permission> list1 = this.iPermissionService.findRolePerm(user.getNickname());
        for(Permission p : list1) {
            perms.add(p.getUrl());
        }*/
        info.setRoles(roles);//添加角色集合   @RequireRoles("")
        info.setStringPermissions(menus);// 添加权限集合 @RequiresPermissions("")
        return info;
    }

    /**
     * 自定义认证规则
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.info("------------身份认证方法---------");
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String userName = token.getUsername();
        String password = new String(token.getPassword());
        if (userName == null) {
            throw new AccountException("Null usernames are not allowed by this realm.");
        }
        TUser user = userService.selectUserByUserName(userName);
        if (user == null) {
            throw new UnknownAccountException("No account found for admin [" + userName + "]");
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, password, getName());
        return info;
    }
}
