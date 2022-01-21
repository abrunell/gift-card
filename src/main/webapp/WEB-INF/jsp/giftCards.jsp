<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.time.format.DateTimeFormatter" %>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/tables.css" >
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/addForm.css" >
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css" >
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.11.3/css/jquery.dataTables.css">
    <title>View Gift Cards</title>
</head>
<body>
    <%@ include file = "header.jsp" %>

    <div class="container">
        <div class="add">
            <br>
            <p>Add a new gift card:</p>

            <form:form action="${pageContext.request.contextPath}/giftCard"  method="post" modelAttribute="giftCard" >
                <div>
                    <form:label path="initialBalance">Starting Balance: </form:label><br>
                    <form:input type="number" max="50.00" step="0.01" path="initialBalance"/>
                    <input type="submit" value="Submit" >
                </div>
                <br><br><form:errors path="initialBalance" cssClass="error" />
            </form:form>
        </div>

        <div>
            <c:if test="${addGiftCardSuccess}">
                <div class="success">Successfully added Gift Card ${savedGiftCard.cardNumber} for $${savedGiftCard.initialBalance}</div>
            </c:if>
            <c:if test="${registrationSuccess}">
                <div class="success">Successfully registered Gift Card ${registeredGiftCard.cardNumber}.</div>
            </c:if>
            <c:if test="${deactivationSuccess}">
                <div class="success">Successfully deactivated Gift Card with card number: ${deactivatedGiftCard.cardNumber}</div>
            </c:if>
            <c:if test="${exceptionThrown}">
                <div class="error-message">Error: ${errorMessage}</div><br>
            </c:if>
        </div>

        <br>

        <div class="table-div">
            <table id="table_id" class="styled-table" >
                <thead>
                <tr>
                    <th>Card Number</th>
                    <th>Initial Balance</th>
                    <th>Remaining Balance</th>
                    <th>Phone Number</th>
                    <th>Activation Time</th>
                    <th></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="giftCard" items="${giftCardList}" >
                <tr>
                    <td>${giftCard.cardNumber}</td>
                    <td>${giftCard.initialBalance}</td>
                    <td>${giftCard.remainingBalance}</td>
                    <td>${giftCard.customer.phoneNumber}</td>
                    <td>${giftCard.activationTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"))}</td>

                    <td>
                        <a class="registerButton" href="${pageContext.request.contextPath}/registration?cardNumber=${giftCard.cardNumber}">Register Card</a>
                    </td>
                    <td>
                        <form:form method="post"
                                   action="${pageContext.request.contextPath}/giftCard/deactivate?cardNumber=${giftCard.cardNumber}"
                                   onsubmit="return confirm('Are you sure? This should only be done for new gift cards that were created by mistake, or cards that have a $0 balance and are not being reloaded.')">
                            <input id="deactivateButton" type="submit" value="Deactivate" />
                        </form:form>
                    </td>
                </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>

        <c:if test="${giftCardList.size() < 1}">
            <div style="color:red; font-weight:bold">There are no gift cards to display.</div>
        </c:if>
    </div>

<%--    Scripts needed to use DataTables for nice table formatting. --%>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"> </script>
    <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.11.3/js/jquery.dataTables.js"></script>
    <script>
        $(document).ready( function () {
            $('#table_id').DataTable();
        } );
    </script>
</body>
</html>
