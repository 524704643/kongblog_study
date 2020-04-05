package com.website.hoho.config;

import com.website.hoho.pojo.dto.UserDto;
import com.website.hoho.service.ArchivesServiceImpl;
import com.website.hoho.service.UserServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {
    @Autowired
    UserServiceImpl userService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行授权");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Subject subject = SecurityUtils.getSubject();
        UserDto userDto = (UserDto) subject.getPrincipal();
        info.addStringPermission(userDto.getPermission());
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行认证");
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        //查询用户
        UserDto userDto = userService.getUserDto(token.getUsername());
        if (userDto==null){
            return null;
        }
        //保存用户的Session
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        session.setAttribute("loginUser",token.getUsername());

        return new SimpleAuthenticationInfo(userDto,userDto.getPassword(),"");
    }
}
