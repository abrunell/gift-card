<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/addform.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css" >
    <title>Register Gift Card</title>
</head>

<body>
<%@ include file = "header.jsp" %>

    <div class="container">
        <div class="add">
            <br>
            <p>Register a card:</p>

            <form action="${pageContext.request.contextPath}/registration?cardNumber=${cardNumber}" method="post">
                <label for="cnum">Card Number:</label><br>
                <input type="number" id="cnum" name="cardNumber" value="${cardNumber}" readonly ><br>
                <label for="pnum">10-digit Phone:</label><br>
                <input type="text" id="pnum" name="phoneNumber" required><br><br>
                <input type="submit" value="Submit">
            </form>
        </div>

        <div>
            <c:if test="${registrationSuccess}">
                <div class="success">Successfully registered Gift Card ${savedGiftCard.cardNumber} to Phone Number ${savedGiftCard.customer.phoneNumber}.</div>
            </c:if>
            <c:if test="${exceptionThrown}">
                <div class="error-message">Error: ${errorMessage}</div>
            </c:if>
        </div>
    </div>
</body>
</html>
