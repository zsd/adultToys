package com.zsd.log.service;

import com.zsd.comm.exception.ServiceException;
import com.zsd.comm.orm.Page;
import com.zsd.comm.utils.Identities;
import com.zsd.log.LogModule;
import com.zsd.log.dao.LogDao;
import com.zsd.log.domain.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * 业务日志服务实现类.
 * @author zhousd
 */
@Service
@Transactional
public class LogServiceImpl implements LogService {

    private static Logger logger = LoggerFactory.getLogger(LogServiceImpl.class);

    @Autowired
    private LogDao logDao;

    @Override
    public void add(Log log) {
        try {
            log.setId(Identities.uuid());
            logDao.save(log);
        } catch (Exception e) {
            logger.error("add log error!", e);
            throw new ServiceException(LogModule.ERR_SEV_ADD, e);
        }
    }


    @Override
    public Log getById(String id) {
        try {
            return logDao.getById(id);
        } catch (Exception e) {
            logger.error("get log by id error!", e);
            throw new ServiceException(LogModule.ERR_SEV_GETBYID, e);
        }
    }

    @Override
    public Page<Log> search(Page<Log> page, Map<String, Object> param) {
        return logDao.search(page, param);
    }

}
