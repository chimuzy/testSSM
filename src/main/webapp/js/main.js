function login() {
    $.ajax({
       type :"get",
       url :"login.jsp",
       success: function (content) {
            $(".main").html(content);
        }
    });
}

function loginController(url) {
    var data=$(".loginForm").serialize();
    $.ajax({
        type:"post",
        url:url+"/user/login.action",
        data:data,
        success: function (content) {
            $(".main").html(content);
        }
    });
}

function loginout(url) {
    $.ajax({
        type:"get",
        url:url+"/user/loginout.action",
        success:function (content) {
            $(".main").html(content);
        }
    });
}

function regist() {
    $.ajax({
        type :"get",
        url :"regist.jsp",
        success: function (content) {
            $(".main").html(content);
        }
    });
}

function validateName(url) {
    var username=$(".username").val();
    $.ajax({
       type:"get",
        url:url+"/user/validate.action?username="+username,
        success:function (flag) {
            if(flag==true){
                $(".namemsg").html("<font color='green'>当前用户名可以使用</font>")
            }else{
                $(".namemsg").html("<font color='red'>当前用户名已存在，不可以使用</font>")
            }
        }
    });
}

function registController(url) {
    var username=$(".username").val();
    var formData =new FormData($(".registForm")[0]);
    if(username!=""){
        $.ajax({
            type:"post",
            url:url+"/user/regist.action",
            data:formData,
            contentType:false,
            processData:false,
            success:function (content) {
                $(".main").html(content);
            }
        });
    }else{
        $(".namemsg").html("<font color='red'>当前用户名不可以为空</font>")
    }
}

function viewAll(url) {
    $.ajax({
        type:"get",
        url:url+"/items/findAll.action",
        success:function (content) {
            $(".main").html(content);
        }
    });
}