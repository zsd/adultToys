<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<!-- saved from url=(0048)http://v3.bootcss.com/examples/navbar-fixed-top/ -->
<html lang="zh-CN">
<head>
	<%@ include file="/common/meta.jsp"%>

    <script>
        var addFlage = false;
        var deleteFlage = false;
        var exportFlage = false;
        var changeFlage = false;
        var assignFlage = false;
    </script>
    <c:if test="${AUTHORITY_CONTEXT.contains(\"AGENCY_POST_CHANGE\")}">
        <script>
            changeFlage = true ;
        </script>
    </c:if>
    <c:if test="${AUTHORITY_CONTEXT.contains(\"AGENCY_POST_ADD\")}">
        <script>
            addFlage = true ;
        </script>
    </c:if>
    <c:if test="${AUTHORITY_CONTEXT.contains(\"AGENCY_POST_DELETE\")}">
        <script>
            deleteFlage = true ;
        </script>
    </c:if>
    <c:if test="${AUTHORITY_CONTEXT.contains(\"AGENCY_POST_EXPORT\")}">
        <script>
            exportFlage = true ;
        </script>
    </c:if>
	<c:if test="${AUTHORITY_CONTEXT.contains(\"AGENCY_POST_ASSIGN\")}">
		<script>
			var assignFlage = true ;
		</script>
	</c:if>
	<style>
		td{
			line-height: 30px!important;
		}
	</style>
</head>
<body>
<div class="wrapper">
	<div class="content">
		<div class="container-fluid" style="padding-top: 15px;" id="allDiv">
			<c:if test="${AUTHORITY_CONTEXT.contains(\"AGENCY_POST_SUBMIT\")}">
				<div class="panel panel-default">
					<div class="panel-body">
						<form class="form-inline">
							<div class="form-group">
								<label>&nbsp;&nbsp;名称：</label>
								<input type="text" class="form-control" id="name">
							</div>
							<button type="button" id="submit" class="btn btn-default">
								<span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;查询
							</button>
						</form>
					</div>
				</div>
			</c:if>

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
					岗位信息
				</h4>
			</div>
			<div class="modal-body">
				<div class="panel-body">
					<div class="col-md-12">
						<form id="saveForm" name="saveForm" method="post">
							<input type="hidden" class="form-control" name="id" >
							<div class="col-md-12">
								<label><font color="red">*</font>单位-部门：</label>
								<input type="hidden" class="form-control" name="orgId" y>
								<input type="hidden" class="form-control" name="departmentId" y>
								<input type="hidden" class="form-control" name="orgName" y>
								<input type="text" class="form-control" name="orgDepartmentName" onclick="selectOrgDepartment();"readonly>
							</div>
							<div class="col-md-12">
								<label><font color="red">*</font>用户：</label>
								<input type="hidden" class="form-control" name="userId" y>
								<input type="text" class="form-control" name="userName" onclick="selectUserTest();"readonly>
							</div>
							<div class="col-md-12">
								<label><font color="red">*</font>名称：</label>
								<input type="text" class="form-control" name="name" >
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

<!-- 选择单位部门摸态框 -->
<div class="modal fade" id="selectOrgDepartmentModal" tabindex="-1" role="dialog"
	 aria-labelledby=selectOrgDepartmentModal" aria-hidden="true">
	<div class="modal-dialog" style="width:1000px;">
		<div class="modal-content">
			<div class="modal-header ">
				<button type="button" class="close"
						data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title" id="selectOrgModalLabel">
					选择单位-部门
				</h4>
			</div>
			<div class="modal-body">
				<div class="panel-body" style="padding: 0px;">
					<div class="col-md-6" style="padding: 0px;">
						<div class="wrapper" style="padding: 0px;">
							<div class="content" style="padding: 0px;">
								<div class="container-fluid" style="padding: 0px;">
									<div class="panel panel-default">
										<div class="panel-body">
											<form class="form-inline">
												<div class="form-group">
													<label>&nbsp;&nbsp;单位名称：</label>
													<input type="text" class="form-control" id="orgName">
												</div>
												<button type="button" id="orgSubmit" class="btn btn-default">
													<span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;查询
												</button>
											</form>
										</div>
									</div>
									<div class="panel panel-default">
										<div class="panel-body">
											<div class="row" style="margin-bottom: 10px;">
												<div class="col-sm-12" style="margin-right:0">
													<nav class="pagination" id="selectOrg" style="margin:0;float:right;">
													</nav>
												</div>
											</div>
											<div class="row">
												<input type="hidden" id="orgPageNo" value="1"/>
												<input type="hidden" id="orgPageSize" value="10"/>
												<table class="table table-striped" style="margin-bottom: 0px;" id="orgListTab">
													<thead style="background-color: gainsboro;">
													<tr>
														<th></th>
														<th>名称</th>
														<th>创建时间</th>
														<th>更新时间</th>
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
					<div class="col-md-1" style="padding: 0px;text-align:center;line-height:350px;">
						<label>
							==>
						</label>
					</div>
					<div class="col-md-5" style="padding: 0px;">
						<div class="wrapper" style="padding: 0px;">
							<div class="content" style="padding: 0px;">
								<div class="container-fluid" style="padding: 0px;">
									<div class="panel panel-default">
										<div class="panel-body">
											<form class="form-inline">
												<div class="form-group">
													<label>&nbsp;&nbsp;部门名称：</label>
													<input type="text" class="form-control" id="departmentName">
												</div>
												<button type="button" id="departmentSubmit" class="btn btn-default">
													<span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;查询
												</button>
											</form>
										</div>
									</div>
									<div class="panel panel-default">
										<div class="panel-body">
											<div class="row" style="margin-bottom: 10px;">
												<div class="col-sm-12" style="margin-right:0">
													<nav class="pagination" id="selectOrgDepartment" style="margin:0;float:right;">
													</nav>
												</div>
											</div>
											<div class="row">
												<input type="hidden" id="departmentPageNo" value="1"/>
												<input type="hidden" id="departmentPageSize" value="10"/>
												<table class="table table-striped" style="margin-bottom: 0px;" id="departmentListTab">
													<thead style="background-color: gainsboro;">
													<tr>
														<th></th>
														<th>名称</th>
														<th>创建时间</th>
														<th>更新时间</th>
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
				</div>
			</div>
			<div class="modal-footer">
				<button  type="button" class="btn btn-primary" onclick="getSelectId2();">
					确定
				</button>
				<button type="button" class="btn btn-default"
						data-dismiss="modal" onclick="clear()">取消
				</button>
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div>
<!-- 选择用户摸态框 -->
<div id="selectUserDiv" >

</div>
<%--<%@ include file="../user/selectUser.jsp"%>--%>
<script type="text/javascript" src="${ctx}/js/platform/plugIns/completePaging.js"></script>
<script type="text/javascript" src="${ctx}/js/platform/org/post.js"></script>
<script type="text/javascript" src="${ctx}/js/platform/authority/selectRole.js"></script>
<script type="text/javascript" src="${ctx}/js/platform/plugIns/select.js"></script>
<script type="text/javascript" src="${ctx}/js/platform/org/selectUser.js"></script>
<%@ include file="../../authority/role/selectRole.jsp"%>
</body>
</html>