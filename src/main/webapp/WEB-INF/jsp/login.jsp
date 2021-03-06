<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>

    <fmt:setLocale value="${sessionScope.local}" />
    <fmt:setBundle basename="local" var="loc" />

    <fmt:message bundle="${loc}" key="local.logination.message" var="message" />
    <fmt:message bundle="${loc}" key="local.locbutton.name.ru" var="ru_button" />
    <fmt:message bundle="${loc}" key="local.locbutton.name.en" var="en_button" />
    <fmt:message bundle="${loc}" key="local.locbutton.registration" var="registration" />
    <fmt:message bundle="${loc}" key="local.locbutton.mainPage" var="mainPage" />

    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;700&display=swap" rel="stylesheet">

    <link rel="stylesheet" href="css/style.css">
    <script defer src="js/loginValidation.js"></script>

</head>
<body>
<div class="form">

    <div class="container">
        <form id="abc" action="Controller" class="abc" method="post">
            <button type="submit" name="local" formaction="Controller?command=goToMainPage" formmethod="post">${mainPage}</button>
            <button type="submit" name="local" formaction="Controller?command=goToRegistrationPage" formmethod="post">${registration}</button>
            <button type="submit" name="local" value="en" formaction="Controller?command=changeLanguage" formmethod="post">${en_button}</button>
            <button type="submit" name="local" value="ru" formaction="Controller?command=changeLanguage" formmethod="post">${ru_button}</button>
        </form>
    </div>

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
