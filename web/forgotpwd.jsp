<%--
  Created by IntelliJ IDEA.
  User: GodLamp
  Date: 2022/8/14
  Time: 19:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>忘记密码</title>
    <style type="text/css">
        html {
            height: 100%;
        }

        body {
            height: 80%;
            background-image: url('<%=request.getContextPath()%>/images/bg_05.png') ;
            background-repeat: no-repeat ;
            /*!* 缩放背景图 *!*/
            background-size: cover;
            background-position: center 0;
        }
        tr td:first-child{
            width: 200px;
            height: 50px;
        }
        td{
            text-align: center;
        }
        input{
            width: 300px;
            height: 35px;
        }
    </style>
</head>
<body>
    <form action="user?action=forgetPassword" method="post">
        <table border="1" cellpadding="0" cellspacing="0" style="width: 600px;height: 500px;margin: 30px auto;">
            <tr>
                <td>请输入忘记密码的账号</td>
                <td>
                    <input type="text" name="account" value="${param.account}"/>
                </td>
            </tr>
            <tr>
                <td>请输入注册账号的用户名：</td>
                <td>
                    <input type="text" name="userName" value=""/>
                </td>
            </tr>
            <tr>
                <td>请输入注册账号的电话：</td>
                <td>
                    <input type="number" name="tel" value=""/>
                </td>
            </tr>
            <tr>
                <td>请输入新密码：</td>
                <td>
                    <input type="password" name="pwd" value=""/>
                </td>
            </tr>
            <tr>
                <td colspan="2" style="text-align: center;">
                    <input type="submit" value="修改" style="margin: 20px auto;"/>
                    <input type="button" value="返回"/>
                </td>
            </tr>
        </table>
    </form>
</body>

<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="js/forgot.js"></script>

</html>
