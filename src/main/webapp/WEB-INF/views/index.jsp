<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 05.11.2018
  Time: 15:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix= "c" uri= "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>helpdesk</title>
</head>
<body>
    <div class="container">
        <h1>This is secured!</h1>
        <p>
            Hello <b><c:out value="${pageContext.request.remoteUser}"/></b>
        </p>
        <c:url var="logoutUrl" value="/logout"/>
        <form class="form-inline" action="${logoutUrl}" method="post">
            <input type="submit" value="Log out"/>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
    </div>
</body>
</html>
