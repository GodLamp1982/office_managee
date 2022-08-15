<%--
  Created by IntelliJ IDEA.
  User: GodLamp
  Date: 2022/8/14
  Time: 18:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>用户点餐详情</title>
    <style type="text/css">
        td{
            width: 200px;
        }
        tr{
            text-align: center;
        }
    </style>
</head>
<body>
    <c:forEach items="${requestScope.allUserOrderNumberDish}" var="a">
        <table border="1" cellspacing="0" cellpadding="0" style="margin-bottom: 20px;">
            <tr>
                <td>订单号</td>
                <td>${a.orderNumber}</td>
            </tr>
            <tr>
                <td>点餐用户</td>
                <td>${a.userName}</td>
            </tr>
            <c:forEach items="${a.dishMap.keySet()}" var="k" >
                <tr>
                    <td>${k}</td>
                    <td>${a.dishMap.get(k)}元</td>
                </tr>
            </c:forEach>

            <tr>
                <td>合计</td>
                <td>${a.total}元</td>
            </tr>
        </table>
    </c:forEach>
    <%--<p style="width: 100px;"><input type="button" value="返回" style="width: 50px;height: 30px;margin-left: 120px;"/></p>--%>
</body>

<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
<script type="text/javascript">
    $(function () {
        $("input[value='返回']").click(function (){
            history.back();
        });
    });
</script>

</html>
