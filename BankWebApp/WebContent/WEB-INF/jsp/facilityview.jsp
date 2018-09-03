<%@page import="com.zycus.bankapp.service.impl.FacilityService"%>
<%@page import="com.zycus.bankapp.bo.*"%>
<%@page import="com.zycus.bankapp.dao.impl.*"%>
<%@page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Facility Prefrence</title>
<link rel="stylesheet" type="text/css" href="../StylingCss/default.css">
</head>
<body>
	<%
		Integer customerId = (Integer) session.getAttribute("cust_id");
		if(customerId == null) {
			request.getRequestDispatcher("/BankWebApp/index.html").forward(request, response);
		}
	%>
	<div id="main">
	<h2>List of Facility prefrences</h2><hr/>
		<%
			int accNo = (Integer)session.getAttribute("AccNo");
			List<Facility> faclist = FacilityService.getFacilityFromAccountNo(accNo);
			out.println("<table>");
		    out.println("<tr><td>Facility ID</td><td>Bill Type</td><td>Provider</td><td>Consumer ID</td><td></td></tr>");
			if(faclist != null) {
				for(Facility fac: faclist){
					
					out.println("<tr><td>"+fac.getId()+"</td><td>"+fac.getBillType()+"</td>");
					out.println("<td>"+fac.getProvider()+"</td><td>"+fac.getConsumerNo()+"</td><td><a href='/BankWebApp/jsp/pay.jsp?id="+fac.getId()+"'>Pay</a></td>");
					out.println("</tr>");
				} 
			}else {
				System.out.println("Something went wrong  !!");
			}
			
			out.println("</table>");	
		%>
	</div>
</body>
</html>