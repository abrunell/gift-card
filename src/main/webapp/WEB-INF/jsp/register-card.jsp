<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/addform.css">
    <title>Register Gift Card</title>
</head>

<body>
<%@ include file = "header.jsp" %>

<div class="container">
    <c:if test="${registrationSuccess}">
        <div class="success">Successfully registered Gift Card ${savedGiftCard.cardNumber} to Phone Number ${savedGiftCard.phoneNumber}.</div>
    </c:if>
    <c:if test="${exceptionThrown}">
        <div class="error-message">Error: ${errorMessage}</div>
    </c:if>

    <form action="${pageContext.request.contextPath}/registration/registerCard" method="post">
        <label for="cnum">Card Number:</label><br>
        <input type="number" id="cnum" name="cardNumber" required><br>
        <label for="pnum">10-digit Phone:</label><br>
        <input type="text" id="pnum" name="phoneNumber" required><br><br>
        <input type="submit" value="Submit">
    </form>


<%--    <form:form action="${register_card_url}" method="post" modelAttribute="giftCard" >--%>

<%--        <div class="row">--%>
<%--            <div class="col-15">--%>
<%--                <form:label path="cardNumber">Card Number: </form:label>--%>
<%--            </div>--%>
<%--            <div class="col-75">--%>
<%--                <input type="number" name="cardNumber"/>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--        <div class="row">--%>
<%--            <div class="col-15">--%>
<%--                <form:label path="phoneNumber">Phone Number: </form:label>--%>
<%--            </div>--%>
<%--            <div class="col-75">--%>
<%--                <input type="text" name="phoneNumber"/>--%>
<%--            </div>--%>
<%--        </div> <br>--%>

<%--        <div class="row">--%>
<%--            <input type="submit" value="Submit">--%>
<%--        </div>--%>
<%--    </form:form>--%>
</div>
</body>
</html>
