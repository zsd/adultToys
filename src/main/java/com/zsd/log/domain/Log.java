package com.zsd.log.domain;

import com.zsd.comm.domain.Client;
import com.zsd.comm.domain.EntityImpl;
import com.zsd.comm.domain.Module;
import com.zsd.user.domain.User;

/**
 * 业务日志实体类.
 * @author zhousd
 */
public class Log extends EntityImpl {

    /**
     * 业务日志类型 1：添加 2：删除 3：修改 4：查询 5：查看，6:详细信息
     * （可以自定义更多操作类型，通用操作依次添加，与业务模块相关的添加业务模块序号前缀）
     */
    private int type;
    private String description; // 日志操作描述
    private String ip; // 登陆人IP
    private Module module; // 模块
    private EntityImpl obj; // 操作业务实体
    private User user; // 操作人岗位
    private Client client; // 客户端

    public static final int TYPE_ADD = 1; // 添加
    public static final int TYPE_DEL = 2; // 删除
    public static final int TYPE_MOD = 3; // 修改
    public static final int TYPE_SEL = 4; // 查询
    public static final int TYPE_GETBYID = 5; // 查看
    public static final int TYPE_LOGIN = 6; // 登录
    public static final int TYPE_LOGOUT = 7; // 登出

    public Log() {
        super();
    }

    public Log(int type, String description, String ip, Module module, EntityImpl obj, User user, Client client) {
        this();
        this.type = type;
        this.description = description;
        this.ip = ip;
        this.module = module;
        this.obj = obj;
        this.user = user;
        this.client = client;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public EntityImpl getObj() {
        return obj;
    }

    public void setObj(EntityImpl obj) {
        this.obj = obj;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
