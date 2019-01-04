<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <title>Issue</title>
</head>
<body>
<h3>Please, enter the issue attributes</h3>
<form:form method="post" action="save" modelAttribute="issue">
    <table>
        <tr>
            <td><form:label path="name">Name</form:label></td>
            <td><form:input path="name" size="100"/></td>
        </tr>
        <tr>
            <td><form:label path="description">Description</form:label></td>
            <td><form:input path="description" size="200"/></td>
        </tr>
        <tr>
            <td> <form:select name="issuePriority" path="issuePriority" id="issuePriority">
                <form:options items="${issuePriorityList}" itemLabel="name" itemValue="id"/>
            </form:select></td>
        </tr>
        <tr>
            <td><input type="submit" value="Save"/></td>
        </tr>
        <tr>
            <td><button onclick="window.history.back()" type="button">Cancel</button></td>
        </tr>
    </table>
</form:form>
</body>
</html>
