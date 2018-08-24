package com.zsd.dic.domain;

import com.zsd.comm.domain.EntityImpl;

import java.util.List;

/**
 * 字典实体.
 * @author zhousd
 */
public class Dic extends EntityImpl {

    private String item = ""; // 项目,如性别,年级等

    private String description = ""; // 描述

    private Dic parent; // 父级字典

    private int orderNum ;  //排序号

    private List<Dic> children;     //子节点

    public Dic() {
        super();
    }

    public Dic(String item) {
        this.item = item;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Dic getParent() {
        return parent;
    }

    public void setParent(Dic parent) {
        this.parent = parent;
    }

    public List<Dic> getChildren() {
        return children;
    }

    public void setChildren(List<Dic> children) {
        this.children = children;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }
}

