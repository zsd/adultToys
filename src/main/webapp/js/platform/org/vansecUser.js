var date = new Date();
var month = (date.getMonth()+1);
var nowDate = date.getFullYear()+(month<10?("-0"+month):("-"+month))+((date.getDate())<10?("-0"+(date.getDate())):("-"+(date.getDate())));
var url = ctx+"/user/getAllUser";
$(function () {


    searchData(url, {});

    //查询
    $("#submit").click(function () {
        var name = $("#name").val();
        var parameter = {
            name:name
        };
        searchData(url,parameter);
    });
});
function dateDiff(sDate1, sDate2) {  //sDate1和sDate2是yyyy-MM-dd格式

    var oDate1, oDate2, iDays;
    oDate1 = new Date(sDate1);
    oDate2 = new Date(sDate2);
    iDays = parseInt(Math.abs(oDate1 - oDate2) / 1000 / 60 / 60 / 24); //把相差的毫秒数转换为天数
    return iDays;  //返回相差天数

}
//查询分页数据并显示到页面
function searchData(url,data){
    $.ajax( {
        dataType: "json",
        type: "post",
        url: url,
        data: data,
        success:function(result){
            console.log(result);
            var table = '';
            try {

                for (var i=0;i<result.length;i++) {

                    table +=
                        '<tr>'+
                        '<td>'+ result[i].departmentName + '</td>'+
                        '<td>'+ result[i].postName + '</td>'+
                        '<td>'+ result[i].name + '</td>'+
                        '<td>'+ result[i].phone + '</td>'+
                        '<td>'+ result[i].birthday + '</td>'+
                        '<td>'+ result[i].email + '</td>'+
                        '<td>'+ formatDate(result[i].create_time) + '</td>'+
                        '<td>'+ formatDate(result[i].update_time) + '</td>'+

                        '</tr>';
                }
                //添加列表数据
                $(table).appendTo($("#listTab").find("tbody").empty());
            } catch (e) {
                table += '<tr><td colspan="6" align="center"><font style="font-weight: bold; color: red">无相关记录存在！</font></td></tr>';
                $(table).appendTo($("#listTab").find("tbody").empty());
            }
        }
    }) ;
}

//时间格式化
function formatDate (strTime) {
    var date = new Date(strTime);
    var month = (date.getMonth()+1);
    return date.getFullYear()+(month<10?("-0"+month):("-"+month))+((date.getDate())<10?("-0"+(date.getDate())):("-"+(date.getDate())));
}

//生日格式化
function birthdayDate (strTime) {
    if (strTime == null || strTime == "") {
        return "";
    }
    var date = new Date(strTime);
    var month=date.getMonth()+1;
    var date=date.getDate();
    return month + "月" + date + "日";
}
//导出数据
function exportXml(){
    jAlert('待开发！', '提示');
}





