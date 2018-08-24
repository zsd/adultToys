package com.zsd.comm;

import com.zsd.comm.utils.Identities;
import org.junit.Assert;
import org.junit.Test;

/**
 * id生成器测试用例.
 * @author zhousd
 */
public class IdentitiesTest {

    @Test
    public void testUuid() {
        System.out.println("uuid = " + Identities.uuid());
        Assert.assertNotNull(Identities.uuid());
    }

//    @Test
//    public void testUuid2() {
//        System.out.println("uuid2 = " + Identities.uuid2());
//        Assert.assertNotNull(Identities.uuid2());
//    }
//
//    @Test
//    public void testUuid3() {
//        System.out.println("uuid3 = " + Identities.uuid3());
//        Assert.assertNotNull(Identities.uuid3());
//    }
}
