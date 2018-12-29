<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <title>Users</title>
</head>
<body>
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
                <!--    <a href="edit/${user.id}">Edit</a>-->
                    <form:form action="edit" method="post">
                        <input type="hidden" name="id" value="${user.id}"/>
                        <button>Edit</button>
                    </form:form>
                </td>
                <td><a href="delete?id=${user.id}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>
