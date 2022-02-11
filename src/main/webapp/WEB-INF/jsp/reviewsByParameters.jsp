<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Form Validation</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;700&display=swap" rel="stylesheet">

    <link rel="stylesheet" href="css/style.css">

    <fmt:setLocale value="${sessionScope.local}" />
    <fmt:setBundle basename="local" var="loc" />

    <fmt:message bundle="${loc}" key="local.message" var="message" />
    <fmt:message bundle="${loc}" key="local.locbutton.name.ru" var="ru_button" />
    <fmt:message bundle="${loc}" key="local.locbutton.name.en" var="en_button" />
</head>
<body>

<div class="container">
    <form id="abc" action="Controller" class="abc" method="post">
        <button type="submit" formaction="Controller?command=goToMainPage" formmethod="post">Main page</button>
        <button type="submit" formaction="Controller?command=logOut" formmethod="post">Log out</button>
        <button type="submit" name="local" value="en" formaction="Controller?command=changeLanguage" formmethod="post">${en_button}</button>
        <button type="submit" name="local" value="ru" formaction="Controller?command=changeLanguage" formmethod="post">${ru_button}</button>
    </form>
</div>

<br>
<br>

<h2 align="center">Reviews</h2>
    <table>
        <tr>
            <th width="8%">User Id</th>
            <th width="20%">Film name</th>
            <th width="50%">Review</th>
            <th width="22%"></th>
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
