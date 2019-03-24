
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>AdminProductMenu</title>
</head>
<body>
<h2>Admin product menu</h2>
<form action="/products" method="get">
    <input type="submit" value="Show all products">
</form>
<br>
<form action="/menu" method="get">
    <input type="submit" name="menu" value="Create product">
    <br>
    <input type="submit" name="menu" value="Delete product">
    <br>
    <input type="submit" name="menu" value="Update product">
</form>
</body>
</html>