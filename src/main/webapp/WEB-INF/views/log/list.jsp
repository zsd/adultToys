<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <%@ include file="/common/meta.jsp"%>
    <script type="text/javascript" src="${ctx}/js/platform/log/log.js"></script>
    <style>
        td{
            line-height: 30px!important;
        }
    </style>
</head>
<body>
<div class="wrapper">
    <div class="content">
        <div class="container-fluid" style="padding-top: 15px;">

            <div class="panel panel-default">
                <div class="panel-body">
                    <form class="form-inline">
                        <div class="form-group">
                            <label>&nbsp;&nbsp;类型：</label>
                            <select class="form-control" id="type" name="type">
                                <option value="">请选择</option>
                                <option value="1">添加</option>
                                <option value="2">删除</option>
                                <option value="3">修改</option>
                                <option value="4">查询</option>
                                <option value="5">查看</option>
                                <option value="6">登录</option>
                                <option value="7">登出</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label>&nbsp;&nbsp;描述：</label>
                            <input type="text" class="form-control" id="description">
                        </div>
                        <button type="button" id="submit" class="btn btn-default">
                            <span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;查询
                        </button>
                    </form>
                </div>
            </div>


            <div class="panel panel-default">
                <div class="panel-body">
                    <div class="row" style="margin-bottom: 10px;">
                        <div class="col-sm-7">
                            <div role="toolbar" class="btn-toolbar">
                                <div class="btn-group dropup">
                                </div>
                                <button type="button" id="exp" class="btn btn-default" onclick="exportXml()">
                                    导出<span class="glyphicon glyphicon-download-alt" aria-hidden="true"></span>
                                </button>
                            </div>
                        </div>
                        <div class="col-sm-5" style="margin-right:0">
                            <nav class="pagination" style="margin:0;float:right;">
                            </nav>
                        </div>
                    </div>
                    <div class="row">
                        <input type="hidden" name="pageNo" value="1"/>
                        <input type="hidden" name="pageSize" value="10"/>
                        <table class="table table-striped" style="margin-bottom: 0px;" id="listTab">
                            <thead style="background-color: gainsboro;">
                            <tr>
                                <th><input type="checkbox" class="alltoggle" id="selectAll"></th>
                                <th>业务日志类型</th>
                                <th>日志操作描述</th>
                                <th>登陆人IP</th>
                                <th>模块</th>
                                <th>操作业务实体</th>
                                <th>客户端</th>
                                <th>操作时间</th>
                            </tr>
                            </thead>
                            <tbody class="listTab">
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div><!-- /.container -->
    </div>
</div>
<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close"
                        data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    信息维护
                </h4>
            </div>
            <div class="modal-body">
                <div class="panel-body">
                    <div class="col-md-12">
                        <form id="saveForm" name="saveForm" method="post">
                            <input type="hidden" class="form-control" name="id" >
                            <div class="col-md-12">
                                <label><font color="red">*</font>类型：</label>
                                <input type="text" class="form-control" name="type" >
                            </div>
                            <div class="col-md-12">
                                <label><font color="red">*</font>描述：</label>
                                <input type="text" class="form-control" name="description" >
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button id="saveYes" type="button" class="btn btn-primary" onclick="save();">
                    保存
                </button>
                <button id="saveNo" type="button" class="btn" style="display:none;">
                    保存
                </button>
                <button type="button" class="btn btn-default"
                        data-dismiss="modal">取消
                </button>
            </div>
        </div>
    </div>
</div>
</body>
</html>