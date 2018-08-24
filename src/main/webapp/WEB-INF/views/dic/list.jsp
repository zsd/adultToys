<%--
  Created by IntelliJ IDEA.
  User: wupb
  Date: 2016/6/12
  Time: 16:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<!-- saved from url=(0048)http://v3.bootcss.com/dic/navbar-fixed-top/ -->
<html lang="zh-CN">
<head>
    <%@ include file="/common/meta.jsp"%>
    <link id="easyuiTheme" rel="stylesheet" type="text/css" href="<c:url value='/static/core/plugins/easyui/themes/default/easyui.css'/>" />
    <link id="iconTheme" rel="stylesheet" type="text/css" href="<c:url value='/static/core/plugins/easyui/themes/icon.css'/>" />
    <script type="text/javascript" src="<c:url value='/static/portal/js/jquery.easyui.min.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/static/core/plugins/easyui/locale/easyui-lang-zh_CN.js'/>"></script>
    <script type="text/javascript" src="${ctx}/js/platform/dic/dic.js"></script>
    <style>
        html,body{
            height:100%;
        }
        td{
            line-height: 30px!important;
        }
        .datagrid>.panel-body{
            padding: 0px;
            border: 0px;
            border-top:1px solid #ccc;
        }
    </style>
</head>
<body>
<div class="wrapper">
    <div class="content">
        <div class="container-fluid" style="padding-top: 15px;">
            <div class="panel panel-default" >
                <div class="panel-body" style="border: 0px;">
                    <form class="form-inline">
                        <div class="form-group">
                            <label>&nbsp;&nbsp;根字典名称：</label>
                            <input type="text" class="form-control" id="item">
                        </div>
                        <button type="button" id="submit" class="btn btn-default">
                            <span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;查询
                        </button>
                    </form>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-body" style="border: 0px;">
                    <div class="row" style="margin-bottom: 10px;">
                        <div class="col-sm-7">
                            <div role="toolbar" class="btn-toolbar">
                                <div class="btn-group dropup">
                                    <button type="button" class="btn btn-success dropdown-toggle"  onclick="addFn();">
                                        新增 <span class="glyphicon glyphicon-plus"></span>
                                    </button>
                                </div>
                                <button type="button" id="update" class="btn btn-default" onclick="showDetail();">
                                    修改</span>
                                </button>
                                <button type="button"  class="btn btn-default" onclick="unselectAllFn();">
                                    清选</span>
                                </button>
                                <button type="button" id="del" class="btn btn-default" onclick="deleteFn();">
                                    删除</span>
                                </button>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <table id="menuGrid"></table>
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
                <div class="panel-body" style="border: 0px;">
                    <div class="col-md-12">
                        <form id="saveForm" name="saveForm" method="post">
                            <input type="hidden" class="form-control" name="id" >
                            <div class="col-md-12">
                                <label><font color="red"></font>父节点：</label>
                                <input type="text" class="form-control" name="parentItem"  readonly>
                                <input type="hidden" class="form-control" name="parentId" >
                            </div>
                            <div class="col-md-12">
                                <label><font color="red">*</font>字典名称：</label>
                                <input type="text" class="form-control" name="item" >
                            </div>
                            <div class="col-md-12">
                                <label><font color="red">*</font>字典值：</label>
                                <input type="text" class="form-control" name="name" >
                            </div>
                            <div class="col-md-12">
                                <label><font color="red">*</font>排序号：</label>
                                <input type="text" class="form-control" name="orderNum" >
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
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
</body>
</html>