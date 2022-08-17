<%--
  Created by IntelliJ IDEA.
  User: GodLamp
  Date: 2022/8/14
  Time: 9:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>用户</title>
    <link type="text/css" rel="stylesheet" href="css/default.css"/>
    <style type="text/css">
        ul{
            display: block;
            text-align: center;
            list-style: none;
        }
        li{
            float: left;
            margin-left: 20px;
        }
        button{
            margin-right: 20px;
            width: 140px;
            height: 35px;
            font-size: 17px;
        }
        tr td{
            height: 50px;
        }
    </style>
</head>
<body>
    <%
        //设置此标志代表是管理员在添加用户
        request.getSession().setAttribute("judge",1);
    %>

    <p style="width: 100%;text-align: right;height: 50px;margin-top: 20px;"><button type="button"><a href="register.jsp?power=1">添加用户</a></button></p>
    <table border="1" cellpadding="0"cellspacing="0" style="width: 100%;text-align: center;">
        <tr>
            <td>序号</td>
            <td>姓名</td>
            <td>账号</td>
            <td>电话</td>
            <td>地址</td>
            <td>操作</td>
        </tr>
        <c:forEach items="${requestScope.allUser}" var="u">
            <tr>
                <td>${u.userId}</td>
                <td>${u.userName}</td>
                <td>${u.account}</td>
                <td>${u.tel}</td>
                <td>${u.address}</td>
                <td>
                    <a href="user?action=oneUserFind&account=${u.account}">修改</a>
                    /
                    <a href="user?action=managerDelUser&userId=${u.userId}" id="delButton" name="${u.userId}">删除</a>
                    <%--<a href="user?action=managerDelUser&userId=${u.userId}">删除</a>--%>
                </td>
            </tr>
        </c:forEach>
        <tr>
            <td colspan="6">
                <ul style="margin-left: 550px;height: 35px;line-height: 35px;">
                    <li><a href="user?action=allUser&currentPage=1">首页</a></li>
                    <li><a href="user?action=allUser&currentPage=${requestScope.preIndex}">上一页</a></li>
                    <c:forEach begin="1" end="${requestScope.allCount}" var="i">
                        <li><a href="user?action=allUser&currentPage=${i}">${i}</a></li>
                    </c:forEach>
                    <li><a href="user?action=allUser&currentPage=${requestScope.nextIndex}">下一页</a></li>
                    <li><a href="user?action=allUser&currentPage=${requestScope.allCount}">末页</a></li>
                </ul>
            </td>
        </tr>
    </table>

</body>

<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
<script type="text/javascript">
    $(function () {
        $("tr:odd").addClass("background-color","lightgray");
    });

    $("#delButton").click(function () {
        if (confirm("您确定删除该用户吗？")){
            var id = $("#delButton").attr("name");
            location.href="user?action=managerDelUser&userId=" + id;
        }
    });

</script>

</html>
