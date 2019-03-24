<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <meta charset="UTF-8">
    <title>CreateProduct</title>
</head>
<body>
<h2>Create Order</h2><br>

<form action="/orders" method="post">
    <input type="text" value="Client_ID " readonly="readonly"><input type="text" name="clientId"><br>
    <input type="text" value="Product_IDs (put ids separated by <,>)" readonly="readonly"><input type="text" name="productIds">
    <br>
    <br><input type="submit" value="Create">
    <br><br>
    <div>
        <iframe src="/clients">
        </iframe>
        <iframe src="/products">
        </iframe>
    </div>
</form>
<br>
<iframe src="/orders"></iframe>
</body>
</html>