<%--
  Created by IntelliJ IDEA.
  User: GodLamp
  Date: 2022/8/14
  Time: 14:57
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<html>
<head>
    <title>���Ӳ�Ʒ</title>
    <style type="text/css">
        input{
            width: 200px;
            height: 30px;
        }
        .allbutton{
            width: 150px;
            height: 35px;
        }
    </style>
</head>
<form action="dish?action=addDishInfo" method="post" enctype="multipart/form-data">
    <table border="1" cellpadding="0" cellspacing="0" style="width: 500px;height: 500px;margin: 20px auto;text-align: center;">
        <tr>
            <td>����</td>
            <td>
                <input type="text" name="dishName"/>
            </td>
        </tr>
        <tr>
            <td>��ɫ</td>
            <td>
                <input type="text" name="feature"/>
            </td>
        </tr>
        <tr>
            <td>ʳ��</td>
            <td>
                <input type="text" name="ingredients"/>
            </td>
        </tr>
        <tr>
            <td>�۸�</td>
            <td>
                <input type="number" name="price"/>Ԫ
            </td>
        </tr>
        <tr>
            <td>����</td>
            <td>
                <select name="typeId" class="allbutton">
                    <c:forEach items="${requestScope.allDishTypess}" var="t">
                        <option value="${t.typeId}" ${d.dish.typeId == t.typeId ? "checked='checked'" : ""}>${t.typeName}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td>ͼƬ</td>
            <td>
                <input type="file" name="file" class="allbutton"/>
            </td>
        </tr>
        <tr>
            <td>��ע</td>
            <td>
                <select name="remark" class="allbutton">
                    <option value="0">��</option>
                    <option value="1">�����ؼ�</option>
                    <option value="2">��ʦ�Ƽ�</option>
                </select>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="����"/>&nbsp;
                <input type="button" value="����"/>
            </td>
        </tr>

    </table>
</form>
</body>

<script type="text/javascript" src="../js/jquery-1.8.3.js"></script>
<script type="text/javascript">
    $("input[value='����']").click(function () {
        history.back();
    });
</script>

</html>
