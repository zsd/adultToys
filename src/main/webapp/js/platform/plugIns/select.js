/**
 * Created by msx on 2017/2/4.
 * 分页插件
 * PS:除了提示的两个方法可以重写，其他方法均不能重写
 */

/**
 * @param o 需要传入
 * url：项目路径
 * allDiv: 总的Div的Id
 * userId:  存储选中的数据Id
 * userName: 存储选中的数据名称
 * tableInfo：表格显示字段和字段显示的方法（name为显示的列名，attr为字段对应的实体名字，transformation：字段显示方法）
 * @constructor
 */
function Select(o){
    var initPagm={
        url:ctx,
        allDiv:"selectUserDiv",
        userId:"userId",
        userName:"userName",
        tableInfo:[]
    };
    $.extend(initPagm,o);

    for(var i = 0;i< initPagm.tableInfo.length;i++){
        var v = {transformation:function(v){return v;}};
        $.extend(v,initPagm.tableInfo[i]);
        initPagm.tableInfo[i]= v;
    }
    //初始化元素Id
    var date = new Date();
    this.onlyOne = date.getTime();
    this.pageNoId = "pageNo"+this.onlyOne;
    this.pageSizeId = "pageSize"+this.onlyOne;
    this.selectAllId = "selectAll"+this.onlyOne;
    this.exportId = "export"+this.onlyOne;
    this.itemsName = "items"+this.onlyOne;
    this.rowIdImputClass = "rowIdImput"+this.onlyOne;
    this.modelId = "modelId"+this.onlyOne;
    this.submitId = "submitId"+this.onlyOne;
    this.submitNameId = "submitNameId"+this.onlyOne;
    this.sureButtonId = "sureButtonId"+this.onlyOne;
    this.userId = initPagm.userId;
    this.userName = initPagm.userName;


    //保存表格条件信息
    this.tableInfo = initPagm.tableInfo;
    //初始化Div
    this.initDiv(initPagm);

    this.url = initPagm.url;
    var thisSelectUser = this;
    //点击分页触发事件
    $(".pageClass"+this.onlyOne).live("click",function () {
        var pageNum = this.getAttribute("pageNum");
        thisSelectUser.gotoPage(pageNum);
    });

    //查询用户
    $("#"+this.submitId).click(function () {
        var name = $("#"+thisSelectUser.submitNameId).val();
        var parameter = {
            name:name
        };
        //保存查询条件
        thisSelectUser.queryCriteria = parameter;
        thisSelectUser.searchData(this.url,parameter);
    });

    //点击导出按钮
    $("#"+this.sureButtonId).click(function () {
        thisSelectUser.getSelectUser();
    });
}
//查询路径
Select.prototype.url = "";
//当前页数
Select.prototype.pageNoId = "";
//一页显示个数
Select.prototype.pageSizeId = "";
//选择的字符串
Select.prototype.itemsName = "";
//查询条件
Select.prototype.queryCriteria ={};
//单选框唯一样式识别
Select.prototype.rowIdImputClass = "";
//表格详情
Select.prototype.tableInfo = [];
//唯一标识
Select.prototype.onlyOne = "";
//摸态框ID
Select.prototype.modelId = "";
//查询按钮Id
Select.prototype.submitId = "";
//查询条件组件Id
Select.prototype.submitNameId = "";
//确定按钮Id
Select.prototype.sureButtonId = "";
//用户Id组件Id
Select.prototype.userId = "";
//用户名组件Id
Select.prototype.userName = "";


//时间格式化
Select.prototype.formatDate = function (strTime) {
    var date = new Date(strTime);
    var month = (date.getMonth()+1);
    return date.getFullYear()+(month<10?("-0"+month):("-"+month))+((date.getDate())<10?("-0"+(date.getDate())):("-"+(date.getDate())));
};

//分页
Select.prototype.gotoPage = function (newPageNo) {
    $("#"+this.pageNoId).val(newPageNo);
    var pageNo = $("#"+this.pageNoId).val();
    var pageSize = $("#"+this.pageSizeId).val();
    this.queryCriteria.pageNo = pageNo;
    this.queryCriteria.pageSize = pageSize;
    this.searchData(this.url+"search", this.queryCriteria);
};

