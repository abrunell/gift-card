<%@ page contentType="text/html;charset=UTF-8"  %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.time.format.DateTimeFormatter" %>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/tables.css" >
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/addform.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css" >
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.11.3/css/jquery.dataTables.css">
    <title>View Purchases</title>
</head>
<body>
    <%@ include file = "header.jsp" %>

    <div class="container">
        <div class="add">
            <br>
            <p>Add a new purchase:</p>

            <form action="${pageContext.request.contextPath}/purchases" method="post">
                <div class="add-form-div-parent">
                    <div class="add-form-div">
                        <label for="cnum">Card Number:</label><br>
                        <input type="text" id="cnum" name="cardNumber" required >
                    </div>
                    <div class="add-form-div">
                        <label for="amount">Purchase Amount:</label><br>
                        <input id="amount" type="number" step="0.01" name="amount" required ><br>
                    </div>
                    <div class="add-form-div multi-field-submit">
                        <input type="submit" value="Submit">
                    </div>
                </div>
            </form>
        </div>

        <div>
            <c:if test="${addPurchaseSuccess}">
                <div class="success">Successfully added purchase on card ${savedPurchase.giftCard.cardNumber} for $${savedPurchase.amount}</div>
            </c:if>
            <c:if test="${exceptionThrown}">
                <div class="error-message">Error: ${errorMessage}</div>
            </c:if>
        </div>

        <br>

        <div class="table-div" style="width: 30%">
            <table id="table_id" class="styled-table" >
                <thead>
                <tr>
                    <th>Purchase Amount</th>
                    <th>Gift Card Number</th>
                    <th>Purchase Time</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${purchaseList}" var="purchase">
                <tr>
                    <td>${purchase.amount}</td>
                    <td>${purchase.giftCard.cardNumber}</td>
                    <td>${purchase.purchaseDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"))}</td>
                </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>

        <c:if test="${purchaseList.size() < 1}">
            <div style="color:red; font-weight:bold">There are no purchases to display.</div>
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