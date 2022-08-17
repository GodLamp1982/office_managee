<%--
  Created by IntelliJ IDEA.
  User: GodLamp
  Date: 2022/8/13
  Time: 21:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>修改个人资料</title>
        <style type="text/css">
            html {
                height: 100%;
            }

            body {
                height: 90%;
                background-image: url('<%=request.getContextPath()%>/images/bg_06.png') ;
                background-repeat: no-repeat ;
                /*!* 缩放背景图 *!*/
                background-size: cover;
                background-position: center 0;
            }
            input{
                width: 300px;
                height: 40px;
                font-size: 18px;
            }
            tr td:first-child{
                width: 80px;
            }
        </style>
    </head>
    <body>


    <form action="<%=request.getContextPath()%>/user?action=updateUserData" method="post">
        <c:if test="${sessionScope.managerUpdateJudge == null}" var="exp">
            <c:set var="u" value="${sessionScope.currentUser}" />
        </c:if>
        <c:if test="${!exp}">
            <c:set var="u" value="${requestScope.managerUpdatePersonData}" />
        </c:if>

        <table border="1" cellspacing="0" cellpadding="0" style="width: 500px;height: 500px;margin: 50px auto;background-color: lightgrey;text-align: center;">
            <tr>
                <td>姓名</td>
                <td>
                    <input type="hidden" name="userId" value="${u.userId}"/>
                    <input type="text" name="userName" value="${u.userName}"/>
                </td>
            </tr>
            <tr>
                <td>账号</td>
                <td>
                    <input type="text" name="account" value="${u.account}"/>
                </td>
            </tr>
            <tr>
                <td>密码</td>
                <td>
                    <input type="password" name="pwd" value="${u.password}"/>
                </td>
            </tr>
            <tr>
                <td>电话</td>
                <td>
                    <input type="number" name="tel" value="${u.tel}"/>
                </td>
            </tr>
            <tr>
                <td>地址</td>
                <td>
                    <input type="hidden" name="power" value="${u.power}"/>
                    <input type="text" name="address" value="${u.address}"/>
                </td>
            </tr>
            <tr style="text-align: center;">
                <td colspan="2">
                    <input type="submit" value="修改" style="margin-bottom: 20px;"/><br>
                    <input type="button" value="返回"/>
                </td>
            </tr>
        </table>
    </form>
</body>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.8.3.js"></script>
<script type="text/javascript" >
    $(function () {
        $("input[value='返回']").click(function () {
            location.href = "<%=request.getContextPath()%>/dish?action=generalUserIndex";
            /*history.back();*/
        });

    });
</script>

</html>
