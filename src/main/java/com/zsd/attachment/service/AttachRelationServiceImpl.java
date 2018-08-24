package com.zsd.attachment.service;

import com.zsd.attachment.dao.AttachRelationDao;
import com.zsd.attachment.domain.AttachRelation;
import com.zsd.comm.utils.Identities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author xierh
 * 附件业务关联服务实现.
 */
@Service
@Transactional
public class AttachRelationServiceImpl implements AttachRelationService {

    private static Logger logger = LoggerFactory.getLogger(AttachRelationServiceImpl.class);

    @Autowired
    private AttachRelationDao dao;

    @Override
    public void insert(AttachRelation relation) {
       try {
            relation.setId(Identities.uuid());
            dao.insert(relation);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Override
    public AttachRelation getByAttachId(String attachId) {
        try {
            logger.debug("the attachId is {}", attachId);
            return dao.getByAttachId(attachId);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public void deleteByAttachId(String attachId) {
        try {
            logger.debug("the attachId is {}", attachId);
            dao.deleteByAttachId(attachId);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
}
