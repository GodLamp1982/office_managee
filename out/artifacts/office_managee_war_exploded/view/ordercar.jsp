<%--
  Created by IntelliJ IDEA.
  User: GodLamp
  Date: 2022/8/13
  Time: 20:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>点餐车</title>
    <style type="text/css">
        html {
            height: 100%;
        }

        body {
            height: 100%;
            background-image: url('<%=request.getContextPath()%>/images/bg_05.png') ;
            background-repeat: no-repeat ;
            /*!* 缩放背景图 *!*/
            background-size: cover;
            background-position: center 0;
        }

        tr td:last-child{
            width: 40px;
        }

        .button{
            margin: 20px auto;
            width: 100px;
            height: 30px;
        }

    </style>
</head>
<body>
    <p style="text-align: center;">当前订单号：${sessionScope.currentOrderNumber}</p>
    <table border="1" cellpadding="0" cellspacing="0" style="width: 80%;text-align: center;margin-left: 150px;color: black;">
        <tr>
            <td>序号</td>
            <td>菜名</td>
            <td>价格：元</td>
            <td>图片</td>
            <td>
                选择
                <input type="checkbox" name="allselect"/>
            </td>
        </tr>

        <c:forEach items="${requestScope.currentOrderCar}" var="c">
            <tr>
                <td>${c.dish.dishId}</td>
                <td>${c.dish.dishName}</td>
                <td>${c.dish.price}元</td>
                <td><img src="images/${c.dish.photo}" width="100px" height="100px" /></td>
                <td>
                    <input type="checkbox" name="select" value="${c.dish.dishId}" class="selection"/>
                </td>
            </tr>
        </c:forEach>

        <tr>
            <td colspan="5" style="text-align: center;">
                <input type="hidden" name="path" value="<%=request.getContextPath()%>"/>
                <input type="button" value="提交" style="margin-right: 60px;" class="button"/>&nbsp;
                <input type="button" value="删除" style="margin-right: 60px;" class="button"/>&nbsp;
                <input type="button" value="返回" class="button"/>
            </td>
        </tr>
    </table>
</body>

<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="js/ordercar.js"></script>

</html>
