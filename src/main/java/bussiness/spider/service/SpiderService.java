package bussiness.spider.service;

import us.codecraft.webmagic.Page;

/**
 * 淘宝数据采集服务接口.
 * @author zhousd
 */
public interface SpiderService {

    /**
     * 采集数据执行方法.
     */
    void spider(String searchParam);

}
