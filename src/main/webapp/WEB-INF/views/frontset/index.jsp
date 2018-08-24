<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//Dtd XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/Dtd/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <script>
        var userId = '${USER_CONTEXT.id}' ;
    </script>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="pragma" content="no-cache" />
    <meta http-equiv="Cache-Control" content="no-cache, must-revalidate" />
    <meta http-equiv="expires" content="0" />

    <%@include file="/WEB-INF/views/inc/include.jsp"%>
    <link rel="icon" href="static/portal/images/favicon.ico">
    <title>试运行</title>

    <link rel="stylesheet" type="text/css" href="<c:url value='/static/portal/style/main-frame.css'/>" />
    <link rel="stylesheet" type="text/css" href="<c:url value='/static/portal/style/frame.css'/>" />
    <script type="text/javascript" src="<c:url value='/static/portal/js/navigation2.js'/>"></script>

    <link rel="stylesheet" type="text/css" href="<c:url value='/static/portal/style/ReportServer.css'/>" />
    <link rel="stylesheet" type="text/css" href="<c:url value='/static/portal/style/ReportServer_002.css'/>" />
    <link rel="stylesheet" type="text/css" href="<c:url value='/static/portal/style/ReportServer_003.css'/>" />
    <link rel="stylesheet" type="text/css" href="<c:url value='/static/portal/style/ReportServer_004.css'/>" />

    <script type="text/javascript">
        function divOver(o){
            $(o).addClass("fs_navigation_item_over");
        }
        function divOut(o){
            $(o).removeClass("fs_navigation_item_over");
        };
        $(function(){
            //createMenu();
            $(".fs_navigation_item").live("click",function(){
                $(".fs_navigation_item").removeClass("fs_navigation_item_click");
                $(this).addClass("fs_navigation_item_click");
            });
            $(".addDiv:eq(0)").click();
            for(var x in menus){
                initLeftMenu(menus[$(".fs_navigation_item_click").parent().parent().parent().attr("flage")]);
                break;
            }

            $('#position').combobox({
                onSelect: function(rec){
                    changePosition(rec.value);
                }
            });
        });

        function closeTads(option){
            $('#tabs').tabs('close',option);
        }

        function toggle(sDivId) {
            if($("#divContent1").css("display")=="none"){

                $("#divContent1").show();
                $("#ex").removeClass("close");
                $("#ex").addClass("open");
                $("#body").layout("panel","north").panel("resize",{height:94});
                $("#body").layout("resize");
            }
            else{
                $("#divContent1").hide();
                $("#ex").removeClass("open");
                $("#ex").addClass("close");
                $("#body").layout("panel","north").panel("resize",{height:40});
                $("#body").layout("resize");
            }

        }

        function show(){
            $("#loading").fadeOut("normal", function(){
                $(this).remove();
            });
        }
        var delayTime;
        $.parser.onComplete = function(){
            if(delayTime)
                clearTimeout(delayTime);
            delayTime = setTimeout(show,500);
        };


    </script>

    <style type="text/css">
        .panel-header{
            background: #f5f5f5;
            border-top-left-radius: 3px;
            border-top-right-radius: 3px;
            border-color:#ccc;
        }
        a.tabs-inner{
            border-color: #ccc !important;
        }
        .panel-body {
            border-color: #ccc;

        }
        .layout-expand .panel-header, .layout-expand .panel-body {
            background: #f5f5f5;
            filter: none;
            overflow: hidden;
        }
        ul.tabs{
            background: #f5f5f5;
        }
        .fontstyle
        {
            table-layout:fixed;
            font-size: 13px;
            color: #ffffff;
            text-decoration: none;

        }
        #menuDiv a{
            float:left;
        }
    </style>
