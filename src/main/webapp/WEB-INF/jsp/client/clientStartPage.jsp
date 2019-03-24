<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<head>
    <meta charset="UTF-8">
    <title>Hi Client!</title>
</head>
<body>
<h2>Please Log in or register for continue</h2>
<form action="/clientMenu" method="get">
    <input type="submit" name ="menu"  value="Registration">
</form>
<br>
<form action="/clientMenu" method="get">
    <input type="submit"  name ="menu"  value="Log in">
</form>


</body>
</html>