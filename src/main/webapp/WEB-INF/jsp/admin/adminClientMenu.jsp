<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>ClientMenu</title>
</head>

<body>

<h2>Admin menu for work with client</h2>
<form action="/client/clientRegistration">
    <input type="submit" value="Create client">
</form>
<br>
<form action="/admin/deleteClient.html">
    <input type="submit" value="Delete client">
</form>
<br>
<form action="/admin/updateClient.html">
    <input type="submit" value="Update">
</form>
<br>
<form action="/clients" method="get">
    <input type="submit" value="Show all clients">
</form>
<br>

</body>
</html>