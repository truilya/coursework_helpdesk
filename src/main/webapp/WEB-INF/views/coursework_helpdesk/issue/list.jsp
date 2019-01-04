<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <title>Issues</title>
</head>
<body>
<section>
    <h2>Issues</h2>
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
            <th colspan="1">Action</th>
        </tr>
        </thead>
        <c:forEach items="${issues}" var="issue">
            <jsp:useBean id="issue" scope="page" type="coursework_helpdesk.model.Issue"/>
            <tr>
                <td>${issue.id}</td>
                <td>${issue.name}</td>
                <td>${issue.dateCreated}</td>
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

            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>
