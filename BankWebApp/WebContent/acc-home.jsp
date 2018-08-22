<%@page import="com.zycus.bankWebApp.CustomerDAO"%>
<%@page import="com.zycus.bankWebApp.Customer"%>
<%@page import="com.zycus.bankWebApp.Account"%>
<%@page import="com.zycus.bankWebApp.AccountDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
<link rel="stylesheet" type="text/css" href="StylingCss/default.css">
</head>
<body>
	<div id="main">
	<%
			Integer customerId = (Integer) session.getAttribute("cust_id");
			if(customerId == null) {
				response.sendRedirect("index.html");
			}
			int accNumber = AccountDAO.getIdFromCustID(customerId);
			session.setAttribute("AccNo", accNumber);
			
	
			CustomerDAO customerdao = new CustomerDAO();
			Customer customer = new Customer();
			customer = customerdao.findById(customerId);
			AccountDAO accountdao = new AccountDAO();
			Account account = new Account();
			account  = accountdao.findById(accNumber);
			
			if(account != null && customer != null) {
			
	%>
		<h2>Welcome, <%= customer.getFirstname()+" "+customer.getLastname()%></h2><hr /><br/>
		<form action="logout.do" method="get">
    		<input type="submit" value="Logout" />
		</form>
		<br/>
		<table>
		<tr>
			<th>Account Type : <%= account.getAccountType() %> </th>
			<th>Balance : <%= account.getBalance() %></th>
		</tr>
		
		</table>
		<br/>
		<a href="facility.jsp">Add Facility</a><br/><br />
		<a href="facilityview.jsp">View your Facility Preference</a><br/><br />
		<a href="display-fac-pay.jsp">Display previous facility payment history</a><br/><br />
		
	<%
			}else {
				out.println("Something went wrong, try again....");
			}
	%>
		
		
	</div>
</body>
</html>