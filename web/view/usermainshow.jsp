<%--
  Created by IntelliJ IDEA.
  User: GodLamp
  Date: 2022/8/16
  Time: 15:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>点餐界面</title>
    <link type="text/css" rel="stylesheet" href="css/default.css"></link>
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

        html {
            height: 90%;
            color: #5cbdaa;
        }

        body {
            height: 100%;
            background-image: url('<%=request.getContextPath()%>/images/bg_03.png') ;
            background-repeat: no-repeat ;
            /* 缩放背景图 */
            background-size: cover;
            background-position: center 0;
        }

        img{
            border-radius: 10%;
        }

        button{
            margin-right: 20px;
            width: 140px;
            height: 35px;
            font-size: 20px;
        }

    </style>
</head>
<body>

<h1 style="text-align: center;height: 60px;line-height: 60px;">欢迎${sessionScope.currentUser.userName}，进入点餐系统</h1>
<hr>
<p style="text-align: center;">

</p>
<%--按照条件搜索--%>
<form action="dish?action=searchByCondition" method="post">
    <p style="text-align: right;margin-right: 30px;margin-bottom: 10px;margin-top: 10px;">
        <input type="text" name="title" placeholder="请输入关键字" style="width: 200px;height: 30px;font-size: 15px;text-align: center;"/>
        <input type="number" name="begin" placeholder="请输入最低价格" style="width: 150px;height: 30px;font-size: 15px;text-align: center;"/>
        <input type="number" name="end" placeholder="请输入最高价格" style="width: 150px;height: 30px;font-size: 15px;text-align: center;"/>
        <input type="submit" value="搜索" style="width: 80px;height: 30px;"/>
    </p>
</form>

<div style="width: 10%;display: inline-block;float: left;">
    <button type="button"><a href="dish?action=generalUserIndex">全部</a></button>

    <%--菜品类型--%>
    <c:forEach items="${requestScope.allDishType}" var="t">
        <button type="button"><a href="dish?action=generalUserIndex&remark=${t.typeId}">${t.typeName}</a></button>
    </c:forEach>

    <button type="button"><a href="order?action=orderCar" id="a1">查看点餐车</a></button>

    <button type="button"><a href="<%=request.getContextPath()%>/view/persondata.jsp">修改个人资料</a></button>

    <button type="button"><a href="user?action=quitSystem">退出点餐系统</a></button>
</div>

</body>
</html>
