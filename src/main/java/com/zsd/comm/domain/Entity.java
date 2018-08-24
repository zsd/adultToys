package com.zsd.comm.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体接口.
 */
public interface Entity extends Serializable {

    /**
     * 获取实体流水号
     */
    String getId();


    /**
     * 获取创建时间
     */
    Date getCreateTime();

    /**
     * 获取更新时间.
     */
    Date getUpdateTime();

    /**
     * 获取对象类型.
     */
    ObjectType getObjType();

}
