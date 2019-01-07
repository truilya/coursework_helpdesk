
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix= "c" uri= "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>helpdesk</title>
</head>
<body>
    <section>
        <h2>This is Helpdesk!</h2>
        <c:forEach items="${roles}" var="role">
            <c:if test="${role=='ADMIN'}">
                <a href="/coursework_helpdesk/user/list">Users</a>
            </c:if>
            <c:if test="${role=='USER'}">
                <a href="/coursework_helpdesk/issue/list">Issues</a>
            </c:if>
            <c:if test="${role=='ENGINEER'}">
                <a href="/coursework_helpdesk/issue/list">Issues</a>
            </c:if>
        </c:forEach>
    </section>
</body>
</html>
