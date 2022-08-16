<%--
  Created by IntelliJ IDEA.
  User: GodLamp
  Date: 2022/8/13
  Time: 13:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>注册</title>
    <style type="text/css">
        span{
            color: red;
        }
    </style>
</head>
<body>
    <form action="user?action=register" method="post">
        <table border="1" cellpadding="0" cellspacing="0">
            <tr>
                <td>用户姓名</td>
                <td>
                    <input type="text" name="userName"/><span></span>
                </td>
            </tr>
            <tr>
                <td>账号</td>
                <td>
                    <input type="text" name="account"/><span>${requestScope.accountHasExist}</span>
                </td>
            </tr>
            <c:choose>
                <c:when test="${param.power == '1'}">
                    <tr>
                        <td>账号类型</td>
                        <td>
                            <input type="radio" name="pow" value="0" id="ra01"/><label for="ra01">普通账号</label>
                            <input type="radio" name="pow" value="1" id="ra02"/><label for="ra02">管理员账号</label>
                        </td>
                    </tr>
                </c:when>
            </c:choose>
            <tr>
                <td>密码</td>
                <td>
                    <input type="password" name="pwd"/><span></span>
                </td>
            </tr>
            <tr>
                <td>确认密码</td>
                <td>
                    <input type="password" name="secondPwd"/><span></span>
                </td>
            </tr>
            <tr>
                <td>电话</td>
                <td>
                    <input type="number" name="tel"/><span></span>
                </td>
            </tr>
            <tr>
                <td>地址</td>
                <td>
                    <input type="text" name="address"/><span></span>
                </td>
            </tr>
            <tr>
                <td style="text-align: center;" colspan="2">
                    <input type="submit" value="注册"/>&nbsp;
                    <input type="button" value="返回">
                </td>
            </tr>
        </table>
    </form>
</body>

<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="js/register.js"></script>

</html>
