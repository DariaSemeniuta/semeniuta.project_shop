<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <meta charset="UTF-8">
    <title>DeleteClient</title>
</head>
<body>
<form action="/clients/delete" method="post">
    Id: <input type="text" name="id">
    <input type="submit" value="Delete">
</form>
<br><br>
<iframe src="/clients">All clients</iframe>

</body>
</html>