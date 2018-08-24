package com.zsd.dic.service;

import com.zsd.AbstractTest;
import com.zsd.dic.data.DicDataProvider;
import com.zsd.dic.domain.Dic;
import org.junit.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 示例程序测试类.
 */
public class DicServiceTest extends AbstractTest {

    @Autowired
    private DicService dicService;

    /**
     * 根据ID获取字典测试.
     */
    @Test
    public void getByIdTest() {
        Dic dic = dicService.getById(DicDataProvider.ID_MALE);
        Assert.assertNotNull(dic);
    }


    /**
    /**
     * 保存测试.
     */
    @Test
    public void saveTest() {
        Dic dic = DicDataProvider.getDic1();
        dicService.save(dic);
    }

    /**
     * 保存测试.
     */
    @Test
    public void save1Test() {
        Dic dic = DicDataProvider.getDic2();
        dicService.save(dic);
    }

    /**
     * 更新测试.
     */
    @Test
    public void updateTest() {
        Dic dic = dicService.getById(DicDataProvider.ID_MALE);
        dic.setName("男性");
        dic.setDescription("性别为男性");
        dicService.update(dic);
    }

    /**
     * 根据ID删除字典测试.
     */
    @Test
    public void deleteTest() {
        dicService.delete(DicDataProvider.ID_MALE);
        Dic dic = dicService.getById(DicDataProvider.ID_MALE);
        Assert.assertNull(dic);
    }


    /**
     * *******************************************************
     *                    业务字典值添加
     * *******************************************************
     */
    /**
     * 添加组织类型字典1.
     */
    @Test
    public void saveOrgType1Test() {
        Dic dic = DicDataProvider.getOrgTypeDic1();
        dicService.save(dic);
    }

    @Test
    public void saveOrgType2Test() {
        Dic dic = DicDataProvider.getOrgTypeDic2();
        dicService.save(dic);
    }
}
