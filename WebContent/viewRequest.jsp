<%@ page language="java" %>
<html>
<head>
    <title>View Request</title>
</head>
<body>

<h3>View Maintenance Request</h3>

<form action="MainServlet" method="post">

    <input type="hidden" name="operation" value="viewRecord">

    Requester Name:
    <input type="text" name="requesterName"><br><br>

    Request Date:
    <input type="date" name="requestDate"><br><br>

    <input type="submit" value="View Request">

</form>

</body>
</html>
