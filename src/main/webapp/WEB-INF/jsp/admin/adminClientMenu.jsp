<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>ClientMenu</title>
</head>

<body>

<h2>Admin menu for work with client</h2>
<form action="/menu" method="get">
    <input type="submit" name="menu" value="Create client">
    <br><br>
    <input type="submit" name="menu" value="Delete client">
    <br><br>
    <input type="submit" name="menu" value="Update client">
</form>
<br>
<form action="/clients" method="get">
    <input type="submit" value="Show all clients">
</form>
<br>

</body>
</html>