<%@ page language="java" %>
<html>
<head>
    <title>View All Requests</title>
</head>
<body>

<h3>View All Maintenance Requests</h3>

<form action="MainServlet" method="post">

    <input type="hidden" name="operation" value="viewAllRecords">

    <input type="submit" value="View All">

</form>

</body>
</html>
