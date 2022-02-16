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
    <script src="js/reviewChangePage.js" type="text/javascript"></script>

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
        <button type="submit" formaction="Controller?command=goToAdminPage" formmethod="post">${mainPage}</button>
        <button type="submit" formaction="Controller?command=logOut" formmethod="post">${logout}</button>
        <button type="submit" name="local" value="en" formaction="Controller?command=changeLanguage" formmethod="post">${en_button}</button>
        <button type="submit" name="local" value="ru" formaction="Controller?command=changeLanguage" formmethod="post">${ru_button}</button>
    </form>
</div>

<br>

<h2>Change Review</h2>
<div class="container">
    <table>
        <tr>
            <div>
                <th>ID</th>
                <th>Review</th>
            </div>
        </tr>
        <tr>
            <div>
                <td>${review.id}</td>
                <td><div id="changeResponse">${review.review}</div></td>
            </div>
        </tr>
        <tr>
            <div>

                <td>
                    <button onclick="changeReview(document.getElementById('a').value, document.getElementById('b').value)">Add</button>
                </td>
                <td>
                    <input type="hidden" value="${review.id}" name="a" id="a">
                    <input type="text" value="${review.review}" name="revi" id="b">
                </td>

            </div>
        </tr>


        <tr>
            <div>
                <td><button onclick="deleteReview(this)" type="submit" name="id" value="${review.id}" id="deleteReview">delete review</button></td>
                <td><div id="deleteResponse">Не удален</div></td>
            </div>
        </tr>

    </table>

</div>


</body>
</html>
