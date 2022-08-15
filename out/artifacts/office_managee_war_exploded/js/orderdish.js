
$(function () {

    $("input[value='加入点餐车']").click(function () {
        var path = $("input[name='path']").val();
        var str = "";
        $(".selection").each(function () {
            if( $(this).attr("checked") ){
                str += $(this).val() + ",";
            }
        });
        location.href=path +"/order?action=addBatchOrder&dishIds=" + str;
    });

    /*$("input[name='noOrder']").change(function (){
        if ($("input[name='noOrder']").val().length != 0){
            alert($("input[name='noOrder']").val());
        }
    });*/

});