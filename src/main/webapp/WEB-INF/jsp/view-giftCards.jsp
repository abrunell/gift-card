<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.time.format.DateTimeFormatter" %>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="/css/tables.css" >
    <title>View Gift Cards</title>
</head>
<body>
    <%@ include file = "header.jsp" %>

    <div class="table-background">
        <table class="styled-table">
            <thead>
            <tr>
                <th>Card Number</th>
                <th>Initial Balance</th>
                <th>Remaining Balance</th>
                <th>Phone Number</th>
                <th>Activation Time</th>
            </tr>
            </thead>

            <tbody>
            <c:forEach var="giftCard" items="${giftCards}" >
                <tr>
                    <td>${giftCard.cardNumber}</td>
                    <td>${giftCard.initialBalance}</td>
                    <td>${giftCard.remainingBalance}</td>
                    <td>${giftCard.phoneNumber}</td>
                    <td>${giftCard.activationTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"))}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <c:if test="${giftCards.size() < 1}">
            <div style="color:red; font-weight:bold">There are no gift cards to display.</div>
        </c:if>
    </div>
</body>
</html>
