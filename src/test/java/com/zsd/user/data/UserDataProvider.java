package com.zsd.user.data;

import com.zsd.comm.DataUtilsTest;
import com.zsd.user.domain.User;

import java.util.Calendar;

/**
 * 用户测试数据提供类
 */
public class UserDataProvider {

    private UserDataProvider() {}

    /**
     * 生成用户数据.
     */
    public static User getUser() {
        User user = new User();
        user.setId(DataUtilsTest.ID_1);
        user.setName("测试用户_001");
        user.setLoginName("test1");
        user.setPassword("123");
        user.setPhone("18681891234");
        user.setEmail("test1@sina.com");
        user.setPhoto("aaa.jpg");
        Calendar calendar = Calendar.getInstance();
        user.setBirthDay(calendar.getTime());
        user.setWechatId("wechatid1");
        user.setWechatName("wechatname1");
        user.setWeiboId("weiboid1");
        user.setWeiboName("weiboname1");
        user.setRewardMoney(100);
        user.setRewardPoint(100);
        return user;
    }
}
