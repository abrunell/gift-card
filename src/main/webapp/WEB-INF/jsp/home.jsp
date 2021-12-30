<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="/css/home.css">
        <title>Welcome</title>
    </head>

    <body>
        <%@ include file = "header.jsp" %>

        <div class="main">
            <p>Austin's eGift Cards</p>
            <form action="/purchases/addPurchase">
                <input type="submit" value="Record a Purchase" />
            </form> <br> <br>
            <form action="/purchases/viewPurchases">
                <input type="submit" value="View Purchases" />
            </form> <br> <br>
            <form action="/giftCard/addGiftCard">
                <input type="submit" value="Add Gift Card" />
            </form> <br> <br>
            <form action="/giftCard/viewGiftCards">
                <input type="submit" value="View Gift Cards" />
            </form> <br> <br>
            <form action="/customer/addCustomer">
                <input type="submit" value="Add a New Customer" />
            </form> <br> <br>
            <form action="/customer/viewCustomers">
                <input type="submit" value="View Customers" />
            </form> <br> <br>
            <form action="/registration/registerCard">
                <input type="submit" value="Register Gift Card" />
            </form>
        </div>
    </body>
</html>
