package com.website.hoho.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.server.session.DefaultWebSessionManager;
import sun.security.krb5.Realm;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    //ShiroFilterFactoryBean  3
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        Map<String, String> map = new HashMap<>();
        //authc 必须认证才可以访问
        map.put("/admin/login_submit","anon");
        map.put("/admin/toAdd","user");
        map.put("/admin/main","user");
        map.put("/admin/add","user");
        map.put("/admin/index","user");
        map.put("/admin/search","user");
        bean.setFilterChainDefinitionMap(map);
        bean.setSecurityManager(securityManager);
        bean.setLoginUrl("/admin/login");

        return bean;
    }


    //DefaultWebSecurityManager 2
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        return securityManager;
    }


    //创建realm对象 需要自定义类
    @Bean
    public UserRealm userRealm(){
        return new UserRealm();
    }

    //整合shiro方言，用来整合thymeleaf
    @Bean
    public ShiroDialect shiroDialect(){
        return new ShiroDialect();
    }



}
