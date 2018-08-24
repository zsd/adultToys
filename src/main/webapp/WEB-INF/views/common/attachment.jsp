<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<style>
    .uploadify-button-text {
        color: #FFF;
    }
</style>
<!--附件列表视图-->
<script type="text/template" id="ATTACHMENT_NODE_TMP">
    <div class="cpbBackReason">
        <ul class="cpbReasonList ATTACHMENT_LIST_NODE">

        </ul>
        <@if(!readonly){@>
        <ul class="operateNav">
            <li class="onListItem UPLOAD_BTN"><a href="javascript:void(0);" class="">上传附件</a></li>
        </ul>
        <@}@>
    </div>
</script>

<!--附件列表视图-->
<script type="text/template" id="ATTACHMENT_ITEM_NODE_TMP">
    <@if(!readonly){@>
    <b class="icon45 cpb_rlIco DELETE_ITEM_BTN" style="cursor: pointer" title="删除"></b>
    <@}@>
    <a href="${ctx}/attachment/download?attachId=<@=attachment.id@>&isDownloadDHFS=false&isOnLine=false" title="下载文件"><@=attachment.oldName@></a>
    <span class="cpb_rlBelong">[<@=attachment.fileSize@>KB]</span>
</script>

<!--上传附件弹出框视图-->
<script type="text/template" id="UPLOAD_DIALOG_TMP">
    <div class="x1 pdboxHead" >
        <h3 class="pdboxTit">上传文件</h3>
        <a href="javascript:void(0);" class="icon67 pdboxClo CLOSE"></a>
    </div>

    <div class="pdboxCont" style="z-index:1;width: 450px;height: 350px;">
        <div class="tipMainWrap">

            请选择需要上传的文件<input type="file" name="attachment" id="attachment" /><div id="queue"></div>

        </div>
    </div>

    <div class="pdboxToolbar" style="z-index:-1">
        <div class="pdboxBtns">
            <input type="button" class="xbt1 btw75 CONFIRM" value="确定" name="s">
            <input type="button" class="xbt5 btw75 CANCEL" value="取消" name="s">
        </div>
    </div>
</script>