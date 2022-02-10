<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
<%--    <title>Sign In</title>--%>

<%--    <meta charset="utf-8">--%>

<%--    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">--%>

    <fmt:setLocale value="${sessionScope.local}" />
    <fmt:setBundle basename="local" var="loc" />

    <fmt:message bundle="${loc}" key="local.logination.message" var="message" />
    <fmt:message bundle="${loc}" key="local.locbutton.name.ru" var="ru_button" />
    <fmt:message bundle="${loc}" key="local.locbutton.name.en" var="en_button" />

<%--    <style>--%>

<%--        body {--%>
<%--            display: flex;--%>
<%--            justify-content: center;--%>
<%--            align-items: center;--%>
<%--            height: 50vh;--%>
<%--        }--%>

<%--        .form {--%>
<%--            width: 200px;--%>
<%--            padding: 10px;--%>
<%--            border-radius: 10px;--%>
<%--            box-shadow: 0 4px 16px #ccc;--%>
<%--            font-family: sans-serif;--%>
<%--            letter-spacing: 1px;--%>
<%--        }--%>
<%--    </style>--%>

    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Form Validation</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;700&display=swap" rel="stylesheet">

    <link rel="stylesheet" href="css/style2.css">
    <script defer src="js/loginValidation.js"></script>

</head>
<body>
<div class="form">
<%--    <h2 align="center">Login</h2>--%>
<%--    <form  action="Controller" method="post">--%>
<%--        <input type="hidden" name="command" value="login">--%>
<%--        <p>Login</p>--%>
<%--        <p><input type="text" name="login" placeholder="login" required></p>--%>
<%--        <p>Password</p>--%>
<%--        <p><input type="text" name="password" placeholder="password" required></p>--%>
<%--        <p align="center"><input type="submit" value="press"  required></p>--%>
<%--    </form>--%>

<%--    <div align="center">--%>
<%--        <form>--%>
<%--            <button type="submit" name="local" value="en" formaction="Controller?command=changeLanguage" formmethod="post">${en_button}</button>--%>

<%--            <button type="submit" name="local" value="ru" formaction="Controller?command=changeLanguage" formmethod="post">${ru_button}</button>--%>
<%--        </form>--%>
<%--    </div>--%>

    <div class="container">
        <form id="abc" action="Controller" class="abc" method="post">
            <button type="submit" name="local" formaction="Controller?command=goToMainPage" formmethod="post">Main page</button>
            <button type="submit" name="local" formaction="Controller?command=goToRegistrationPage" formmethod="post">Registration</button>
            <button type="submit" name="local" value="en" formaction="Controller?command=changeLanguage" formmethod="post">${en_button}</button>
            <button type="submit" name="local" value="ru" formaction="Controller?command=changeLanguage" formmethod="post">${ru_button}</button>
        </form>
    </div>

<%--    <br/>--%>
<%--    <c:out value="${message}"/>--%>

</div>


<br>
<br>

<div class="container">
    <form id="form" action="Controller" class="form" method="post">
        <input type="hidden" name="command" value="login">
        <h1>Login</h1>
        <div class="input-control">
            <label for="login">Login</label>
            <input id="login" name="login" type="text">
            <div class="error"></div>
        </div>
        <div class="input-control">
            <label for="password">Password</label>
            <input id="password" name="password" type="password">
            <div class="error"></div>
        </div>

        <button type="submit">Sign Up</button>
    </form>
</div>

</body>
</html>
