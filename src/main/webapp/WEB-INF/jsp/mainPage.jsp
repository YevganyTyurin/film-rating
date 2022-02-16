<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>

    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;700&display=swap" rel="stylesheet">

    <link rel="stylesheet" href="css/style.css">

    <fmt:setLocale value="${sessionScope.local}" />
    <fmt:setBundle basename="local" var="loc" />

    <fmt:message bundle="${loc}" key="local.message" var="message" />
    <fmt:message bundle="${loc}" key="local.locbutton.name.ru" var="ru_button" />
    <fmt:message bundle="${loc}" key="local.locbutton.name.en" var="en_button" />
    <fmt:message bundle="${loc}" key="local.locbutton.blocking" var="ban" />
    <fmt:message bundle="${loc}" key="local.locbutton.login" var="login" />
    <fmt:message bundle="${loc}" key="local.locbutton.registration" var="registration" />
    <fmt:message bundle="${loc}" key="local.locbutton.logout" var="logout" />
    <fmt:message bundle="${loc}" key="local.locbutton.films" var="films" />
</head>
<body>

<div class="container">
    <form id="abc" action="Controller" class="abc" method="post">
        <button type="submit" name="local" formaction="Controller?command=goToFilmsChoicePage" formmethod="post">${films}</button>
        <c:if test="${not empty userRole}">
            <button type="submit" formaction="Controller?command=logOut" formmethod="post">${logout}</button>
        </c:if>
        <c:if test="${empty userRole}">
            <button type="submit" name="local" formaction="Controller?command=goToRegistrationPage" formmethod="post">${registration}</button>
            <button type="submit" name="local" formaction="Controller?command=goToLoginPage" formmethod="post">${login}</button>
        </c:if>
        <button type="submit" name="local" value="en" formaction="Controller?command=changeLanguage" formmethod="post">${en_button}</button>
        <button type="submit" name="local" value="ru" formaction="Controller?command=changeLanguage" formmethod="post">${ru_button}</button>
    </form>
</div>


<br>

<%--<h2>${ban}</h2>--%>

<c:if test="${not empty systemMessage}">
<%--    <h2>${systemMessage}</h2>--%>
    <h2>${ban}</h2>
</c:if>

<%--<c:if test="${not empty systemMessage}">--%>
<%--    &lt;%&ndash;    <h2>${systemMessage}</h2>&ndash;%&gt;--%>
<%--    <h2>11111</h2>--%>
<%--</c:if>--%>

</body>
</html>
