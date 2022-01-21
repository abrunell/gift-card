<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/header.css">
        <title>Austin's eGift Cards</title>
    </head>
    <body>
        <div class="topnav">
            <div class="links">
                <a href="${pageContext.request.contextPath}/">Home</a>
                <a href="${pageContext.request.contextPath}/giftCard">Gift Cards</a>
                <a href="${pageContext.request.contextPath}/purchases">Purchases</a>
                <a href="${pageContext.request.contextPath}/customer">Customers</a>
                <a href="${pageContext.request.contextPath}/about">About</a>
            </div>
        </div>

        <%--Script to highlight the color of the active tab. --%>
        <script src="http://code.jquery.com/jquery-2.1.4.min.js"></script>
        <script>
            $(function(){
                $('a').each(function(){
                    if ($(this).prop('href') == window.location.href) {
                        $(this).addClass('active'); $(this).parents('li').addClass('active');
                    }
                });
            });
        </script>
    </body>
</html>



