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
  </head>
  <body>
    <%--上部分--%>
      <div style="width: 100%;text-align: center;">
        <h1>网络点餐系统</h1>
        <a href="login.jsp">进入点餐系统</a>
      </div>
      <%--下部分--%>
      <div>
        <h5>热点菜品</h5>
          <c:forEach items="${requestScope.toDayHotDish}" var="h">
              <div style="display: inline-block;">
                  <img src="images/${h.dish.photo}" width="100px" height="100px" /><br>
                  <span><a href="dish?action=dishDetail&dishId=${h.dish.dishId}">${h.dish.dishName}</a></span>&nbsp;
                  <span>${h.dish.price}元</span>
              </div>
          </c:forEach>
      </div>

      <div>
          <h5>今日特价</h5>
          <c:forEach items="${requestScope.toDayPriceDish}" var="h">
              <div style="display: inline-block;">
                  <img src="images/${h.dish.photo}" width="100px" height="100px" /><br>
                  <span><a href="dish?action=dishDetail&dishId=${h.dish.dishId}">${h.dish.dishName}</a></span>&nbsp;&nbsp;&nbsp;
                  <span>${h.dish.price}元</span>
              </div>
          </c:forEach>
      </div>

      <div>
          <h5>厨师推荐</h5>
          <c:forEach items="${requestScope.toDayRecomDish}" var="h">
              <div style="display: inline-block;">
                  <img src="images/${h.dish.photo}" width="100px" height="100px" /><br>
                  <span><a href="dish?action=dishDetail&dishId=${h.dish.dishId}">${h.dish.dishName}</a></span>&nbsp;&nbsp;&nbsp;
                  <span>${h.dish.price}元</span>
              </div>
          </c:forEach>
      </div>
  </body>
</html>
