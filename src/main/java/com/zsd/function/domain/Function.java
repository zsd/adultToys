package com.zsd.function.domain;

import com.zsd.comm.domain.EntityImpl;

import java.util.List;

/**
 * Created by 黄辅湘 on 2016/6/22.
 */
public class Function extends EntityImpl {

    private String code; // 编号

    private String src; // 路径

    private String type; // 类型

    private String description; // 描述

    private String isShow; // 是否显示

    private int orderNum; // 排序

    private String parentId; // 父节点ID

    private String icon; // 图片

    private List<Function> children; // 子节点集合

    public Function() {
        super();
    }

    public Function(String name, String src, String type,String description,String isShow,int orderNum,String parentId,String icon){
        this.setName(name);
        this.src = src ;
        this.type = type ;
        this.description = description ;
        this.isShow = isShow ;
        this.orderNum = orderNum ;
        this.parentId = parentId ;
        this.icon = icon ;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIsShow() {
        return isShow;
    }

    public void setIsShow(String isShow) {
        this.isShow = isShow;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public List<Function> getChildren() {
        return children;
    }

    public void setChildren(List<Function> children) {
        this.children = children;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
