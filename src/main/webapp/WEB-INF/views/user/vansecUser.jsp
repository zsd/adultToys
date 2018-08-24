<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<!-- saved from url=(0048)http://v3.bootcss.com/examples/navbar-fixed-top/ -->
<html lang="zh-CN">
<head>
	<%@ include file="/common/meta.jsp"%>

	<script type="text/javascript" src="${ctx}/js/platform/org/vansecUser.js"></script>
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
			<c:if test="${AUTHORITY_CONTEXT.contains(\"AGENCY_USER_SUBMIT\")}">
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
			<div class="panel panel-default">
				<div class="panel-body">
					<div class="row" style="margin-bottom: 10px;">
						<div class="col-sm-7">
							<div role="toolbar" class="btn-toolbar">
								<c:if test="${AUTHORITY_CONTEXT.contains(\"AGENCY_USER_EXPORT\")}">
									<button type="button" id="exp" class="btn btn-default" onclick="exportXml()">
										导出<span class="glyphicon glyphicon-download-alt" aria-hidden="true"></span>
									</button>
								</c:if>
							</div>
						</div>
					</div>
					<div class="row">
						<input type="hidden" name="pageNo" value="1"/>
						<input type="hidden" name="pageSize" value="10"/>
						<table class="table table-striped" style="margin-bottom: 0px;" id="listTab">
							<thead style="background-color: gainsboro;">
							<tr>
								<th>部门</th>
								<th>岗位</th>
								<th>名称</th>
								<th>联系电话</th>
								<th>出生日期</th>
								<th>邮箱</th>
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


</body>
</html>