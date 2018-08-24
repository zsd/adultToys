var url = ctx+"/org/search";
var o = {
    url:ctx+"/org/",                                                                                                                                                                                                                     
    allDiv:"allDiv",
    authority:[{type:"add",value:addFlage},
               {type:"delete",value:deleteFlage},
               {type:"export",value:exportFlage},
               {type:"change",value:changeFlage}],
    tableInfo:[{name:"名称 ",attr:"name"},
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
    completePaging.searchData(url, parameter);
    
    //查询
    $("#submit").click(function () {
        var name = $("#name").val();
        var parameter = {
            name:name
        };
        //保存查询条件
        completePaging.queryCriteria = parameter;
        completePaging.searchData(url,parameter);
    });
});

//修改
function showDetail(id) {
    var data =  $('.'+completePaging.rowIdImputClass+'[value='+id+']').data("datas") ;
    $("input[name=id]").val(data.id);
    $("input[name=name]").val(data.name);
    $("#myModal").modal("show");
}

//保存
function save() {
    var id = $("input[name=id]").val();
    var name = $("input[name=name]").val();

    var objectEq = {
        id:id,
        name:name
    } ;

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
        url: ctx+"/org/save",
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
                completePaging.searchData(url, parameter);
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
    $("#myModal").modal("show");
};

//重写插件的导出方法
completePaging.exportXml = function exportXml(){
    jAlert('待开发！', '提示');
};

//时间格式化
function formatDate(strTime) {
    var date = new Date(strTime);
    var month = (date.getMonth()+1);
    return date.getFullYear()+(month<10?("-0"+month):("-"+month))+((date.getDate())<10?("-0"+(date.getDate())):("-"+(date.getDate())));
}