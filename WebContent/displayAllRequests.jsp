<%@ page language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.wipro.maintenance.bean.MaintenanceBean" %>

<html>
<head>
    <title>All Requests</title>
</head>
<body>

<%
List<MaintenanceBean> list = 
    (List<MaintenanceBean>)request.getAttribute("list");

if(list == null || list.size() == 0) {
%>
    <h3>No records available!</h3>
<%
} else {
%>
    <h3>All Maintenance Requests</h3>

    <table border="1">
        <tr>
            <th>Request ID</th>
            <th>Name</th>
            <th>Issue</th>
            <th>Date</th>
            <th>Priority</th>
            <th>Remarks</th>
        </tr>

<%
    for(MaintenanceBean b : list) {
%>
        <tr>
            <td><%= b.getRequestId() %></td>
            <td><%= b.getRequesterName() %></td>
            <td><%= b.getIssueType() %></td>
            <td><%= b.getRequestDate() %></td>
            <td><%= b.getPriority() %></td>
            <td><%= b.getRemarks() %></td>
        </tr>
<%
    }
}
%>

</table>

<br>
<a href="menu.html">Go to Menu</a>

</body>
</html>
