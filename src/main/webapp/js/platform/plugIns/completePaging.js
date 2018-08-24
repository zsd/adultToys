/**
 * Created by msx on 2017/5/17.
 * 分页插件（可包含按钮）
 * PS:除了提示的两个方法可以重写，其他方法均不能重写
 */

/**
 * @param o 需要传入
 * url：项目路径
 * allDiv：总的Div的Id
 * authority：基本权限信息（type为按钮类别：add、delete、export、change这4种，权限值为value：true或者false）
 * buttonInfo: 按钮组（name:按钮显示字段，method:点击按钮方法，authority:按钮权限）
 *             PS：按钮方法默认传入一个参数id，所以方法必须带一个id参数
 * tableInfo：表格显示字段和字段显示的方法（name为显示的列名，attr为字段对应的实体名字，transformation：字段显示方法）
 * @constructor
 */
function CompletePaging(o){
    var initPagm={
        url:ctx,
        allDiv:"allDiv",
        authority:[{type:"add",value:false},
            {type:"delete",value:false},
            {type:"export",value:false},
            {type:"change",value:false}],
        buttonInfo:[{name:"测试",method:"showDetail",authority:false}],
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
    this.deleteId = "delete"+this.onlyOne;
    this.addId = "add"+this.onlyOne;
    this.exportId = "export"+this.onlyOne;
    this.itemsName = "items"+this.onlyOne;
    this.rowIdImputClass = "rowIdImput"+this.onlyOne;
    //保存表格条件信息
    this.tableInfo = initPagm.tableInfo;
    //保存按钮组信息
    this.buttonInfo = initPagm.buttonInfo;
    //权限处理
    this.authorityHandle(initPagm);
    //初始化Div
    this.initDiv(initPagm);

    this.url = initPagm.url;
    var thisPaging = this;
    //点击分页触发事件
    $(".pagingA"+this.onlyOne).live("click",function () {
        var pageNum = this.getAttribute("pageNum");
        thisPaging.gotoPage(pageNum);
    });
    //当点击全选/全不选框时，全选或者全不选全部
    $("#"+this.selectAllId).click(function(event){
        var check_state = event.target.checked;
        $('input[name="'+thisPaging.itemsName+'"][type="checkbox"]').each(function() {
            this.checked = check_state;
        });
    });
    //点击数据的单选框
    $("."+this.rowIdImputClass).live("click",function(event){
        var flage =  true;
        var check_state = event.target.checked;
        $("."+thisPaging.rowIdImputClass).each(function() {
            if(this.checked != check_state){
                flage = false;
            }
        });
        if(flage){
            $("#"+thisPaging.selectAllId)[0].checked = check_state;
        }else{
            $("#"+thisPaging.selectAllId)[0].checked = false;
        }
    });
    //批量删除
    $("#"+this.deleteId).click(function () {
        var idStr = thisPaging.getSelectId();
        if (idStr == "") {
            jAlert('请选择要删除的记录！', '提示');
            return;
        }
        jConfirm('确定要删除记录?', '提示', function(r) {
            if( r ){
                $.ajax( {
                    dataType: "json",
                    type: "get",
                    url: thisPaging.url+"delete?idStr=" + idStr,
                    success:function(data){
                        if (data.code == "200") {
                            jAlert(data.msg, '提示');
                            var pageNo = 1;
                            var pageSize = 10;
                            var parameter = {
                                pageNo:pageNo,
                                pageSize:pageSize
                            };
                            thisPaging.searchData(thisPaging.url+"search", parameter);
                        } else {
                            jAlert(data.msg, '提示');
                        }
                    }
                }) ;
            }
        });
    });
    //点击新增按钮
    $("#"+this.addId).click(function () {
        thisPaging.addFn();
    });
    //点击导出按钮
    $("#"+this.exportId).click(function () {
        thisPaging.exportXml();
    });
}
//查询路径
CompletePaging.prototype.url = "";
//当前页数
CompletePaging.prototype.pageNoId = "";
//一页显示个数
CompletePaging.prototype.pageSizeId = "";
//选择的字符串
CompletePaging.prototype.itemsName = "";
//查询条件
CompletePaging.prototype.queryCriteria ={};
//全选按钮Id
CompletePaging.prototype.selectAllId = "";
//删除按钮Id
CompletePaging.prototype.deleteId = "";
//导出按钮Id
CompletePaging.prototype.exportId = "";
//新增按钮Id
CompletePaging.prototype.addId = "";
//单选框唯一样式识别
CompletePaging.prototype.rowIdImputClass = "";
//表格详情
CompletePaging.prototype.tableInfo = [];
//唯一标识
CompletePaging.prototype.onlyOne = "";
//新增按钮权限
CompletePaging.prototype.addAuthority = false;
//删除按钮权限
CompletePaging.prototype.deleteAuthority = false;
//编辑按钮权限
CompletePaging.prototype.changeAuthority = false;
//导出按钮权限
CompletePaging.prototype.exportAuthority = false;
//按钮组
CompletePaging.prototype.buttonInfo = [];

//时间格式化
CompletePaging.prototype.formatDate = function (strTime) {
    var date = new Date(strTime);
    var month = (date.getMonth()+1);
    return date.getFullYear()+(month<10?("-0"+month):("-"+month))+((date.getDate())<10?("-0"+(date.getDate())):("-"+(date.getDate())));
};

//计算两个时间差
CompletePaging.prototype.dateDiff = function(sDate1, sDate2){
    var oDate1, oDate2, iDays;
    oDate1 = new Date(sDate1);
    oDate2 = new Date(sDate2);
    iDays = parseInt(Math.abs(oDate1 - oDate2) / 1000 / 60 / 60 / 24); //把相差的毫秒数转换为天数
    return iDays;  //返回相差天数
};

//分页
CompletePaging.prototype.gotoPage = function (newPageNo) {
    $("#"+this.pageNoId).val(newPageNo);
    var pageNo = $("#"+this.pageNoId).val();
    var pageSize = $("#"+this.pageSizeId).val();
    this.queryCriteria.pageNo = pageNo;
    this.queryCriteria.pageSize = pageSize;
    this.searchData(this.url+"search", this.queryCriteria);
};

//分页
CompletePaging.prototype.getPage= function (pageNo, totalPages) {
    var page1 = "";
    if (totalPages <= 3) {
        for (var i=1; i<totalPages+1; i++) {
            page1 += '<li><a class="pagingA'+this.onlyOne+'" pageNum="'+ i +'" href="javascript:void(0);">'+ i +'</a></li>';
        }
    } else {
        if (pageNo <= 2) {
            page1  = '<li><a class="pagingA'+this.onlyOne+'" pageNum="1" href="javascript:void(0);">1</a></li>' +
                '<li><a class="pagingA'+this.onlyOne+'" pageNum="2" href="javascript:void(0);">2</a></li>' +
                '<li><a class="pagingA'+this.onlyOne+'" pageNum="3" href="javascript:void(0);">3</a></li>';
            return page1;
        }
        var count = totalPages - pageNo;
        if (count == 0) {
            var startCount = totalPages - 2;
            var middleCount = totalPages - 1;
            page1  = '<li><a class="pagingA'+this.onlyOne+'" pageNum="'+ startCount +'" href="javascript:void(0);">'+startCount+'</a></li>' +
                '<li><a class="pagingA'+this.onlyOne+'" pageNum="'+ middleCount +'" href="javascript:void(0);">'+middleCount+'</a></li>' +
                '<li><a class="pagingA'+this.onlyOne+'" pageNum="'+ totalPages +'" href="javascript:void(0);">'+totalPages+'</a></li>';
            return page1;
        } else {
            var startCount = pageNo -1;
            var endCount = pageNo +1;
            page1 = '<li><a class="pagingA'+this.onlyOne+'" pageNum="'+ startCount +'" href="javascript:void(0);">'+startCount+'</a></li>' +
                '<li><a class="pagingA'+this.onlyOne+'" pageNum="'+ pageNo +'" href="javascript:void(0);">'+pageNo+'</a></li>' +
                '<li><a class="pagingA'+this.onlyOne+'" pageNum="'+ endCount +'" href="javascript:void(0);">'+endCount+'</a></li>';
            return page1;
        }
    }
    return page1;
};

//获取已选择的ID字符串
CompletePaging.prototype.getSelectId = function (){
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
CompletePaging.prototype.addSelectUser = function (page){
    var pageNo = page.pageNo;
    var totalPages = page.totalPages;
    var firstPage = '<li><a class="pagingA'+this.onlyOne+'" pageNum="1" href="javascript:void(0);">&lt;&lt;</a></li>';
    var prePage = '<li><a class="pagingA'+this.onlyOne+'" pageNum="'+ page.prePage +'" href="javascript:void(0);">&lt;</a></li>';
    var page1 = this.getPage(pageNo, totalPages);
    var nextPage = '<li><a class="page-item pagingA'+this.onlyOne+'" pageNum="'+ page.nextPage +'" href="javascript:void(0);">&gt;</a></li>';
    var endPages = '<li><a class="page-item pagingA'+this.onlyOne+'" pageNum="'+ page.totalPages +'" href="javascript:void(0);">&gt;&gt;</a></li>';
    var pageInfo = firstPage + prePage + page1 + nextPage + endPages;
    $(pageInfo).appendTo($(".pagination"+this.onlyOne).empty());
};

//查询分页数据并显示到页面
CompletePaging.prototype.searchData = function (url,data){
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
                var changeStr;
                var tableTd;
                var buttonStr = '';
                for (var i=0;i<result.length;i++) {
                    changeStr = '';
                    tableTd = '';
                    buttonStr = '';
                    //按钮组处理
                    for(var j = 0;j<me.buttonInfo.length;j++){
                        if(me.buttonInfo[j].authority){
                            buttonStr += '<button type="button" onclick="'+me.buttonInfo[j].method+'(\''+result[i].id+'\')" class="btn btn-default btn-sm">&nbsp;'+me.buttonInfo[j].name+'</button>&nbsp;&nbsp;';
                        }
                    }
                    if(me.changeAuthority){
                        changeStr = '<button type="button" onclick="showDetail(\''+result[i].id+'\')" class="btn btn-default btn-sm"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>&nbsp;编辑</button>';
                    }
                    //遍历传入的表格列，并构建成表格内容格式
                    for(var j = 0;j<me.tableInfo.length;j++){
                        tableTd += '<td>' + me.tableInfo[j].transformation(result[i][me.tableInfo[j].attr])  + '</td>';
                    }
                   table +=
                        '<tr>'+
                        '<td><input class="rowIdImput '+me.rowIdImputClass+'" type="checkbox" name="items'+me.onlyOne+'" value="'+ result[i].id +'"></td>' +
                        tableTd;
                    table += '<td>' + buttonStr +changeStr+ '</td>'+
                        '</tr>';
                }
                //添加列表数据
                $(table).appendTo($("#listTab"+me.onlyOne).find("tbody").empty());

                //添加分页信息
                me.addSelectUser(page);
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
CompletePaging.prototype.initDiv = function (initPagm){
    var tableTh = '';
    var addButton = '';
    var deleteButton = '';
    var exportButton = '';
    for(var i = 0;i<initPagm.tableInfo.length;i++){
        tableTh += '<th>'+initPagm.tableInfo[i].name+'</th>';
    }
    //权限处理
    if(this.addAuthority){
        addButton = '<button id="'+this.addId+'" type="button" class="btn btn-success dropdown-toggle">新增<span class="glyphicon glyphicon-plus"></span></button>';
    }
    if(this.deleteAuthority){
        deleteButton = '<button type="button" id="'+this.deleteId+'" class="btn btn-default"> 删除<span class="glyphicon glyphicon-minus" aria-hidden="true"></span></button> ';
    }
    if(this.exportAuthority){
        exportButton = '<button type="button" id="'+this.exportId+'" class="btn btn-default"> 导出<span class="glyphicon glyphicon-download-alt" aria-hidden="true"></span></button>';
    }

    //按钮表格整体Div
    var tableDiv =
        '<div class="panel panel-default">' +
            '<div class="panel-body">' +
                '<div class="row">' +
                    '<div class="col-sm-7">' +
                        '<div role="toolbar" class="btn-toolbar">' +
                            '<div class="btn-group dropup">' +
                                addButton+
                             '</div>' +
                            deleteButton +
                            exportButton +
                        '</div>' +
                    '</div>' +
                    '<div class="col-sm-5" style="margin-right:0">' +
                        '<nav class="pagination pagination'+this.onlyOne+'" style="margin:0;float:right;">' +
                        '</nav>' +
                    '</div>'+
                '</div>' +
            '</div>' +
            '<div class="row" style="margin:0px;" >' +
                '<input type="hidden" id="'+this.pageNoId+'" name="pageNo" value="1"/>' +
                '<input type="hidden" id="'+this.pageSizeId+'" name="pageSize" value="10"/>' +
                '<table class="table table-striped" style="margin-bottom: 0px;" id="listTab'+this.onlyOne+'">' +
                '<thead style="background-color:gainsboro;">' +
                    '<tr>' +
                        '<th><input type="checkbox" class="alltoggle" id="'+this.selectAllId+'"></th>' +
                        tableTh +
                    '<th>操作</th>' +
                    '</tr>' +
                '</thead>' +
                '<tbody class="listTab">' +
                '</tbody>' +
            '</table>' +
            '</div>' +
        '</div>';
    $("#"+initPagm.allDiv).append(tableDiv);
};

//点击新增按钮方法（实例化后需要重写）
CompletePaging.prototype.addFn = function(){};
//点击导出按钮方法（实例化后需要重写）
CompletePaging.prototype.exportXml = function(){};
//权限处理方法
CompletePaging.prototype.authorityHandle = function(initPagm){
    //权限处理
    for(var i = 0;i<initPagm.authority.length;i++){
        var flag = initPagm.authority[i].value;
        if(initPagm.authority[i].type == 'add'){
            this.addAuthority = flag;
        }else if(initPagm.authority[i].type == 'delete'){
            this.deleteAuthority = flag;
        }else if(initPagm.authority[i].type == 'export'){
            this.exportAuthority = flag;
        }else if(initPagm.authority[i].type == 'change'){
            this.changeAuthority = flag;
        }
    }
};