var date = new Date();
var month = (date.getMonth()+1);
var nowDate = date.getFullYear()+(month<10?("-0"+month):("-"+month))+((date.getDate())<10?("-0"+(date.getDate())):("-"+(date.getDate())));
var urlPost = ctx+"/post/search";
var urlDepartment = ctx+"/department/search";
var urlOrg = ctx+"/org/search";
var o = {
    url:ctx+"/post/",
    allDiv:"allDiv",
    authority:[{type:"add",value:addFlage},
        {type:"delete",value:deleteFlage},
        {type:"export",value:exportFlage},
        {type:"change",value:changeFlage}],
    buttonInfo:[{name:"分配角色",method:"selectRoleFn",authority:assignFlage}],
    tableInfo:[{name:"岗位名称",attr:"name"},
        {name:"用户名",attr:"user",transformation:function(v){
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
    completePaging.searchData(urlPost, parameter);

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
    var data =  $('.rowIdImput[value='+id+']').data("datas") ;
    $("input[name=id]").val(data.id);
    $("input[name=name]").val(data.name);
    $("input[name=departmentId]").val(data.parent.id);
    $("input[name=userId]").val(data.user.id);
    var getData = {
        id:data.parent.id,
        userId:data.user.id
    };
    $.ajax( {
        dataType: "json",
        type: "GET",
        url: ctx+"/post/get",
        data: getData,
        success:function(page){
            $("input[name=orgDepartmentName]").val(page.orgName+"-"+page.departmentName);
            $("input[name=userName]").val(page.userName);
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
    var departmentId = $("input[name=departmentId]").val();
    var userId = $("input[name=userId]").val();

    var objectEq = {
        id:id,
        name:name,
        parent:{id:departmentId},
        user:{id:userId}
    } ;

    if (name == null || name == "") {
        jAlert('请填写名称！', '提示');
        return;
    }

    if (departmentId == null || departmentId == "") {
        jAlert('请选择单位和部门！', '提示');
        return;
    }

    if (userId == null || userId == "") {
        jAlert('请选择用户！', '提示');
        return;
    }

    $("#saveYes").hide();
    $("#saveNo").show();
    var json = {json:JSON.stringify(objectEq)};
    $.ajax( {
        dataType: "json",
        type: "post",
        url: ctx+"/post/save",
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
                searchData(urlPost, parameter);
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
    $("input[name=orgDepartmentName]").val("");
    $("input[name=userName]").val("");
    $("#myModal").modal("show");
};

//重写插件的导出方法
completePaging.exportXml = function exportXml(){
    jAlert('待开发！', '提示');
};



$(function () {
    //默认
    var orgPageNo = $("#orgPageNo").val();
    var orgPageSize = $("#orgPageSize").val();
    var orgParameter = {
        pageNo:orgPageNo,
        pageSize:orgPageSize
    };
    searchOrgData(urlOrg, orgParameter);
    //查询单位
    $("#orgSubmit").click(function () {
        var orgName = $("#orgName").val();
        var orgParameter = {
            name:orgName
        };
        searchOrgData(urlOrg,orgParameter);
    });

    //查询部门
    $("#departmentSubmit").click(function () {
        var orgId = $("input[name=orgId]").val();
        if(orgId == "" || orgId == null){
            jAlert('请先选择单位！', '提示');
            return ;
        }
        var departmentName = $("#departmentName").val();
        var departmentParameter = {
            name:departmentName,
            orgId:orgId
        };
        searchDepartmentData(urlDepartment,departmentParameter);
    });
});

//查询单位分页数据并显示到页面
function searchOrgData(url,data){
    $.ajax( {
        dataType: "json",
        type: "post",
        url: url,
        data: data,
        success:function(page){
            var result = page.result;
            var table = '';
            try {
                for (var i=0;i<result.length;i++) {
                    table +=
                        '<tr>'+
                        '<td><input class="rowIdImput" type="radio" name="orgItems" onclick="getSelectOrgId();" value="'+ result[i].id +'"></td>'+
                        '<td>'+ result[i].name + '</td>'+
                        '<td>'+ formatDate(result[i].createTime) + '</td>'+
                        '<td>'+ formatDate(result[i].updateTime) + '</td>';
                    table +=
                        '</tr>';
                }
                //添加列表数据
                $(table).appendTo($("#orgListTab").find("tbody").empty());

                //添加分页信息
                var pageNo = page.pageNo;
                var totalPages = page.totalPages;

                var firstPage = '<li><a href="javascript:gotoPage2('+ page.startPage +');">&lt;&lt;</a></li>';
                var prePage = '<li><a href="javascript:gotoPage2('+ page.prePage +');">&lt;</a></li>';
                var page1 = getPage2(pageNo, totalPages);
                var nextPage = '<li><a class="page-item" href="javascript:gotoPage2('+ page.nextPage +');">&gt;</a></li>';
                var endPages = '<li><a class="page-item" href="javascript:gotoPage2('+ page.totalPages +');">&gt;&gt;</a></li>';
                var pageInfo = firstPage + prePage + page1 + nextPage + endPages;
                $(pageInfo).appendTo($("#selectOrg").empty());
                for (var i=0;i<result.length;i++) {
                    $('input[name=orgItems][value='+result[i].id+']').data("datas",result[i]);
                }
            } catch (e) {
                table += '<tr><td colspan="6" align="center"><font style="font-weight: bold; color: red">无相关记录存在！</font></td></tr>';
                $(table).appendTo($("#orgListTab").find("tbody").empty());
            }
        }
    }) ;
}

function gotoPage2(newPageNo){
    $("#orgPageNo").val(newPageNo);
    var orgPageNo = $("#orgPageNo").val();
    var orgPageSize = $("#orgPageSize").val();
    var name = $("#orgName").val();
    var args = {
        pageNo:orgPageNo,
        pageSize:orgPageSize,
        name:name
    };
    searchOrgData(urlOrg, args);
}

//获取已选择的单位ID字符串
function getSelectOrgId(){
    var selectElement = $("input[name='orgItems']:checked");

    if(selectElement.length==0){
        jAlert('请选择单位！', '提示');
        return ;
    }else{
        var data =  selectElement.data("datas") ;
        $("input[name=orgId]").val(data.id);
        $("input[name=orgName]").val(data.name);
    }

    //默认
    var pageNo = 1;
    var pageSize = 10;
    var orgId = $("input[name=orgId]").val();
    var parameter = {
        pageNo:pageNo,
        pageSize:pageSize,
        orgId:orgId
    };
    searchDepartmentData(urlDepartment, parameter);
}

function getPage2(pageNo, totalPages) {
    var page1 = "";
    if (totalPages <= 3) {
        for (var i=1; i<totalPages+1; i++) {
            page1 += '<li><a href="javascript:gotoPage2('+i+');">'+ i +'</a></li>';
        }
    } else {
        if (pageNo <= 2) {
            page1  = '<li><a href="javascript:gotoPage2(1);">1</a></li>' +
                '<li><a href="javascript:gotoPage2(2);">2</a></li>' +
                '<li><a href="javascript:gotoPage2(3);">3</a></li>';
            return page1;
        }
        var count = totalPages - pageNo;
        if (count == 0) {
            var startCount = totalPages - 2;
            var middleCount = totalPages - 1;
            page1  = '<li><a href="javascript:gotoPage2('+startCount+');">'+startCount+'</a></li>' +
                '<li><a href="javascript:gotoPage2('+middleCount+');">'+middleCount+'</a></li>' +
                '<li><a href="javascript:gotoPage2('+totalPages+');">'+totalPages+'</a></li>';
            return page1;
        } else {
            var startCount = pageNo -1;
            var endCount = pageNo +1;
            page1 = '<li><a href="javascript:gotoPage2('+startCount+');">'+startCount+'</a></li>' +
                '<li><a href="javascript:gotoPage2('+pageNo+');">'+pageNo+'</a></li>' +
                '<li><a href="javascript:gotoPage2('+endCount+');">'+endCount+'</a></li>';
            return page1;
        }
    }
    return page1;
}

//打开选择单位部门
function selectOrgDepartment(){
    $("#selectOrgDepartmentModal").modal("show");
}

//查询部门分页数据并显示到页面
function searchDepartmentData(url,data){
    $.ajax( {
        dataType: "json",
        type: "post",
        url: url,
        data: data,
        success:function(page){
            var result = page.result;
            var table = '';
            try {
                for (var i=0;i<result.length;i++) {
                    table +=
                        '<tr>'+
                        '<td><input class="rowIdImput" type="radio" name="departmentItems" value="'+ result[i].id +'"></td>'+
                        '<td>'+ result[i].name + '</td>'+
                        '<td>'+ formatDate(result[i].createTime) + '</td>'+
                        '<td>'+ formatDate(result[i].updateTime) + '</td>';
                    table +=
                        '</tr>';
                }
                //添加列表数据
                $(table).appendTo($("#departmentListTab").find("tbody").empty());

                //添加分页信息
                var pageNo = page.pageNo;
                var totalPages = page.totalPages;

                var firstPage = '<li><a href="javascript:gotoPage3('+ page.startPage +');">&lt;&lt;</a></li>';
                var prePage = '<li><a href="javascript:gotoPage3('+ page.prePage +');">&lt;</a></li>';
                var page1 = getPage3(pageNo, totalPages);
                var nextPage = '<li><a class="page-item" href="javascript:gotoPage3('+ page.nextPage +');">&gt;</a></li>';
                var endPages = '<li><a class="page-item" href="javascript:gotoPage3('+ page.totalPages +');">&gt;&gt;</a></li>';
                var pageInfo = firstPage + prePage + page1 + nextPage + endPages;
                $(pageInfo).appendTo($("#selectOrgDepartment").empty());
                for (var i=0;i<result.length;i++) {
                    $('input[name=departmentItems][value='+result[i].id+']').data("datas",result[i]);
                }
            } catch (e) {
                table += '<tr><td colspan="6" align="center"><font style="font-weight: bold; color: red">无相关记录存在！</font></td></tr>';
                $(table).appendTo($("#departmentListTab").find("tbody").empty());
            }
        }
    }) ;
}

function gotoPage3(newPageNo){
    $("#departmentPageNo").val(newPageNo);
    var departmentPageNo = $("#departmentPageNo").val();
    var departmentPageSize = $("#departmentPageSize").val();
    var name = $("#departmentName").val();
    var orgId = $("input[name=orgId]").val();
    var args = {
        pageNo:departmentPageNo,
        pageSize:departmentPageSize,
        name:name,
        orgId:orgId
    };
    searchDepartmentData(urlDepartment, args);
}

function getPage3(pageNo, totalPages) {
    var page1 = "";
    if (totalPages <= 3) {
        for (var i=1; i<totalPages+1; i++) {
            page1 += '<li><a href="javascript:gotoPage3('+i+');">'+ i +'</a></li>';
        }
    } else {
        if (pageNo <= 2) {
            page1  = '<li><a href="javascript:gotoPage3(1);">1</a></li>' +
                '<li><a href="javascript:gotoPage3(2);">2</a></li>' +
                '<li><a href="javascript:gotoPage3(3);">3</a></li>';
            return page1;
        }
        var count = totalPages - pageNo;
        if (count == 0) {
            var startCount = totalPages - 2;
            var middleCount = totalPages - 1;
            page1  = '<li><a href="javascript:gotoPage3('+startCount+');">'+startCount+'</a></li>' +
                '<li><a href="javascript:gotoPage3('+middleCount+');">'+middleCount+'</a></li>' +
                '<li><a href="javascript:gotoPage3('+totalPages+');">'+totalPages+'</a></li>';
            return page1;
        } else {
            var startCount = pageNo -1;
            var endCount = pageNo +1;
            page1 = '<li><a href="javascript:gotoPage3('+startCount+');">'+startCount+'</a></li>' +
                '<li><a href="javascript:gotoPage3('+pageNo+');">'+pageNo+'</a></li>' +
                '<li><a href="javascript:gotoPage3('+endCount+');">'+endCount+'</a></li>';
            return page1;
        }
    }
    return page1;
}

function getSelectId2(){
    var selectElement = $("input[name='departmentItems']:checked");

    if(selectElement.length==0){
        jAlert('请选择部门！', '提示');
        return ;
    }else{
        var data =  selectElement.data("datas") ;
        $("input[name=departmentId]").val(data.id);
        $("input[name=orgDepartmentName]").val($("input[name=orgName]").val()+"-"+data.name);
        $("#selectOrgDepartmentModal").modal("hide");
    }
}