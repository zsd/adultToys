<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE HTML>
<html>
<head>
	<%@ include file="/common/meta.jsp"%>
	<title>文件存储</title>
</head>

<body>
	<h2>列表</h2>

	<c:forEach items="${page.result}" var="file">
		<p>
			<span>${file.id}</span><span> | </span>
			<span>${file.name}</span><span> | </span>
			<span>${file.path}</span><span> | </span>
			<span>${file.parentId}</span><span> | </span>
		</p>
	</c:forEach>
</body>
</html>