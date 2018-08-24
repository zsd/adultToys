package com.zsd.log.dao;

import com.zsd.comm.exception.ServiceException;
import com.zsd.comm.orm.Page;
import com.zsd.log.LogModule;
import com.zsd.log.dao.mapper.LogMapper;
import com.zsd.log.domain.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 业务日志数据访问接口.
 * @author zhousd
 */
@Repository
public class LogDao {

    private static Logger logger = LoggerFactory.getLogger(LogDao.class);

    @Autowired
    private LogMapper logMapper;

    /**
     * 添加业务日志.
     * @param log 业务日志
     */
    public void save(Log log) {
        try {
            logMapper.save(log);
        } catch (Exception e) {
            logger.error("add log error!", e);
            throw new ServiceException(LogModule.ERR_DAO_ADD, e);
        }
    }

    /**
     * 根据ID获取业务日志.
     * @param id 业务日志ID
     * @return 业务日志实体
     */
    public Log getById(String id) {
        try {
            return logMapper.getById(id);
        } catch (Exception e) {
            logger.error("Get log by id error!", e);
            throw new ServiceException(LogModule.ERR_DAO_GETBYID, e);
        }
    }

    public Page<Log> search(Page<Log> page, Map<String, Object> param) {
        int skip = (page.getPageNo() - 1) * page.getPageSize();
        int limit = page.getPageSize();
        String type = param.get("type") == null ? null: param.get("type").toString();
        String description = param.get("description") == null ? null : param.get("description").toString();
        List<Log> lists = logMapper.search(type, description, skip, limit);
        long count = logMapper.count(type, description);
        page.setResult(lists);
        page.setTotalCount(count);
        return page;
    }
}
