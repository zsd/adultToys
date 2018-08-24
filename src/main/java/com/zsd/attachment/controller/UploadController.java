package com.zsd.attachment.controller;

import com.zsd.attachment.domain.AttachRelation;
import com.zsd.attachment.domain.Attachment;
import com.zsd.attachment.service.AttachRelationService;
import com.zsd.attachment.service.AttachmentService;
import com.zsd.comm.context.SecurityContextHolder;
import com.zsd.comm.utils.Identities;
import com.zsd.comm.utils.JsonMapper;
import com.zsd.user.domain.User;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xierh
 * 附件上传控制器
 */
@Controller
@RequestMapping(value = "attachment")
public class UploadController {

    private static Logger logger = LoggerFactory.getLogger(UploadController.class);

    private JsonMapper jsonMapper = new JsonMapper();

    @Autowired
    private AttachmentService attachmentService;

    @Autowired
    private AttachRelationService attachRelationService;

    /**
     *
     * @param request
     * @param response
     * @param isUploadDHFS 是否上传到DHFS true:上传到DHFS,false:上传到本地服务
     * @param id 业务对象ID
     * @param type 业务对象类型
     * @return
     */
    @RequestMapping(value = "upload", method = RequestMethod.POST)
    @ResponseBody
    public String upload(MultipartHttpServletRequest request, HttpServletResponse response,
                         boolean isUploadDHFS, String id, int type ,String json) {
        Map<String, Object> result = new HashMap<>();
        try {
            this.process(request, response, result, isUploadDHFS, id, type,json);
            result.put("code", "200");
            result.put("msg", "上传成功！");
            return jsonMapper.toJson(result);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.put("code", "500");
            result.put("msg", "上传失败！");
            return jsonMapper.toJson(result);
        }
    }

    private void process(MultipartHttpServletRequest request, HttpServletResponse response,
                         Map<String, Object> result, boolean isUploadDHFS, String id, int type, String json) throws IOException {
        MultipartFile file = request.getFile("attachment");
        if (ObjectUtils.equals(file, null)) {
            response.sendError(404, "File not found!");
            return;
        }

        String fileName = file.getOriginalFilename();
        int index = fileName.lastIndexOf(".");
//        String oldName = fileName.substring(index);
        String oldName = fileName;
        String suffix = fileName.substring(index + 1, fileName.length());
        String path = "";
        long size = file.getSize() / 1024;

        Attachment attachment = new Attachment();
        String uuid2 = Identities.uuid();
        attachment.setId(uuid2);
        attachment.setOldName(oldName);
        attachment.setNewName(uuid2+fileName.substring(index));
        attachment.setSuffix(suffix);
        attachment.setFileSize(size);

        User user = SecurityContextHolder.getUser();
        attachment.setCreator(user);

        if (isUploadDHFS) {
            path = attachmentService.write2HDFSByYear(file.getInputStream(), "【"+uuid2+"】"+fileName);//上传到Hadoop
        } else {
            path = attachmentService.write2ServerByYear(file.getInputStream(), "【"+uuid2+"】"+fileName);//上传到本地服务
        }
        attachment.setPath(path);
        attachmentService.insert(attachment, user);

        AttachRelation attachRelation = new AttachRelation(attachment, type, id);
        attachRelationService.insert(attachRelation);
    }
}
