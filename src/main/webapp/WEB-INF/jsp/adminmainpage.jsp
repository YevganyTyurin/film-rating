<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>

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

    <script src="js/adminMainPage.js" type="text/javascript"></script>

    <fmt:setLocale value="${sessionScope.local}" />
    <fmt:setBundle basename="local" var="loc" />

    <fmt:message bundle="${loc}" key="local.message" var="message" />
    <fmt:message bundle="${loc}" key="local.locbutton.name.ru" var="ru_button" />
    <fmt:message bundle="${loc}" key="local.locbutton.name.en" var="en_button" />
</head>
<body>
<div class="container">
    <form id="abc" action="Controller" class="abc" method="post">
        <button type="button" value="Find Reviews" onclick="toggle_visibility('findReviews')">Find Reviews</button>

        <button type="button" value="Find Users"  onclick="toggle_visibility('findUsers')">Find Users</button>

        <button type="button" value="add Film" onclick="toggle_visibility('addFilm')">add Film</button>


        <button type="submit" name="local" formaction="Controller?command=goToMainPage" formmethod="post">Main page</button>
        <button type="submit" formaction="Controller?command=logOut" formmethod="post">Log out</button>
        <button type="submit" name="local" value="en" formaction="Controller?command=changeLanguage" formmethod="post">${en_button}</button>
        <button type="submit" name="local" value="ru" formaction="Controller?command=changeLanguage" formmethod="post">${ru_button}</button>
    </form>
</div>

<br>
<br>

<div class="container">
    <div id="findReviews" style="display: none">
        <form  action="Controller?command=findReviewsByParameters" method="get">
            <h3>Select reviews parameters</h3>
            <input type="hidden" name="command" value="findReviewsByParameters">
            <br>
            <div class="inputs">
                <label>User id: </label>
                <input type="number" name="userId" value="user id"/>
            </div>
            <br>
            <div class="inputs">
                <label>Production Year: </label>
                <input type="number" name="productionYear" value="production year"/>
            </div>
            <br>
            <label>Age Rating: </label>
            <div class="inputs">
                <input type="radio" name="age_rating" value="R6">6+
                <input type="radio" name="age_rating" value="R12"/>12+
                <input type="radio" name="age_rating" value="R14"/>14+
                <input type="radio" name="age_rating" value="R16"/>16+
                <input type="radio" name="age_rating" value="R18"/>18+
            </div>
            <br>
            <label>Type of video: </label>
            <div class="inputs">
                <input type="radio" name="type" value="film"/>Film
                <input type="radio" name="type" value="serial"/>Serial
            </div>
            <br>
            <label>Genres: </label>
            <div class="inputs">
                <input type="checkbox" name="genre" value="drama"/>drama
                <input type="checkbox" name="genre" value="action"/>action
                <input type="checkbox" name="genre" value="sci-fi"/>sci-fi
                <input type="checkbox" name="genre" value="thriller"/>thriller
            </div>
            <br>

            <button type="submit">Find reviews</button>
        </form>
    </div>
</div>


<div class="container">
    <div id="findUsers" style="display: none">
        <form action="Controller?command=findUsers" method="get">

            <h3>Select user parameters</h3>
            <input type="hidden" name="command" value="findUsers">

            <div class="inputs">
                <label>User id: </label>
                <input type="number" name="userId" value="user id"/>
            </div>

            <button type="submit">Find user</button>
        </form>
    </div>
</div>


<div class="container">
    <div id="addFilm" style="display: none">
        <form action="Controller?command=addFilm" method="get">
            <h3>Add film parameters</h3>
            <input type="hidden" name="command" value="addFilm">
            <br>
            <div class="inputs">
                <label>Film name: </label>
                <input type="text" name="filmName" value=""/>
            </div>
            <br>
            <div class="inputs">
                <label>Description: </label>
                <input type="text" name="description" value=""/>
            </div>
            <br>
            <div class="inputs">
                <label>Production Year: </label>
                <input type="number" name="productionYear" value=""/>
            </div>
            <br>
            <label>Age Rating: </label>
            <div class="inputs">
                <input type="radio" name="age_rating" value="R6">6+
                <input type="radio" name="age_rating" value="R12"/>12+
                <input type="radio" name="age_rating" value="R14"/>14+
                <input type="radio" name="age_rating" value="R16"/>16+
                <input type="radio" name="age_rating" value="R18"/>18+
            </div>
            <br>
            <label>Type of video: </label>
            <div class="inputs">
                <input type="radio" name="type" value="film"/>Film
                <input type="radio" name="type" value="serial"/>Serial
            </div>
            <br>
            <label>Genres: </label>
            <div class="inputs">
                <input type="checkbox" name="genre" value="drama"/>drama
                <input type="checkbox" name="genre" value="action"/>action
                <input type="checkbox" name="genre" value="sci-fi"/>sci-fi
                <input type="checkbox" name="genre" value="thriller"/>thriller
            </div>
            <br>

            <button type="submit">Add film</button>
        </form>
    </div>
</div>


</body>
</html>
