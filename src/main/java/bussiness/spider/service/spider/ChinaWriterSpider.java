package bussiness.spider.service.spider;

import com.zsd.comm.utils.JsonMapper;
import com.zsd.comm.utils.Unicode2utf8Utils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.JsonPathSelector;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 淘宝爬虫.
 * @author zhousd
 */
public class ChinaWriterSpider implements PageProcessor {

    private JsonMapper jsonMapper = new JsonMapper();

    private static final String urlList = "http://www\\.chinawriter\\.com\\.cn/n1/([0-9]*)/([0-9]*)/c[0-9]*-[0-9]*\\.html";

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(6000)
            .addHeader("Accept-Encoding", "/")
            .setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.59 Safari/537.36");

    @Override
    public void process(Page page) {
        try {
            //列表页
            if (page.getUrl().regex(urlList).match()) {
                String url =page.getUrl().toString();
            }
            //可增加else if 处理不同URL地址
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Site getSite() {
        return site;
    }
}
