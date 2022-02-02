<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <meta charset="utf-8">

        <script src="https://code.jquery.com/jquery-1.10.2.js"
                type="text/javascript"></script>
    <script src="js/showUserPage.js" type="text/javascript"></script>
<%--    <link href="css/style.css" rel="stylesheet">--%>

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
<table>
    <tr>
        <div >
            <td>ID</td>
            <td>name</td>
            <td>surname</td>
            <td>nickname</td>
            <td>role</td>
            <td>is banned</td>
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
<div align="center">
    <button onclick="makeAdmin(document.getElementById('a').value, document.getElementById('b').value)">make admin</button>
    <button onclick="makeUser(document.getElementById('a').value, document.getElementById('b').value)">make user</button>
<%--    <button onclick="ban(document.getElementById('c').value, document.getElementById('d').value)">ban</button>--%>
<%--    <button onclick="unban(document.getElementById('c').value, document.getElementById('d').value)">unban</button>--%>
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
