<%@ page contentType="text/html;charset=UTF-8"  %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/tables.css" >
    <title>View Purchases</title>
</head>
<body>
    <%@ include file = "header.jsp" %>

    <table class="styled-table">
        <thead>
        <tr>
            <th>Purchase Time</th>
            <th>Purchase Amount</th>
            <th>Gift Card Number</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${purchases}" var="purchase">
            <tr>
                <td>${purchase.dateTime}</td>
                <td>${purchase.amount}</td>
                <td>${purchase.cardNumber}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <c:if test="${purchases.size() < 1}">
        <div style="color:red; font-weight:bold">There are no purchases to display.</div>
    </c:if>
</body>
</html>