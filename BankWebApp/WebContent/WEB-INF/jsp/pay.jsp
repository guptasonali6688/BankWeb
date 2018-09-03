<%@page import="com.zycus.bankapp.dao.impl.*"%>
<%@page import="com.zycus.bankapp.bo.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Payment Details</title>
<link rel="stylesheet" type="text/css" href="../StylingCss/default.css">
</head>
<body>
	<%
		Integer customerId = (Integer) session.getAttribute("cust_id");
		if(customerId == null) {
			response.sendRedirect("/BankWebApp/index.html");
		}
	%>
	<div id="main">
	<h2>Payment Details</h2><hr /><br />
		<form action="/BankWebApp/payment.do" method="post">
			Facility-Id: <input type="text" name="facId" value="<%= request.getParameter("id") %>" readonly /><br/><br/>
			<%
				FacilityDAO facdao = new FacilityDAO();
				Facility fac = new Facility();
				fac = facdao.findById(Integer.parseInt(request.getParameter("id")));
			%>
			Bill Type : <input type="text" name="billType" value="<%= fac.getBillType() %>" readonly /><br/><br/>
			Provider : <input type="text" name="provider" value="<%= fac.getProvider() %>" readonly /><br/><br/>
			Consumer Number : <input type="text" name="consumerId" value="<%= fac.getConsumerNo() %>" readonly /><br/><br/>
			Amount : <input type="text" name="amount"/ ><br/> <br/>
			<input type="submit" value="Pay" name="submit" />
		</form>
	</div>
</body>
</html>