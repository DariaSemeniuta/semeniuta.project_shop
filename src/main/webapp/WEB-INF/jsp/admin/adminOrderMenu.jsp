<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>AdminOrderMenu</title>
</head>
<body>
<h2>Admin order menu</h2>
<form action="/orders" method="get">
    <input type="submit" value="Show all orders">
</form>
<br>
<form action="/menu" method="get">
    <input type="submit" name="menu" value="Create order">
    <br><br>
    <input type="submit" name="menu" value="Delete order">
    <br><br>
    <input type="submit" name="menu" value="Update order">
</form>
</body>
</html>