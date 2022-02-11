<%@ page import="com.epam.film.rating.entity.film.Film" %>
<%@ page import="java.util.List" %>

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

    <script src="https://code.jquery.com/jquery-1.10.2.js"
            type="text/javascript"></script>
    <script src="js/app-ajax.js" type="text/javascript"></script>


    <fmt:setLocale value="${sessionScope.local}" />
    <fmt:setBundle basename="local" var="loc" />

    <fmt:message bundle="${loc}" key="local.message" var="message" />
    <fmt:message bundle="${loc}" key="local.locbutton.name.ru" var="ru_button" />
    <fmt:message bundle="${loc}" key="local.locbutton.name.en" var="en_button" />

</head>
<body>

<div class="container">
    <form id="abc" action="Controller" class="abc" method="post">
        <button type="submit" name="local" formaction="Controller?command=goToMainPage" formmethod="post">Main page</button>
        <button type="submit" formaction="Controller?command=logOut" formmethod="post">Log out</button>
        <button type="submit" name="local" value="en" formaction="Controller?command=changeLanguage" formmethod="post">${en_button}</button>
        <button type="submit" name="local" value="ru" formaction="Controller?command=changeLanguage" formmethod="post">${ru_button}</button>
    </form>
</div>

    <table>
        <tr>
            <div >
                <th>ID</th>
                <th>Film name</th>
                <th>Production year</th>
                <th>Description</th>
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

        <c:if test="${permission eq true}" var="testcif">
            <div id="review" align="center">
                <input type="button" value="Оставить отзыв" id='show_review_form' />
                <form style="display: none" id="review_form">
                    <label class="textarea">
                        <textarea cols="5" placeholder="Describe your experience.." id="review_text"></textarea>
                    </label>

                    Mark: <select name="user_profile_color_2" id="mark" required="required">
                    <option value=""></option>
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                </select>

                    <c:set var="filmId" scope="request" value="${film.id}" />

                    <input type="button" value="Оставить отзыв" id="submit_review" />
                </form>
            </div>

            <br>
            <div align="center" id="ajaxGetUserServletResponse"></div>
            <br>
        </c:if>

    <c:if test="${not empty reviews}">
        <br>
        <br>
        <h2>Reviews to this film</h2>
    </c:if>

    <c:forEach items="${reviews}" var="review" varStatus="status">
        <div class="c" id="${status.index}" align="center">
            <table>
                <tr>
                    <th>ID</th>
                    <th>review</th>
                </tr>

            <tr>
                <td>${review.id}</td>
                <td>${review.review}</td>
            </tr>
            </table>
            <tr>
                <sup class="n" id="plusamount">${review.likesAmount}</sup>

                <button class="n" onclick="updateLike(this)" type="submit" name="id" value="${review.id}" id="plus">+</button>

                <button class="n" onclick="updateDislike(this)" type="submit" name="id" value="${review.id}" id="minus">-</button>

                <sup class="n" id="minusamount">${review.dislikesAmount}</sup>
            </tr>
        </div>
    </c:forEach>
<br>

</body>
</html>
