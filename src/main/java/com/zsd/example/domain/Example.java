package com.zsd.example.domain;

import com.zsd.comm.domain.EntityImpl;

/**
 * 框架使用例子, 实体.
 * @author zhousd
 */
public class Example extends EntityImpl {

    private String gender; //性别

    /**
    * 构造函数, 建议保留.
    */
    public Example() {
        super();
    }

    /**
    * 构造函数.
    * @param id 主键
    * @param name 姓名
    * @param gender 性别
    */
    public Example(String id, String name, String gender) {
        this();
        this.setId(id);
        this.setName(name);
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

}
