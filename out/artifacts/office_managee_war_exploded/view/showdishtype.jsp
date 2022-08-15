<%--
  Created by IntelliJ IDEA.
  User: GodLamp
  Date: 2022/8/14
  Time: 9:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>菜品分类管理</title>
</head>
<body>
    <p style="width: 100%;text-align: center;"><a href="view/adddishtype.jsp">添加菜品分类</a></p>
    <table border="1" cellspacing="0" cellpadding="0">
        <tr>
            <td>序号</td>
            <td>分类名称</td>
            <td>操作</td>
        </tr>

        <c:forEach items="${requestScope.dishTypes}" var="t">
            <tr>
                <td>${t.typeId}</td>
                <td>${t.typeName}</td>
                <td>
                    <a href="view/adddishtype.jsp?typeName=${t.typeName}&typeId=${t.typeId}">修改</a>
                    /
                    <a href="dish?action=delType&id=${t.typeId}">删除</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
