package bussiness.spider.service;

import com.zsd.AbstractTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import us.codecraft.webmagic.Spider;

/**
 * 淘宝数据采集服务测试.
 * @author zhousd
 */
public class SpiderServiceTest extends AbstractTest {

    @Autowired
    private SpiderService spiderService;

    /**
     * 测试新增业务日志.
     */
    @Test
    public void testSpider() {
        String searchParam = "旅游";
        spiderService.spider(searchParam);
    }

}
