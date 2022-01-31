<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <meta charset="utf-8">

    <link href="css/style.css" rel="stylesheet">

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

<h3 align="center">Reviews</h3>
    <table>
        <tr>
            <td>User Id</td>
            <td>Film name</td>
            <td>Review</td>
            <td></td>
        </tr>
        <c:forEach items="${reviews}" var="review" varStatus="status">
        <tr>
            <td>${review.userId}</td>
            <td>${review.filmName}</td>
            <td>${review.review}</td>
            <td>
                <form action="Controller?command=goToReviewChangePage" method="post" >
                    <button type="submit" name="id" value="${review.reviewId}">Change review</button>
                </form>
            </td>
        </tr>
        </c:forEach>
    </table>


</body>
</html>
