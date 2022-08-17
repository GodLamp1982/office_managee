<%--
  Created by IntelliJ IDEA.
  User: GodLamp
  Date: 2022/8/14
  Time: 20:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改密码失败</title>
</head>
<body>
    修改密码失败！
    1秒后重新返回登录界面......
</body>

<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
<script type="text/javascript">
    $(function () {
        setTimeout(forwordpage,1*1000);
    });

    function forwordpage() {
        location.href="<%=request.getContextPath()%>/login.jsp";
    }
</script>

</html>
