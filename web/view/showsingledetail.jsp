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
</head>
<body>
    <c:set value="${requestScope.dish}" var="d" />
    <table border="1" cellpadding="0" cellspacing="0">
        <tr>
            <td>菜名</td>
            <td>${d.dish.dishName}</td>
        </tr>
        <tr>
            <td>特色</td>
            <td>${d.dish.feature}</td>
        </tr>
        <tr>
            <td>食材</td>
            <td>${d.dish.ingredients}</td>
        </tr>
        <tr>
            <td>价格</td>
            <td>${d.dish.price}元</td>
        </tr>
        <tr>
            <td>类型</td>
            <td>${d.typeName}</td>
        </tr>
        <tr>
            <td>图片</td>
            <td>
                <img src="images/${d.dish.photo}" width="100px" height="100px" />
            </td>
        </tr>
        <tr>
            <td>点击率</td>
            <td>${d.dish.clickRote}次</td>
        </tr>
        <tr>
            <td>备注</td>
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
            <td colspan="2" style="text-align: center;">
                <input type="button" value="返回"/>
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
