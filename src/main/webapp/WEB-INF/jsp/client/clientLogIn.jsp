<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <meta charset="UTF-8">
    <title>ClienLogIn</title>
</head>
<h2>
    Please enter your login and password
</h2>
<form action="/clientLogIn" method="post">
    <input type="text" value="User email" readonly="readonly"><input type="text" name="email"><br>
    <input type="text" value="User phone" readonly="readonly"><input type="password" name="phone"><br>
    <br>
    <input type="submit" value="Log In">

</form>
<body>

</body>
</html>