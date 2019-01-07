<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <title>Users</title>
</head>
<body>
<a href="/logout">Logout</a>
<section>
    <h2>Users</h2>
    <hr/>
    <a href="add">Add User</a>
    <hr/>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>login</th>
            <th colspan="2">Action</th>
        </tr>
        </thead>
        <c:forEach items="${users}" var="user">
            <jsp:useBean id="user" scope="page" type="coursework_helpdesk.model.User"/>
            <tr>
                <td>${user.login}</td>
                <td>
                    <form:form action="edit" method="post">
                        <input type="hidden" name="id" value="${user.id}"/>
                        <button>Edit</button>
                    </form:form>
                </td>
                <td><form:form action="delete" method="post">
                    <input type="hidden" name="id" value="${user.id}"/>
                    <button>Delete</button>
                </form:form></td>
            </tr>
        </c:forEach>
        <tr>
            <td><button onclick="window.history.back()" type="button">Cancel</button></td>
        </tr>
    </table>
</section>
</body>
</html>
