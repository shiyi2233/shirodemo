package com.shiyi.test.realm;

import com.shiyi.test.entity.AdminUser;
import com.shiyi.test.service.ActiveUserService;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author: shiyi
 * @Description:
 * @Date: Create in 9:36 2019/10/18
 */
public class MyShiroRealm extends AuthorizingRealm {
    @Autowired
    private ActiveUserService activeUserService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        String userName = principals.getPrimaryPrincipal().toString().split(":")[0];
        //根据用户userName查询权限（permission) 此处省略sql写固定权限
        Set<String> permissions = new HashSet<>();
        permissions.add("shiro:all");
        info.setStringPermissions(permissions);
        return info;
    }
    /**
     * 认证信息，主要针对用户登录，
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        UsernamePasswordToken utoken = (UsernamePasswordToken) authcToken;
        AdminUser adminUser = new AdminUser();
        adminUser.setUserName(utoken.getUsername());
        adminUser.setPassWord(new String(utoken.getPassword()));
        //根据账号密码查用户信息
        AdminUser user = activeUserService.verificationUser(adminUser);
        if (null == user) {
            throw new AccountException("账号不存在！");
        } else if (!StringUtils.equals(user.getPassWord(), adminUser.getPassWord())) {
            throw new AccountException("密码不正确！");
        }
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user.getUserName(),// 用户名
                user.getPassWord(), // 密码
                getName());
        return simpleAuthenticationInfo;
    }
}
