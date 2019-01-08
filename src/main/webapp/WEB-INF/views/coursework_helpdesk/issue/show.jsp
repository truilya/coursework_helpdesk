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
<h3>Issue attributes</h3>
    <table>
        <tr>
            <td>Date created</td>
            <td> ${f:formatLocalDateTime(issue.dateCreated, "dd.MM.yyyy")} </td>
        </tr>
        <tr>
            <td>Creator</td>
            <td>${issue.creator.login}</td>
        </tr>
        <tr>
            <td>Current engineer</td>
            <td>${issue.engineer.login}</td>
        </tr>
        <tr>
            <td>Name</td>
            <td>${issue.name}</td>
        </tr>
        <tr>
            <td>Description</td>
            <td>${issue.description}</td>
        </tr>
        <tr>
            <td>Priority</td>
            <td>${issue.issuePriority.name}</td>
        </tr>
        <tr>
            <td>Current status</td>
            <td>${issue.issueStatus.name}</td>
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


</body>
</html>
