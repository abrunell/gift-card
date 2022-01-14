<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/addform.css">
    <title>Add Gift Card</title>
</head>

<body>
<%@ include file = "header.jsp" %>

<div class="container">
    <c:if test="${addPurchaseSuccess}">
        <div class="success">Successfully added purchase on card ${savedPurchase.cardNumber} for $${savedPurchase.amount}</div>
    </c:if>
    <c:if test="${exceptionThrown}">
        <div class="error-message">Error: ${errorMessage}</div>
    </c:if>

    <form:form action="${add_purchase_url}" method="post" modelAttribute="purchase">

        <div class="mainForm">
            <form:label path="cardNumber">Card Number:</form:label> <br>
            <form:input type="text" path="cardNumber"/> <br>
            <form:label path="amount">Purchase Amount:</form:label> <br>
            <form:input type="number" path="amount"/>
        </div> <br>

        <div class="row">
            <input type="submit" value="Submit">
        </div>
    </form:form>
</div>
</body>
</html>