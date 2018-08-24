package com.zsd.attachment.controller;

import com.zsd.attachment.domain.Attachment;
import com.zsd.attachment.service.AttachmentService;
import com.zsd.comm.utils.JsonMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

/**
 * @author xierh
 * 附件下载控制器
 */
@Controller
@RequestMapping(value = "attachment")
public class DownloadController {

    private static Logger logger = LoggerFactory.getLogger(DownloadController.class);

    private JsonMapper jsonMapper = new JsonMapper();

    @Autowired
    private AttachmentService attachmentService;

    /**
     * 下载文件或在线打开
     * @param attachId 附件ID
     * @param request
     * @param response
     * @param isOnLine 是否在线打开
     * @param isDownloadDHFS 是否从DHFS下载 true:从DHFS下载,false:从本地服务下载
     */
    @RequestMapping(value = "download")
    public void download(String attachId, HttpServletRequest request, HttpServletResponse response,
                         boolean isOnLine, boolean isDownloadDHFS) {
        try {
            logger.debug("the fileId is {}", attachId);
            FileInputStream in = null;
            BufferedInputStream br = null;
            Attachment attachment = attachmentService.find(attachId);
            if (attachment == null || attachment.getPath() == null) {
                response.sendError(404, "File not found!");
                return;
            }

//            String realPath = Constants.FILE_PATH+"/"+attachment.getPath();
            if (isDownloadDHFS) {
                InputStream inputStream = attachmentService.readFromDHFS(attachment.getPath());//从HDFS获取文件流
                br = new BufferedInputStream(inputStream);
            } else {
//                in = new FileInputStream(realPath);//从服务获取文件流
                in = attachmentService.readFromServer(attachment.getPath());//从服务获取文件流
                br = new BufferedInputStream(in);
            }
            byte[] bs = new byte[1024];
            int len = 0;
            //response.reset(); // 非常重要
            if (isOnLine) { // 在线打开方式
                /*URL u = new URL("file:///" + realPath);
                String contentType = u.openConnection().getContentType();
                response.setContentType(contentType);*/
                response.setContentType("application/pdf");
                response.setHeader("Content-Disposition", "inline;filename="+ encode(request.getHeader("User-Agent"), attachment.getOldName()));
                // 文件名应该编码成utf-8
            } else {
                // 纯下载方式
                response.setContentType("application/x-msdownload");
                response.setHeader("Content-Disposition", "attachment;filename="+ encode(request.getHeader("User-Agent"), attachment.getOldName()));
            }
            OutputStream out = response.getOutputStream();
            while ((len = br.read(bs)) > 0) {
                out.write(bs, 0, len);
            }
            out.flush();
            out.close();
            br.close();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * 浏览器编码处理
     * @param userAgent 识别浏览器
     * @param content 编码内容
     * @return
     * @throws UnsupportedEncodingException
     */
    private String encode(String userAgent,String content) throws UnsupportedEncodingException {
        if (userAgent.toLowerCase().indexOf("firefox") >0){
            content = new String(content.getBytes("UTF-8"), "ISO8859-1");
        }else if (userAgent.toUpperCase().indexOf("MSIE") >0){
            content = URLEncoder.encode(content, "UTF-8");
            content = StringUtils.replace(content, "+", "%20");
        } else {
            content = URLEncoder.encode(content, "UTF-8");
            content = StringUtils.replace(content, "+", "%20");
        }
        return content;
    }
}
