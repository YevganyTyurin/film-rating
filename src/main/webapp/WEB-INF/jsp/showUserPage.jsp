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

        <script src="https://code.jquery.com/jquery-1.10.2.js"
                type="text/javascript"></script>
    <script src="js/showUserPage.js" type="text/javascript"></script>

    <fmt:setLocale value="${sessionScope.local}" />
    <fmt:setBundle basename="local" var="loc" />

    <fmt:message bundle="${loc}" key="local.message" var="message" />
    <fmt:message bundle="${loc}" key="local.locbutton.name.ru" var="ru_button" />
    <fmt:message bundle="${loc}" key="local.locbutton.name.en" var="en_button" />
    <fmt:message bundle="${loc}" key="local.locbutton.logout" var="logout" />
    <fmt:message bundle="${loc}" key="local.locbutton.mainPage" var="mainPage" />
</head>
<body>

<div class="container">
    <form id="abc" action="Controller" class="abc" method="post">
        <button type="submit" name="local" formaction="Controller?command=goToAdminPage" formmethod="post">${mainPage}</button>
        <button type="submit" formaction="Controller?command=logOut" formmethod="post">${logout}</button>
        <button type="submit" name="local" value="en" formaction="Controller?command=changeLanguage" formmethod="post">${en_button}</button>
        <button type="submit" name="local" value="ru" formaction="Controller?command=changeLanguage" formmethod="post">${ru_button}</button>
    </form>
</div>

<br>
<br>
<table>
    <tr>
        <div >
            <th width="7%">ID</th>
            <th width="20%">name</th>
            <th width="20%">surname</th>
            <th width="20%">nickname</th>
            <th width="20%">role</th>
            <th width="13%">is banned</th>
        </div>
    </tr>
    <tr>
        <div>
            <td>${user.id}</td>
            <td>${user.name}</td>
            <td>${user.surname}</td>
            <td>${user.nickname}</td>
            <td>
                <input type="hidden" value="${user.id}" name="a" id="a">
                <input type="hidden" value="${user.role}" name="b" id="b">
                <div id="roleResponse">${user.role}</div>
            </td>
            <td>
                <input type="hidden" value="${user.id}" name="a" id="c">
                <input type="hidden" value="${user.isBanned()}" name="b" id="d">
                <div id="isBannedResponse">${user.isBanned()}</div>
            </td>
        </div>
    </tr>
</table>

<br>
<div>
    <button onclick="changeRole(document.getElementById('a').value)" id="changeRole">change role</button>
    <button onclick="ban(document.getElementById('c').value)" id="banUnban">
        <c:if test="${user.isBanned() eq true}">
            unban
        </c:if>
        <c:if test="${user.isBanned() eq false}">
            ban
        </c:if>
    </button>
</div>

</body>
</html>