<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Sign In</title>

    <meta charset="utf-8">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">

    <fmt:setLocale value="${sessionScope.local}" />
    <fmt:setBundle basename="local" var="loc" />

    <fmt:message bundle="${loc}" key="local.logination.message" var="message" />
    <fmt:message bundle="${loc}" key="local.locbutton.name.ru" var="ru_button" />
    <fmt:message bundle="${loc}" key="local.locbutton.name.en" var="en_button" />

    <style>

        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 50vh;
        }

        .form {
            width: 200px;
            padding: 10px;
            border-radius: 10px;
            box-shadow: 0 4px 16px #ccc;
            font-family: sans-serif;
            letter-spacing: 1px;
        }
    </style>

</head>
<body>
<div class="form">
    <h2 align="center">Login</h2>
    <form  action="Controller" method="post">
        <input type="hidden" name="command" value="login">
        <p>Login</p>
        <p><input type="text" name="login" placeholder="login" required></p>
        <p>Password</p>
        <p><input type="text" name="password" placeholder="password" required></p>
        <p align="center"><input type="submit" value="press"  required></p>
    </form>

    <div align="center">
        <form>
            <button type="submit" name="local" value="en" formaction="Controller?command=changeLanguage" formmethod="post">${en_button}</button>

            <button type="submit" name="local" value="ru" formaction="Controller?command=changeLanguage" formmethod="post">${ru_button}</button>
        </form>
    </div>

    <br/>
<%--    <c:out value="${message}"/>--%>

</div>

</body>
</html>
