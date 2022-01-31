<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <meta charset="utf-8">

    <link href="css/style.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-1.10.2.js"
            type="text/javascript"></script>
    <script src="js/reviewChangePage.js" type="text/javascript"></script>

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
<h3 align="center">Change Review</h3>
<div align="center">
    <table>
        <tr>
            <div>
                <td>ID</td>
                <td>Review</td>
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
