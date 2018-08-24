<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <%@ include file="/common/meta.jsp"%>
</head>

<body style="background-color: #F9F9F9;">

<div class="container-fluid">
    <div class="row">
        <div class="col-md-12" style="padding-top: 60px;padding-bottom: 30px;">
            <div class="center-block" style="width: 430px;">
                <img src="${ctx}/images/logo.png"/>
            </div>
        </div>
        <div class="col-md-12">
            <div class="panel panel-default center-block"
                 style="width: 350px;padding:10px 20px;">
                <form action="${ctx}/index" method="post">
                    <div class="form-group">
                        <label for="loginName">用户名</label>
                        <input type="text" class="form-control" id="loginName" name="loginName" placeholder="请输入用户名">
                    </div>
                    <div class="form-group">
                        <label for="password">密码</label>
                        <input type="password" class="form-control" id="password" name="password" placeholder="请输入密码">
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-success center-block" style="width: 310px;">登录</button>
                    </div>
                    <p class="animate7 fadeIn" style="  text-align: center;color: red;">${error} </p>
                </form>
            </div>
        </div>
    </div>
</div>

</body>
</html>