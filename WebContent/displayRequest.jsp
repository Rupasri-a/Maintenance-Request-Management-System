<%@ page language="java" %>
<%@ page import="com.wipro.maintenance.bean.MaintenanceBean" %>

<html>
<head>
    <title>Display Request</title>
</head>
<body>

<%
MaintenanceBean bean = (MaintenanceBean)request.getAttribute("bean");

if(bean == null) {
%>
    <h3>No matching records exists! Please try again!</h3>
<%
} else {
%>
    <h3>Maintenance Request Details</h3>

    Request ID: <%= bean.getRequestId() %><br>
    Requester Name: <%= bean.getRequesterName() %><br>
    Issue Type: <%= bean.getIssueType() %><br>
    Request Date: <%= bean.getRequestDate() %><br>
    Priority: <%= bean.getPriority() %><br>
    Remarks: <%= bean.getRemarks() %><br>
<%
}
%>

<a href="menu.html">Go to Menu</a>

</body>
</html>
