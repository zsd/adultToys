package com.zsd.authority.service;

import com.zsd.AbstractTest;
import com.zsd.authority.domain.RoleFunction;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 角色权限测试用例.
 * @author zhousd
 */
public class RoleFunctionServiceTest extends AbstractTest {

    @Autowired
    private RoleFunctionService roleFunctionService;

    @Test
    public void testSave() {
        RoleFunction role = new RoleFunction();
        role.setFunctionId("85be7590ed544b41b1bf8d35342fc3c9");
        role.setRoleId("9abc70679dd944b59529e3b0ed0777e5");
        roleFunctionService.save(role);
    }

    @Test
    public void testGetByRoleId() {
        List<RoleFunction> list = roleFunctionService.getByRoleId("9abc70679dd944b59529e3b0ed0777e5");
        System.out.println(list.get(1).getFunctionId());
        System.out.println(list.get(0).getFunctionId());
    }
}
