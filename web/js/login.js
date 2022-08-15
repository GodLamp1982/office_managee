
$(function () {
    //验证码
    getCode();
    setInterval(getCode,60*1000);

    $("#showCode").click(function () {
        getCode();
    });

    $("#codeSpan").change(function (){
        getCode();
    });

    $("form").submit(function () {
        var result = true;
        if( $("input[name='account']").val().length == 0 || $("input[name='pwd']").val().length == 0  || $("input[name='code']").val().length == 0 ){
            result = false;
        }
        return result;
    });

    $("input[name='account']").change(function () {
        var account = $("input[name='account']").val();
        var href = "forgotpwd.jsp?account=" + account;
        $("#a1").attr("href",href);
    });

});

function getCode() {
    $.ajax({
        type:"post",
        url:"user",
        data:"action=code",
        success:function (msg) {
            $("#showCode").text(msg);
            /*自动填写，以后删除*/
            $("input[name='code']").val(msg);
        },
        error:function () {
            alert("验证码获取失败！");
        }
    });
}
