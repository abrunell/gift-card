<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="/css/aboutUs.css">
    <title>About Austin's eGift Card</title>
</head>
<body>
    <%@ include file = "header.jsp" %>

    <div class="about-section">
        <h1>About Austin's eGift Card</h1>
        <p>Thanks for checking out my web app portfolio project!</p>
        <p>Building this app improved my skills in the following technologies:</p>
        <pre class="techs">Spring Boot 2  |  Maven  |  Hibernate  |  Hibernate Validator  |  PostgresSQL  |  Java  |  JSP  |  HTML  |  CSS  |  JUnit  |  Mockito  |  IntelliJ IDEA.</pre>
    </div>

    <div class="card">
        <img src="/images/austin.jpg" alt="Austin" style="width:100%">
        <div class="container">
            <h2>Austin Brunell</h2>
            <p class="title">Software Developer</p>
            <a href="url">My Resume</a>
            <p>austin.brunell@gmail.com</p>
            <a href="mailto:austin.brunell@gmail.com"><button class="button">Contact</button></a>
        </div>
    </div>
</body>
</html>
