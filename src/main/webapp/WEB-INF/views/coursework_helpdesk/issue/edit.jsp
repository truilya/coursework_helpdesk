<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://coursework_helpdesk/functions" prefix="f" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Issue</title>
</head>
<body>
<a href="/logout">Logout</a>
<h3>Please, enter the issue attributes</h3>
<form:form method="post" action="saveUpdate" modelAttribute="issue">
    <input type="hidden" name="id" value="${issue.id}"/>
    <input type="hidden" name="dateCreated" value="${issue.dateCreated}"/>
    <input type="hidden" name="creator" value="${issue.creator.id}"/>
    <table>
        <tr>
            <td><form:label path="dateCreated">Date created</form:label></td>
            <td> ${f:formatLocalDateTime(issue.dateCreated, "dd.MM.yyyy")} </td>
        </tr>
        <tr>
            <td><form:label path="creator">Creator</form:label></td>
            <td>${issue.creator.login}</td>
        </tr>
        <tr>
            <td><form:label path="engineer">Current engineer</form:label></td>
            <td>${issue.engineer.login}</td>
        </tr>
        <tr>
            <td><form:label path="engineer">Engineer</form:label></td>
            <td> <form:select name="engineer" path="engineer" id="engineer">
                <form:options items="${engineerList}" itemLabel="login" itemValue="id"/>
            </form:select></td>
        </tr>
        <tr>
            <td><form:label path="name">Name</form:label></td>
            <td><form:input path="name" size="100"/></td>
        </tr>
        <tr>
            <td><form:label path="description">Description</form:label></td>
            <td><form:textarea path="description" rows="5" size="200"/></td>
        </tr>
        <tr>
            <td><form:label path="issuePriority">Priority</form:label></td>
            <td> <form:select name="issuePriority" path="issuePriority" id="issuePriority">
                <form:options items="${issuePriorityList}" itemLabel="name" itemValue="id"/>
            </form:select></td>
        </tr>
        <tr>
            <td><form:label path="issueStatus">Current status</form:label></td>
            <td>${issue.issueStatus.name}</td>
        </tr>
        <tr>
            <td><form:label path="issueStatus">Status</form:label></td>
            <td> <form:select name="issueStatus" path="issueStatus" id="issueStatus">
                <form:options items="${issueStatusList}" itemLabel="name" itemValue="id"/>
            </form:select></td>
        </tr>
        <tr>
            <td>Your comment for change</td>
            <td><textarea rows="5" cols="100" name="comment">Please, input Your comment with changes of issue attributes</textarea></td>
        </tr>
        <tr>
            <td><input type="submit" value="Save"/></td>
        </tr>
        <tr>
            <td><button onclick="window.history.back()" type="button">Cancel</button></td>
        </tr>
    </table>
    <br/>
    <h3>History of changes</h3>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>Date</th>
            <th>User</th>
            <th>Comment</th>
            <th>Status</th>
            <th>Priority</th>
            <th>Engineer</th>
            <th>Name</th>
            <th>Creator</th>
            <th>Description</th>
        </tr>
        </thead>
        <c:forEach items="${issueHistoryList}" var="issueHistory">
            <jsp:useBean id="issueHistory" scope="page" type="coursework_helpdesk.model.IssueHistory"/>
            <tr>
                <td>${f:formatLocalDateTime(issueHistory.dateChanged, "dd.MM.yyyy")}</td>
                <td>${issueHistory.changer.login}</td>
                <td>${issueHistory.comment}</td>
                <td>${issueHistory.issueStatus.name}</td>
                <td>${issueHistory.issuePriority.name}</td>
                <td>${issueHistory.engineer.login}</td>
                <td>${issueHistory.name}</td>
                <td>${issueHistory.creator.login}</td>
                <td>${issueHistory.description}</td>
            </tr>
        </c:forEach>
    </table>
</form:form>

</body>
</html>
