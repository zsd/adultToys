package com.zsd.comm.domain;

/**
 * 实体类型.
 */
public class ObjectType extends EntityImpl {

    public ObjectType() {
        super();
    }

    public ObjectType(String id, String name) {
        this();
        this.setId(id);
        this.setName(name);
    }
}
