<%@ page language="java" %>
<html>
<head>
    <title>Add Request</title>
</head>
<body>

<h3>Add Maintenance Request</h3>

<form action="MainServlet" method="post">

    <input type="hidden" name="operation" value="newRecord">

    Requester Name:
    <input type="text" name="requesterName"><br><br>

    Issue Type:
    <input type="text" name="issueType"><br><br>

    Request Date:
    <input type="date" name="requestDate"><br><br>

    Priority:
    <input type="text" name="priority"><br><br>

    Remarks:
    <input type="text" name="remarks"><br><br>

    <input type="submit" value="Add Request">

</form>

</body>
</html>
