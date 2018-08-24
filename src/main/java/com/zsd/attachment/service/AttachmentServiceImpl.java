package com.zsd.attachment.service;

import com.zsd.attachment.dao.AttachmentDao;
import com.zsd.attachment.domain.Attachment;
import com.zsd.comm.domain.Entity;
import com.zsd.comm.hdfs.FileService;
import com.zsd.comm.orm.Page;
import com.zsd.user.domain.User;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * @author xierh
 * 附件访问服务实现.
 */
@Service
@Transactional
public class AttachmentServiceImpl implements AttachmentService {

    private static Logger logger = LoggerFactory.getLogger(AttachmentServiceImpl.class);

    @Autowired
    private AttachmentDao dao;

    @Autowired
    private AttachRelationService attachRelationService;

//    @Value("${hdfs.url}")
    private String HPATH = "hdfs://10.0.8.83:8020/ssim";//DHFS文件存储服务URL

//    @Value("${serverpath.url}")
    private String FILE_PATH = "C://platfile";//服务器存储文件URL

    @Override
    public String write2HDFS(InputStream in, String fileName) {
        try {
            logger.debug("write2HDFS.start");
            String serverPath = fileName;
            FileService fileService = new FileService();
            fileService.writeFile(in, HPATH+"/"+serverPath);
            logger.debug("write2HDFS.end");
            return serverPath;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public String write2HDFSByYear(InputStream in, String fileName) {
        try {
            logger.debug("write2HDFSByYear.start");
            Calendar ca = Calendar.getInstance();
            int currYear = ca.get(Calendar.YEAR);
            int currMonth = ca.get(Calendar.MONTH)+1;
            String serverPath = currYear+"/"+currMonth+"/"+fileName;
            FileService fileService = new FileService();
            fileService.writeFile(in, HPATH+"/"+serverPath);

            logger.debug("write2HDFSByYear.end");
            return serverPath;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public InputStream readFromDHFS(String path) {
        try {
            logger.debug("readFromDHFS.start");
            FileService fileService = new FileService();
            InputStream in = fileService.readFile(HPATH+"/"+path);
            logger.debug("readFromDHFS.end");
            return in;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public String write2ServerByYear(InputStream in, String fileName) {
        try {
            logger.debug("write2ServerByYear.start");
            int len = 0;
            byte[] bs = new byte[1024];
            FileOutputStream out = null;
            Calendar ca = Calendar.getInstance();
            int currYear = ca.get(Calendar.YEAR);
            int currMonth = ca.get(Calendar.MONTH)+1;

            String realPath = this.makeDir(FILE_PATH);
            realPath = this.makeDir(realPath+"/"+currYear);
            realPath = this.makeDir(realPath+"/"+currMonth);
            String serverPath = realPath+"/"+fileName;

            out = new FileOutputStream(serverPath);
            while((len = in.read(bs)) > 0){
                out.write(bs, 0, len);
            }
            in.close();
            out.close();
            String root = currYear+"/"+currMonth+"/"+fileName;
            logger.debug("write2ServerByYear.end");
            return root;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public FileInputStream readFromServer(String path) {
        try {
            logger.debug("readFromServer.start");
            FileInputStream fileInputStream = new FileInputStream(FILE_PATH+"/"+path);
            logger.debug("the path is {}", FILE_PATH+"/"+path);
            return fileInputStream;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public void insert(Attachment attachment, User user) {
        dao.insert(attachment, user);
    }

    @Override
    public void update(Attachment attachment, User user) {
        dao.update(attachment, user);
    }

    @Override
    public void updateNewName(String id, String newName, User user) {
        dao.updateNewName(id, newName, user);
    }

    @Override
    public void delete(Attachment attachment) {
        dao.delete(attachment);
    }

    @Override
    public void delete(String id) {
        try {
            logger.debug("the id is {} ", id);
            Attachment attachment = this.find(id);
            if (ObjectUtils.equals(attachment, null)) {
                return;
            }
            File serverFile = new File(FILE_PATH+"/"+attachment.getPath());
            if (serverFile.isFile() && serverFile.exists()) {
                serverFile.delete();
            }
            File hdfsFile = new File(HPATH+"/"+attachment.getPath());
            if (hdfsFile.isFile() && hdfsFile.exists()) {
                hdfsFile.delete();
            }
            attachRelationService.deleteByAttachId(id);
            dao.delete(id);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Override
    public Attachment find(String id) {
        Attachment attachment = dao.find(id);
        if (attachment !=null) {
            attachment.setAttachRelation(attachRelationService.getByAttachId(attachment.getId()));
        }
        return attachment;
    }

    @Override
    public List<Attachment> finds(Entity entity) {
        return dao.finds(entity);
    }

    @Override
    public List<Attachment> finds(String entityId) {
        return dao.finds(entityId);
    }

    @Override
    public List<Attachment> getList(String objectId, int type) {
        try {
            logger.debug("the objectId and type is {},{}", objectId, type);
            List<Attachment> attachmentList = dao.getList(objectId, type);
            for (Attachment attachment : attachmentList) {
                attachment.setAttachRelation(attachRelationService.getByAttachId(attachment.getId()));
            }
            return attachmentList;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public Page<Attachment> search(Page<Attachment> page, Map<String, Object> param) {
        try {
            int skip = (page.getPageNo() - 1) * page.getPageSize();
            int limit = page.getPageSize();
            param.put("skip", skip);
            param.put("limit", limit);
            List<Attachment> attachmentList = dao.search(param);
            long count = dao.count(param);
            for (Attachment attachment : attachmentList) {
                attachment.setAttachRelation(attachRelationService.getByAttachId(attachment.getId()));
            }
            page.setResult(attachmentList);
            page.setTotalCount(count);
            return page;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 新建文件目录
     * @param root 文件路径
     */
    private static String makeDir(String root){
        File file = new File(root);
        // 判断目录或文件是否存在
        if (!file.exists()) {  // 不存在则创建文件夹
            file.mkdir();
            logger.debug("create file:"+root);
        }
        logger.debug(root+"----already exists.");
        return root;
    }
}
