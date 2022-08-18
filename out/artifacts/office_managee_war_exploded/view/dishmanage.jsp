<%--
  Created by IntelliJ IDEA.
  User: GodLamp
  Date: 2022/8/14
  Time: 10:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>菜品管理</title>
    <link rel="stylesheet" href="css/default.css" type="text/css"/>
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
        .allbutton{
            width: 100px;
            height: 25px;
            font-size: 16px;
            margin: 15px 15px;
        }
        tr td{
            height: 50px;
        }
    </style>
</head>
<body>

<%--按照条件搜索--%>
<form action="dish?action=searchByCondition&jud=1" method="post">
    <p style="text-align: right;margin-right: 30px;margin-bottom: 10px;margin-top: 10px;">
        <input type="text" name="title" placeholder="请输入关键字" style="width: 200px;height: 30px;font-size: 15px;text-align: center;"/>
        <input type="number" name="begin" placeholder="请输入最低价格" style="width: 150px;height: 30px;font-size: 15px;text-align: center;"/>
        <input type="number" name="end" placeholder="请输入最高价格" style="width: 150px;height: 30px;font-size: 15px;text-align: center;"/>
        <input type="submit" value="搜索" style="width: 80px;height: 30px;"/>
    </p>
</form>

    <p>
        <select name="selectDishType" class="allbutton">
            <option value="-4">全部</option>
            <c:forEach items="${requestScope.allDishTypes}" var="t">
                <option value="${t.typeId}">${t.typeName}</option>
            </c:forEach>
        </select>
        <input type="button" value="按分类查询" class="allbutton"/>
        <button type="button" class="allbutton" style="float: right;margin-right: 30px"><a href="dish?action=findAllDishTypessss">添加菜品</a></button>
    </p>
    <table border="1" cellspacing="0" cellpadding="0" style="width: 100%;text-align: center;">
        <tr>
            <td>序号</td>
            <td>菜品名称</td>
            <td>价格</td>
            <td>类型</td>
            <td>点餐率：次</td>
            <td>备注</td>
            <td>操作</td>
        </tr>
        <c:forEach items="${requestScope.allDishs}" var="d">
            <tr>
                <td>${d.dish.dishId}</td>
                <td>${d.dish.dishName}</td>
                <td>${d.dish.price}元</td>
                <td>${d.typeName}</td>
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
                    <a href="dish?action=findDishById&dishId=${d.dish.dishId}">修改</a>
                    /
                    <a href="dish?action=delDish&dishId=${d.dish.dishId}">删除</a>
                </td>
            </tr>
        </c:forEach>
        <tr>
            <td colspan="7">
                <ul style="margin-left: 500px;">
                    <li><a href="dish?action=findAllDish&currentPage=1">首页</a></li>
                    <li><a href="dish?action=findAllDish&currentPage=${requestScope.preIndex}">上一页</a></li>
                    <c:forEach begin="1" end="${requestScope.allCount}" var="i">
                        <li><a href="dish?action=findAllDish&currentPage=${i}">${i}</a></li>
                    </c:forEach>
                    <li><a href="dish?action=findAllDish&currentPage=${requestScope.nextIndex}">下一页</a></li>
                    <li><a href="dish?action=findAllDish&currentPage=${requestScope.allCount}">末页</a></li>
                </ul>
            </td>
        </tr>
    </table>
</body>

<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
<script type="text/javascript">
    $(function () {

        $("input[value='按分类查询']").click(function () {
            var typeName = $("select[name='selectDishType']").val();
            location.href="dish?action=findByDishType&type=" + typeName;
        });

        $("form").submit(function () {
            var result = true;
            if ($("input[name='title']").val().length == 0 && $("input[name='begin']").val().length == 0 && $("input[name='end']").val().length == 0){
                result = false;
                alert("没有填写查询条件");
            }
            return result;
        });

    });
</script>

</html>
