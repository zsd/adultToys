package com.zsd.attachment;

import com.zsd.comm.exception.MessageProvider;

public enum AttachmentModule {

    ERR_DAO_ATTACHMENT_INSERT,
    ERR_DAO_ATTACHMENT_UPDATE,
    ERR_DAO_ATTACHMENT_UPDATE_NEW_NAME,
    ERR_DAO_ATTACHMENT_DELETE,
    ERR_DAO_ATTACHMENT_FIND,
    ERR_DAO_ATTACHMENT_FINDS,

    ERR_DAO_ATTACHMENTRELATION_INSERT,
    ERR_DAO_ATTACHMENT_GETLIST,
    ERR_DAO_ATTACHMENTRELATION_GETBYATTACHID,
    ERR_DAO_ATTACHMENTRELATION_DELETEBYATTACHID,
    ERR_DAO_ATTACHMENT_SEARCH,
    ERR_DAO_ATTACHMENT_COUNT,
    ;

    @Override
    public String toString() {
        return MessageProvider.getResource(AttachmentModule.class).getMessage(this.name());
    }
}
