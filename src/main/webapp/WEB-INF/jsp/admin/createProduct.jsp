<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>CreateProduct</title>
</head>
<body>
<h2>Please fill form</h2><br>
<form action="/products" method="post">
    <input type="text" value="Name" readonly="readonly"><input type="text" name="name"><br>
    <input type="text" value="Price" readonly="readonly"><input type="text" name="price"><br>
    <br>
    <input type="submit" value="Create product">
</form>
</body>
</html>