var url = ctx+'/dic/search?id=root';
$(function(){
    //初始化菜单列表
    init(url);
    //查询
    $("#submit").click(function () {
        var item = $("#item").val();
        if(item == null || item == ''){
            url = ctx+'/dic/search?id=root';
        }else{
            url = ctx+'/dic/submit?item='+encodeURI(encodeURI(item));
        }
        init(url);
    });
});

function init(url) {
    var treegrid = $("#menuGrid").treegrid({
        url: url,
        method: 'get',
        rownumbers: true,
        idField: 'id',
        height:300,
        treeField: 'item',
        fitColumns : true,
        columns : [ [ {
            field : 'item',
            title : '字典名称',
            width : 250
        }, {
            field : 'name',
            title : '字典值',
            width : 250,
        },{
            field : 'orderNum',
            title : '排序号',
            width : 250
        },{
            field : 'description',
            title : '描述',
            width : 250
        }
        ] ],
        onLoadSuccess: function() {
            $('#menuGrid').treegrid('collapseAll');
        }
    });
}

var parentRowItem  = '';
function getParentRow(datas,parentId){
    for(var i=0 ; i<datas.length ;i++){
        if(datas[i].id==parentId){
            parentRowItem = datas[i].item;
            $("input[name=parentItem]").val(parentRowItem);
        }else if(datas[i].children && datas[i].children.length>0){
            getParentRow(datas[i].children,parentId);
        }
    }
}

//修改
function showDetail(){
    var datas =  $("#menuGrid").treegrid("getData");
    var row = $("#menuGrid").treegrid("getSelected");
    if(row&&row!="null"){
        if(row.parent.id!='root'){
            getParentRow(datas,row.parent.id);
            $("input[name=parentId]").val(row.parent.id);
        }else{
            $("input[name=parentItem]").val("");
            $("input[name=parentId]").val("");
        }
        $("input[name=id]").val(row.id);
        $("input[name=item]").val(row.item);
        $("input[name=name]").val(row.name);
        $("input[name=orderNum]").val(row.orderNum);
        $("input[name=description]").val(row.description);
        $("#myModal").modal("show");
    }else{
        jAlert('请选择要修改的记录！', '提示');
    }
}

//保存
function save() {
    var id = $("input[name=id]").val();
    var item = $("input[name=item]").val();
    if(item == null || item == ""){
        jAlert('请填写字典名称','提示');
    }
    var name = $("input[name=name]").val();
    if(name == null || name == ""){
        jAlert('请填写字典值','提示');
    }
    var orderNum = $("input[name=orderNum]").val();
    if(orderNum == null || orderNum == ""){
        jAlert('请填写排序号','提示');
    }
    var description = $("input[name=description]").val();
    if(description == null || description == ""){
        jAlert('请填写描述','提示');
    }
    var parentId =  $("input[name=parentId]").val();
    var parentItem = $("input[name=parentItem]").val();

    if(parentId == ''){
        parentId = 'root';
    }

    var objectEq = {
        id:id,
        item:item,
        name:name,
        description:description,
        orderNum:orderNum,
        parent:{id:parentId,item:parentItem}
    };
    $("#saveYes").hide();
    $("#saveNo").show();
    var json = {json:JSON.stringify(objectEq)};

    $.ajax( {
        dataType: "json",
        type: "post",
        url: ctx+"/dic/save",
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

//新增按钮初始化
function addFn(){
    var row = $("#menuGrid").treegrid("getSelected");
    if(row&&row!="null"){
        $("input[name=parentItem]").val(row.item);
        $("input[name=parentId]").val(row.id);
    }else{
        $("input[name=parentItem]").val("");
        $("input[name=parentId]").val("");
    }
    
    $("input[name=id]").val("");
    $("input[name=item]").val("");
    $("input[name=name]").val("");
    $("input[name=description]").val("");
    $("input[name=orderNum]").val("");
    $("#myModal").modal("show");
}

//清选
function unselectAllFn(){
    $("#menuGrid").treegrid("unselectAll");
}

//删除
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
                url: ctx+"/dic/delete?id=" + row.id,
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