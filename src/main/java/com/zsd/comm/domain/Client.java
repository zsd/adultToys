package com.zsd.comm.domain;

/**
 * 客户端实体.
 * @author zhousd
 */
public class Client extends EntityImpl {

    private int type = 0; // 客户端类型

    private String version = ""; // 客户端版本

    public Client() {
        super();
    }

    public Client(int type) {
        super();
    }

    public Client(String id, int type, String version) {
        this.setId(id);
        this.type = type;
        this.version = version;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
