$(function(){


//初始化菜单列表
    $("#menuGrid").treegrid({
        url: ctx+'/function/search?id=root',
        method: 'get',
        rownumbers: true,
        idField: 'id',
        height:300,
        treeField: 'name',
        fitColumns : true,
        columns : [ [ {
        field : 'name',
        title : '菜单名称',
        width : 200
    }, {
            field : 'type',
            title : '类型',
            width : 100,
            formatter:function(value, row, index){
                return value==1?'菜单':"按钮"
            }
        },{
            field : 'code',
            title : '编号',
            width : 200
        }, {
        field : 'src',
        title : '链接地址',
        width : 200
    },  {
        field : 'isShow',
        title : '是否显示',
        width : 100,
        formatter:function(value, row, index){
            return value==1?'是':"否"
        }
    },{
        field : 'orderNum',
            title : '排序号',
            width : 200
    }
    ] ],
        onLoadSuccess: function() {
            $('#menuGrid').treegrid('collapseAll');
        }

});



});
var parentRowName  = '';
function getParentRow(datas,parentId){
    for(var i=0 ; i<datas.length ;i++){
        if(datas[i].id==parentId){
            parentRowName = datas[i].name;
            $("input[name=parentName]").val(parentRowName);
        }else if(datas[i].children && datas[i].children.length>0){
            getParentRow(datas[i].children,parentId);
        }
    }
}
//修改
function showDetail() {
    var datas =  $("#menuGrid").treegrid("getData");
    var row = $("#menuGrid").treegrid("getSelected");
    if(row&&row!="null"){
        if(row.parentId!='root'){
            getParentRow(datas,row.parentId);
            $("input[name=parentId]").val(row.parentId);
        }else{
            $("input[name=parentName]").val("");
            $("input[name=parentId]").val("");
        }

        $("input[name=id]").val(row.id);
        $("input[name=name]").val(row.name);
        $("input[name=code]").val(row.code);
        $("input[name=src]").val(row.src);
        $("input[name=orderNum]").val(row.orderNum);
        $("#type").val(row.type);
        $("input[name=description]").val(row.description);
        $("#isShow").val(row.isShow);
        $("input[name=icon]").val(row.icon);
        $("#myModal").modal("show");
    }else{
        jAlert('请选择要修改的记录！', '提示');
    }


}

//保存
function save() {
    var parentId = $("input[name=parentId]").val();
    var id = $("input[name=id]").val();
    var name = $("input[name=name]").val();
    var code = $("input[name=code]").val();
    var src = $("input[name=src]").val();
    var orderNum = $("input[name=orderNum]").val();
    var type = $("#type").val();
    var description = $("input[name=description]").val();
    var isShow  = $("#isShow").val();
    var  icon = $("input[name=icon]").val();
    var objectEq = {
        name:name,
        code:code,
        src:src,
        orderNum:orderNum,
        type:type,
        description:description,
        isShow:isShow,
        icon:icon
    } ;
    if(parentId!=''){
        objectEq.parentId=parentId;
    }else{
        objectEq.parentId="root";
    }
    if(id!=''){
        objectEq.id=id;
    }

    if (name == null || name == "") {
        jAlert('请填写名称！', '提示');
        return;
    }
    if (code == null || code == "") {
        jAlert('请填写编号！', '提示');
        return;
    }

    $("#saveYes").hide();
    $("#saveNo").show();
    var json = {json:JSON.stringify(objectEq)};
    $.ajax( {
        dataType: "json",
        type: "post",
        url: ctx+"/function/save",
        data:json,
        success:function(data){
            if (data.code == "200") {
                $("#myModal").modal("hide");
                $("#menuGrid").treegrid("reload");
                jAlert(data.msg, '提示');
            } else {
                jAlert(data.msg, '提示');
            }
            $("#saveYes").show();
            $("#saveNo").hide();
        }
    }) ;

}
function addFn(){
    var row = $("#menuGrid").treegrid("getSelected");
    if(row&&row!="null"){
        $("input[name=parentName]").val(row.name);
        $("input[name=parentId]").val(row.id);
    }else{
        $("input[name=parentName]").val("");
        $("input[name=parentId]").val("");
    }
    $("input[name=id]").val("");
    $("input[name=name]").val("");
    $("input[name=code]").val("");
    $("input[name=src]").val("");
    $("input[name=orderNum]").val("");
    $("#type").val(1);
    $("input[name=description]").val("");
    $("#isShow").val(1);
    $("input[name=icon]").val("");
    $("input[name=description]").val("");
    $("#myModal").modal("show");
}

function unselectAllFn(){
    $("#menuGrid").treegrid("unselectAll");
}

function deleteFn(){
    var row = $("#menuGrid").treegrid("getSelected");


        if (!row) {
            jAlert('请选择要删除的记录！', '提示');
            return;
        }
        jConfirm('确定要删除记录?', '提示', function(r) {
            if( r ){
                $.ajax( {
                    dataType: "json",
                    type: "get",
                    url: ctx+"/function/delete?id=" + row.id,
                    success:function(data){
                        if (data.code == "200") {
                            $("#menuGrid").treegrid("reload");
                            jAlert(data.msg, '提示');

                        } else {
                            jAlert(data.msg, '提示');
                        }
                    }
                }) ;
            }
        });
}