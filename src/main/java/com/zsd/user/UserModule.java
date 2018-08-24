package com.zsd.user;

import com.zsd.comm.exception.MessageProvider;

/**
 * 组织机构异常信息管理.
 * @author zhousd
 */
public enum UserModule {

    /**服务层**/
    ERR_SEV_USER_LOGIN_ERROR,

    ERR_SEV_POST_GETBYID,
    ERR_SEV_ROLE_GETBYPOSTID,

    ERR_SEV_ORG_GETBYID,

    /**DAO层**/
    ERR_DAO_POST_GETBYID,
    ERR_DAO_POST_GETBYGRIDCODE,

    ERR_DAO_ROLE_GETBYPOSTID,


    ;

    @Override
    public String toString() {
        return MessageProvider.getResource(UserModule.class).getMessage(this.name());
    }
}