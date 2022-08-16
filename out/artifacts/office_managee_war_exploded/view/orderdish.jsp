<%--
  Created by IntelliJ IDEA.
  User: GodLamp
  Date: 2022/8/13
  Time: 15:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

    <div style="background-color: whitesmoke;float: left;display: inline-block;width: 89%;">
        <table border="1" cellspacing="0"cellpadding="0" style="">
            <tr style="text-align: center;">
                <td>序号</td>
                <td>菜名</td>
                <td>特色</td>
                <td>食材</td>
                <td>价格：元</td>
                <td>类型</td>
                <td>图片</td>
                <td>点餐率：次</td>
                <td>备注</td>
                <td>
                    选择<input type="checkbox" name="allselect"/>
                </td>
            </tr>

            <c:forEach items="${requestScope.allDish}" var="d">
                <tr style="text-align: center;">
                    <td style="text-align: center;">${d.dish.dishId}</td>
                    <td>${d.dish.dishName}</td>
                    <td>${d.dish.feature}</td>
                    <td>${d.dish.ingredients}</td>
                    <td>${d.dish.price}元</td>
                    <td>${d.typeName}</td>
                    <td style="text-align: center;"><img src="images/${d.dish.photo}" width="100px" height="100px" /></td>
                    <td>${d.dish.clickRote}次</td>
                    <c:choose>
                        <c:when test="${d.dish.remark == 0}">
                            <td>无</td>
                        </c:when>
                        <c:when test="${d.dish.remark == -1}">
                            <td>今日特价</td>
                        </c:when>
                        <c:when test="${d.dish.remark == -2}">
                            <td>厨师推荐</td>
                        </c:when>
                    </c:choose>
                    <td>
                        <input type="checkbox" name="select" value="${d.dish.dishId}" class="selection"/>
                    </td>
                </tr>
            </c:forEach>

            <tr style="text-align: center">
                <td colspan="10">
                    <ul>
                        <li><a href="dish?action=generalUserIndex&currentPage=1">首页</a></li>
                        <li><a href="dish?action=generalUserIndex&currentPage=${requestScope.preIndex}">上一页</a></li>
                        <c:forEach begin="1" end="${requestScope.allCount}" var="i">
                            <li><a href="dish?action=generalUserIndex&currentPage=${i}">${i}</a></li>
                        </c:forEach>
                        <li><a href="dish?action=generalUserIndex&currentPage=${requestScope.nextIndex}">下一页</a></li>
                        <li><a href="dish?action=generalUserIndex&currentPage=${requestScope.allCount}">末页</a></li>
                    </ul>
                </td>
            </tr>

            <tr style="text-align: center;">
                <td colspan="10">
                    <input type="hidden" name="path" value="<%=request.getContextPath()%>"/>
                    <input type="button" value="加入点餐车"/>
                </td>
            </tr>
        </table>
    </div>

</body>

<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="js/orderdish.js"></script>
<script type="text/javascript">
    $(function (){
        if ( ${requestScope.noOrderCarError} ){
            alert("没有选中的商品");
        }
    });
</script>

</html>
