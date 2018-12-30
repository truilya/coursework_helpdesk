
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <title>User</title>
</head>
<body>
<h3>Please, enter the user attributes</h3>
<form:form method="POST"
           action="save" modelAttribute="user">
    <input type="hidden" name="id" value="${user.id}"/>
    <table>
        <tr>
            <td><form:label path="login">Login</form:label></td>
            <td><form:input path="login"/></td>
        </tr>
        <tr>
            <td><form:label path="password">
                Password</form:label></td>
            <td><form:input path="password"/></td>
        </tr>
        <td><form:label path="roles">Roles</form:label></td>
        <td><form:checkboxes path="roles" items="${roleList}"/></td>
        <tr>
            <td><input type="submit" value="Submit"/></td>
        </tr>
    </table>
</form:form>
</body>
</html>
