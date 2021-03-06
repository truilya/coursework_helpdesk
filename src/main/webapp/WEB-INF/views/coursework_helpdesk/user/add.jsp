
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <title>User</title>
</head>
<body>
<a href="/logout">Logout</a>
<h3>Please, enter the user attributes</h3>
<form:form method="POST"
           action="save" modelAttribute="user">
    <table>
        <tr>
            <td><form:label path="login">Name</form:label></td>
            <td><form:input path="login"/></td>
        </tr>
        <tr>
            <td><form:label path="password">
                Password</form:label></td>
            <td><form:password path="password"/></td>
        </tr>
        <td><form:label path="roles">Roles</form:label></td>
        <td><form:checkboxes path="roles" items="${roleList}"/></td>
        <tr>
            <td><input type="submit" value="Submit"/></td>
        </tr>
        <tr>
            <td><button onclick="window.history.back()" type="button">Cancel</button></td>
        </tr>
    </table>
</form:form>
</body>
</html>
