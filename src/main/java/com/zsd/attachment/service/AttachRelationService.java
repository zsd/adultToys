package com.zsd.attachment.service;

import com.zsd.attachment.domain.AttachRelation;

/**
 * @author xierh
 * 附件与业务关联关系服务接口.
 */
public interface AttachRelationService {

    /**
     * 插入附件与业务关联实体
     * @param relation
     */
    void insert(AttachRelation relation);

    /**
     * 根据附件ID获取附件与业务关联
     * @param attachId 附件ID
     * @return
     */
    AttachRelation getByAttachId(String attachId);

    /**
     * 根据附件ID删除附件与业务关联
     * @param attachId
     */
    void deleteByAttachId(String attachId);
}
