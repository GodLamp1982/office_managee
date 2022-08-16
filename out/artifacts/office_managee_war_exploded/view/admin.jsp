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
    <link type="text/css" rel="stylesheet" href="css/default.css"/>
    <style type="text/css">
        button{
            margin-right: 20px;
            width: 140px;
            height: 35px;
            font-size: 17px;
        }
        html {
            height: 100%;
        }

        body {
            height: 100%;
            background-image: url('<%=request.getContextPath()%>/images/bg_04.png') ;
            background-repeat: no-repeat ;
            /* 缩放背景图 */
            background-size: cover;
            background-position: center 0;
        }
    </style>
</head>
<body>
    <h1 style="width: 100%;text-align: center;height: 60px;line-height: 60px;">您好，${sessionScope.currentUser.userName}管理员</h1>
    <hr>
    <div style="display: inline-block;width: 10%;float: left;">
        <button type="button"><a href="user?action=allUser" target="frame">用户管理</a></button>
        <button type="button"><a href="dish?action=dishTypeManage" target="frame">菜品分类管理</a></button>
        <button type="button"><a href="dish?action=findAllDish" target="frame">菜品管理</a></button>
        <button type="button"><a href="order?action=findAllOrder" target="frame">查看用户点餐情况</a></button>
        <button type="button"><a href="user?action=quitSystem">退出点餐系统</a></button>
    </div>
    <iframe name="frame" src="" style="width: 89%;height: 600px;display: inline-block;float: left;background-color: ghostwhite"></iframe>
</body>
</html>
