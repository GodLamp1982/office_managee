
$(function () {

    $("input[value='返回']").click(function () {
        history.back();
    });

    $("form").submit(function () {
        $("input[type != 'submit'][type != 'button']").next().text("");
        var result = true;
        if ( $("input[name='userName']").val().length == 0 ){
            result = false;
            $("input[name='userName']").next().text("姓名不能为空！");
        }
        if ( $("input[name='account']").val().length == 0 ){
            result = false;
            $("input[name='account']").next().text("账号不能为空！");
        }
        if ( $("input[name='pwd']").val().length == 0 ){
            result = false;
            $("input[name='pwd']").next().text("密码不能为空！");
        }
        if ( $("input[name='secondPwd']").val().length == 0 ){
            result = false;
            $("input[name='secondPwd']").next().text("二次确认密码不能为空！");
        }
        if ( $("input[name='tel']").val().length == 0 ){
            result = false;
            $("input[name='tel']").next().text("电话不能为空！");
        }
        if ( $("input[name='address']").val().length == 0 ){
            result = false;
            $("input[name='address']").next().text("地址不能为空！");
        }
        if ( $("input[name='pwd']").val() != $("input[name='secondPwd']").val() ){
            result = false;
            $("input[name='secondPwd']").next().text("密码不一致！");
        }
        return result;
    });

});

