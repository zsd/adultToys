package com.zsd.authority.data;

import com.zsd.authority.domain.Role;
import com.zsd.comm.DataUtilsTest;

/**
 * 角色测试数据提供类.
 * @author zhousd
 */
public final class RoleDataProvider {

    /**
     * 生成角色.
     */
    public static Role getRole() {
        Role role = new Role();
        role.setId(DataUtilsTest.ID_1);
        role.setName("测试角色");
        role.setDescription("测试角色");
        return role;
    }

    private RoleDataProvider() {}
}
