<%--<%@ page language="java" contentType ="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"  %>--%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
<%--    <meta charset="utf-8">--%>

<%--    <fmt:setLocale value="${sessionScope.local}" />--%>
<%--    <fmt:setBundle basename="local" var="loc" />--%>

<%--    <fmt:message bundle="${loc}" key="local.logination.message" var="message" />--%>
<%--    <fmt:message bundle="${loc}" key="local.locbutton.name.ru" var="ru_button" />--%>
<%--    <fmt:message bundle="${loc}" key="local.locbutton.name.en" var="en_button" />--%>
</head>

<body>
    <jstl:redirect url="/Controller?command=goToMainPage" />
</body>
</html>