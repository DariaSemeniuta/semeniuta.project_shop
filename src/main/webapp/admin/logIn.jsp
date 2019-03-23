<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <meta charset="UTF-8">
    <title>Log in</title>
</head>
<body>
<h2>
    Please enter your login and password
</h2>
<form action="/adminLogIn" method="post">
    <input type="text" value="User" readonly="readonly"><input type="text" name="user"><br>
    <input type="text" value="Password" readonly="readonly"><input type="password" name="password"><br>
    <br>
    <input type="submit" value="Log In">

</form>
<p>${message}</p>
</body>
</html>