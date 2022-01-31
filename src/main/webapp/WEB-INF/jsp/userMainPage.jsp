<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>

<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="utf-8">

    <fmt:setLocale value="${sessionScope.local}" />
    <fmt:setBundle basename="local" var="loc" />

    <fmt:message bundle="${loc}" key="local.message" var="message" />
    <fmt:message bundle="${loc}" key="local.locbutton.name.ru" var="ru_button" />
    <fmt:message bundle="${loc}" key="local.locbutton.name.en" var="en_button" />
</head>
<body>

<br>
<div align="right">
    <form>
        <button type="submit" name="local" value="en" formaction="Controller?command=changeLanguage" formmethod="post">${en_button}</button>

        <button type="submit" name="local" value="ru" formaction="Controller?command=changeLanguage" formmethod="post">${ru_button}</button>

        <button type="submit" formaction="Controller?command=logOut" formmethod="post">Log out</button>
    </form>
</div>

<br>
<br>
<div align="center">
<form  action="Controller?command=findFilmsByParameters" method="get">
    <h3>Select film parameters</h3>
    <input type="hidden" name="command" value="findFilmsByParameters">
    <p>production year <input type="number" name="year" value="production year"/></p>
    <p>
        <input type="checkbox" name="age_rating" value="R6">6+
        <input type="checkbox" name="age_rating" value="R12"/>12+
        <input type="checkbox" name="age_rating" value="R14"/>14+
        <input type="checkbox" name="age_rating" value="R16"/>16+
        <input type="checkbox" name="age_rating" value="R18"/>18+
    </p>

    <p>
        <input type="checkbox" name="type" value="film"/>Film
        <input type="checkbox" name="type" value="serial"/>Serial
    </p>

    <p>
        <input type="checkbox" name="genre" value="drama"/>drama
        <input type="checkbox" name="genre" value="action"/>action
        <input type="checkbox" name="genre" value="sci-fi"/>sci-fi
        <input type="checkbox" name="genre" value="thriller"/>thriller
    </p>

    <input type="submit" value="press"/>
</form>
</div>
</body>
</html>
