package com.zsd.comm.context;

import org.springframework.beans.factory.annotation.Value;

/**
 * 以静态变量持有系统环境上下文
 */
public class SystemContextHolder {

    /**
     * 系统环境参数, 详见 com.vansec.comm.utils.Constants
     *
     * 开发:development
     * 测试:test
     * 生产:production
     */
    private static String environment;

    /**
     * 系统登录页参数
     */
    private static String login;

    /**
     * 系统主页参数
     */
    private static String home;

    /**
     * 私有的构造函数
     */
    private SystemContextHolder() {}

    public static String getEnvironment() {
        return environment;
    }

    @Value(value = "${system.environment}")
    public void setEnvironment(String environment) {
        SystemContextHolder.environment = environment;
    }

    public static String getLogin() {
        return login;
    }

    @Value(value = "${system.login}")
    public void setLogin(String login) {
        SystemContextHolder.login = login;
    }

    public static String getHome() {
        return home;
    }

    @Value(value = "${system.home}")
    public void setHome(String home) {
        SystemContextHolder.home = home;
    }
}
