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
	</script>
	<c:if test="${AUTHORITY_CONTEXT.contains(\"AGENCY_ORG_CHANGE\")}">
		<script>
			changeFlage = true ;
		</script>
	</c:if>
	<c:if test="${AUTHORITY_CONTEXT.contains(\"AGENCY_ORG_ADD\")}">
		<script>
			addFlage = true ;
		</script>
	</c:if>
	<c:if test="${AUTHORITY_CONTEXT.contains(\"AGENCY_ORG_DELETE\")}">
		<script>
			deleteFlage = true ;
		</script>
	</c:if>
	<c:if test="${AUTHORITY_CONTEXT.contains(\"AGENCY_ORG_EXPORT\")}">
		<script>
			exportFlage = true ;
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
			<c:if test="${AUTHORITY_CONTEXT.contains(\"AGENCY_ORG_SUBMIT\")}">
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
					单位信息
				</h4>
			</div>
			<div class="modal-body">
				<div class="panel-body">
					<div class="col-md-12">
						<form id="saveForm" name="saveForm" method="post">
							<input type="hidden" class="form-control" name="id" >
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
</body>
<script type="text/javascript" src="${ctx}/js/platform/plugIns/completePaging.js"></script>
<script type="text/javascript" src="${ctx}/js/platform/org/org.js"></script>
</html>