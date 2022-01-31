<%@ page import="com.epam.film.rating.entity.film.Film" %>
<%@ page import="java.util.List" %>

<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="utf-8">

    <script src="https://code.jquery.com/jquery-1.10.2.js"
            type="text/javascript"></script>
    <script src="js/app-ajax.js" type="text/javascript"></script>
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
<br>
<h3 align="center">Description of film</h3>
<table>
    <tr>
        <div >
            <td>ID</td>
            <td>Film name</td>
            <td>Production year</td>
            <td>Description</td>
        </div>
    </tr>
    <tr>
        <div>
            <td>${film.id}</td>
            <td>${film.name}</td>
            <td>${film.productionYear}</td>
            <td>${film.description}</td>
        </div>
    </tr>
</table>
<br>

<c:if test="${permission eq true}" var="testcif">
    <div id="review" align="center">
        <input type="button" value="Оставить отзыв" id='show_review_form' />
        <form style="display: none" id="review_form">
            Mark: <select name="user_profile_color_2" id="mark" required="required">
            <option value=""></option>
            <option value="1">1</option>
            <option value="2">2</option>
            <option value="3">3</option>
            <option value="4">4</option>
            <option value="5">5</option>
        </select>

            Review :<input type="text"  id="review_text"><br>

            <c:set var="filmId" scope="request" value="${film.id}" />

            <input type="button" value="Оставить отзыв" id="submit_review" />
        </form>
    </div>

    <div align="center" id="ajaxGetUserServletResponse"></div>
    <br>
</c:if>

<h3 align="center">Reviews</h3>
    <c:forEach items="${reviews}" var="review" varStatus="status">
        <div class="c" id="${status.index}" align="center">
            <table>
                <tr>
                    <td>ID</td>
                    <td>review</td>
                </tr>

            <tr>
                <td>${review.id}</td>
                <td>${review.review}</td>
            </tr>
            </table>
            <tr>
                <sup class="n" >${review.likesAmount}</sup>

                <button class="n" onclick="updateLike(this)" type="submit" name="id" value="${review.id}" id="plus">+</button>

                <button class="n" onclick="updateDislike(this)" type="submit" name="id" value="${review.id}" id="minus">-</button>

                <sup class="n" >${review.dislikesAmount}</sup>
            </tr>
        </div>
        <br>
    </c:forEach>

</body>
</html>
