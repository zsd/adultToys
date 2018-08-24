package com.zsd.authority.domain;

/**
 * 角色关联权限实体.
 * @author zhousd
 */
public class RoleFunction {

    private String roleId; // 关联角色ID

    private String functionId; // 关联功能ID

    public RoleFunction() {
        super();
    }

    public RoleFunction(String roleId,String functionId){
        this.roleId = roleId;
        this.functionId = functionId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getFunctionId() {
        return functionId;
    }

    public void setFunctionId(String functionId) {
        this.functionId = functionId;
    }
}
