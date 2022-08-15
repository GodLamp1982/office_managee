
$(function () {

    $("input[value='提交']").click(function () {
        var path = $("input[name='path']").val();
        var str = "";
        $(".selection").each(function () {
            if( $(this).attr("checked") ){
                str += $(this).val() + ",";
            }
        });
        location.href=path +"/order?action=orderCarCommit&dishIds=" + str;
    });

    $("input[value='删除']").click(function () {
        var path = $("input[name='path']").val();
        var str = "";
        $(".selection").each(function () {
            if( $(this).attr("checked") ){
                str += $(this).val() + ",";
            }
        });
        location.href=path +"/order?action=deleteSelectedOrderCar&dishIds=" + str;
    });

    $("input[value='返回']").click(function () {
        var path = $("input[name='path']").val();
        location.href=path +"/dish?action=generalUserIndex";
    });

});