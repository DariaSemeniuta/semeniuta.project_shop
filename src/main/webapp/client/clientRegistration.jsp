<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <meta charset="UTF-8">
    <title>Registration</title>
</head>
<body>
<h2>Please fill form</h2><br>
<form action="/clients/registration" method="post" border="2">
    <input type="text" value="Name" readonly="readonly"><input type="text" name="name"><br>
    <input type="text" value="Surname" readonly="readonly"><input type="text" name="surname"><br>
    <input type="text" value="Age" readonly="readonly"><input type="text" name="age"><br>
    <input type="text" value="Phone" readonly="readonly"><input type="text" name="phone"><br>
    <input type="text" value="Email" readonly="readonly"><input type="text" name="email"><br>
    <br>
    <input type="submit" value="Create user">
</form>
</body>
</html>