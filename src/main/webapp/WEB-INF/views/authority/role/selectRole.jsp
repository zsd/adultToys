<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<div class="modal fade" id="selectRoleModal" tabindex="-1" role="dialog"
     aria-labelledby=selectRoleModal" aria-hidden="true">
    <div class="modal-dialog" style="width:800px;">
        <div class="modal-content">
            <div class="modal-header ">
                <button type="button" class="close"
                        data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" >
                    角色分配
                </h4>
            </div>
            <div class="modal-body">
                <div class="panel-body" style="padding: 0px;">
                    <div class="col-md-7" style="padding: 0px;">
                        <div class="wrapper" style="padding: 0px;">
                            <div class="content" style="padding: 0px;">
                                <div class="container-fluid" style="padding: 0px;">
                                    <div class="panel panel-default" style="padding: 0px;">
                                        <div class="panel-body">
                                            <form class="form-inline">
                                                <div class="form-group">
                                                    <label>&nbsp;&nbsp;角色名称：</label>
                                                    <input type="text" class="form-control" id="selectRoleName">
                                                </div>
                                                <button type="button" id="selectRoleSubmit" class="btn btn-default">
                                                    <span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;查询
                                                </button>
                                            </form>
                                        </div>
                                    </div>
                                    <div class="panel panel-default " style="margin: 0px;">
                                        <div class="panel-body" style="height: 300px;overflow-y: auto;">
                                            <div class="row" style="margin-bottom: 10px;">
                                                <div class="col-sm-12" style="margin-right:0">
                                                    <nav class="pagination selectRole" style="margin:0;float:right;">
                                                    </nav>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <input type="hidden" id="selectRolePageNo" value="1"/>
                                                <input type="hidden" id="selectRolePageSize" value="10"/>
                                                <table class="table table-striped" style="margin-bottom: 0px;" id="selectRoleListTab">
                                                    <thead style="background-color: gainsboro;">
                                                    <tr>
                                                        <th><input type="checkbox" class="alltoggle" id="selectAll"></th>
                                                        <th>名称</th>
                                                        <th>描述</th>
                                                        <th>创建时间</th>
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
                    </div>
                    <div class="col-md-1" style="padding: 0px;">
                    </div>
                    <div class="col-md-4" style="padding: 0px;" >
                        <div class="wrapper" style="padding: 0px;">
                            <div class="content" style="padding: 0px;">
                                <div class="container-fluid" style="padding: 0px;">
                                    <div class="panel panel-default" style="margin: 0px;">
                                        <div class="panel-body" style="height: 390px;overflow-y: auto;" id="selectRoleDiv">

                                        </div>
                                    </div>
                                </div><!-- /.container -->
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button  type="button" class="btn btn-primary" onclick="saveSelectRole();">
                    确定
                </button>
                <button type="button" class="btn btn-default"
                        data-dismiss="modal" onclick="clear()">取消
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
