<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>DeleteProduct</title>
</head>
<body>
<form action="/products/delete" method="post">
    Id: <input type="text" name="id">
    <input type="submit" value="Delete">
</form>
<br><br>
<iframe src="/products">All products</iframe>

</body>
</html>