var urlPost = ctx+"/post/search";
var o = {
    url:ctx+"/post/",
    allDiv:"selectUserDiv",
    userId:"postId",
    userName:"postName",
    tableInfo:[{name:"岗位名称 ",attr:"name"},
        {name:"用户名称 ",attr:"user",transformation:function(v){
            return v.name;
        }},
        {name:"创建时间 ",attr:"createTime",transformation:function(v){
            return formatDate(v);
        }},
        {name:"更新时间 ",attr:"updateTime",transformation:function(v){
            return formatDate(v);
        }}]
};
var selectPost = new Select(o);
$(function () {
    //默认
    var pageNo = $("#"+selectPost.pageNoId).val();
    var pageSize = $("#"+selectPost.pageSizeId).val();
    var parameter = {
        pageNo:pageNo,
        pageSize:pageSize
    };
    selectPost.searchData(urlPost, parameter);
});

//打开选择用户
function selectUserTest(){
    $("#"+selectPost.modelId).modal("show");
}