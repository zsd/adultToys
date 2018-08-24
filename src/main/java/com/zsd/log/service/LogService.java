package com.zsd.log.service;

import com.zsd.comm.orm.Page;
import com.zsd.log.domain.Log;

import java.util.Map;

/**
 * 业务日志服务接口.
 * @author zhousd
 */
public interface LogService {

    /**
     * 添加业务日志.
     * @param log 业务日志
     */
    void add(Log log);

    /**
     * 根据ID获取业务日志.
     * @param id 业务日志ID
     * @return 业务日志实体
     */
    Log getById(String id);

    /**
     * 分页查询.
     */
    Page<Log> search(Page<Log> page, Map<String, Object> param);

}
