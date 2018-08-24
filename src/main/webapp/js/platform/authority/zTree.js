var treeNodes ;
var zTree;
var setting = {
    check: {
        enable: true, //显示复选框
        chkboxType: { "Y": "ps", "N": "ps" }
    },
    data: {
        simpleData: {
            enable:true
        }
    }
};
$(function () {

});

function getTree(roleId){
    $.ajax({
        cache:false,
        type: 'GET',
        dataType : "json",
        url:ctx+"/role/tree?roleId="+roleId ,//请求的action路径
        error: function () {//请求失败处理函数
            alert('请求失败');
        },
        success:function(data){ //请求成功后处理函数。
            treeNodes = data;   //把后台封装好的简单Json格式赋给treeNodes
            zTree = $.fn.zTree.init($("#tree"), setting, treeNodes);
        }
    });
}

function saveTree(){
    //获取角色ID
    var roleId = $("#roleId").val();

    //获取选择节点值
    var treeObj = $.fn.zTree.getZTreeObj("tree");
    var nodes = treeObj.getCheckedNodes(true);
    var chooseId = [];
    for(var i = 0;i<nodes.length;i++){
        chooseId[i] = nodes[i].id;
    }
    var json = {json:JSON.stringify(chooseId)};
    $("#saveTreeYes").hide();
    $("#saveTreeNo").show();
    $.ajax({
        type: 'POST',
        dataType : "text",
        data:json,
        url:ctx+"/role/saveTree?roleId="+roleId ,//请求的action路径
        error: function () {//请求失败处理函数
            jAlert('请求失败', '提示');
        },
        success:function(data){ //请求成功后处理函数。
            jAlert(data, '提示');
            $("#saveTreeYes").show();
            $("#saveTreeNo").hide();
            $("#TreeModal").modal("hide");
        }
    });
}