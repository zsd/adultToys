package com.zsd.authority;

import com.zsd.comm.exception.MessageProvider;

/**
 * 权限模块消息枚举类.
 * @author zhousd
 */
public enum AuthorityModule {

    //角色
    ERR_DAO_ROLE_GETBYUSERID,
    ERR_DAO_ROLE_SAVE,
    ERR_DAO_ROLE_UPDATE,
    ERR_DAO_ROLE_DELETE,
    ERR_DAO_ROLE_SEARCH,
    ERR_DAO_ROLE_SEARCHCOUNT,
    ERR_DAO_ROLE_SAVEUSERROLE,
    ERR_DAO_ROLE_DELETEBYUSERID,
    ERR_DAO_ROLE_DELETEBYROLE;

    @Override
    public String toString() {
        return MessageProvider.getResource(AuthorityModule.class).getMessage(this.name());
    }
}
