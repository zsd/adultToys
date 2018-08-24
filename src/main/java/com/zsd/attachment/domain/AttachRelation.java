package com.zsd.attachment.domain;

import com.zsd.comm.domain.EntityImpl;

/**
 * @author xierh
 * 附件与业务关联关系实体.
 */
public class AttachRelation extends EntityImpl {

    private Attachment attachment;//附件实体

    private int objectType;//业务对象类型

    private String objectId;//业务对象ID

    public AttachRelation() {
    }

    public AttachRelation(Attachment attachment, int objectType, String objectId) {
        this.attachment = attachment;
        this.objectType = objectType;
        this.objectId = objectId;
    }

    public Attachment getAttachment() {
        return attachment;
    }

    public void setAttachment(Attachment attachment) {
        this.attachment = attachment;
    }

    public int getObjectType() {
        return objectType;
    }

    public void setObjectType(int objectType) {
        this.objectType = objectType;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

}
