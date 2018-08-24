package com.zsd.comm.domain;

/**
 * 业务模块实体.
 * @author zhousd
 */
public class Module extends EntityImpl {

    public Module() {
        super();
    }

    public Module(String id, String name) {
        this();
        this.setId(id);
        this.setName(name);
    }
}
