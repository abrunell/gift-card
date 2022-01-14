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
            <c:if test="${addGiftCardSuccess}">
                <div class="success">Successfully added Gift Card with card number: ${savedGiftCard.cardNumber}</div>
            </c:if>
            <c:if test="${exceptionThrown}">
                <div class="error-message">Error: ${errorMessage}</div>
            </c:if>

            <form:form action="${add_giftCard_url}" method="post" modelAttribute="giftCard">

                <div class="mainForm">
                        <form:label path="cardNumber">Card Number: </form:label> <br>
                        <form:errors path="cardNumber" cssClass="error" />
                        <form:input type="number" min="1" path="cardNumber" /> <br>

                        <form:label path="initialBalance">Starting Balance: </form:label> <br>
                        <form:errors path="initialBalance" cssClass="error" />
                        <form:input type="number" max="50.00" path="initialBalance" />
                </div> <br>

                <div class="row">
                    <input type="submit" value="Submit">
                </div>
            </form:form>
        </div>
    </body>
</html>
