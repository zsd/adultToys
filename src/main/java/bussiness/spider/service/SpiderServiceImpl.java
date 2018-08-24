package bussiness.spider.service;

import bussiness.spider.service.pipeline.TaobaoJsonPipeline;
import bussiness.spider.service.spider.TaobaoSpider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import us.codecraft.webmagic.Spider;

/**
 * 淘宝数据采集服务类.
 * @author zhousd
 */
@Service
@Transactional
public class SpiderServiceImpl implements SpiderService {

    private static Logger logger = LoggerFactory.getLogger(SpiderServiceImpl.class);

    @Override
    public void spider(String searchParam) {
        Spider.create(new TaobaoSpider()).addUrl("https://s.taobao.com/search?q=" + searchParam)
                .addPipeline(new TaobaoJsonPipeline("data\\" + searchParam)).run();
    }
}
