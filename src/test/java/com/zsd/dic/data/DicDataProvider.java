package com.zsd.dic.data;

import com.zsd.dic.domain.Dic;

/**
 * 业务日志测试数据提供类.
 * @author zhousd
 */
public final class DicDataProvider {

    public static final String ID_MALE = "1"; // 男性
    public static final String ID_FEMALE = "2"; // 女性
    public static final String ITEM_SEX = "性别"; // 项目

    /**
     * 生成字典数据.
     * 性别-男
     */
    public static Dic getDic1() {
        Dic dic = new Dic();
        dic.setId(ID_MALE);
        dic.setItem(ITEM_SEX);
        dic.setName("男");
        dic.setDescription("性别为男性");
        return dic;
    }

    /**
     * 生成字典数据.
     * 性别-女
     */
    public static Dic getDic2() {
        Dic dic = getDic1();
        dic.setId(ID_FEMALE);
        dic.setName("女");
        dic.setDescription("性别为女性");
        return dic;
    }

    /**
     * 生成组织机构部门字典.
     */
    public static Dic getOrgTypeDic1() {
        Dic dic = new Dic();
        dic.setItem("组织机构类型");
        dic.setName("部门");
        dic.setDescription("部门机构");
        return dic;
    }

    /**
     * 生成组织机构单位字典.
     */
    public static Dic getOrgTypeDic2() {
        Dic dic = getOrgTypeDic1();
        dic.setName("单位");
        dic.setDescription("单位机构");
        return dic;
    }

    private DicDataProvider() {}
}
