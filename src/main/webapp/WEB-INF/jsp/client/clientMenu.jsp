<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<h2>Client menu</h2>
<form action="/clientMenu" method="get">
    <input type="submit" name ="menu" value="Edit your profile"/>
    <br><br>
    <input type="submit" name ="menu" value="Create orders"/>
</form>
<br>
<form action="/clients" method="get">
    <input type="submit"  value="Show user profile"/>
</form>
<br>
<form action="/orders" method="get">
    <input type="submit" value="Show all orders"/>
</form>
</body>
</html>
