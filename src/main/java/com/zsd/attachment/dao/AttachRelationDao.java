package com.zsd.attachment.dao;

import com.zsd.attachment.AttachmentModule;
import com.zsd.attachment.dao.mapper.AttachRelationMapper;
import com.zsd.attachment.domain.AttachRelation;
import com.zsd.comm.exception.DataAccessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author xierh
 * 附件与业务关联关系数据访问接口.
 */
@Repository
public class AttachRelationDao {

    private static Logger logger = LoggerFactory.getLogger(AttachRelationDao.class);

    @Autowired
    private AttachRelationMapper mapper;

    /**
     * 插入附件关联.
     * @param attachRelation 附件关联实体
     */
    public void insert(AttachRelation attachRelation) {
        try {
            mapper.insert(attachRelation);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new DataAccessException(AttachmentModule.ERR_DAO_ATTACHMENTRELATION_INSERT, e);
        }
    }

    /**
     * 根据附件ID获取附件与业务关联
     * @param attachId 附件ID
     * @return
     */
    public AttachRelation getByAttachId(String attachId) {
        try {
            return mapper.getByAttachId(attachId);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new DataAccessException(AttachmentModule.ERR_DAO_ATTACHMENTRELATION_GETBYATTACHID, e);
        }
    }

    public void deleteByAttachId(String attachId) {
        try {
            mapper.deleteByAttachId(attachId);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new DataAccessException(AttachmentModule.ERR_DAO_ATTACHMENTRELATION_DELETEBYATTACHID, e);
        }
    }
}
