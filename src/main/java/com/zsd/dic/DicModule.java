package com.zsd.dic;

import com.zsd.comm.exception.MessageProvider;

/**
 * 业务日志模块异常信息定义.
 * @author zhousd
 */
public enum DicModule {

    /**服务层**/
    ERR_SEV_SAVE,
    ERR_SEV_GETBYID,
    ERR_SEV_UPDATE,
    ERR_SEV_DEL,
    ERR_SEV_GETBYITEM,

    /**DAO层**/
    ERR_DAO_INSERT,
    ERR_DAO_GETBYID,
    ERR_DAO_UPDATE,
    ERR_DAO_DEL,
    ERR_DAO_GETBYITEM

    ;

    @Override
    public String toString() {
        return MessageProvider.getResource(DicModule.class).getMessage(this.name());
    }
}
