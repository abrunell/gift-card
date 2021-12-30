<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="/css/addform.css">
    <title>Add Customer</title>
</head>

<body>
<%@ include file = "header.jsp" %>

<div class="container">
    <c:if test="${addCustomerSuccess}">
        <div class="success">Successfully added customer with phone number: ${customer.phoneNumber}</div>
    </c:if>
    <c:if test="${exceptionThrown}">
        <div class="error-message">Error: ${errorMessage}</div>
    </c:if>

    <form:form action="${add_customer_url}" method="post" modelAttribute="customer">

        <div class="mainForm">
            <form:label path="firstName">First Name:</form:label> <br>
            <form:input type="text" path="firstName"/> <br>

            <form:label path="lastName">Last Name:</form:label> <br>
            <form:input type="text" path="lastName"/> <br>

            <form:label path="phoneNumber">10-digit Phone:</form:label> <br>
            <form:errors path="phoneNumber" cssClass="error" />
            <form:input type="text" path="phoneNumber"/> <br>
        </div> <br>

        <div class="row">
            <input type="submit" value="Submit">
        </div>
    </form:form>
</div>
</body>
</html>
