<%@page import="com.zycus.bankapp.service.impl.*"%>
<%@page import="com.zycus.bankapp.bo.*"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Facility Payment History</title>
<link rel="stylesheet" type="text/css" href="/BankWebApp/StylingCss/default.css">
</head>
<body>
	<div id="main">
	<h1>Facility Payment History</h1> <hr/>
		<table>
		<%
		Integer customerId = (Integer) session.getAttribute("cust_id");
		if(customerId == null) {
			request.getRequestDispatcher("/BankWebApp/index.html").forward(request, response);
		}

		Integer accNo = (Integer)session.getAttribute("AccNo");
		FacilityService facilityService = new FacilityService();
		PaymentService payService = new PaymentService();
		
		List<Payment> paylist = null;
	    List<Facility> faclist = FacilityService.getFacilityFromAccountNo(accNo.intValue());
	    out.println("<table>");
	    out.println("<tr><td>Payment ID</td><td>Facility ID</td><td>Amount</td><td>Date</td></tr>");
		if(faclist != null) {
			for(Facility fac: faclist){
				System.out.println(fac.getId());
				paylist = PaymentService.getDataFromFacilityId(fac.getId());
				if(paylist != null){
					for(Payment pay : paylist){
						out.println("<tr><td>"+pay.getId()+"</td><td>"+pay.getFacid()+"</td>");
						out.println("<td>"+pay.getAmount()+"</td><td>"+pay.getTime()+"</td>");
						out.println("</tr>");
					}
				}
			}
		}else {
			out.println("No facility payment history avaialbe...");
			System.out.println("Something went wrong  !!");
		}
		out.println("</table>");
		
		%>
		</table>
	</div>
</body>
</html>