</head>
<body id="body" class="easyui-layout" >
<div id="loading" style="position: absolute;   z-index: 1000; top: 0px; left: 0px; width: 100%; height: 100%; background: #ffffff; text-align: center; padding-top: 20%;">
    <!-- 加载中...... -->
    <div style="width: 200px; height: 100px;MARGIN-RIGHT: auto; MARGIN-LEFT: auto;" class="mymask">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
    <!-- <div style="color:red; background: url('images/loading.gif') no-repeat scroll 5px center #ffffff;">1231231</div> -->
    <!-- <div style="display: block;" width: 100%; height: 100% class="datagrid-mask-msg">正在处理，请稍待。。。</div> -->
</div>
<div id="menuDiv" style="  position: absolute; z-index: 99; width: 95%;margin-top:10px;">
    ${bannerMenu}
</div>
<div style="width: 172px;z-index:999;position: absolute;" class="fs_userp_normal ui-state-enabled fs_userp_withnavigation;" id="fs_userp">
    <ul>
        <li class="fs_userp_item">
            <a class="fs_userp_register">&nbsp;</a>
        </li>
        <li class="fs_userp_item fs_userp_userqr">
            <a class="fs_userp_username">用户名：${USER_CONTEXT.name}&nbsp;</a>
        </li>
        <li class="fs_userp_item fs_userp_userqr">
            <a onclick="openChangePassword();">&nbsp;&nbsp;修改密码</a>
        </li>
    </ul>
</div>

<div style="width: 52px;z-index:999;position: absolute;right:10px;top:60px;" class="fs_userp_normal ui-state-enabled fs_userp_withnavigation;" >
    <a href="${ctx}/logout" style="padding-left: 16px;color:#fff;" class="fs_userp_exit">退出&nbsp;</a>
</div>

<div id="topmenu" data-options="region:'north',borde:false"
     style="height: 94px; overflow-y: hidden;background-image: url(<c:url value='/static/portal/style/images/dangxiaobg.png'/>);">
    <div id="fs_default_logo" style="top: 30px;"></div>
    <div class="tophead_bottom_all" style="display:none;">
        <div class="tophead_bottom">
            <div class="tophead_nav">
                <ul id='menuUl' style="display:none;">

                </ul>

                <div id="ex" class="open" style="float: right"
                     onclick="javascript:toggle();"></div>
            </div>
        </div>
    </div>

</div>
<!--sidebar begain-->
<div data-options="region:'west',split:true" title="导航菜单"
     style="width: 220px;">
    <div id="navigation1" border="false">

        <div id="fs_navigationtree_treerender" style="border:0px;">
            <div
                    style="width: auto; height: auto; overflow-y: auto; overflow-x: hidden; border: medium none;"
                    id="bbtree1444787537892" class="ui-state-enabled fs_tree fr-tree">
                <div class="bbit-filtertree-body">
                    <div class="fr-tree-bwrap">
                        <ul id="navigationUl" class="fr-tree-root undefined">

                        </ul>
                    </div>
                </div>
            </div>
        </div>

    </div>
    <div id="mm" class="easyui-menu cs-tab-menu">
        <div id="mm-tabupdate">刷新</div>
        <div class="menu-sep"></div>
        <div id="mm-tabclose">关闭</div>
        <div id="mm-tabcloseother">关闭其他</div>
        <div id="mm-tabcloseall">关闭全部</div>
    </div>
</div>
<!--sidebar end-->
<!--main begain-->
<div data-options="region:'center'" style="padding: 0;">
    <div id="tabs" class="easyui-tabs" fit="true" border="false"></div>
</div>
<!--main end-->
<!--bot begain-->
<div region="south" split="false" style="height: 30px;">
    <div class="bot_wrap_right">copyright &copy; 2018-2019 试运行小组
        (推荐浏览器：chrome、360极速浏览器、IE9.0以上等)
    </div>
</div>
<!--bot end-->
<%@ include file="../frontset/changePassword.jsp"%>
</body>
</html>
<script type="text/javascript">
    var menus=$.parseJSON('${menus}');

    $(".addDiv").find("img").attr("src",ctx+'/static/portal/imgs/s.gif');
    $(".addDiv:eq("+($(".addDiv").length-1)+")").find(".fs_navigation_item").addClass('fs_navigation_item_click') ;

</script>
