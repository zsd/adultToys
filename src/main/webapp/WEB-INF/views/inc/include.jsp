<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<link id="easyuiTheme" rel="stylesheet" type="text/css" href="<c:url value='/static/core/plugins/easyui/themes/default/easyui.css'/>" />
<link id="iconTheme" rel="stylesheet" type="text/css" href="<c:url value='/static/core/plugins/easyui/themes/icon.css'/>" />
<script type="text/javascript" src="<c:url value='/static/core/plugins/jquery/jquery-1.8.0.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/static/core/plugins/extends/jquery.withub.core.js'/>"></script>
<script type="text/javascript" src="<c:url value='/static/core/plugins/easyui/jquery.easyui.withub.js'/>"></script>
<script type="text/javascript" src="<c:url value='/static/core/plugins/easyui/locale/easyui-lang-zh_CN.js'/>"></script>

<script type="text/javascript">
	var ctx = "${ctx}";
	$(function(){
		$("body").ajaxComplete(function(event,request, settings){  
			  for(var i=0 ;i<$(".datagrid-row").length;i++){
				  if((i+1)%2==0){
					  $($(".datagrid-row")[i]).css( "background","#F2F2F2");
				  }
			  }
		}); 
	});
</script>
<style type="text/css">
/* .datagrid-row:nth-child(even){
    background: #F2F2F2;
} */
.datagrid-row{background:expression(this.sectionRowIndex%2==0?"#F2F2F2":"#F2F2F2")}
 .datagrid-row-over{
    background: #eaf2ff !important;
} 
.datagrid-row-selected {
    background: #fbec88 !important;
}
</style>