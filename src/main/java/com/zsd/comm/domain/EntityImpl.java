package com.zsd.comm.domain;

import java.util.Date;

/**
 * 基础业务实体.
 * 所有的业务实体都继承自基础业务实体
 * @author zhousd
 */
public class EntityImpl implements Entity {

    private String id; // 主键时间

    private Date createTime; // 创建时间
    private Date updateTime; // 更新
    private String name; // 名称
    private ObjectType objType; // 实体类型

    public EntityImpl() {
    }

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public ObjectType getObjType() {
        return objType;
    }

    public void setObjType(ObjectType objType) {
        this.objType = objType;
    }

}