//分页
Select.prototype.getPage= function (pageNo, totalPages) {
    var page1 = "";
    if (totalPages <= 3) {
        for (var i=1; i<totalPages+1; i++) {
            page1 += '<li><a class="pageClass'+this.onlyOne+'" pageNum="'+ i +'" href="javascript:void(0);">'+ i +'</a></li>';
        }
    } else {
        if (pageNo <= 2) {
            page1  = '<li><a class="pageClass'+this.onlyOne+'" pageNum="1" href="javascript:void(0);">1</a></li>' +
                '<li><a class="pageClass'+this.onlyOne+'" pageNum="2" href="javascript:void(0);">2</a></li>' +
                '<li><a class="pageClass'+this.onlyOne+'" pageNum="3" href="javascript:void(0);">3</a></li>';
            return page1;
        }
        var count = totalPages - pageNo;
        if (count == 0) {
            var startCount = totalPages - 2;
            var middleCount = totalPages - 1;
            page1  = '<li><a class="pageClass'+this.onlyOne+'" pageNum="'+ startCount +'" href="javascript:void(0);">'+startCount+'</a></li>' +
                '<li><a class="pageClass'+this.onlyOne+'" pageNum="'+ middleCount +'" href="javascript:void(0);">'+middleCount+'</a></li>' +
                '<li><a class="pageClass'+this.onlyOne+'" pageNum="'+ totalPages +'" href="javascript:void(0);">'+totalPages+'</a></li>';
            return page1;
        } else {
            var startCount = pageNo -1;
            var endCount = pageNo +1;
            page1 = '<li><a class="pageClass'+this.onlyOne+'" pageNum="'+ startCount +'" href="javascript:void(0);">'+startCount+'</a></li>' +
                '<li><a class="pageClass'+this.onlyOne+'" pageNum="'+ pageNo +'" href="javascript:void(0);">'+pageNo+'</a></li>' +
                '<li><a class="pageClass'+this.onlyOne+'" pageNum="'+ endCount +'" href="javascript:void(0);">'+endCount+'</a></li>';
            return page1;
        }
    }
    return page1;
};

//获取已选择的ID字符串
Select.prototype.getSelectId = function (){
    var ids = '';
    $('input[name="'+this.itemsName+'"][type="checkbox"]:checked').each(function() {
        if(ids == ''){
            ids = $(this).val();
        }else{
            ids = ids + ',' + $(this).val();
        }
    });
    return ids;
};

//添加分页信息
Select.prototype.addPage = function (page){
    var pageNo = page.pageNo;
    var totalPages = page.totalPages;
    var firstPage = '<li><a class="pageClass'+this.onlyOne+'" pageNum="1" href="javascript:void(0);">&lt;&lt;</a></li>';
    var prePage = '<li><a class="pageClass'+this.onlyOne+'" pageNum="'+ page.prePage +'" href="javascript:void(0);">&lt;</a></li>';
    var page1 = this.getPage(pageNo, totalPages);
    var nextPage = '<li><a class="page-item pageClass'+this.onlyOne+'" pageNum="'+ page.nextPage +'" href="javascript:void(0);">&gt;</a></li>';
    var endPages = '<li><a class="page-item pageClass'+this.onlyOne+'" pageNum="'+ page.totalPages +'" href="javascript:void(0);">&gt;&gt;</a></li>';
    var pageInfo = firstPage + prePage + page1 + nextPage + endPages;
    $(pageInfo).appendTo($(".pagination"+this.onlyOne).empty());
};

//查询分页数据并显示到页面
Select.prototype.searchData = function (url, data){
    var me = this;
    $.ajax( {
        dataType: "json",
        type: "post",
        url: me.url+"search",
        data: data,
        success:function(page){
            var result = page.result;
            var table = '';
            try {
                var tableTd;
                for (var i=0;i<result.length;i++) {
                    tableTd = '';
                    //遍历传入的表格列，并构建成表格内容格式
                    for(var j = 0;j<me.tableInfo.length;j++){
                        tableTd += '<td>' + me.tableInfo[j].transformation(result[i][me.tableInfo[j].attr])  + '</td>';
                    }
                   table +=
                        '<tr>'+
                        '<td><input class="rowIdImput '+me.rowIdImputClass+'" type="radio" name="items'+me.onlyOne+'" value="'+ result[i].id +'"></td>' +
                        tableTd;
                    table +=
                        '</tr>';
                }
                //添加列表数据
                $(table).appendTo($("#listTab"+me.onlyOne).find("tbody").empty());

                //添加分页信息
                me.addPage(page);
                for (var i=0;i<result.length;i++) {
                    $('.'+me.rowIdImputClass+'[value='+result[i].id+']').data("datas",result[i]);
                }
            } catch (e) {
                table += '<tr><td colspan="6" align="center"><font style="font-weight: bold; color: red">无相关记录存在！</font></td></tr>';
                $(table).appendTo($("#listTab"+me.onlyOne).find("tbody").empty());
            }
        }
    }) ;
};

