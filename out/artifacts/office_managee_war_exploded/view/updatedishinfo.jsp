<%--
  Created by IntelliJ IDEA.
  User: GodLamp
  Date: 2022/8/14
  Time: 11:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>修改菜品信息</title>
    <style type="text/css">
        tr{
            height: 50px;
        }
        input{
            width: 300px;
            height: 30px;
        }
    </style>
</head>
<body>
<c:set value="${requestScope.dish}" var="d" />
<form action="dish?action=updateDishInfo&dishId=${d.dish.dishId}" method="post" enctype="multipart/form-data">
<table border="1" cellpadding="0" cellspacing="0" style="width: 500px;margin: 15px auto;text-align: center;">
    <tr>
        <td>菜名</td>
        <td>
            <input type="text" name="dishName" value="${d.dish.dishName}"/>
        </td>
    </tr>
    <tr>
        <td>特色</td>
        <td>
            <input type="text" name="feature" value="${d.dish.feature}"/>
        </td>
    </tr>
    <tr>
        <td>食材</td>
        <td>
            <input type="text" name="ingredients" value="${d.dish.ingredients}"/>
        </td>
    </tr>
    <tr>
        <td>价格</td>
        <td>
            <input type="number" name="price" value="${d.dish.price}"/>元
        </td>
    </tr>
    <tr>
        <td>类型</td>
        <td>
            <select name="typeId">
                <c:forEach items="${requestScope.allDishTypess}" var="t">
                    <option value="${t.typeId}" ${d.dish.typeId == t.typeId ? "selected='selected'" : ""}>${t.typeName}</option>
                </c:forEach>
            </select>
        </td>
    </tr>
    <tr>
        <td>图片</td>
        <td>
            <img src="images/${d.dish.photo}" width="100px" height="100px" />
            <input type="file" name="file"/>
        </td>
    </tr>
    <tr>
        <td>点击率</td>
        <td>${d.dish.clickRote}次</td>
    </tr>
    <tr>
        <td>备注</td>
        <td>
            <select name="remark">
                <option value="0" ${d.dish.remark == 0 ? "checked='checked'" : ""}>无</option>
                <option value="1" ${d.dish.remark == 1 ? "checked='checked'" : ""}>今日特价</option>
                <option value="2" ${d.dish.remark == 2 ? "checked='checked'" : ""}>厨师推荐</option>
            </select>
        </td>
    </tr>
    <tr>
        <td colspan="2" style="text-align: center;">
            <input type="submit" value="修改" style="margin: 10px;"/>
            <input type="button" value="返回" style="margin: 10px;"/>
        </td>
    </tr>

</table>
</form>
</body>

<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
<script type="text/javascript">
    $("input[value='返回']").click(function () {
        history.back();
    });
</script>

</html>
