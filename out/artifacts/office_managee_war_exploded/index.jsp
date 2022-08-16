<%--
  Created by IntelliJ IDEA.
  User: GodLamp
  Date: 2022/8/13
  Time: 8:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
        <title>网络点餐系统首页</title>
        <link rel="stylesheet" type="text/css" href="css/default.css"/>
      <style type="text/css">
          html {
              height: 100%;
          }

          body {
              height: 100%;
              background-image: url('images/bg_01.png') ;
              background-repeat: no-repeat ;
              /* 缩放背景图 */
              background-size: cover;
              background-position: center 0;
          }
          img{
              border-radius: 10%;
          }

      </style>
  </head>
  <body>
    <%--上部分--%>
      <div>
        <h1 style="width: 100%;text-align: center;">网上点餐系统</h1>
          <button type="button" style="background-color: lightyellow;float: right;margin-right: 30px;"><a href="login.jsp" style="font-size: 18px;">登录/注册</a></button><br>
      </div>
      <%--下部分--%>
      <div style="float: left;width: 40%;padding: 60px;">
          <h5 style="text-align: center;font-size: 20px;">热点菜品</h5>
          <c:set var="l" value="1"/>
          <c:forEach items="${requestScope.toDayHotDish}" var="h">
              <div style="display: inline-block;margin:20px;text-align: right;">
                  <img src="images/${h.dish.photo}" width="150px" height="150px" style="margin-left: 70px;margin-top: 20px;"/><br>
                  <span><a href="dish?action=dishDetail&dishId=${h.dish.dishId}">${h.dish.dishName}</a></span>&nbsp;
                  <span style="margin-right: 20px;">${h.dish.price}元</span>
              </div>
              <c:if test="${l == 2}">
                  <br>
              </c:if>
              <c:set var="l" value="${l+1}"/>
          </c:forEach>
      </div>

    <div style="float: left;padding: 0px;">
        <div>
            <h5 style="text-align:center;font-size: 20px;margin-top: 60px">今日特价</h5>
            <c:forEach items="${requestScope.toDayPriceDish}" var="h">
                <div style="display: inline-block;margin: 20px;text-align: right;">
                    <img src="images/${h.dish.photo}" width="150px" height="150px" style="margin-left: 70px;margin-top: 20px" /><br>
                    <span><a href="dish?action=dishDetail&dishId=${h.dish.dishId}">${h.dish.dishName}</a></span>&nbsp;&nbsp;&nbsp;
                    <span style="margin-right: 20px;">${h.dish.price}元</span>
                </div>
            </c:forEach>
        </div>

        <div>
            <h5 style="text-align:center;font-size: 20px;margin-top: 40px;">厨师推荐</h5>
            <c:forEach items="${requestScope.toDayRecomDish}" var="h">
                <div style="display: inline-block;text-align: right;margin: 20px;">
                    <img src="images/${h.dish.photo}" width="150px" height="150px" style="margin-left: 70px;margin-top: 20px" /><br>
                    <span><a href="dish?action=dishDetail&dishId=${h.dish.dishId}">${h.dish.dishName}</a></span>&nbsp;&nbsp;&nbsp;
                    <span style="margin-right: 20px;">${h.dish.price}元</span>
                </div>
            </c:forEach>
        </div>
    </div>


  </body>
</html>
