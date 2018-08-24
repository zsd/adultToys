package com.zsd.attachment.service;

import com.zsd.AbstractTest;
import com.zsd.attachment.domain.Attachment;
import com.zsd.comm.orm.Page;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xierh on 2016/7/1.
 */
public class AttachmentServiceTest extends AbstractTest {

    @Autowired
    private AttachmentService attachmentService;

    @Test
    public void testGetById() {
        String id = "62eb4e9bbdb44318b5e50b77ffecc20f";
        Attachment attachment = attachmentService.find(id);
        Assert.assertNotNull(attachment);
    }

    @Test
    public void testSearch() {
        Page<Attachment> page = new Page<>();
        Map<String, Object> param = new HashMap<>();
        page = attachmentService.search(page, param);
        Assert.assertNotNull(page);
    }
}
