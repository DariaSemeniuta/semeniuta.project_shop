<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <meta charset="UTF-8">
    <title>DeleteOrder</title>
</head>
<body>
<form action="/orders/delete" method="post">
    Id: <input type="text" name="id">
    <input type="submit" value="Delete">
</form>
<br><br>
<iframe src="/orders">All orders</iframe>

</body>
</html>