<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>

<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <fmt:setLocale value="${sessionScope.local}" />
    <fmt:setBundle basename="local" var="loc" />

    <fmt:message bundle="${loc}" key="local.registration.message" var="message" />
    <fmt:message bundle="${loc}" key="local.locbutton.name.ru" var="ru_button" />
    <fmt:message bundle="${loc}" key="local.locbutton.name.en" var="en_button" />

    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Form Validation</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;700&display=swap" rel="stylesheet">

    <link rel="stylesheet" href="css/style2.css">
    <script defer src="js/registrationValidation.js"></script>
</head>
<body>

<div class="container">
    <form id="abc" action="Controller" class="abc" method="post">
        <button type="submit" name="local" formaction="Controller?command=goToMainPage" formmethod="post">Main page</button>
        <button type="submit" name="local" formaction="Controller?command=goToLoginPage" formmethod="post">Login</button>
        <button type="submit" name="local" value="en" formaction="Controller?command=changeLanguage" formmethod="post">${en_button}</button>
        <button type="submit" name="local" value="ru" formaction="Controller?command=changeLanguage" formmethod="post">${ru_button}</button>
    </form>
</div>


<%--<div class="navbar">--%>
<%--    <div class="menu">--%>
<%--        <ul>--%>
<%--            <li><a href="#">HOME</a></li>--%>
<%--            <li><a href="#">ABOUT</a></li>--%>
<%--            <li><a href="#">SERVICE</a></li>--%>
<%--            <li><a href="#">DESIGN</a></li>--%>
<%--            <li><a href="#">CONTACT</a></li>--%>
<%--        </ul>--%>
<%--    </div>--%>
<%--</div>--%>


<%--<nav>--%>
<%--    <div class="nav-bar">--%>
<%--        <i class='bx bx-menu sidebarOpen' ></i>--%>
<%--        <span class="logo navLogo"><a href="#">CodingLab</a></span>--%>

<%--        <div class="menu">--%>
<%--            <div class="logo-toggle">--%>
<%--                <span class="logo"><a href="#">CodingLab</a></span>--%>
<%--                <i class='bx bx-x siderbarClose'></i>--%>
<%--            </div>--%>

<%--            <ul class="nav-links">--%>
<%--                <li><a href="#">Home</a></li>--%>
<%--                <li><a href="#">About</a></li>--%>
<%--                <li><a href="#">Portfolio</a></li>--%>
<%--                <li><a href="#">Services</a></li>--%>
<%--                <li><a href="#">Contact</a></li>--%>
<%--            </ul>--%>
<%--        </div>--%>

<%--        <div class="darkLight-searchBox">--%>
<%--            <div class="dark-light">--%>
<%--                <i class='bx bx-moon moon'></i>--%>
<%--                <i class='bx bx-sun sun'></i>--%>
<%--            </div>--%>

<%--            <div class="searchBox">--%>
<%--                <div class="searchToggle">--%>
<%--                    <i class='bx bx-x cancel'></i>--%>
<%--                    <i class='bx bx-search search'></i>--%>
<%--                </div>--%>

<%--                <div class="search-field">--%>
<%--                    <input type="text" placeholder="Search...">--%>
<%--                    <i class='bx bx-search'></i>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</nav>--%>

<%--<h2>Registration</h2>--%>
<%--<form action="Controller" method="post">--%>
<%--    <input type="hidden" name="command" value="registration">--%>
<%--    <p>Login <input type="text" name="login" required></p>--%>
<%--    <p>Password <input type="text" name="password" required></p>--%>
<%--    <p>Nickname <input type="text" name="nickname" required></p>--%>
<%--    <p>Name <input type="text" name="name" required></p>--%>
<%--    <p>Surname <input type="text" name="surname" required></p>--%>
<%--    <p>Phone number <input type="text" name="phoneNumber" required></p>--%>
<%--    <p>eMail <input type="text" name="email" required></p>--%>
<%--    <p><input type="submit" value="RUN" required></p>--%>
<%--</form>--%>

<%--<form action="Controller?command=changeLanguage" method="post">--%>
<%--    <input type="hidden" name="local" value="ru" /> <input type="submit"--%>
<%--                                                           value="${ru_button}" /> <br/>--%>
<%--</form>--%>

<%--<form action="Controller?command=changeLanguage" method="post">--%>
<%--    <input type="hidden" name="local" value="en" /> <input type="submit"--%>
<%--                                                           value="${en_button}" /> <br/>--%>
<%--</form>--%>
<%--<br/>--%>

<%--<c:out value="${message}"/>--%>

<%--<br/>--%>
<%--<a href="Controller?command=goToLoginPage">True log in</a>--%>
<%--<br/>--%>
<%--<br/>--%>
<%--<br/>--%>

<%--<div align="center">--%>
<%--    <form>--%>
<%--        <button type="submit" name="local" value="en" formaction="Controller?command=changeLanguage" formmethod="post">${en_button}</button>--%>

<%--        <button type="submit" name="local" value="ru" formaction="Controller?command=changeLanguage" formmethod="post">${ru_button}</button>--%>
<%--    </form>--%>
<%--</div>--%>




<div class="container">
        <form id="form" action="Controller" class="form" method="post">
            <input type="hidden" name="command" value="registration">
        <h1>Registration</h1>
        <div class="input-control">
            <label for="login">Login</label>
            <input id="login" name="login" type="text">
            <div class="error"></div>
        </div>
        <div class="input-control">
            <label for="password">Password</label>
            <input id="password" name="password" type="password">
            <div class="error"></div>
        </div>
        <div class="input-control">
            <label for="password2">Password again</label>
            <input id="password2" name="password2" type="password">
            <div class="error"></div>
        </div>
        <div class="input-control">
            <label for="nickname">Nickname</label>
            <input id="nickname" name="nickname" type="text">
            <div class="error"></div>
        </div>
        <div class="input-control">
            <label for="name">Name</label>
            <input id="name" name="name" type="text">
            <div class="error"></div>
        </div>
        <div class="input-control">
            <label for="surname">Surname</label>
            <input id="surname" name="surname" type="text">
            <div class="error"></div>
        </div>
        <div class="input-control">
            <label for="phoneNumber">Phone number</label>
            <input id="phoneNumber" name="phoneNumber" type="text">
            <div class="error"></div>
        </div>
        <div class="input-control">
            <label for="email">Email</label>
            <input id="email" name="email" type="text">
            <div class="error"></div>
        </div>

        <button type="submit">Sign Up</button>
    </form>
</div>

</body>
</html>