//初始化页面Div
Select.prototype.initDiv = function (initPagm){
    var tableTh = '';
    for(var i = 0;i<initPagm.tableInfo.length;i++){
        tableTh += '<th>'+initPagm.tableInfo[i].name+'</th>';
    }

    //按钮表格整体Div
    var tableDiv =
        '<div class="modal fade" id="'+this.modelId+'" tabindex="-1" role="dialog"' +
            'aria-labelledby=select'+this.modelId+'" aria-hidden="true">'+
            '<div class="modal-dialog" style="width:700px;">' +
                '<div class="modal-content">' +
                    '<div class="modal-header">' +
                        '<button type="button" class="close"' +
                                'data-dismiss="modal" aria-hidden="true">' +
                            '&times;' +
                        '</button>' +
                        '<h4 class="modal-title" id="'+this.modelId+'Label">' +
                            '选择界面' +
                        '</h4>' +
                    '</div>' +
                    '<div class="modal-body">' +
                        '<div class="panel-body" style="padding: 0px;">' +
                            '<div class="col-md-12" style="padding: 0px;">' +
                                '<div class="wrapper" style="padding: 0px;">' +
                                    '<div class="content" style="padding: 0px;">' +
                                        '<div class="container-fluid" style="padding: 0px;">' +
                                            '<div class="panel panel-default">' +
                                                '<div class="panel-body">' +
                                                    '<form class="form-inline">' +
                                                        '<div class="form-group">' +
                                                            '<label>&nbsp;&nbsp;名称：</label>' +
                                                            '<input type="text" class="form-control" id="'+this.submitNameId+'">' +
                                                        '</div>' +
                                                        '<button type="button" id="'+this.submitId+'" class="btn btn-default">' +
                                                            '<span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;查询' +
                                                        '</button>' +
                                                    '</form>' +
                                                '</div>' +
                                            '</div>' +
                                            '<div class="panel panel-default">' +
                                                '<div class="panel-body">' +
                                                    '<div class="row" style="margin-bottom: 10px;">' +
                                                        '<div class="col-sm-12" style="margin-right:0">' +
                                                            '<nav class="pagination pagination'+this.onlyOne+'" style="margin:0;float:right;">' +
                                                            '</nav>' +
        '</div>' +
        '</div>' +
        '<div class="row">' +
        '<input type="hidden" id="'+this.pageNoId+'" name="pageNo" value="1"/>' +
        '<input type="hidden" id="'+this.pageSizeId+'" name="pageSize" value="10"/>' +
        '<table class="table table-striped" style="margin-bottom: 0px;" id="listTab'+this.onlyOne+'">' +
        '<thead style="background-color: gainsboro;">' +
        '<tr>' +
        '<th></th>' +
        tableTh+
        '</tr>' +
        '</thead>' +
        '<tbody class="listTab">' +
        '</tbody>' +
        '</table>' +
        '</div>' +
        '</div>' +
        '</div>' +
        '</div><!-- /.container -->' +
        '</div>' +
        '</div>' +
        '</div>' +
        '</div>' +
        '</div>' +
        '<div class="modal-footer">' +
        '<button  type="button" id="'+this.sureButtonId+'" class="btn btn-primary">' +
        '确定' +
        '</button>' +
        '<button type="button" class="btn btn-default"' +
        'data-dismiss="modal">取消' +
        '</button>' +
        '</div>' +
        '</div>' +
        '</div>' +
        '</div>';
    $("#"+initPagm.allDiv).append(tableDiv);
};
//点击确定按钮方法
Select.prototype.getSelectUser = function(){
    var selectElement = $('input[name="'+this.itemsName+'"]:checked');
    if(selectElement.length==0){
        jAlert('请选择！', '提示');
        return ;
    }else{
        var data =  selectElement.data("datas") ;
        $('input[name="'+this.userId+'"]').val(data.id);
        $('input[name="'+this.userName+'"]').val(data.name);
        $("#"+this.modelId).modal("hide");
    }
};