<%--
  Created by IntelliJ IDEA.
  User: GodLamp
  Date: 2022/8/17
  Time: 11:57
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style type="text/css">
        th{
            background-color: lightgray;
        }
        input{
            height: 29px;
            width: 95%;
        }

        select{
            height: 29px;
            width: 100%;
        }
    </style>

</head>
<body>
<table border="" cellspacing="" cellpadding="" style="width: 100%;">
    <tr>
        <th style="width: 10%; height: 50px;">学号</th>
        <th style="width: 12%;height: 50px;">姓名</th>
        <th style="width: 12%;height: 50px;">平时成绩</th>
        <th style="width: 12%;height: 50px;">考试成绩</th>
        <th style="width: 12%;height: 50px;">总成绩</th>
    </tr>

    <%--<c:forEach items="${exp}" var="exp">--%>
        <tr>
            <td>${exp.stu_no}</td>
            <td>${exp.stu_name}</td>
            <td><input type="text" name="usual" id="x"  class="eva"/>asd</td>
            <td><input type="text" name="exam" id="y"  class="eva"/>都市</td>
            <td><input type="text" name="total" id="sum" value="" readonly/>的</td>
        </tr>
        <tr>
            <td>${exp.stu_no}</td>
            <td>${exp.stu_name}</td>
            <td><input type="text" name="usual"/>奥数</td>
            <td><input type="text" name="exam" class="eva"/>都市</td>
            <td><input type="text" name="total"  value="" readonly/>大</td>
        </tr>
    <%--</c:forEach>--%>

    <tr>
        <td colspan="5" style="color: red;">注：平时成绩占40%，考试成绩占60%</td>
    </tr>
</table>
</body>

<script src="js/jquery-1.8.3.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
    $(function () {
        $(".eva").change(function () {
            $("tr td:first-child").each(function () {
                var pre = $(this).next().next().val();
                var nex = $(this).next().next().next().val();
                var num = pre + "*" + "0.4" + "+" + nex + "*" + "0.6";
                var valu = eval(num);
                $(this).next().next().next().next().val(valu);
            });
        });


        /*$(".eva").keyup(function (){
            var num = $("input[name='usual']").val() + "*" + "0.4" + "+" + $("input[name='exam']").val() + "*" + "0.6";
            $("input[name='sum']").val(eval(num));
        });*/

    });

    /*function addSum(a){
        var x = document.getElementById("x").value;
        var y = document.getElementById("y").value;

        var num = x + "*" + "0.4" + "+" + y + "*" + "0.6";
        //alert(num);

        document.getElementById("sum").value = eval(num);
    }*/
</script>

</html>
