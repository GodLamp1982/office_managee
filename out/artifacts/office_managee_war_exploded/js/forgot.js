
$(function () {

    $("input[value='返回']").click(function () {
        history.back();
    });

    $("form").submit(function () {
        var result = true;
        var account = $("input[name='account']").val();
        $.ajax({
            type:"post",
            url:"user",
            data:"action=valiAccountHasExist&account=" + account,
            success:function (msg) {
                if ("true" != msg){
                    result = false;
                }
            },
            error:function () {
                alert("获取失败！");
            }
        });

        if( $("input[name='account']").length < 1 || $("input[name='userName']").length < 1 || $("input[name='tel']").length < 1 || $("input[name='pwd']").length < 1){
            result = false;
        }

        return result;
    });

});