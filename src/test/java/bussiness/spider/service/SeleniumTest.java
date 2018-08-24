package bussiness.spider.service;

import com.zsd.AbstractTest;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * 测试Selenium、chromeDriver.
 */
public class SeleniumTest extends AbstractTest {

    /**
     * 测试自动打开Chrome浏览器，测试未成功.
     */
    @Test
    public void testChromeDriver() {
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        WebDriver driver = new ChromeDriver();
//        System.setProperty("webdriver.firefox.bin", "/Applications/Firefox.app/Contents/MacOS/firefox-bin");
//        WebDriver driver = new FirefoxDriver();
        driver.navigate().to("https://www.baidu.com");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("The testing page title is: " + driver.getTitle());
        driver.quit();
    }
}
