<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;700&display=swap" rel="stylesheet">

    <link rel="stylesheet" href="css/style.css">
    <script defer src="js/loginValidation.js"></script>

    <fmt:setLocale value="${sessionScope.local}" />
    <fmt:setBundle basename="local" var="loc" />

    <fmt:message bundle="${loc}" key="local.message" var="message" />
    <fmt:message bundle="${loc}" key="local.locbutton.name.ru" var="ru_button" />
    <fmt:message bundle="${loc}" key="local.locbutton.name.en" var="en_button" />
    <fmt:message bundle="${loc}" key="local.locbutton.login" var="login" />
    <fmt:message bundle="${loc}" key="local.locbutton.registration" var="registration" />
    <fmt:message bundle="${loc}" key="local.locbutton.logout" var="logout" />
    <fmt:message bundle="${loc}" key="local.locbutton.mainPage" var="mainPage" />

</head>
<body>

<div class="container">
    <form id="abc" action="Controller" class="abc" method="post">
        <button type="submit" name="local" formaction="Controller?command=goToMainPage" formmethod="post">${mainPage}</button>
        <c:if test="${not empty userRole}">
            <button type="submit" formaction="Controller?command=logOut" formmethod="post">${logout}</button>
        </c:if>
        <c:if test="${empty userRole}">
            <button type="submit" formaction="Controller?command=goToRegistrationPage" formmethod="post">${registration}</button>
            <button type="submit" name="local" formaction="Controller?command=goToLoginPage" formmethod="post">${login}</button>
        </c:if>
        <button type="submit" name="local" value="en" formaction="Controller?command=changeLanguage" formmethod="post">${en_button}</button>
        <button type="submit" name="local" value="ru" formaction="Controller?command=changeLanguage" formmethod="post">${ru_button}</button>
    </form>
</div>

<br>
<br>
<h2>List of films</h2>


<div class="container">
    <table>
        <tr>
            <div >
                <c:if test="${not empty userRole}">
                    <th width="7%">ID</th>
                    <th width="13%">Film name</th>
                    <th width="10%">Production year</th>
                    <th width="10%">Genre</th>
                    <th width="50%">Description</th>
                    <th width="10%"></th>
                </c:if>

                <c:if test="${empty userRole}">
                    <th width="9%">ID</th>
                    <th width="15%">Film name</th>
                    <th width="12%">Production year</th>
                    <th width="12%">Genre</th>
                    <th width="52%">Description</th>
                </c:if>
            </div>
        </tr>
        <c:forEach items="${films}" var="film" varStatus="status">
            <tr>
                <div>
                    <td>${film.id}</td>
                    <td>${film.name}</td>
                    <td>${film.productionYear}</td>
                    <td>
                        <c:forEach items="${film.genre}" var="genre">
                            ${genre}<br/>
                        </c:forEach>
                    </td>
                    <td>${film.description}</td>
                    <c:if test="${not empty userRole}">
                        <td>
                            <form action="Controller?command=goToFilmDescriptionPage" id="${status.index}" method="post" >
                                <button type="submit" name="filmId" value="${film.id}">Show film</button>
                            </form>
                        </td>
                    </c:if>
                </div>
            </tr>
        </c:forEach>

    </table>

</div>

    <footer>
        <div id="fut">
            <ul>
                <c:forEach items="${pageNumbers}" var="page" varStatus="status">
                    <li>
                        <a href="Controller?${URL}&pageNumber=${page}">${page}</a>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </footer>

</body>
</html>