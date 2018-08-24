package com.zsd.attachment.domain;

import com.zsd.comm.domain.EntityImpl;
import com.zsd.user.domain.User;

/**
 * @author xierh
 * 附件实体.
 */
public class Attachment extends EntityImpl {

    /**
     * 原附件名
     */
    private String oldName;

    /**
     * 新附件名
     */
    private String newName;

    /**
     * 附件后缀, doc|docx|xsl|xslx 等
     */
    private String suffix;

    /**
     * 附件类型, word|excel|pdf 等
     */
    private String type;

    /**
     * 附件存储路径
     */
    private String path;

    /**
     * 附件大小, 单位KB
     */
    private long fileSize;

    /**
     * 创建者
     */
    private User creator;

    private AttachRelation attachRelation;//附件关联

    public String getOldName() {
        return oldName;
    }

    public void setOldName(String oldName) {
        this.oldName = oldName;
    }

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public AttachRelation getAttachRelation() {
        return attachRelation;
    }

    public void setAttachRelation(AttachRelation attachRelation) {
        this.attachRelation = attachRelation;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }
}
