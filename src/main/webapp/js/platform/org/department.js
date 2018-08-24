var date = new Date();
var month = (date.getMonth()+1);
var nowDate = date.getFullYear()+(month<10?("-0"+month):("-"+month))+((date.getDate())<10?("-0"+(date.getDate())):("-"+(date.getDate())));
var urlDepartment = ctx+"/department/search";

var o = {
    url:ctx+"/department/",
    allDiv:"allDiv",
    authority:[{type:"add",value:addFlage},
        {type:"delete",value:deleteFlage},
        {type:"export",value:exportFlage},
        {type:"change",value:changeFlage}],
    tableInfo:[{name:"名称 ",attr:"name"},
        {name:"所属单位 ",attr:"parent",transformation:function(v){
            return v.name;
        }},
        {name:"创建时间 ",attr:"createTime",transformation:function(v){
            return formatDate(v);
        }},
        {name:"更新时间 ",attr:"updateTime",transformation:function(v){
            return formatDate(v);
        }}]
};
var completePaging = new CompletePaging(o);

$(function () {
    //默认
    var pageNo = $("#"+completePaging.pageNoId).val();
    var pageSize = $("#"+completePaging.pageSizeId).val();
    var parameter = {
        pageNo:pageNo,
        pageSize:pageSize
    };
    completePaging.searchData(urlDepartment, parameter);

    //查询
    $("#submit").click(function () {
        var name = $("#name").val();
        var parameter = {
            name:name
        };
        completePaging.queryCriteria = parameter;
        completePaging.searchData(urlDepartment,parameter);
    });
});

//修改
function showDetail(id) {
    var data =  $('.rowIdImput[value='+id+']').data("datas") ;
    $("input[name=id]").val(data.id);
    $("input[name=name]").val(data.name);
    $("input[name=orgId]").val(data.parent.id);
    var getData = {
        id:data.parent.id
    };
    $.ajax( {
        dataType: "json",
        type: "GET",
        url: ctx+"/department/get",
        data: getData,
        success:function(page){
            $("input[name=orgName]").val(page.orgName);
            $("#myModal").modal("show");
        }
    }) ;
}

//时间格式化
function formatDate (strTime) {
    var date = new Date(strTime);
    var month = (date.getMonth()+1);
    return date.getFullYear()+(month<10?("-0"+month):("-"+month))+((date.getDate())<10?("-0"+(date.getDate())):("-"+(date.getDate())));
}

//保存
function save() {
    var id = $("input[name=id]").val();
    var name = $("input[name=name]").val();
    var orgId = $("input[name=orgId]").val();
    var orgName = $("input[name=orgName]").val();

    var objectEq = {
        id:id,
        name:name,
        parent:{id:orgId}
    } ;

    if (orgName == null || orgName == "") {
        jAlert('请选择单位！', '提示');
        return;
    }

    if (name == null || name == "") {
        jAlert('请填写名称！', '提示');
        return;
    }

    $("#saveYes").hide();
    $("#saveNo").show();
    var json = {json:JSON.stringify(objectEq)};
    $.ajax( {
        dataType: "json",
        type: "post",
        url: ctx+"/department/save",
        data:json,
        success:function(data){
            if (data.code == "200") {
                $("#myModal").modal("hide");
                jAlert(data.msg, '提示');
                var pageNo = 1;
                var pageSize = 10;
                var parameter = {
                    pageNo:pageNo,
                    pageSize:pageSize
                };
                completePaging.searchData(urlDepartment, parameter);
            } else {
                jAlert(data.msg, '提示');
            }
            $("#saveYes").show();
            $("#saveNo").hide();
        }
    }) ;
}

//重写插件的新增方法
completePaging.addFn = function addFn(){
    $("input[name=id]").val("");
    $("input[name=name]").val("");
    $("input[name=orgName]").val("");
    $("#myModal").modal("show");
};

//重写插件的导出方法
completePaging.exportXml = function exportXml(){
    jAlert('待开发！', '提示');
};

//----------------------------           选择单位区域           -------------------------------
var urlOrg = ctx+"/org/search";
var o = {
    url:ctx+"/org/",
    allDiv:"selectOrgDiv",
    userId:"orgId",
    userName:"orgName",
    tableInfo:[{name:"名称 ",attr:"name"},
        {name:"创建时间 ",attr:"createTime",transformation:function(v){
            return formatDate(v);
        }},
        {name:"更新时间 ",attr:"updateTime",transformation:function(v){
            return formatDate(v);
        }}]
};
var selectOrg = new Select(o);
$(function () {
    //默认
    var pageNo = $("#"+selectOrg.pageNoId).val();
    var pageSize = $("#"+selectOrg.pageSizeId).val();
    var parameter = {
        pageNo:pageNo,
        pageSize:pageSize
    };
    selectOrg.searchData(urlOrg, parameter);
});

//打开选择用户
function openSelectOrg(){
    $("#"+selectOrg.modelId).modal("show");
}