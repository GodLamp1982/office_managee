<%--
  Created by IntelliJ IDEA.
  User: GodLamp
  Date: 2022/8/14
  Time: 10:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>菜品添加</title>
    <style type="text/css">

    </style>
</head>
<body>

<c:if test="${param.typeName == null}" var="exp">
    <c:set var="urlPara" value="addType" />
</c:if>
<c:if test="${!exp}">
    <c:set var="urlPara" value="updateDishType"/>
</c:if>

<form action="../dish?action=${urlPara}" method="post">
    <table border="1" cellpadding="0" cellspacing="0" style="width: 500px;height: 150px;margin: 50px auto;text-align: center;">
        <tr>
            <td>类型名</td>
            <td>
                <input type="hidden" name="id" value="${param.typeId}"/>
                <input type="text" name="typeName" value="${param.typeName}" style="width: 200px" height="50px"/>
            </td>
        </tr>
        <tr>
            <td colspan="2" style="text-align: center;">
                <input type="submit" value="提交" style="height: 40px;width: 100px;"/>
                <input type="button" value="返回" style="height: 40px;width: 100px;"/>
            </td>
        </tr>

    </table>
</form>
</body>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.8.3.js"></script>
<script type="text/javascript">
    $("input[value='返回']").click(function () {
        history.back();
    });
</script>

</html>
