<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!--选择页数的下拉框模版-->
<script type="text/template" id="SELECT_PAGE_NODE_TMP">
    <div class="ai_box">
        <@_.each(items, function(elem, index, list) {@>
        <a class="ais" value="<@=elem.id@>"><div class="ait"><@=elem.name@></div></a>
        <@});@>
    </div>
</script>