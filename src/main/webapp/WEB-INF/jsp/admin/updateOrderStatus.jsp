<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>UpdateOrder</title>
</head>
<body>
<h2>Please fill form</h2><br>
<form action="/orders/update" method="post">
    <input type="text" value="Id" readonly="readonly"><input type="text" name="id"><br>
    <input type="text" value="Status" readonly="readonly"><select name="status" form="order">
    <option value="New">New</option>
    <option value="In progress">In progress</option>
    <option value="Done">Done</option>
    <option value="Canceled">Canceled</option>

</select>
    <br>
    <input type="submit" value="Update orders">
</form>
<br>
<iframe src="/orders"></iframe>
</body>
</html>