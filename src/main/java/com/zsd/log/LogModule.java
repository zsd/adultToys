package com.zsd.log;

import com.zsd.comm.exception.MessageProvider;

/**
 * 业务日志模块异常信息定义.
 * @author zhousd
 */
public enum LogModule {

    /**服务层**/
    ERR_SEV_ADD,
    ERR_SEV_GETBYID,

    /**DAO层**/
    ERR_DAO_ADD,
    ERR_DAO_GETBYID,

    ;

    @Override
    public String toString() {
        return MessageProvider.getResource(LogModule.class).getMessage(this.name());
    }
}
