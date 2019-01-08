<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://coursework_helpdesk/functions" prefix="f" %>
<html>
<head>
    <title>Issues</title>
</head>
<body>
<a href="/logout">Logout</a>
<section>
    <h2>Issues</h2>
    <form:form method="post" action="list" modelAttribute="issueFilter">
        <table>
            <thead>
            <tr>
                <th>Start date created</th>
                <th>End date created</th>
                <th>Current status</th>
                <th>Priority</th>
                <th>Creator</th>
                <th>Current engineer</th>
            </tr>
            </thead>
            <tr>
                <td><input type="date" name="startDate" value="${param.startDate}"></td>
                <td><input type="date" name="endDate" value="${param.endDate}"></td>
                <td>
                    <form:select multiple="true" path="issueStatuses"  name="issueStatuses" id="issueStatuses">
                        <form:options items="${allIssueStatusList}" itemLabel="name" itemValue="id"/>
                    </form:select>
                </td>
                <td>
                    <form:select multiple="true"  name="issuePriorities" path="issuePriorities" id="issuePriorities">
                        <form:options items="${issuePriorityList}" itemLabel="name" itemValue="id"/>
                    </form:select>
                </td>
                <td>
                    <form:select multiple="true"  name="creators" path="creators" id="creators">
                        <form:options items="${userList}" itemLabel="login" itemValue="id"/>
                    </form:select>
                </td>
                <td>
                    <form:select  multiple="true" name="engineers" path="engineers" id="engineers">
                        <form:options items="${engineerList}" itemLabel="login" itemValue="id"/>
                    </form:select>
                </td>
            </tr>
        </table>
        <button type="submit">Filter</button>
    </form:form>
    <hr/>
    <a href="add">New Issue</a>
    <hr/>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Date created</th>
            <th>Priority</th>
            <th>Status</th>
            <th>Creator</th>
            <th>Engineer</th>
            <th colspan="2">Action</th>
        </tr>
        </thead>
        <c:forEach items="${issues}" var="issue">
            <jsp:useBean id="issue" scope="page" type="coursework_helpdesk.model.Issue"/>
            <tr>
                <td>${issue.id}</td>
                <td>${issue.name}</td>
                <td> ${f:formatLocalDateTime(issue.dateCreated, "dd.MM.yyyy")} </td>
                <td>${issue.issuePriority.name}</td>
                <td>${issue.issueStatus.name}</td>
                <td>${issue.creator.login}</td>
                <td>${issue.engineer.login}</td>
                <td>
                    <form:form action="edit" method="post">
                        <input type="hidden" name="id" value="${issue.id}"/>
                        <button>Edit</button>
                    </form:form>
                </td>
                <td>
                    <form:form action="show" method="post">
                        <input type="hidden" name="id" value="${issue.id}"/>
                        <button>Show</button>
                    </form:form>
                </td>
            </tr>
        </c:forEach>
        <tr>
            <td><button onclick="window.history.back()" type="button">Cancel</button></td>
        </tr>
    </table>
</section>
</body>
</html>
