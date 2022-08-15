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
    </style>
</head>
<body>
    <%
        //设置此标志代表是管理员在添加用户
        request.getSession().setAttribute("judge",1);
    %>

    <p style="width: 100%;text-align: center;"><a href="register.jsp">添加用户</a></p>
    <table border="1" cellpadding="0"cellspacing="0" style="width: 100%;">
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
                    <a href="user?action=managerDelUser&userId=${u.userId}">删除</a>
                </td>
            </tr>
        </c:forEach>
        <tr>
            <td colspan="6">
                <ul>
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
</html>
