<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/aboutUs.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css" >
    <title>About Austin's eGift Card</title>
</head>
<body>
    <%@ include file = "header.jsp" %>

    <div class="about-section">
        <h1>About Austin's eGift Cards</h1>
        <p>Thanks for checking out my web app portfolio project!</p>
        <p>Technologies used:</p>
        <pre class="techs">Spring Boot 2  |  Java  |  Hibernate  |  Hibernate Validator  |  PostgresSQL  |  Maven  |  JSP  |  HTML  |  CSS  |  JUnit  |  Mockito  |  IntelliJ IDEA </pre>
    </div>

    <div class="container">
        <div class="card">
            <img src="${pageContext.request.contextPath}/images/austin.jpg" alt="Austin" style="width:100%">
            <div class="card-text">
                <h2>Austin Brunell</h2>
                <p class="title">Software Developer</p>
                <a href="${pageContext.request.contextPath}/resume/Austin Brunell Resume.pdf">Austin's Resume</a>
                <br><br>
                <a href="mailto:austin.brunell@gmail.com">austin.brunell@gmail.com</a>
                <br><br>
                <a href="mailto:austin.brunell@gmail.com"><button class="button">Contact</button></a>
            </div>
        </div>
    </div>
</body>
</html>
