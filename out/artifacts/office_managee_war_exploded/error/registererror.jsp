<%--
  Created by IntelliJ IDEA.
  User: GodLamp
  Date: 2022/8/13
  Time: 15:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册失败</title>
</head>
<body>
    注册失败
    1秒后返回注册界面.......
</body>

<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
<script type="text/javascript">
    $(function () {
        setTimeout(forwordpage,1*1000);
    });

    function forwordpage() {
        location.href="register.jsp";
    }
</script>

</html>
