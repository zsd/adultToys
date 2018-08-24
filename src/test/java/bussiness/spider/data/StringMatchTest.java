package bussiness.spider.data;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringMatchTest {

    @Test
    public void match1() {
        String str = "c403994-29991816.html";
        String pattern = "c[0-9]*-[0-9]*.html";

        this.match(str, pattern);
    }

    @Test
    public void match2() {
        String str = "http://www.chinawriter.com.cn/n1/2018/0515/c403994-29991816.html";
        String pattern = "http://www\\.chinawriter\\.com\\.cn/n1/([0-9]*)/([0-9]*)/c[0-9]*-[0-9]*\\.html";
        this.match(str, pattern);
    }

    /**
     * 匹配方法.
     */
    private boolean match(String string, String patternStr) {
        Pattern r = Pattern.compile(patternStr);
        Matcher m = r.matcher(string);
        System.out.println(string + "===match===" + patternStr + "=" + m.matches());
        return m.matches();
    }
}
