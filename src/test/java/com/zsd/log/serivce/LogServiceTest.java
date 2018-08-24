package com.zsd.log.serivce;

import com.zsd.AbstractTest;
import com.zsd.log.data.LogDataProvider;
import com.zsd.log.domain.Log;
import com.zsd.log.service.LogService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 业务日志服务测试.
 * @author zhousd
 */
public class LogServiceTest extends AbstractTest {

    @Autowired
    private LogService logService;

    /**
     * 测试新增业务日志.
     */
    @Test
    public void testAdd() {
        logService.add(LogDataProvider.getLog());
    }

    /**
     * 测试根据ID查询日志.
     */
    @Test
    public void testGetById() {
        String logId = "160de88a1b7c466faef1910c90ad2f9c";
        Log log = logService.getById(logId);
        Assert.assertNotNull(log);
    }


}
