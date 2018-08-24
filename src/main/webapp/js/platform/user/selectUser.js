var urlUser = ctx+"/user/search";
var o = {
    url:ctx+"/user/",
    allDiv:"selectUserDiv",
    userId:"userId",
    userName:"userName",
    tableInfo:[{name:"名称 ",attr:"name"},
        {name:"创建时间 ",attr:"createTime",transformation:function(v){
            return formatDate(v);
        }},
        {name:"更新时间 ",attr:"updateTime",transformation:function(v){
            return formatDate(v);
        }}]
};
var selectUser = new Select(o);
$(function () {
    //默认
    var pageNo = $("#"+selectUser.pageNoId).val();
    var pageSize = $("#"+selectUser.pageSizeId).val();
    var parameter = {
        pageNo:pageNo,
        pageSize:pageSize
    };
    selectUser.searchData(urlUser, parameter);
});

//打开选择用户
function selectUserTest(){
    $("#"+selectUser.modelId).modal("show");
}