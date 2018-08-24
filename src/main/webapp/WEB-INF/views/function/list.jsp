<%--
  Created by IntelliJ IDEA.
  User: 黄辅湘
  Date: 2016/6/22
  Time: 14:40
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
    <script type="text/javascript" src="${ctx}/js/platform/function/function.js"></script>
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
                    菜单信息
                </h4>
            </div>
            <div class="modal-body">
                <div class="panel-body" style="padding: 0px;border: 0px;">
                    <div class="col-md-12"  style="padding-left: 0px;">
                        <form id="saveForm" name="saveForm" method="post">
                            <input type="hidden" class="form-control" name="id" >

                            <div class="col-md-12">
                                <label><font color="red"></font>父节点：</label>
                                <input type="text" class="form-control" name="parentName"  readonly>
                                <input type="hidden" class="form-control" name="parentId" >
                            </div>

                            <div class="col-md-12">
                                <label><font color="red">*</font>名称：</label>
                                <input type="text" class="form-control" name="name" >
                            </div>

                            <div class="col-md-12">
                                <label><font color="red">*</font>编号：</label>
                                <input type="text" class="form-control" name="code" >
                            </div>

                            <div class="col-md-12">
                                <label><font color="red">*</font>类型：</label>
                                <select class="form-control" name="type" id="type">
                                    <option value="1">菜单</option>
                                    <option value="2">按钮</option>
                                </select>
                            </div>

                            <div class="col-md-12">
                                <label>路径：</label>
                                <input type="text" class="form-control" name="src" >
                            </div>

                            <div class="col-md-12">
                                <label><font color="red">*</font>排序：</label>
                                <input type="number" class="form-control" name="orderNum" >
                            </div>

                            <div class="col-md-12">
                                <label>描述：</label>
                                <input type="text" class="form-control" name="description" >
                            </div>

                            <div class="col-md-12">
                                <label><font color="red">*</font>是否显示：</label>
                                <select class="form-control" name="isShow" id="isShow">
                                    <option value="1">是</option>
                                    <option value="0">否</option>
                                </select>
                            </div>

                            <div class="col-md-12">
                                <label>图片路径：</label>
                                <input type="text" class="form-control" name="icon">
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