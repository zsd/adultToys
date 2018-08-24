package bussiness.spider.service;

import bussiness.spider.service.spider.GithubRepoPageProcessor;
import org.junit.Test;
import us.codecraft.webmagic.Spider;

public class GithubRepoPageProcessorTest {

    @Test
    public void testRun() {
        Spider.create(new GithubRepoPageProcessor()).addUrl("https://github.com/code4craft").thread(5).run();
    }
}
