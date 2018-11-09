<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Taco Cloud</title>
</head>
<body>
<h1>Login</h1>
<div th:if="${error}">
    Unable to login. Check your username and password.
</div>
<p>New here? Click
    <a th:href="@{/register}">here</a> to register.</p>
<!-- tag::thAction[] -->
<form method="POST" th:action="@{/login}" id="loginForm">
    <!-- end::thAction[] -->
    <label for="username">Username: </label>
    <input type="text" name="username" id="username" /><br/>
    108 CHAPTER 4 Securing Spring
    <label for="password">Password: </label>
    <input type="password" name="password" id="password" /><br/>
    <input type="submit" value="Login"/>
</form>
</body>
</html>