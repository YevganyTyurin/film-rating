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
</head>
<body>

<h2>Registration</h2>
<form action="Controller" method="post">
    <input type="hidden" name="command" value="registration">
    <p>Login <input type="text" name="login" required></p>
    <p>Password <input type="text" name="password" required></p>
    <p>Nickname <input type="text" name="nickname" required></p>
    <p>Name <input type="text" name="name" required></p>
    <p>Surname <input type="text" name="surname" required></p>
    <p>Phone number <input type="text" name="phoneNumber" required></p>
    <p>eMail <input type="text" name="email" required></p>
    <p><input type="submit" value="RUN" required></p>
</form>

<form action="Controller?command=changeLanguage" method="post">
    <input type="hidden" name="local" value="ru" /> <input type="submit"
                                                           value="${ru_button}" /> <br/>
</form>

<form action="Controller?command=changeLanguage" method="post">
    <input type="hidden" name="local" value="en" /> <input type="submit"
                                                           value="${en_button}" /> <br/>
</form>
<br/>

<c:out value="${message}"/>

<br/>
<a href="Controller?command=goToLoginPage">True log in</a>
<br/>

</body>
</html>
