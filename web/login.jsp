<%--
  Created by IntelliJ IDEA.
  User: GodLamp
  Date: 2022/8/13
  Time: 13:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
    <link type="text/css" rel="stylesheet" href="css/login.css"/>

</head>
<body>
    <form action="user?action=login" method="post">
        <table border="1" cellspacing="0" cellpadding="0" width="300px">
            <tr class="midd">
                <td colspan="2">
                    <h1>欢迎登录</h1>
                </td>
            </tr>
            <tr class="midd">
                <td colspan="2">
                    <a href="dish?action=indexShow">返回主页</a>
                </td>
            </tr>
            <tr>
                <td>账号：</td>
                <td>
                    <input type="text" name="account" value="ds"/><span>${sessionScope.userError == 201 ? "账号不存在！" : ""}</span>
                </td>
            </tr>
            <tr>
                <td>密码：</td>
                <td>
                    <input type="password" name="pwd" value="asd"/><span>${sessionScope.pwdError == 202 ? "密码错误！" : ""}</span>
                </td>
            </tr>
            <tr>
                <td>验证码:</td>
                <td>
                    <input type="text" name="code" style="width: 80px" id="in01"/>
                    <div style="background-color: lightgray;width: 60px;display: inline-block;" id="showCode"></div><span id="codeSpan"></span>
                    <span>${sessionScope.codeError == 204 ? "验证码错误！" : ""}</span>
                </td>
            </tr>
            <tr class="midd">
                <td colspan="2" style="text-align: center;">
                    <input type="submit" value="登录" style="margin-right: 30px"/>
                    <a href="forgotpwd.jsp" style="margin-right: 30px" id="a1">忘记密码？</a>
                    <a href="register.jsp?power=0">注册</a>
                </td>
            </tr>
        </table>
    </form>
</body>

<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="js/login.js"></script>

</html>
