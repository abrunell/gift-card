<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css" >
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/home.css" >
        <title>Welcome</title>
    </head>

    <body>
    <%@ include file = "header.jsp" %>

        <div class="homeName">
            <h1>Austin's eGift Cards</h1>
            <p>An app for creating and managing virtual gift cards for your business.</p>
        </div>

        <div class="features">
            <p class="label">Austin's eGift Cards makes it easy for you to:</p>
            <p class="feature">Create new gift cards</p>
            <p class="feature">Track gift card transactions</p>
            <p class="feature">Register gift cards to specific customers</p>
            <p class="feature">See gift card statistics</p>
        </div>
    </body>
</html>
