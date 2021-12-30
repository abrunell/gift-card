<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <link rel="stylesheet" type="text/css" href="/css/tables.css" >
        <title>View Customers</title>
    </head>
    <body>
    <%@ include file = "header.jsp" %>

    <div class="table-background">
        <table class="styled-table">
            <thead>
            <tr>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Phone Number</th>
            </tr>
            </thead>

            <tbody>
            <c:forEach var="customer" items="${customers}" >
                <tr>
                    <td>${customer.firstName}</td>
                    <td>${customer.lastName}</td>
                    <td>${customer.phoneNumber}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <c:if test="${customers.size() < 1}">
            <div style="color:red; font-weight:bold">There are customers to display.</div>
        </c:if>
    </div>
    </body>
</html>
