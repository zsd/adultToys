function openChangePassword(){
    //初始化
    changeFu();
    $("#test").dialog({
        title: '修改密码',
        width: 450,
        height: 350,
        modal: true,
     buttons: [{
         text: '确认',
         handler: function () {
             changePassword();
         }
     }, {
         text: '取消',
         handler: function () {
             $('#test').dialog('close');
         }
     }]
    });
    $(".panel-title:eq(1)").css("padding",'15px');
    $("#test").dialog("open");
}
//修改密码
function changePassword() {
    //获取输入的旧密码
    var oldPassword = $("#oldPassword").val();
    //获取输入的新密码
    var newPassword = $("#newPassword").val();
    //获取重新输入的新密码
    var newPasswordAgain = $("#newPasswordAgain").val();
    if(newPassword != newPasswordAgain){
        alert("两次密码输入不一致");
        return ;
    }else{
        var parameter= {
            userId:userId,
            oldPassword:oldPassword,
            newPassword:newPassword
        };
        $.ajax( {
            dataType: "json",
            type: "post",
            url: ctx+"/user/changePassword",
            data: parameter,
            success:function(data){
                if (data.code == "200") {
                    alert(data.msg);
                    $('#test').dialog('close');
                } else {
                    alert(data.msg);
                }
            }
        }) ;
    }
}

//初始化
function changeFu(){
    $("#oldPassword").val("");
    $("#newPassword").val("");
    $("#newPasswordAgain").val("");
}