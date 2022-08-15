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
    <link type="text/css" rel="stylesheet" href="css/style.css"/>
    <style>
        body {
            height: 100%;
            background: #16a085;
            overflow: hidden;
        }

        canvas {
            z-index: -1;
            position: absolute;
        }

        span{
            color: whitesmoke;
            font-size: 15px;
        }

    </style>
</head>
<body>
    <form action="user?action=login" method="post">
        <dl class="admin_login" style="width: 450px;">
            <dt style="color: white;">
                <strong>网络点餐系统</strong>
                <em>Ordering System</em>
            </dt>
            <dd class="user_icon" style="margin-left: 80px">
                <input type="text" placeholder="账号" class="login_txtbx" name="account" value="ds" style="height: 40px;width: 270px"/>
                <span>${sessionScope.userError == 201 ? "账号不存在！" : ""}</span>
            </dd>
            <dd class="pwd_icon" style="margin-left: 80px">
                <input type="password" placeholder="密码" class="login_txtbx" name="pwd" value="asd" style="height: 40px;width: 270px"/>
                <span>${sessionScope.pwdError == 202 ? "密码错误！" : ""}</span>
            </dd>
            <dd class="val_icon" style="margin-left: 80px">
                <div class="checkcode" style="height: 40px;width: 270px;">
                    <input type="text" id="J_codetext" placeholder="验证码" maxlength="4" class="login_txtbx" name="code" id="in01" style="height: 40px;width: 170px;"/>
                    <div style="background-color: lightgray;width: 100px;height:40px;display: inline-block;font-size: 20px;text-align: center;line-height: 40px;float: right;" id="showCode"></div>
                </div>
                <span id="codeSpan"></span>
                <span style="line-height: 40px;">${sessionScope.codeError == 204 ? "验证码错误！" : ""}</span>
            </dd>
            <dd style="text-align: center;">
                <a href="dish?action=indexShow" style="margin-right: 30px;color: white;">返回主页</a>
                <a href="forgotpwd.jsp" style="margin-right: 30px;color: white;" id="a1">忘记密码？</a>
                <a href="register.jsp?power=0" style="color: white;">注册</a>
            </dd>
            <dd style="text-align: center;">
                <input type="submit" value="立即登陆" class="submit_btn" style="width: 270px;height: 40px;"/>
            </dd>
        </dl>
    </form>
</body>

<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="js/login.js"></script>
<script src="js/verificationNumbers.js"></script>
<script src="js/Particleground.js"></script>
<script>
    $(document).ready(function() {
        //粒子背景特效
        $('body').particleground({
            dotColor: '#5cbdaa',
            lineColor: '#5cbdaa'
        });
    });
</script>
</html>
