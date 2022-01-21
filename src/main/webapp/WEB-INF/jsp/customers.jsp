<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
    <head>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/tables.css" >
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/addForm.css" >
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css" >
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.11.3/css/jquery.dataTables.css">
        <title>View Customers</title>
    </head>
    <body>
    <%@ include file = "header.jsp" %>

        <div class="container">
            <div class="add">
                <br>
                <p>Add a new customer:</p>

                <form:form action="${pageContext.request.contextPath}/customer" method="post" modelAttribute="customer">
                    <div class="add-form-div-parent">
                        <div class="add-form-div">
                            <form:label path="firstName">First Name (optional):</form:label> <br>
                            <form:input type="text" path="firstName"/> <br>
                        </div>
                        <div class="add-form-div">
                            <form:label path="lastName">Last Name (optional):</form:label> <br>
                            <form:input type="text" path="lastName"/> <br>
                        </div>
                        <div class="add-form-div">
                            <form:label path="phoneNumber">10-digit Phone:</form:label> <br>
                            <form:input type="text" path="phoneNumber" required="required" /> <br>
                            <form:errors path="phoneNumber" cssClass="error" />
                        </div>
                        <div class="add-form-div multi-field-submit">
                            <input type="submit" value="Submit">
                        </div>
                    </div>
                </form:form>
            </div>

            <div>
                <c:if test="${addCustomerSuccess}">
                    <div class="success">Successfully added Customer with phone number ${savedCustomer.phoneNumber}.</div>
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
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Phone Number</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="customer" items="${customerList}" >
                        <tr>
                            <td>${customer.firstName}</td>
                            <td>${customer.lastName}</td>
                            <td>${customer.phoneNumber}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>

            <c:if test="${customerList.size() < 1}">
            <div style="color:red; font-weight:bold">There are customers to display.</div>
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
