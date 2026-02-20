package com.wipro.maintenance.servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.wipro.maintenance.dao.MaintenanceDAO;
import java.util.List;


import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


import com.wipro.maintenance.bean.MaintenanceBean;
import com.wipro.maintenance.service.Administrator;

@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {


    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	 protected void doPost(HttpServletRequest request,
             HttpServletResponse response)
throws ServletException, IOException {

String operation = request.getParameter("operation");

if (operation.equals("newRecord")) {

try {
   MaintenanceBean bean = new MaintenanceBean();

   bean.setRequesterName(request.getParameter("requesterName"));
   bean.setIssueType(request.getParameter("issueType"));

   String dateStr = request.getParameter("requestDate");
   Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
   bean.setRequestDate(date);

   bean.setPriority(request.getParameter("priority"));
   bean.setRemarks(request.getParameter("remarks"));

   Administrator admin = new Administrator();
   String result = admin.addRecord(bean);

   if (result.equals("FAIL") || result.equals("INVALID INPUT")) {
       response.sendRedirect("error.html");
   } else {
       response.sendRedirect("success.html");
   }

} catch (Exception e) {
   e.printStackTrace();   // ⭐ ADD THIS (debugging)
   response.sendRedirect("error.html");
}
}


else if (operation.equals("viewRecord")) {

try {
   String name = request.getParameter("requesterName");

   String dateStr = request.getParameter("requestDate");
   Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);

   MaintenanceDAO dao = new MaintenanceDAO();
   MaintenanceBean bean = dao.getRecord(name, date);

   request.setAttribute("bean", bean);

   RequestDispatcher rd =
           request.getRequestDispatcher("displayRequest.jsp");
   rd.forward(request, response);

} catch (Exception e) {
   e.printStackTrace();
   response.sendRedirect("error.html");
}
}


else if (operation.equals("viewAllRecords")) {

try {
   MaintenanceDAO dao = new MaintenanceDAO();

   List<MaintenanceBean> list = dao.getAllRecords();

   request.setAttribute("list", list);

   RequestDispatcher rd =
		   request.getRequestDispatcher("displayAllRequests.jsp");
   rd.forward(request, response);

} catch (Exception e) {
   e.printStackTrace();
   response.sendRedirect("error.html");
}
}
}
}
