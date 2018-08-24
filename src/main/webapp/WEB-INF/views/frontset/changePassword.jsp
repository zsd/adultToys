<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<style type="text/css">
    .window {
        background: #fff!important;
        border: 1px solid rgba(0, 0, 0, 0.2);
        padding: 0px;
    }

    #test{
        border: 0px;
    }
    .dialog-button{
        background: #fff;
        padding: 15px;
    }
    .window .window-header {
        background: #fff;
    }

    .testInput {
        display: block;
        width: 80%;
        height: 17px;
        padding: 6px 12px;
        margin-left: 30px;
        margin-right: 30px;
        font-size: 14px;
        line-height: 1.42857143;
        color: #555;
        background-color: #fff;
        background-image: none;
        border: 1px solid #ccc;
        border-radius: 4px;
        -webkit-box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
        box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
        -webkit-transition: border-color ease-in-out .15s,-webkit-box-shadow ease-in-out .15s;
        -o-transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s;
        transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s;
    }
    .testLabel {
        display: inline-block;
        max-width: 100%;
        margin:15px;
        font-weight: 700;
    }
    .l-btn span .l-btn-icon-left {
        padding: 0px;
    }
</style>
<script type="text/javascript" src="${ctx}/js/platform/frontset/changePassword.js"></script>
<div id="test">
    <div>
        <label class="testLabel"><font color="red">*</font>原密码：</label>
        <input type="password" class="testInput" id="oldPassword" >
    </div>
    <div>
        <label class="testLabel"><font color="red">*</font>新密码：</label>
     <input type="password" class="testInput" id="newPassword" >
    </div>
    <div>
        <label class="testLabel"><font color="red">*</font>确认密码：</label>
     <input type="password" class="testInput" id="newPasswordAgain" >
    </div>
</div>
<div class="modal fade" id="changePassword" tabindex="-1" role="dialog"
     aria-labelledby="changePasswordModal" aria-hidden="true">
    <div class="modal-dialog" style="width:700px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close"
                        data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="changePasswordModalLabel">
                    修改密码
                </h4>
            </div>
            <div class="modal-body">
                <div class="panel-body">
                    <div class="col-md-12">
                        <form id="saveForm" name="saveForm" method="post">
                            <div class="col-md-12">
                                <label><font color="red">*</font>原密码：</label>
                                <input type="text" class="form-control" name="oldPassword" >
                            </div>
                            <div class="col-md-12">
                                <label><font color="red">*</font>新密码：</label>
                                <input type="text" class="form-control" name="newPassword" >
                            </div>
                            <div class="col-md-12">
                                <label><font color="red">*</font>再输入一次新密码：</label>
                                <input type="text" class="form-control" name="newPasswordAgain" >
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button id="saveYes" type="button" class="btn btn-primary" onclick="changePassword();">
                    确定
                </button>
                <button id="saveNo" type="button" class="btn" style="display:none;">
                    确定
                </button>
                <button type="button" class="btn btn-default"
                        data-dismiss="modal">取消
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>