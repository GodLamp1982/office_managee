<%--
  Created by IntelliJ IDEA.
  User: GodLamp
  Date: 2022/8/14
  Time: 8:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>管理员界面</title>
</head>
<body>
    <h1 style="width: 100%;text-align: center;">您好，${sessionScope.currentUser.userName}管理员</h1>
    <hr>
    <p style="text-align: center;">
        <a href="user?action=allUser" target="frame">用户管理</a>&nbsp;&nbsp;
        <a href="dish?action=dishTypeManage" target="frame">菜品分类管理</a>&nbsp;&nbsp;
        <a href="dish?action=findAllDish" target="frame">菜品管理</a>&nbsp;&nbsp;
        <a href="order?action=findAllOrder" target="frame">查看用户点餐情况</a>&nbsp;&nbsp;
        <a href="user?action=quitSystem">退出点餐系统</a>
    </p>
    <iframe name="frame" src="" width="100%" height="600px"></iframe>
</body>
</html>
