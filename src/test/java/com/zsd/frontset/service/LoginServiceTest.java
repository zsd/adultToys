package com.zsd.frontset.service;

import com.zsd.AbstractTest;
import com.zsd.user.domain.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 登录服务测试类.
 */
public class LoginServiceTest extends AbstractTest {

    @Autowired
    private LoginService loginService;
    
    /**
     * 登录测试.
     */
    @Test
    public void loginTest(){
        User user = new User("admin", "123");
        loginService.login(user);
    }

    /**
     * 登出测试.
     */
    @Test
    public void logoutTest(){
        loginService.logout();
    }

}
