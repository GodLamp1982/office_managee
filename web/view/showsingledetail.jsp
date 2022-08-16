<%--
  Created by IntelliJ IDEA.
  User: GodLamp
  Date: 2022/8/13
  Time: 12:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>菜品详情</title>
    <style type="text/css">
        tr td{
            margin-left: 20px;
        }
        tr td:last-child{
            width: 400px;
        }
        html {
            height: 100%;
        }

        body {
            height: 100%;
            background-image: url('../images/bg_02.png') ;
            background-repeat: no-repeat ;
            /*!* 缩放背景图 *!*/
            background-size: cover;
            background-position: center 0;
        }
        img{
            border-radius: 10%;
        }
    </style>
</head>
<body>
    <c:set value="${requestScope.dish}" var="d" />
    <table cellpadding="0" cellspacing="0" style="width: 800px;">
        <tr>
            <td rowspan="7">
                <img src="images/${d.dish.photo}" width="300px" height="300px" />
            </td>
            <td>菜名：</td>
            <td>${d.dish.dishName}</td>
        </tr>
        <tr>
            <td>特色：</td>
            <td>${d.dish.feature}</td>
        </tr>
        <tr>
            <td>食材：</td>
            <td>${d.dish.ingredients}</td>
        </tr>
        <tr>
            <td>价格：</td>
            <td>${d.dish.price}元</td>
        </tr>
        <tr>
            <td>类型：</td>
            <td>${d.typeName}</td>
        </tr>
        <tr>
            <td>点击率：</td>
            <td>${d.dish.clickRote}次</td>
        </tr>
        <tr>
            <td>备注：</td>
            <c:choose>
                <c:when test="${d.dish.remark == 0}">
                    <td></td>
                </c:when>
                <c:when test="${d.dish.remark == -1}">
                    <td>今日特价</td>
                </c:when>
                <c:when test="${d.dish.remark == -2}">
                    <td>厨师推荐</td>
                </c:when>
            </c:choose>
        </tr>
        <tr>
            <td colspan="3" style="text-align: right;">
                <input type="button" value="返回" style="width: 70px"/>
            </td>
        </tr>

    </table>
</body>

<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
<script type="text/javascript">
    $("input[value='返回']").click(function () {
        history.back();
    });
</script>

</html>
