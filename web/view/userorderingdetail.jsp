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
    <link type="text/css" rel="stylesheet" href="css/default.css"/>
    <style type="text/css">
        td{
            width: 200px;
        }
        tr{
            text-align: center;
        }
        ul li{
            float: left;
            margin-left: 20px;
        }
    </style>
</head>
<body>
    <div style="width: 100%;" align="center">
        <c:set var="o" value="1"/>
        <c:forEach items="${requestScope.allUserOrderNumberDish}" var="a">
            <table border="1" cellspacing="0" cellpadding="0" style="display: inline-block;margin: 50px 30px;">
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
            <c:if test="${o == 2}">
                <br>
            </c:if>
            <c:set var="o" value="${o + 1}"/>
        </c:forEach>

        <ul style="list-style: none;margin-left: 510px;height: 35px;line-height: 35px;">
            <li><a href="order?action=findAllOrder&currentPage=1">首页</a></li>
            <li><a href="order?action=findAllOrder&currentPage=${requestScope.preIndex}">上一页</a></li>
            <c:forEach begin="1" end="${requestScope.allCount}" var="p">
                <li><a href="order?action=findAllOrder&currentPage=${p}">${p}</a></li>
            </c:forEach>
            <li><a href="order?action=findAllOrder&currentPage=${requestScope.nextIndex}">下一页</a></li>
            <li><a href="order?action=findAllOrder&currentPage=${requestScope.allCount}">末页</a></li>
        </ul>
    </div>


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
