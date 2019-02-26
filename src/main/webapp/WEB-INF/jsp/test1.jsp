<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%
    String path = request.getContextPath();
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=0.5, maximum-scale=1.0, user-scalable=no" />
    <title>综合管理平台</title>
</head>
<body>

<h2>首页<h2>
data:
    <c:forEach items="${userList}" var="ul">
        ${ul}<br/>
    </c:forEach>

       <%-- <#list userList as item>
            ${item!}<br />
        </#list>--%>

    <button class="a"> click me</button>

</body>
</html>