package com.zsd.attachment.dao;

import com.zsd.attachment.AttachmentModule;
import com.zsd.attachment.dao.mapper.AttachmentMapper;
import com.zsd.attachment.domain.Attachment;
import com.zsd.comm.domain.Entity;
import com.zsd.comm.exception.DataAccessException;
import com.zsd.user.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author xierh
 * 附件数据访问对象.
 */
@Repository
public class AttachmentDao {

    private static Logger logger = LoggerFactory.getLogger(AttachmentDao.class);

    @Autowired
    private AttachmentMapper mapper;

    /**
     * 插入附件.
     * @param attachment 附件实体对象
     * @param user       用户对象
     */
    public void insert(Attachment attachment, User user) {
        try {
            Date time = new Date();
            attachment.setCreateTime(time);
            attachment.setUpdateTime(time);
            mapper.insert(attachment, user);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new DataAccessException(AttachmentModule.ERR_DAO_ATTACHMENT_INSERT, e);
        }
    }

    /**
     * 更新附件.
     * @param attachment 附件实体对象
     * @param user       用户对象
     */
    public void update(Attachment attachment, User user) {
        try {
            attachment.setUpdateTime(new Date());
            mapper.update(attachment, user);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new DataAccessException(AttachmentModule.ERR_DAO_ATTACHMENT_UPDATE, e);
        }
    }

    /**
     * 更新附件新名称
     *
     * @param id      附件流水号
     * @param newName 附件新名称
     * @param user    用户对象
     */
    public void updateNewName(String id, String newName, User user) {
        try {
            mapper.updateNewName(id, newName, user, new Date());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new DataAccessException(AttachmentModule.ERR_DAO_ATTACHMENT_UPDATE_NEW_NAME, e);
        }
    }

    /**
     * 删除附件
     *
     * @param attachment 附件实体对象
     */
    public void delete(Attachment attachment) {
        this.delete(String.valueOf(attachment.getId()));
    }

    /**
     * 删除附件
     *
     * @param id 附件流水号
     */
    public void delete(String id) {
        try {
            mapper.delete(id);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new DataAccessException(AttachmentModule.ERR_DAO_ATTACHMENT_DELETE, e);
        }
    }

    /**
     * 获取附件
     *
     * @param id 附件流水号
     */
    public Attachment find(String id) {
        try {
            return mapper.find(id);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new DataAccessException(AttachmentModule.ERR_DAO_ATTACHMENT_FIND, e);
        }
    }

    /**
     * 获取附件集合
     *
     * @param entity 与附件关联的实体对
     */
    public List<Attachment> finds(Entity entity) {
        return this.finds(String.valueOf(entity.getId()));
    }

    /**
     * 获取附件集合
     *
     * @param entityId 与附件关联的实体对象流水号
     */
    public List<Attachment> finds(String entityId) {
        try {
            return mapper.finds(entityId);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new DataAccessException(AttachmentModule.ERR_DAO_ATTACHMENT_FINDS, e);
        }
    }

    /**
     * 根据业务对象ID和业务类型获取附件
     * @param objectId
     * @param type
     * @return
     */
    public List<Attachment> getList(String objectId, int type) {
        try {
            return mapper.getList(objectId, type);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new DataAccessException(AttachmentModule.ERR_DAO_ATTACHMENT_GETLIST, e);
        }
    }

    public List<Attachment> search(Map<String, Object> param) {
        try {
            return mapper.search(param);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new DataAccessException(AttachmentModule.ERR_DAO_ATTACHMENT_SEARCH, e);
        }
    }

    public long count(Map<String, Object> param) {
        try {
            return mapper.count(param);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new DataAccessException(AttachmentModule.ERR_DAO_ATTACHMENT_COUNT, e);
        }
    }
}
