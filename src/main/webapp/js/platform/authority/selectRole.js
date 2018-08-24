var selectRoleurl = ctx+"/role/search";
var selectpostId = '';
$(function () {

    //查询
    $("#selectRoleSubmit").click(function () {
        var name = $("#selectRoleName").val();
        var parameter = {
            name:name
        };
        selectRolesearchData(selectRoleurl,parameter);
    });
});

//查询分页数据并显示到页面
function selectRolesearchData(selectRoleurl,data){
    $.ajax( {
        dataType: "json",
        type: "post",
        url: selectRoleurl,
        data: data,
        success:function(page){
            var result = page.result;
            var table = '';
            try {
                for (var i=0;i<result.length;i++) {
                    var checked = '';
                    if($('.selectDiv[id='+result[i].id+']').length>0){
                        checked = 'checked';
                    }

                    table +=
                        '<tr>'+
                        '<td><input class="rowIdImput" '+checked+' type="checkbox" name="selectRoleitems" value="'+ result[i].id +'" onclick="selectRoleTrue(this);"></td>'+
                        '<td>'+ result[i].name + '</td>'+
                        '<td>'+ result[i].description + '</td>'+
                        '<td>'+ formatDate(result[i].createTime) + '</td>';
                    table +=
                        '</tr>';
                }
                //添加列表数据
                $(table).appendTo($("#selectRoleListTab").find("tbody").empty());

                //添加分页信息
                var pageNo = page.pageNo;
                var totalPages = page.totalPages;

                var firstPage = '<li><a href="javascript:selectRolegotoPage('+ page.startPage +');">&lt;&lt;</a></li>';
                var prePage = '<li><a href="javascript:selectRolegotoPage('+ page.prePage +');">&lt;</a></li>';
                var page1 = selectRolegetPage(pageNo, totalPages);
                var nextPage = '<li><a class="page-item" href="javascript:selectRolegotoPage('+ page.nextPage +');">&gt;</a></li>';
                var endPages = '<li><a class="page-item" href="javascript:selectRolegotoPage('+ page.totalPages +');">&gt;&gt;</a></li>';
                var pageInfo = firstPage + prePage + page1 + nextPage + endPages;
                $(pageInfo).appendTo($(".selectRole").empty());
                for (var i=0;i<result.length;i++) {
                    $('.rowIdImput[value='+result[i].id+']').data("datas",result[i]);
                }
            } catch (e) {
                table += '<tr><td colspan="6" align="center"><font style="font-weight: bold; color: red">无相关记录存在！</font></td></tr>';
                $(table).appendTo($("#selectRoleListTab").find("tbody").empty());
            }
        }
    }) ;
}

function selectRolegotoPage(pageNo){
    var pageNo = pageNo;
    var pageSize = 10;
    var name = $("#selectRoleName").val();
    var args = {
        pageNo:pageNo,
        pageSize:pageSize,
        name:name
    };
    selectRolesearchData(selectRoleurl, args);
}


//获取已选择的ID字符串
function selectRolegetSelectId(){
    var ids = '';
    var selectElement = $("input[name='selectRoleitems']:checked");
    if(selectElement.length==0){
        jAlert('请选择单位！', '提示');
        return ;
    }else{
        var data =  selectElement.data("datas") ;
        $("input[name=orgId]").val(data.id);
        $("input[name=orgName]").val(data.name);
        $("#selectOrgModal").modal("hide");

    }

}

function selectRolegetPage(pageNo, totalPages) {
    var page1 = "";
    if (totalPages <= 3) {
        for (var i=1; i<totalPages+1; i++) {
            page1 += '<li><a href="javascript:selectRolegotoPage('+i+');">'+ i +'</a></li>';
        }
    } else {
        if (pageNo <= 2) {
            page1  = '<li><a href="javascript:selectRolegotoPage(1);">1</a></li>' +
                '<li><a href="javascript:selectRolegotoPage(2);">2</a></li>' +
                '<li><a href="javascript:selectRolegotoPage(3);">3</a></li>';
            return page1;
        }
        var count = totalPages - pageNo;
        if (count == 0) {
            var startCount = totalPages - 2;
            var middleCount = totalPages - 1;
            page1  = '<li><a href="javascript:selectRolegotoPage('+startCount+');">'+startCount+'</a></li>' +
                '<li><a href="javascript:selectRolegotoPage('+middleCount+');">'+middleCount+'</a></li>' +
                '<li><a href="javascript:selectRolegotoPage('+totalPages+');">'+totalPages+'</a></li>';
            return page1;
        } else {
            var startCount = pageNo -1;
            var endCount = pageNo +1;
            page1 = '<li><a href="javascript:selectRolegotoPage('+startCount+');">'+startCount+'</a></li>' +
                '<li><a href="javascript:selectRolegotoPage('+pageNo+');">'+pageNo+'</a></li>' +
                '<li><a href="javascript:selectRolegotoPage('+endCount+');">'+endCount+'</a></li>';
            return page1;
        }
    }
    return page1;
}


function selectRoleFn(postId){

    selectpostId = postId;
    initSelectRoleFn();
    //默认
    var parameter = {
        pageNo:1,
        pageSize:10
    };
    selectRolesearchData(selectRoleurl, parameter);
    $("#selectRoleModal").modal("show");
}

function initSelectRoleFn(){
    $("#selectRoleDiv").html("");
    var postData = $('.rowIdImput[value='+selectpostId+']').data("datas");
    var roleList = postData.roleList?postData.roleList:[];
    for(var j=0;j<roleList.length;j++){
        var str =  '<div class="panel panel-default selectDiv" style="background-color: #f9f9f9;padding: 5px;" id="'+roleList[j].id+'">'+
            roleList[j].name+
            '<button type="button" class="close" onclick="delSelectRole(this);">'+
            '×'+
            ' </button>'+
            '</div>';
        $("#selectRoleDiv").append(str);
    }
}

function selectRoleTrue(o){
    var selectElement = $(o).data("datas") ;
    if(o.checked){
        if($("#"+selectElement.id).length==0){
            var str =  '<div class="panel panel-default selectDiv" style="background-color: #f9f9f9;padding: 5px;" id="'+selectElement.id+'">'+
                selectElement.name+
                '<button type="button" class="close" onclick="delSelectRole(this);">'+
                '×'+
                ' </button>'+
                '</div>';
            $("#selectRoleDiv").append(str);
        }
    }else{
        $("#"+selectElement.id).remove();
    }

}
function delSelectRole(o){
    var p = $(o).parent();

    var r = $('.rowIdImput[value='+p.attr("id")+']');
    if(r){
        try {
            r[0].checked = false;
        }catch (o){
        }

    }
    p.remove();
}
function saveSelectRole(){
    var s = $(".selectDiv");

    var objectEq = {
        id:selectpostId,
        type:3
    } ;


    var roles = [];
    for(var i=0;i<s.length;i++){
        var role = {};
        role.id=$(s[i]).attr("id");
        roles.push(role)
    }
    objectEq.roleList = roles;
    var json = {json:JSON.stringify(objectEq)};
    $.ajax( {
        dataType: "json",
        type: "GET",
        url: ctx+"/post/saveRoleRel",
        data:json,
        success:function(data){
            if (data.code == "200") {
                $("#submit").click();
                $("#selectRoleModal").modal("hide");
                jAlert(data.msg, '提示');
            } else {
                jAlert(data.msg, '提示');
            }
        }
    }) ;
}