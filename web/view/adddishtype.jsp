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
    <title></title>
</head>
<body>

<c:if test="${param.typeName == null}" var="exp">
    <c:set var="urlPara" value="addType" />
</c:if>
<c:if test="${!exp}">
    <c:set var="urlPara" value="updateDishType"/>
</c:if>

<form action="../dish?action=${urlPara}" method="post">
    <table border="1" cellpadding="0" cellspacing="0">
        <tr>
            <td>类型名</td>
            <td>
                <input type="hidden" name="id" value="${param.typeId}"/>
                <input type="text" name="typeName" value="${param.typeName}"/>
            </td>
        </tr>
        <tr>
            <td colspan="2" style="text-align: center;">
                <input type="submit" value="提交"/>
                <input type="button" value="返回"/>
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
