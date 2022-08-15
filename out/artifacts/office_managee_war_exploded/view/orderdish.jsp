<%--
  Created by IntelliJ IDEA.
  User: GodLamp
  Date: 2022/8/13
  Time: 15:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>点餐界面</title>
    <style type="text/css">
        span{
            color: red;
        }
        ul{
            display: block;
            text-align: center;
            list-style: none;
        }
        li{
            float: left;
            margin-left: 20px;
        }

    </style>
</head>
<body>
    <h1 style="text-align: center">欢迎${sessionScope.currentUser.userName}，进入点餐系统</h1>
    <hr>
    <p style="text-align: center;">
        <a href="dish?action=generalUserIndex">全部</a>
        <c:forEach items="${requestScope.allDishType}" var="t">
            <a href="dish?action=generalUserIndex&remark=${t.typeId}">${t.typeName}</a>&nbsp;&nbsp;&nbsp;
        </c:forEach>
        <a href="order?action=orderCar" id="a1">查看点餐车</a><span><sub>${requestScope.noOrderCarError}</sub></span>&nbsp;&nbsp;&nbsp;
        <a href="<%=request.getContextPath()%>/view/persondata.jsp">修改个人资料</a>&nbsp;&nbsp;&nbsp;
        <a href="user?action=quitSystem">退出点餐系统</a>&nbsp;&nbsp;&nbsp;
    </p>

    <table border="1" cellspacing="0"cellpadding="0" style="width: 100%">
        <tr style="text-align: center;">
            <td>序号</td>
            <td>菜名</td>
            <td>特色</td>
            <td>食材</td>
            <td>价格：元</td>
            <td>类型</td>
            <td>图片</td>
            <td>点餐率：次</td>
            <td>备注</td>
            <td>选择</td>
        </tr>

        <c:forEach items="${requestScope.allDish}" var="d">
            <tr style="text-align: center;">
                <td style="text-align: center;">${d.dish.dishId}</td>
                <td>${d.dish.dishName}</td>
                <td>${d.dish.feature}</td>
                <td>${d.dish.ingredients}</td>
                <td>${d.dish.price}元</td>
                <td>${d.typeName}</td>
                <td style="text-align: center;"><img src="images/${d.dish.photo}" width="100px" height="100px" /></td>
                <td>${d.dish.clickRote}次</td>
                <c:choose>
                    <c:when test="${d.dish.remark == 0}">
                        <td>无</td>
                    </c:when>
                    <c:when test="${d.dish.remark == -1}">
                        <td>今日特价</td>
                    </c:when>
                    <c:when test="${d.dish.remark == -2}">
                        <td>厨师推荐</td>
                    </c:when>
                </c:choose>
                <td>
                    <input type="checkbox" name="select" value="${d.dish.dishId}" class="selection"/>
                </td>
            </tr>
        </c:forEach>

        <tr style="text-align: center">
            <td colspan="10">
                <ul>
                    <li><a href="dish?action=generalUserIndex&currentPage=1">首页</a></li>
                    <li><a href="dish?action=generalUserIndex&currentPage=${requestScope.preIndex}">上一页</a></li>
                    <c:forEach begin="1" end="${requestScope.allCount}" var="i">
                        <li><a href="dish?action=generalUserIndex&currentPage=${i}">${i}</a></li>
                    </c:forEach>
                    <li><a href="dish?action=generalUserIndex&currentPage=${requestScope.nextIndex}">下一页</a></li>
                    <li><a href="dish?action=generalUserIndex&currentPage=${requestScope.allCount}">末页</a></li>
                </ul>
            </td>
        </tr>

        <tr style="text-align: center;">
            <td colspan="10">
                <input type="hidden" name="path" value="<%=request.getContextPath()%>"/>
                <input type="button" value="加入点餐车"/>
            </td>
        </tr>
    </table>
</body>

<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="js/orderdish.js"></script>

</html>
